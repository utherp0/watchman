package org.uth.watchman;

import io.quarkus.funqy.*;
import io.quarkus.funqy.knative.events.*;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.Json;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.uth.watchman.utils.Postman;

public class Watchman 
{
  @ConfigProperty(name = "BOT")
  String _bottoken;

  @Funq
  public void process( byte[] input, @Context CloudEvent inputEvent )
  {
    ResponsePayload output = new ResponsePayload();

    String payload = new String(input);
    System.out.println( payload );
    System.out.println( inputEvent );

    String telegramTarget = "https://api.telegram.org/bot" + _bottoken + "/sendMessage";

    // Post a telegram response for the hell of it
    Postman postman = new Postman( telegramTarget );
    //return output;
  }
}
