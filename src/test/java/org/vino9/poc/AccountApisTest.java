package org.vino9.poc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class AccountApisTest {

  @Test
  void swagger_ui_is_available() {
    given().when().get("/swagger-ui").then().statusCode(200).body(containsString("Swagger"));
  }

  @Test
  void prometheus_metrics_is_available() {
    given().when().get("/q/metrics").then().statusCode(200).body(containsString("jvm"));
  }
}
