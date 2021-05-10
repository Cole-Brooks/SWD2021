/**
 * Implementation of the client side of the ModifyFile program
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ModifyFileClient extends JFrame
{
   /////////////////////////////////////////////////////////
   //                   FIELDS
   /////////////////////////////////////////////////////////
   /** Jtest field to enter the text file name */
   private JTextField fileNameField;
   /** JEditor pane to display text file */
   private JTextArea displayArea;
   /** message from the server */
   private String message = "";
   /** output stream to server */
   private ObjectOutputStream output;
   /** input stream to server */
   private ObjectInputStream input;
   /** host server for this application */
   private String hostName;
   /** socket to communicate with server */
   private Socket client;

   /////////////////////////////////////////////////////////
   //                   METHODS
   /////////////////////////////////////////////////////////
   /**
    * Sets up the GUI and connects to the server
    */
   public ModifyFileClient(String host)
   {
      super("Simple Text Editor");
      //System.out.println("ModifyFileClient::Constructor");
      hostName = host;

      // create enterField and register its listener
      fileNameField = new JTextField("Enter file URL here");
      add(fileNameField, BorderLayout.NORTH);
      fileNameField.addActionListener(
              new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent actionEvent) {
                    sendData("file://" + actionEvent.getActionCommand());
                    fileNameField.setText("");
                 }
              }
      );

      // just trying to add a menu bar
      JMenuBar menuBar = new JMenuBar();
      JMenu menu = new JMenu("File");
      JMenuItem save = new JMenuItem("Save");

      save.addActionListener(
              new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent actionEvent) {
                    sendData(displayArea.getText());
                 }
              }
      );

      menu.add(save);
      menuBar.add(menu);
      setJMenuBar(menuBar);


      displayArea = new JTextArea(); // create contentsArea
      displayArea.setEditable(true);

      add(new JScrollPane(displayArea), BorderLayout.CENTER);
      setSize(600, 600); // set size of window
      setVisible(true); // show window
   }

   /**
    * connects to server
    * @throws IOException input output exception
    */
   private void connectToServer() throws IOException
   {
      //System.out.println("ModifyFileClient::connectToServer");
      displayMessage("Attempting connection\n");

      // create Socket to make connection to server
      client = new Socket(InetAddress.getByName(hostName), 23500);

   }

   /**
    * connects to server and processes messages recieved from the server
    */
   public void runClient()
   {
      //System.out.println("ModifyFileClient::runClient");
      try // connect to server, get streams, process connection
      {
         connectToServer(); // create a Socket to make connection
         getStreams(); // get the input and output streams
         processConnection(); // process connection
      }
      catch (EOFException eofException)
      {
         displayMessage("\nClient terminated connection");
      }
      catch (IOException ioException)
      {
         ioException.printStackTrace();
      }
      finally
      {
         closeConnection(); // close connection
      }
   }

   /**
    * edits the display area in the event-dispatch thread
    * @param messageToDisplay the message which will be displayed
    */
   private void displayMessage(final String messageToDisplay)
   {
    //  System.out.println("ModifyFileClient::displayMessage");
      SwingUtilities.invokeLater(
              new Runnable()
              {
                 public void run() // updates displayArea
                 {
//                    try{
                       displayArea.setText(messageToDisplay);
                       //Document doc = displayArea.getDocument();
                       //doc.insertString(doc.getLength(), messageToDisplay, null);
//                    } catch (BadLocationException e) {
//                       e.printStackTrace();
//                    }
                 }
              }
      );
   }

   /**
    * Cleans up sockets, connections, and streams upon program completion.
    */
   private void closeConnection()
   {
      setTextFieldEditable(false); // disable enterField

      try
      {
         output.close(); // close output stream
         input.close(); // close input stream
         client.close(); // close socket
      }
      catch (IOException ioException)
      {
         ioException.printStackTrace();
      }
   }

   /**
    * Manipulates the fileNameField to be editable or not on the event-dispatch thread
    * @param editable   boolean denoting whether the field should be editable or not.
    */
   private void setTextFieldEditable(final boolean editable)
   {
      SwingUtilities.invokeLater(
              new Runnable()
              {
                 public void run() // sets enterField's editability
                 {
                    fileNameField.setEditable(editable);
                 }
              }
      );
   }

   /**
    * sets up streams to send and receive data from the server
    * @throws IOException input output exception
    */
   private void getStreams() throws IOException
   {
      System.out.println("ModifyFileClient::getStreams");
      // set up output stream for objects
      output = new ObjectOutputStream(client.getOutputStream());
      output.flush(); // flush output buffer to send header information

      // set up input stream for objects
      input = new ObjectInputStream(client.getInputStream());

      displayMessage("\nGot I/O streams\n");
   }

   /**
    * Brains of teh operation. Processes data sent from the server
    * @throws IOException input output exception
    */
   private void processConnection() throws IOException
   {
      System.out.println("ModifyFileClient::processConnections");
      // enable enterField so client user can send messages
      setTextFieldEditable(true);

      do // process messages sent from server
      {
         try // read message and display it
         {
            message = (String) input.readObject(); // read new message
            displayMessage("\n" + message); // display message
         }
         catch (ClassNotFoundException classNotFoundException)
         {
            displayMessage("\nUnknown object type received");
         }

      } while (!message.equals("SERVER>>> TERMINATE"));
   }

   /**
    * sends data to the server
    * @param data   data to be sent to the server
    */
   private void sendData(String data){
      try
      {
         output.writeObject(data);
      }
      catch(IOException io){
         displayArea.setText("Error writing object");
      }
   }
} // end class

