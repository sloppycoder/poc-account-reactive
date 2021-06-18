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
        "SELECT account_no, currency, country, branch_code, pg_sleep(delay) FROM accounts WHERE account_no = $1";

    private static final String SQL_RANDOM_ACCOUNT =
            "SELECT $1, currency, country, branch_code, pg_sleep(delay) "
            + "FROM accounts OFFSET floor(random() * (SELECT COUNT(*)  FROM accounts)) LIMIT 1";

    @Inject
    Logger log;

    @Inject
    PgPool client;

    public Uni<AccountDetail> findByAccountNo(String accountNo) {
        var sql =  "random".equalsIgnoreCase(accountNo)
            ? SQL_RANDOM_ACCOUNT
            : SQL_FIND_ACCOUNT_BY_ID;
        return client
            .preparedQuery(sql)
            .execute(Tuple.of(accountNo))
            .onItem().transform(RowSet::iterator)
            .onItem().transform(
                iterator -> iterator.hasNext()
                    ? RowToModelMapper.rowToAccountDetail(iterator.next())
                    : null);
    }
}
