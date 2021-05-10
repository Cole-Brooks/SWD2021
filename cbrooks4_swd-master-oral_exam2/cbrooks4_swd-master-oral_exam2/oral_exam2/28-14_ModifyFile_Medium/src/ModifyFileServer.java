/**
 * Class that implements setting up and running the server for the modify file server program
 */

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ModifyFileServer
{
   /** editor pane which loads the file upon request from client */
   private final JEditorPane fileRetriever;
   /** output stream to client */
   private ObjectOutputStream output;
   /** input stream from client */
   private ObjectInputStream input;
   /** Server socket */
   private ServerSocket server;
   /** connection to the client */
   private Socket connection;
   /** the file that the client has requested to open/edit/read */
   private File file;

   /**
    * server constructor. Instantiates the fileRetriever
    */
   public ModifyFileServer()
   {
      System.out.println("Server up and running!");
      fileRetriever = new JEditorPane(); // create the file retriever
   }

   /**
    * sets up and runs the server
    */
   public void runServer()
   {
      try // set up server to receive connections; process connections
      {
         server = new ServerSocket(23500, 100); // create ServerSocket

         while (true) 
         {
            try 
            {
               waitForConnection(); // wait for a connection
               getStreams(); // get input & output streams
               processConnection(); // process connection
            } 
            catch (EOFException eofException) 
            {
               eofException.printStackTrace();
            } 
            finally 
            {
               closeConnection(); //  close connection
            } 
         } 
      } 
      catch (IOException ioException) 
      {
         ioException.printStackTrace();
      } 
   }

   /**
    * Waits for connection
    * @throws IOException  input output exception
    */
   private void waitForConnection() throws IOException
   {
      connection = server.accept(); // allow server to accept connection
   }

   /**
    * sets up streams to send and receive data
    * @throws IOException  input output exception that occurs while setting up streams
    */
   private void getStreams() throws IOException
   {
      // set up output stream for objects
      output = new ObjectOutputStream(connection.getOutputStream());
      output.flush(); // flush output buffer to send header information

      // set up input stream for objects
      input = new ObjectInputStream(connection.getInputStream());
   }

   /**
    * handles data sent to and from the client. Brains of the operation
    * @throws IOException  input output exception
    */
   private void processConnection() throws IOException
   {
      String rxdData = "";

      do // process messages sent from client
      {
         try // read message and display it
         {
            rxdData = (String) input.readObject(); // read new message

            if(rxdData.startsWith("file://")){
               file = new File(rxdData.substring(7));
               try {
                  fileRetriever.setPage(rxdData);
                  sendData(fileRetriever.getText());
               }
               catch(IOException e){
                  sendData("Error Retrieving Specified URL:" + rxdData);
               }
            }
            else
            {
               if(file == null){
                  sendData("No file to edit");
               }
               else {
                  sendData("attempting to edit file");
                  // write data received to the file
                  try{
                     FileWriter fileWriter = new FileWriter(file);
                     fileWriter.write(rxdData);
                     sendData("Successfully saved File!");
                     fileWriter.close();
                  }
                  catch(IOException e){
                     sendData("Unable to save file due to IOException error\n" +
                             "you tried to edit: " + file.getName() + "\n");
                     e.printStackTrace();
                  }
               }
            }

         } 
         catch (ClassNotFoundException classNotFoundException) 
         {
            sendData("Unknown object type received");
         } 

      } while (!rxdData.equals("CLIENT>>> TERMINATE"));
      sendData("Connection to the Server has been Terminated!");
   }

   /**
    * cleans sockets and streams up and closes connections
    */
   private void closeConnection() 
   {
      try 
      {
         output.close(); // close output stream
         input.close(); // close input stream
         connection.close(); // close socket
      } 
      catch (IOException ioException) 
      {
         ioException.printStackTrace();
      } 
   }

   /**
    * displays message on the client side
    * @param message string to be displayed on the clients terminal
    */
   private void sendData(String message)
   {
      try // send object to client
      {
         output.writeObject(message);
         output.flush(); // flush output to client
      } 
      catch (IOException ioException) 
      {
         ioException.printStackTrace();
      } 
   }
}