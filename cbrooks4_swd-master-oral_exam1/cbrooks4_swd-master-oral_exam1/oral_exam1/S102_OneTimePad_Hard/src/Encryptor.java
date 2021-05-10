// Class Encryptor
//

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * Encryptor
 * @description unencrypts messages which were encrypted using the matching encryptor
 * */
public class Encryptor {
    /** Member Variable: alphabet - a string containing the 26 letters of the English alphabet*/
    final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /** Member Variable: encryptionKey - an integer between 0 and 26 specifying number of characters to shift for successful encryption */
    private int encryptionKey;
    /** Member Variable: path - a string containing the path to the key and index files which contain the encryptionKeys Encryptor will use */
    private String path;

    /**
     * setPath
     * @description sets the Encryptors path
     * */
    Encryptor(String path){
        setPath(path);
    }

    // Getters and Setters
    /**
     * setEncryptionKey
     * @description sets the Encryptors encryptionKey
     * */
    public void setEncryptionKey(int n) {
        this.encryptionKey = n;
    }

    /**
     * setPath
     * @description sets the Encryptors path
     * */
    public void setPath(String newPath) {
        this.path = newPath;
    }
    /**
     * getPath
     * @description gets the Encryptors path
     * */
    public String getPath() {
        return path;
    }
    // Methods

    /**
     * indexFinder
     * a helper function that will be used in encryptMessage designed to handle mapping between the alphabet character array
     * and the String message passed into encryptMessage
     * @param c is the character you wish to find the new index for.
     * @return newIndex is the index of the converted characters mapped counterpart
     */
    private int indexFinder(char c){
        int newIndex = alphabet.indexOf(c) + encryptionKey;
        if(newIndex >= alphabet.length()){
            newIndex = newIndex - alphabet.length(); // todo - make a loop that confirms the key is between 0-26
        }
        return newIndex;
    };
    /**
     * encrypt
     * @description Handles the bulk of the work for encrypting messages.
     * @param message - a string that will be encrypted
     * @return encryptedMessage - a string that has been encrypted
     * */
    public String encryptMessage(String message){
        int startIndex = 0;
        char[] encryptedCharArray = new char[message.length()];
        File indexFile = new File(path+"index.txt");
        File keyFile = new File(path+"key.txt");

        // create index reader object and handle exceptions
        try{
            Scanner indexReader = new Scanner(indexFile);
            startIndex = indexReader.nextInt();
            indexReader.close();
        }
        catch(FileNotFoundException error){
            System.out.println("No index file found in " + path);
            error.printStackTrace();
        }
        // create key reader object and handle exceptions
        try{
            Scanner keyReader = new Scanner(keyFile);

            // move the scanner to the key index specified in index.txt
            // note that we are throwing away all the keys before that index
            for(int i=0; i <= startIndex; i++){
                keyReader.nextInt();
            }

            for(int i=0; i < message.length(); i++){
                char currentChar = message.charAt(i);
                if(currentChar == ' ')
                {
                    // Handle spaces - they will not be encrypted
                    encryptedCharArray[i] = currentChar;
                }
                else{
                    // move on to the next key
                    int nextKey = keyReader.nextInt();
                    this.setEncryptionKey(nextKey);
                    encryptedCharArray[i] = alphabet.charAt(indexFinder(currentChar));
                }
            }
        }
        catch(FileNotFoundException error){
            System.out.println("No key file found in " + path);
            error.printStackTrace();
        }
        return new String(encryptedCharArray);
    };
    
} // end class S102_OneTimePad_Hard_Driver

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/