package org.vino9.poc.api;

import io.smallrye.mutiny.Uni;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public interface AccountsApiService {

    Uni<Response> getAccountDetail(String id, SecurityContext securityContext);
}
