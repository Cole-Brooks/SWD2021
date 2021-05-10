// Class Encryptor
//

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Decryptor
 * @description unencrypts messages which were encrypted using the matching encryptor
 * */
public class Decryptor {
    /** Member Variable: alphabet - a string containing the 26 letters of the English alphabet*/
    final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /** Member Variable: decryptionKey - an integer between 0 and 26 specifying number of characters to shift for successful decryption */
    private int decryptionKey;
    /** Member Variable: path - a string containing the path to the key and index files which contain the decryptionKeys Decryptor will use */
    private String path;

    /**
     * setPath
     * @description sets the Decryptors path
     * */
    Decryptor(String path){
        setPath(path);
    };

    // Getters and Setters
    /**
     * setDecryptionKey
     * @description sets the Decryptors decryptionKey
     * */
    public void setDecryptionKey(int n) {
        this.decryptionKey = n;
    }

    /**
     * setPath
     * @description sets the Decryptors path
     * */
    public void setPath(String newPath) {
        this.path = newPath;
    }
    /**
     * getPath
     * @description gets the Decryptors path
     * */
    public String getPath() {return this.path; }
    // Methods

    /**
     * indexFinder
     * @description a helper function that will be used in encryptMessage designed to handle mapping between the alphabet character array
     * and the String message passed into encryptMessage
     * @param c is the character you wish to find the new index for.
     * @return newIndex is the index of the converted characters mapped counterpart
     */
    private int indexFinder(char c){
        int newIndex = alphabet.indexOf(c) - decryptionKey;
        if(newIndex < 0){
            newIndex = alphabet.length() + newIndex;
        }
        return newIndex;
    };

    /**
     * decryptMessage
     * @description Handles the bulk of the work for decrypting messages.
     * @param message - a string that will be decrypted
     * @return decryptedMessage - a string that has been decrypted
     * */
    public String decryptMessage(String message){
        int startIndex = 0;
        char[] decryptedCharArray = new char[message.length()];
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
                    decryptedCharArray[i] = currentChar;
                }
                else{
                    this.setDecryptionKey(keyReader.nextInt());
                    decryptedCharArray[i] = alphabet.charAt(indexFinder(currentChar));
                }
            }
        }
        catch(FileNotFoundException error){
            System.out.println("No key file found in " + path);
            error.printStackTrace();
        }
        return new String(decryptedCharArray);
    };

} // end class Decryptor.java