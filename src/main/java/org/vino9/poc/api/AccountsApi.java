package org.vino9.poc.api;

import org.vino9.poc.model.*;
import org.vino9.poc.api.AccountsApiService;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import org.vino9.poc.model.AccountDetail;

import java.util.Map;
import java.util.List;
import org.vino9.poc.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.inject.Inject;

import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/accounts/{id}")


@io.swagger.annotations.Api(description = "the accounts API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen")
public class AccountsApi  {

    @Inject AccountsApiService service;

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "get account for account number", notes = "Get Account Details for one account", response = AccountDetail.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "search results matching criteria", response = AccountDetail.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "bad input parameter", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "access denied", response = Void.class) })
    public Response getAccountDetail( @PathParam("id") String id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getAccountDetail(id,securityContext);
    }
}
