package org.vino9.poc.api.impl;

import org.vino9.poc.api.*;
import org.vino9.poc.model.*;


import org.vino9.poc.model.AccountDetail;

import java.util.List;
import org.vino9.poc.api.NotFoundException;

import java.io.InputStream;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@RequestScoped
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen")
public class AccountsApiServiceImpl implements AccountsApiService {
      public Response getAccountDetail(String id,SecurityContext securityContext)
      throws NotFoundException {
      // do some magic!
      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  }
}
