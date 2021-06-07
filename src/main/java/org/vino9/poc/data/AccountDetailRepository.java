package org.vino9.poc.data;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import org.eclipse.microprofile.config.inject.ConfigProperty;
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

  @ConfigProperty(name="quarkus.datasource.reactive.url")
  String url; // to show whether reading config from k8s config map works or not

  public Uni<AccountDetail> findByAccountNo(String accountNo) {
    log.infof("retrieving account %s from database %s", accountNo, url);
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
