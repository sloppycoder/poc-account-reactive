package org.vino9.poc.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JacksonConfig implements ContextResolver<ObjectMapper> {
  private final ObjectMapper objectMapper;

  public JacksonConfig() {
    objectMapper = new ObjectMapper().setDateFormat(new RFC3339DateFormat());
  }

  public ObjectMapper getContext(Class<?> arg0) {
    return objectMapper;
  }
}
