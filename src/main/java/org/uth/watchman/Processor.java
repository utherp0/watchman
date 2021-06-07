package org.uth.watchman;

public class Processor 
{
  public static String process( String sender, String message )
  {
    if( message.toLowerCase().indexOf("uthkamel") == -1 )
    {
      return null;
    }

    if( sender.indexOf("No9") != -1 )
    {
      return sender + " sent " + message.length() + " pointless rubbish as per usual";
    }

    return sender + " sent " + message.length() + " characters...";
  }  
}
