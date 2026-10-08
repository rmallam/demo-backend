package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.LinkedHashMap;
import java.util.Map;

@Path("/api")
public class ApiResource {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, String> api() {
    Map<String, String> body = new LinkedHashMap<>();
    body.put("service", "demo-backend");
    body.put("system", "acme-demo");
    body.put("message", "ok");
    return body;
  }
}
