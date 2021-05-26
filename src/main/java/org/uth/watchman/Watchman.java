package org.uth.watchman;

import io.quarkus.funqy.*;
import io.cloudevents.*;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.Json;

public class Watchman 
{
  @Funq
  public void process( byte[] input, @Context CloudEvent inputEvent )
  {
    ResponsePayload output = new ResponsePayload();

    String payload = new String(input);
    System.out.println( payload );
    System.out.println( inputEvent );
    //return output;
  }
}
