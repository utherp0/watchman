package org.uth.watchman.utils;

import java.net.*;
import java.util.Map;

public class Postman 
{
  private String _target = null;

  public Postman( String targetURL )
  {
    _target = targetURL;
  }

  public boolean deliver( Map<String,String> optionalPayload )
  {
    try
    {
      //System.out.println( "Posting to " + _target );
      //System.out.println( "  (Optional Payload: " + optionalPayload + ")");

      URL url = new URL(_target);
      HttpURLConnection postConnection = (HttpURLConnection)url.openConnection();

      postConnection.setRequestMethod( "POST" );
      postConnection.setRequestProperty( "Content-Type", "application/json" );

      postConnection.setDoOutput(true);

      // Process the optional payload parameters
      if( optionalPayload != null )
      {
        StringBuilder payload = new StringBuilder();

        boolean firstParam = true;

        for( String key : optionalPayload.keySet() )
        {
          String value = optionalPayload.get(key);

          if( !firstParam )
          {
            payload.append( "&" + value );
          }
          else
          {
            payload.append( value );
            firstParam = false;
          }
        }

        String converted = ( payload.toString() ).replaceAll(" ", "%20");

        byte[] postDataBytes = converted.getBytes("UTF-8");

        postConnection.getOutputStream().write(postDataBytes);
      }

      int responseCode = postConnection.getResponseCode();

      System.out.println( responseCode + " from " + _target );

      return true;
    }
    catch( Exception exc )
    {
      System.out.println( "Postman failure: " + exc.toString());

      return false;
    }
  }
}
