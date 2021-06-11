package org.vino9.poc.api;

import io.smallrye.mutiny.Uni;
import org.vino9.poc.model.AccountDetail;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/accounts/{id}")
@io.swagger.annotations.Api(description = "the accounts API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen")
public class AccountsApi {

  @Inject AccountsApiService service;

  @GET
  @Produces({"application/json"})
  @io.swagger.annotations.ApiOperation(
      value = "get account for account number",
      notes = "Get Account Details for one account",
      response = AccountDetail.class,
      responseContainer = "List",
      tags = {})
  @io.swagger.annotations.ApiResponses(
      value = {
        @io.swagger.annotations.ApiResponse(
            code = 200,
            message = "search results matching criteria",
            response = AccountDetail.class,
            responseContainer = "List"),
        @io.swagger.annotations.ApiResponse(
            code = 400,
            message = "bad input parameter",
            response = Void.class),
        @io.swagger.annotations.ApiResponse(
            code = 401,
            message = "access denied",
            response = Void.class)
      })
  public Uni<Response> getAccountDetail(
      @PathParam("id") String id, @Context SecurityContext securityContext)
      throws NotFoundException {
    return service.getAccountDetail(id, securityContext);
  }
}
