package org.vino9.poc.config;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class PostgresTestContainerResource implements QuarkusTestResourceLifecycleManager {
  public static final String TEST_DB_USERNAME = "testuser";
  public static final String TEST_DB_PASSWORD = "password";

  static PostgreSQLContainer<?> db =
      new PostgreSQLContainer<>("postgres:12.7")
          .withDatabaseName("testdb")
          .withUsername(TEST_DB_USERNAME)
          .withPassword(TEST_DB_PASSWORD);

  @Override
  public Map<String, String> start() {
    db.start();

    var jdbcUrl = db.getJdbcUrl();
    return Map.of(
        "quarkus.datasource.jdbc.url", jdbcUrl,
        "quarkus.datasource.reactive.url", jdbcUrl.substring(5),
        "quarkus.datasource.username", TEST_DB_USERNAME,
        "quarkus.datasource.password", TEST_DB_PASSWORD);
  }

  @Override
  public void stop() {
    db.stop();
  }
}
