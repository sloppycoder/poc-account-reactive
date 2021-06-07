package org.vino9.poc.api.impl;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;
import org.vino9.poc.api.AccountsApiService;
import org.vino9.poc.api.NotFoundException;
import org.vino9.poc.data.AccountDetailRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@RequestScoped
public class AccountsApiServiceImpl implements AccountsApiService {
  @Inject Logger log;
  @Inject AccountDetailRepository repository;

  public Uni<Response> getAccountDetail(String accountNo, SecurityContext securityContext)
      throws NotFoundException {
    log.infof("retriving account detail for %s", accountNo);
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
