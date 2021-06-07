package org.uth.watchman;

public class Processor 
{
  public static String process( String sender, String message )
  {
    if( message.toLowerCase().indexOf("uthkamel") == -1 )
    {
      return null;
    }

    return sender + " sent " + message.length() + " characters...";
  }  
}
