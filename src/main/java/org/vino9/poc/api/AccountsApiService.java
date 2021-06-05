package org.vino9.poc.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.JavaResteasyServerCodegen",
    date = "2021-06-05T14:09:03.284Z[GMT]")
public interface AccountsApiService {
  Response getAccounts(String id, String balanceDate, SecurityContext securityContext)
      throws NotFoundException;
}
