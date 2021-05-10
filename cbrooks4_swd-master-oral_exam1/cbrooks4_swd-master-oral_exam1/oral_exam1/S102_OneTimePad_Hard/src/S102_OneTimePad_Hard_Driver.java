// Fig. 2.3: S102_OneTimePad_Hard_Driver.java
// Driving the S102_OneTimePad_Hard program.

import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
import java.util.Arrays;
import java.util.Scanner;
/**
 * S102_OneTimePad_Hard_Driver
 * @description  A class designed to test or use the encryption suite created for SWD Spring 2021
 * @author cbrooks4
 * @date 2/7/2021
 * */
public class S102_OneTimePad_Hard_Driver {
    /** main method begins execution of Java application */
    public static void main(String[] args) {
        // Initialize components
        // Allow user to decide whether to use the program or test the program
        // defaults to using the program
        boolean runningTests = false;
        Scanner scnr = new Scanner(System.in); // create the scanner object
        System.out.println("Welcome to the encryption suite. To test suite, type '0'.  To use suite type '1'");
        runningTests = scnr.nextInt() == 0;
        // Test:
        if(runningTests){
            //                           UNIT TESTS
            boolean testsPassed = true;
            // 1: Test total functionality
            String testMessage = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            // Test with index and key pair in current working directory
            // Generate Key/Index file pair
            int testKey1 = 30000;
            String testPath1 = "";
            KeyGenerator testKeyGen1 = new KeyGenerator(testKey1, testPath1);
            testKeyGen1.createFile();
            System.out.println(testKeyGen1.getPath());

            Encryptor testEncryptor1 = new Encryptor(testPath1);
            Decryptor testDecryptor1 = new Decryptor(testPath1);

            String testEncryptedString1 = testEncryptor1.encryptMessage(testMessage);
            if(!testDecryptor1.decryptMessage(testEncryptedString1).equals(testMessage)){
                testsPassed = false;
                System.out.println("Test 1 failed. Decrypted string did not match encrypted string");
                System.out.print("Expected decrypted string = " + testMessage);
                System.out.println(" Actual decrypted string = " + testDecryptor1.decryptMessage(testEncryptedString1));
            }
            // Test with index and key pair in a path other than the current working directory
            int testKey2 = 30000;
            String testPath2 = "../";
            KeyGenerator testKeyGen2 = new KeyGenerator(testKey1, testPath1);
            testKeyGen2.createFile();
            System.out.println(testKeyGen2.getPath());

            Encryptor testEncryptor2 = new Encryptor(testPath1);
            Decryptor testDecryptor2 = new Decryptor(testPath1);

            String testEncryptedString2 = testEncryptor2.encryptMessage(testMessage);
            if(!testDecryptor2.decryptMessage(testEncryptedString2).equals(testMessage)){
                testsPassed = false;
                System.out.println("Test 2 failed. Decrypted string did not match encrypted string");
                System.out.print("Expected decrypted string = " + testMessage);
                System.out.println(" Actual decrypted string = " + testDecryptor2.decryptMessage(testEncryptedString2));
            }
            if(testsPassed){
                System.out.println("All tests have completed successfully!");
            }
            else{
                System.out.println("ATTENTION! Some unit tests have failed... Check logs for more information");
            }
        }
        // Use encryption suite:
        else {
            // Prompt user for a key length,
            // notify user of maximum message length for length of key = 1/4 key length
            System.out.println("Enter key length - note that your message can only be 1/4 of key length for security purposes");
            int key = scnr.nextInt();
            // Prompt user for path
            System.out.println("Enter path for key/index file pair to be generated.  Note that this path is relative to your current working directory");
            System.out.println("To use current directory, type '/'");
            System.out.println("Please remember to end path with a forward slash '/'");
            System.out.println("Note that your path directory MUST currently exist");
            String inputPath = scnr.next();
            // modify path if current directory is given
            String path = inputPath.equals("/") ? "" : inputPath;

            KeyGenerator kg = new KeyGenerator(key, path);
            kg.createFile();
            System.out.println(kg.getPath());

            Encryptor encryptor = new Encryptor(path);
            Decryptor decryptor = new Decryptor(path);

            int esState = -1; // Encryption Suite state variable. Initialize to non-used int0
            Scanner edScanner = new Scanner(System.in).useDelimiter("\\n"); // initialize new scanner to take in sentences

            System.out.println("Encryption suite successfully initialized.");

            while(esState!=0){
                System.out.println("Operation: \nEncrypt (1)\nDecrypt(2)\nQuit(0)");
                esState = scnr.nextInt();
                if(esState == 1){
                    System.out.println("Enter string to encrypt:");
                    String message = edScanner.nextLine();
                    System.out.println(encryptor.encryptMessage(message));
                }
                else if(esState == 2){
                    System.out.println("Enter string to decrypt:");
                    String encryptedMessage = edScanner.nextLine();
                    System.out.println(decryptor.decryptMessage(encryptedMessage));
                }
                else if(esState == 0){
                    System.out.println("Quitting...");
                }
                else{
                    System.out.println("You entered an unusable value");
                }
            }
        }
    } // end method main
} // end class S102_OneTimePad_Hard_Driver
