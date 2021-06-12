package org.vino9.poc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
class AccountApisTest {

  @Test
  void swagger_ui_is_available() {
    given()
        .when()
            .get("/swagger-ui")
        .then()
            .statusCode(200)
            .body(containsString("Swagger"));
  }

  @Test
  void prometheus_metrics_is_available() {
    given()
        .when()
            .get("/q/metrics")
        .then()
            .statusCode(200)
            .body(containsString("jvm"));
  }

  @Test
  void legit_account_can_be_returned() {
    given()
        .when()
            .get("/accounts/11223344").then().statusCode(200).body("currency", equalTo("SGD"));
  }

  @Test
  void unknown_account_returns_not_found() {
    given()
        .when()
            .get("/accounts/8005551212")
        .then()
            .statusCode(404);
  }

  @Test
  void liveness_probe_is_available() {
    given()
        .when()
            .get("/q/health/live")
        .then()
            .statusCode(200);
  }
}
