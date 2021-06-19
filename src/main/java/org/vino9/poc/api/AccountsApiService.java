package org.vino9.poc.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public interface AccountsApiService {

    Response getAccountDetail(String id, SecurityContext securityContext);
}
