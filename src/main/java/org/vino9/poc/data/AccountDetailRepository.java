package org.vino9.poc.data;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    public Uni<AccountDetail> findByAccountNo(String accountNo) {
        var locator =
            "random".equalsIgnoreCase(accountNo) ? " id = random_index($1)" : " account_no = $1";
        return client
            .preparedQuery(SQL_FIND_ACCOUNT_BY_ID + locator)
            .execute(Tuple.of(accountNo))
            .onItem().invoke(rs -> log.infof("%s returned %d rows", locator, rs.rowCount()))
            .onItem().transform(RowSet::iterator)
            .onItem().transform(
                iterator -> iterator.hasNext()
                    ? RowToModelMapper.rowToAccountDetail(iterator.next())
                    : null);
    }
}
