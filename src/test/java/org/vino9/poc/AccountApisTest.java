package org.vino9.poc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class AccountApisTest {

  @Test
  public void swagger_ui_is_available() {
    given().when().get("/swagger-ui").then().statusCode(200).body(containsString("Swagger"));
  }
}