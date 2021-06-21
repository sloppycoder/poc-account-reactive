package org.vino9.poc;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.vino9.poc.data.AccountDetailRepository;

@QuarkusTest
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
    void get_account_detail_is_cached() {
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
