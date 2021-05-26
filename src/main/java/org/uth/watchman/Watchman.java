package org.uth.watchman;

import io.quarkus.funqy.*;
import io.cloudevents.*;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.Json;
import java.util.logging.*;

public class Watchman 
{
  private static final Logger log = Logger.getLogger(Watchman.class.getName());

  @Funq
  public void process( byte[] input, @Context CloudEvent inputEvent )
  {
    ResponsePayload output = new ResponsePayload();

    log.log(Level.INFO, "Called via event " + inputEvent.getType());

    //return output;
  }
}
