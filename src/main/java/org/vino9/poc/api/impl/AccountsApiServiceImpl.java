package org.vino9.poc.api.impl;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import org.jboss.logging.Logger;
import org.vino9.poc.api.AccountsApiService;
import org.vino9.poc.api.NotFoundException;
import org.vino9.poc.model.AccountDetail;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@RequestScoped
public class AccountsApiServiceImpl implements AccountsApiService {
    private static final String SQL_FIND_ACCOUNT_BY_ID =
            "SELECT account_no, currency, country, branch_code FROM accounts WHERE account_no = $1";

    @Inject Logger log;

    @Inject PgPool client;

    public static Uni<AccountDetail> findByAccountNo(PgPool client, String accountNo) {
        return client
                       .preparedQuery(SQL_FIND_ACCOUNT_BY_ID)
                       .execute(Tuple.of(accountNo))
                       .onItem()
                       .transform(RowSet::iterator)
                       .onItem()
                       .transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }

    private static AccountDetail from(Row row) {
        var account = new AccountDetail();
        account.setAccountNo(row.getString(0));
        account.setCurrency(row.getString(1));
        account.setCountry(row.getString(2));
        account.setBranchCode(row.getString(3));
        return account;
    }

    public Uni<Response> getAccountDetail(
            String accountNo, SecurityContext securityContext)
            throws NotFoundException {
        log.infof("retriving account detail for %s", accountNo);
        return findByAccountNo(client, accountNo)
                       .onItem()
                       .transform(
                               acc ->
                                       acc != null
                                               ? Response.ok().entity(acc)
                                               : Response.status(Response.Status.NOT_FOUND))
                       .onItem()
                       .transform(Response.ResponseBuilder::build);
    }
}
