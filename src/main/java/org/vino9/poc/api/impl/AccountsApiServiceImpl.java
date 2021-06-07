package org.vino9.poc.api.impl;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
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

  @ConfigProperty(name="quarkus.datasource.reactive.url")
  String url; // to show whether reading config from k8s config map works or not

  @ConfigProperty(name="quarkus.datasource.username")
  String user;

  public Uni<Response> getAccountDetail(String accountNo, SecurityContext securityContext)
      throws NotFoundException {
    log.infof("retrieving account detail for %s from database %s as user %s", accountNo, url, user);
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
