package org.vino9.poc.api;

import io.smallrye.mutiny.Uni;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.vino9.poc.model.AccountDetail;

@Path("/accounts")

public class AccountsApi {

    @Inject
    AccountsApiService service;

    @GET
    @Path("/{id}")

    @Produces({"application/json"})
    @Operation(summary = "get account for account number", description = "Get Account Details for one account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "search results matching criteria", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AccountDetail.class)))),

        @ApiResponse(responseCode = "400", description = "bad input parameter"),

        @ApiResponse(responseCode = "401", description = "access denied")})
    public Uni<Response> getAccountDetail(@PathParam("id") String id,
        @Context SecurityContext securityContext) {
        return service.getAccountDetail(id, securityContext);
    }
}
