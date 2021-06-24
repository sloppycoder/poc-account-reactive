package org.vino9.poc;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.vino9.poc.data.AccountDetailRepository;

@QuarkusTest
@TestMethodOrder(MethodName.class)
class AccountApisTest {

    @InjectSpy
    AccountDetailRepository repository;

    @Test
    void legit_account_can_be_returned() {
      // @formatter:off
      given()
        .when()
            .get("/accounts/11223344")
        .then()
            .statusCode(200)
            .body("currency", equalTo("SGD"));
      // @formatter:on
    }

    @Test
    void unknown_account_returns_not_found() {
      // @formatter:on
      given()
        .when()
            .get("/accounts/8005551212")
        .then()
            .statusCode(404);
      // @formatter:off
    }

    @Test
    // we want this test case to run before others
    // thus the a0_ name
    void a0_get_account_detail_is_cached() {
        // call the test case twice but check only 1 database call is made
        legit_account_can_be_returned();
        legit_account_can_be_returned();

        Mockito.verify(repository, Mockito.times(1))
            .doQuery("11223344");
    }

    @Test
    void random_account_can_be_returned() {
        // @formatter:off
        given()
            .when()
                .get("/accounts/random")
            .then()
                .statusCode(200)
                .body("currency", equalTo("SGD"));
        // @formatter:on
    }
}
