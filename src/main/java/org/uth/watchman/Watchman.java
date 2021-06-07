package org.uth.watchman;

import io.quarkus.funqy.*;
import io.quarkus.funqy.knative.events.*;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

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

    // Parse the input
    JsonObject message = new JsonObject(payload);

    JsonObject messageFrom = message.getJsonObject("from");
    String sender = messageFrom.getString("last_name") + ", " + messageFrom.getString("first_name");
    String messageText = message.getString("text");
    JsonObject messageChat = message.getJsonObject("chat");
    String chatID = messageChat.getString("id");

    String telegramTarget = "https://api.telegram.org/bot" + _bottoken + "/sendMessage";

    // Process the message accordingly
    String thoughts = Processor.process(sender, messageText);

    if( thoughts != null )
    {
      Postman postman = new Postman( telegramTarget );

      postman.deliver(thoughts, chatID);
    }

    //return output;
  }
}
