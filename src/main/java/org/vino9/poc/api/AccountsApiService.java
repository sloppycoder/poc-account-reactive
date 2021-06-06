package org.vino9.poc.api;

import org.vino9.poc.api.*;
import org.vino9.poc.model.*;


import org.vino9.poc.model.AccountDetail;

import java.util.List;
import org.vino9.poc.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen")
public interface AccountsApiService {
      Response getAccountDetail(String id,SecurityContext securityContext)
      throws NotFoundException;
}
