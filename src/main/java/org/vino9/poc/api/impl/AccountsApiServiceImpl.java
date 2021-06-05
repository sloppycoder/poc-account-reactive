package org.vino9.poc.api.impl;

import org.jboss.logging.Logger;
import org.vino9.poc.api.AccountsApiService;
import org.vino9.poc.api.ApiResponseMessage;
import org.vino9.poc.api.NotFoundException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@RequestScoped
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.JavaResteasyServerCodegen",
    date = "2021-06-05T14:09:03.284Z[GMT]")
public class AccountsApiServiceImpl implements AccountsApiService {
  @Inject
  Logger log;

  public Response getAccounts(String id, String balanceDate, SecurityContext securityContext)
      throws NotFoundException {
    log.infof("retriving account detail for %s", id);
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  }
}
