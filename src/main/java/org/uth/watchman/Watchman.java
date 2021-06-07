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
    String sender = messageFrom.getString("first_name") + " " + messageFrom.getString("last_name");
    String messageText = message.getString("text");
    JsonObject messageChat = message.getJsonObject("chat");
    String chatID = messageChat.getString("id");

    String responseMessage = messageFrom.getString("last_name") + "," + messageFrom.getString("first_name") + " sent a message of length " + messageText.length();

    String telegramTarget = "https://api.telegram.org/bot" + _bottoken + "/sendMessage?text=" + responseMessage.replaceAll( " ", "%20") + "&chat_id=" + chatID;

    // Post a telegram response for the hell of it
    Postman postman = new Postman( telegramTarget );

    Map<String,String> payloadOutput = new HashMap<>();

    payloadOutput.put("chat_id", chatID );
    payloadOutput.put("text", sender + " sent a message of length " + messageText.length());

    postman.deliver(null);

    //return output;
  }
}
