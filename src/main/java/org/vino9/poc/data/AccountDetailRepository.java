package org.vino9.poc.data;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.vertx.mutiny.pgclient.PgPool;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jboss.logging.Logger;
import org.vino9.poc.mapper.RowToModelMapper;
import org.vino9.poc.model.AccountDetail;

@ApplicationScoped
public class AccountDetailRepository {

    private static final String SQL_FIND_ACCOUNT_BY_ID =
        "SELECT account_no, currency, country, branch_code, pg_sleep(delay) FROM accounts WHERE ";

    @Inject
    Logger log;

    @Inject
    PgPool client;

    @Inject
    DataSource dataSource;

    @Inject
    MeterRegistry registry;

    SecureRandom random = new SecureRandom();

    int totalAccounts = -1;

    @Timed(value = "app.db.query.duration", longTask = true, extraTags = {"query", "account_detail"})
    public AccountDetail findByAccountNo(String accountNo) {
        var count = getTotalAccounts();
        if (count <= 0) {
            return null;
        }

        log.infof("trying to retrieve account detail for %s", accountNo);
        var whereClause = "account_no = ?";
        var useRandom = "random".equalsIgnoreCase(accountNo);

        if (useRandom) {
            whereClause = " id = ?";
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ACCOUNT_BY_ID + whereClause);
            if (useRandom) {
                statement.setInt(1, random.nextInt(count));
            } else {
                statement.setString(1, accountNo);
            }

            resultset = statement.executeQuery();

            if (resultset.next()) {
                return RowToModelMapper.rowToAccountDetail(resultset);
            }
        } catch (SQLException ex) {
            log.infof(ex, "error trying to retrieve data from database");
        } finally {
            // Ugly JDBC API!!!
            if (connection != null) {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    connection.close();
                } catch (SQLException throwables) {
                    // nothing we can do here...
                }
            }
        }
        return null;
    }

    private int getTotalAccounts() {
        if (totalAccounts >= 0) {
            return totalAccounts;
        }

        try (var conn = dataSource.getConnection()) {
            var statement = conn.createStatement();
            var rs = statement.executeQuery("select count(*) from accounts");
            if (rs.next()) {
                totalAccounts = rs.getInt(1);
                log.infof("found %d records in accounts", totalAccounts);
            }
        } catch (SQLException e) {
            log.infof("Unable to retrieve total number of accounts, %s", e.getMessage());
            totalAccounts = 0;
        }

        return totalAccounts;
    }
}
