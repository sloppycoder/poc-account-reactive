package org.vino9.poc.data;

import io.vertx.mutiny.pgclient.PgPool;
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
        "SELECT account_no, currency, country, branch_code, pg_sleep(delay) FROM accounts WHERE account_no = ?";

    private static final String SQL_RANDOM_ACCOUNT =
        "SELECT ?, currency, country, branch_code, pg_sleep(delay) "
            + "FROM accounts OFFSET floor(random() * (SELECT COUNT(*)  FROM accounts)) LIMIT 1";

    @Inject
    Logger log;

    @Inject
    PgPool client;

    @Inject
    DataSource dataSource;

    public AccountDetail findByAccountNo(String accountNo) {
        log.infof("3. trying to retrieve account detail for %s", accountNo);
        var sql = "random".equalsIgnoreCase(accountNo)
            ? SQL_RANDOM_ACCOUNT
            : SQL_FIND_ACCOUNT_BY_ID;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, accountNo);
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
}
