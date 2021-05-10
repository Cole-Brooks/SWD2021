import java.io.File; // File object
import java.io.FileWriter; // Write to file
import java.io.IOException; // Error handling
import java.util.Arrays;
import java.util.Random; // Import random library to generate keys

/**
 * KeyGenerator.java
 * @description Generates the key and index files for the decryptor and encryptor classes of the encryption suite
 * @input int keyLength (the number of offsets produced for the key file)
 * @input String path (the file path in which the key and index files will exist)
 * */
public class KeyGenerator {
    // Members
    private int length; /** number of offsets produced and written to key file */
    private String path; /** file path to key and index files produced */

    /**
     * Constructor: KeyGenerator(int keyLength, String path)
     * @description defined in class description
     *  */
    KeyGenerator(int keyLength, String path){
        setPath(path+"key.txt");
        setLength(keyLength);
    }

    /**
     * getIndexFilePath
     * @description helper function which simply replaces key.txt with index.txt
     * */
    private String getIndexFilePath(){
        return path.replace("key.txt", "index.txt");
    }
    // Setters and getters
    /**
     * setPath
     * @description sets the KeyGenerators path
     * */
    public void setPath(String newPath) {
        this.path = newPath;
    }
    /**
     * getPath
     * @description gets the KeyGenerators path
     * */
    public String getPath() {
        return path;
    }

    /**
     * setLength
     * @description sets the KeyGenerators key length
     * */
    public void setLength(int keyLength) {
        this.length = keyLength;
    }
    /**
     * getLength
     * @description gets the KeyGenerators key length
     * */
    public int getLength() {
        return length;
    }

    // Methods
    /**
     * generateKeyFile
     * @description generates an array of random integers of length equal to the KeyGenerators key length
     * */
    public int[] generateKeyFile(){
        Random rand = new Random();
        int[] keyValues = new int[this.getLength()];
        for(int i = 0; i < keyValues.length; i++){
            keyValues[i] = rand.nextInt(26);
        }
        return keyValues;
    }

    /**
     * createFile
     * @description handles opening and writing to files.  Does most of the actual work of the KeyGenerator
     * */
    public void createFile(){
        // Create the path to the file to write the key to
        // create the file and handle possible exceptions
        try {
            File keyFile = new File(this.getPath());
            if (keyFile.createNewFile()) {
                System.out.println("File created successfully: " + keyFile.getName());
            } else {
                System.out.println("Key file already exists! Overwriting key.");
            }

            File indexFile = new File(this.getIndexFilePath());
            if(indexFile.createNewFile()){
                System.out.println("File created successfully: " + indexFile.getName());
            }
            else{
                System.out.println("Index file already exists! Overwriting index.");
            }
        }
        catch (IOException error){
            System.out.println("An error has occurred while creating the key/index file pair.");
            error.printStackTrace();
        }

        // Write to file
        try {
            FileWriter keyWriter = new FileWriter(this.getPath());
            int[] keyArray = new int[length];
            keyArray = this.generateKeyFile();
            for(int i = 0; i < length; i++)
            {
                keyWriter.write(keyArray[i] + " ");
            }
            keyWriter.close();
        }
        catch(IOException error) {
            System.out.println("An error has occurred while writing to the key file.");
            error.printStackTrace();
        }
        try {
            Random rand = new Random();
            FileWriter indexWriter = new FileWriter(this.getIndexFilePath());
            indexWriter.write(String.valueOf(rand.nextInt(this.getLength()/4)));
            indexWriter.close();
        }
        catch(IOException error) {
            System.out.println("An error has occurred while writing to the index file.");
            error.printStackTrace();
        }
    }
}
