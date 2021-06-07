package org.vino9.poc.data;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import org.jboss.logging.Logger;
import org.vino9.poc.mapper.RowToModelMapper;
import org.vino9.poc.model.AccountDetail;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AccountDetailRepository {
  private static final String SQL_FIND_ACCOUNT_BY_ID =
      "SELECT account_no, currency, country, branch_code FROM accounts WHERE account_no = $1";

  @Inject Logger log;

  @Inject PgPool client;

  public Uni<AccountDetail> findByAccountNo(String accountNo) {
    return client
        .preparedQuery(SQL_FIND_ACCOUNT_BY_ID)
        .execute(Tuple.of(accountNo))
        .onItem()
        .transform(RowSet::iterator)
        .onItem()
        .transform(
            iterator ->
                iterator.hasNext() ? RowToModelMapper.rowToAccountDetail(iterator.next()) : null);
  }
}
