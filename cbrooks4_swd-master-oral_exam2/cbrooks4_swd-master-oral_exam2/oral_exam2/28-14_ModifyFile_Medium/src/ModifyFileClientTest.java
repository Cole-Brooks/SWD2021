/**
 * simple driver program for the client side of the modify file program. note that the server must be
 * running when this is ran so that the client has something to connect to. It will die immediately without the
 * server being up and running.
 */

import javax.swing.*;

public class ModifyFileClientTest
{
   public static void main(String[] args)
   {
      ModifyFileClient application;

      if(args.length == 0){
         application = new ModifyFileClient("127.0.0.1"); // default to localhost
      }
      else{
         application = new ModifyFileClient(args[0]); // use args to connect
      }
      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      application.runClient();
   } 
}
