package org.vino9.poc.api.impl;

import io.smallrye.mutiny.Uni;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.jboss.logging.Logger;
import org.vino9.poc.api.AccountsApiService;
import org.vino9.poc.data.AccountDetailRepository;

@RequestScoped
public class AccountsApiServiceImpl implements AccountsApiService {
  @Inject Logger log;
  @Inject AccountDetailRepository repository;

  public Uni<Response> getAccountDetail(String accountNo, SecurityContext securityContext) {
    log.infof("retrieving account detail for %s from database", accountNo);
    return repository
        .findByAccountNo(accountNo)
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
