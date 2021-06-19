package org.vino9.poc.data;

import static com.mongodb.client.model.Filters.eq;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.smallrye.mutiny.Uni;
import java.security.SecureRandom;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.bson.conversions.Bson;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.vino9.poc.model.AccountDetail;

@ApplicationScoped
public class AccountDetailRepository {

    @Inject
    Logger log;

    @Inject
    ReactiveMongoClient client;

    @ConfigProperty(name = "app.database")
    String database;

    SecureRandom random = new SecureRandom();

    public Uni<AccountDetail> findByAccountNo(String accountNo) {
        Bson queryFilter;
        if ("random".equalsIgnoreCase(accountNo)) {
            //random.nextInt()
            queryFilter = eq("id", 10);
        } else {
            queryFilter = eq("account_num", accountNo);
        }

        // randomly pick 1 of the first 10 docs
        return client.getDatabase(database)
            .getCollection("accounts")
            .find(queryFilter)
            .map(doc -> {
                var account = new AccountDetail();
                account.setAccountNo(doc.getString("account_num"));
                account.setCurrency(doc.getString("currency"));
                account.setCountry(doc.getString("country"));
                account.setBranchCode(doc.getString("branch_code"));
                return account;
            })
            .collect().first();
    }
}
