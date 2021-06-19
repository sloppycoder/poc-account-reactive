package org.vino9.poc.api.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.jboss.logging.Logger;
import org.vino9.poc.api.AccountsApiService;
import org.vino9.poc.data.AccountDetailRepository;

@RequestScoped
public class AccountsApiServiceImpl implements AccountsApiService {

    @Inject
    Logger log;
    @Inject
    AccountDetailRepository repository;

    public Response getAccountDetail(String accountNo, SecurityContext securityContext) {
        log.infof("retrieving account detail for %s from database", accountNo);
        var account = repository.findByAccountNo(accountNo);
        if (account == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(account).build();
    }
}
