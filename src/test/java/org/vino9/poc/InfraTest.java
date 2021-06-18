package org.vino9.poc;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

/*
  Test basic infra for the microservice, including
    1. health probes
    2. metrics
    3. swagger UI
 */
@QuarkusTest
public class InfraTest {

    @Test
    void swagger_ui_is_available() {
        // @formatter:off
        given()
            .when()
                .get("/swagger-ui")
            .then()
                .statusCode(200)
                .body(containsString("Swagger"));
        // @formatter:on
    }

    @Test
    void prometheus_metrics_is_available() {
        // @formatter:off
        given()
            .when()
                .get("/q/metrics")
            .then()
                .statusCode(200)
                .body(containsString("jvm"));
        // @formatter:on
    }

    @Test
    void liveness_probe_is_available() {
        // @formatter:off
        given()
            .when()
                .get("/q/health/live")
            .then()
                .statusCode(200);
        // @formatter:off
    }
}
