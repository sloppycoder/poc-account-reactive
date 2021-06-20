package org.vino9.poc.data;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import java.security.SecureRandom;
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

    SecureRandom random = new SecureRandom();
    int totalAccounts = -1;

    public Uni<AccountDetail> findByAccountNo(String accountNo) {
        if ("random".equalsIgnoreCase(accountNo)) {
            return getTotalAccounts()
                .onItem().transformToUni(n -> {
                    return queryAccount("id = $1", Tuple.of(random.nextInt(n)));
                });
        }
        return queryAccount("account_no = $1", Tuple.of(accountNo));
    }

    private Uni<AccountDetail> queryAccount(String whereClause, Tuple binding) {
        return client
            .preparedQuery(SQL_FIND_ACCOUNT_BY_ID + whereClause)
            .execute(binding)
            .onItem().transform(RowSet::iterator)
            .onItem().transform(
                iterator -> iterator.hasNext()
                    ? RowToModelMapper.rowToAccountDetail(iterator.next())
                    : null);
    }


    private Uni<Integer> getTotalAccounts() {
        if (totalAccounts != -1) {
            return Uni.createFrom().item(totalAccounts);
        }
        return client.query("select count(*) from accounts ").execute()
            .onItem().transform(RowSet::iterator)
            .onItem().transform(
                iterator -> iterator.hasNext()
                    ? iterator.next().getInteger(0)
                    : -1);
    }
}
