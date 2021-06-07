package org.uth.watchman.utils;

import java.net.*;

public class Postman 
{
  private String _target = null;

  public Postman( String targetURL )
  {
    _target = targetURL;
  }

  public boolean deliver( String message, String chatID )
  {
    try
    {
      String completeURL = _target + "?chat_id=" + chatID + "&text=" + message.replaceAll( " ", "%20" );
      
      URL url = new URL(completeURL);
      HttpURLConnection postConnection = (HttpURLConnection)url.openConnection();

      postConnection.setRequestMethod( "POST" );
      postConnection.setRequestProperty( "Content-Type", "application/json" );

      postConnection.setDoOutput(true);

      int responseCode = postConnection.getResponseCode();

      System.out.println( responseCode + " " + postConnection.getResponseMessage() + " from " + _target );

      return true;
    }
    catch( Exception exc )
    {
      System.out.println( "Postman failure: " + exc.toString());

      return false;
    }
  }
}
