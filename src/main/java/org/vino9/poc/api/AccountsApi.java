package org.vino9.poc.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.vino9.poc.model.AccountDetail;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/accounts")
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.JavaResteasyServerCodegen",
    date = "2021-06-05T14:09:03.284Z[GMT]")
public class AccountsApi {

  @Inject AccountsApiService service;

  @GET
  @Path("/{id}")
  @Produces({"application/json"})
  @Operation(
      summary = "get account for account number",
      description = "Get Account Details for one account",
      tags = {})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "search results matching criteria",
            content =
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountDetail.class)))),
        @ApiResponse(responseCode = "400", description = "bad input parameter"),
        @ApiResponse(responseCode = "401", description = "access denied")
      })
  public Response getAccounts(
      @PathParam("id") String id,
      @QueryParam("balanceDate") String balanceDate,
      @Context SecurityContext securityContext)
      throws NotFoundException {
    return service.getAccounts(id, balanceDate, securityContext);
  }
}
