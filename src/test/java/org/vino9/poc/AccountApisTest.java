package org.vino9.poc;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AccountApisTest {

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
}
