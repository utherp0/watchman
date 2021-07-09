package org.uth.watchman;

public class Processor 
{
  public static String process( String sender, String message )
  {
    // Check for null message
    if( message == null ) return sender + " sent no text...";

    if( message.toLowerCase().indexOf("uthkamel") == -1 )
    {
      return null;
    }

    return sender + " sent " + message.length() + " characters...";
  }  
}
