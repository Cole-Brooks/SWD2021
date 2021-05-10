/**
 * Driver class for the modify file server. This must be ran first, so the client has something to
 * connect to.
 */
public class ModifyFileServerTest
{
   public static void main(String[] args)
   {
      ModifyFileServer application = new ModifyFileServer(); // create server
      application.runServer(); // run server application
   } 
}
