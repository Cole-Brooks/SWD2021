import java.util.*;

public class Postnet{
    // Fields
    /**
     * intToPnBinary:
     *  A map which stores conversions from integer to postnet binary.
     */
    static final Map<Integer,String> intToPNBinary = Map.of(
            0,"11000",
            1,"00011",
            2,"00101",
            3,"00110",
            4,"01001",
            5,"01010",
            6,"01100",
            7,"10001",
            8,"10010",
            9,"10100"
    );
    /**
     * pnBinaryToInt:
     *  A map which stores conversions back from postnet binary to integer.
     */
    static final Map<String,Integer> pnBinaryToInt = Map.of(
            "11000",0,
            "00011", 1,
            "00101", 2,
            "00110", 3,
            "01001", 4,
            "01010", 5,
            "01100", 6,
            "10001", 7,
            "10010", 8,
            "10100", 9
    );

    /**
     * zipcode:
     *  The object's reference to the zipcode it represents
     */
    private int zipcode;

    /**
     * binZip:
     *  The postnet binary zipcode converted from the integer zipcode
     */
    private String binZip;

    /**
     * barcode:
     *  The string which represents a physical barcode which could be printed on a piece of mail.
     *  Represented by | for a full bar and . for a half bar
     */
    private String barcode;

    /**
     * Postnet
     *
     * @param zip   the zip code which the postnet object will represent
     */
    public Postnet(int zip){
        if(Integer.toString(zip).chars().map(c -> c-'0').toArray().length != 5){
            setBinZip(null);
            setBarcode(null);
            System.out.println("Error: attempted to create a postnet with an improperly formatted zipcode. Zipcode should be 5 digits");
        }
        else {
            setZipcode(zip);
            setBinZip(getBinaryAsSB(zip).toString());
            setBarcode(convertBZToBarcode(this.getBinZip()));
        }
    }

    // Methods
    /**
     * getZipCode
     * @return zipcode  objects current zip code
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * setZipcode
     * @param zipcode   objects new zip code
     */
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * getBinZip
     * @return  binZip  objects POSTNET binary represented zip code
     */
    public String getBinZip() {
        return binZip;
    }

    /**
     * setBinZip PRIVATE: binZip should never be set by anything other than the constructor
     * @param binZip    set objects POSTNET binary represented zip code
     */
    private void setBinZip(String binZip) {
        this.binZip = binZip;
    }

    /**
     * getBarcode
     * @return barcode objects barcode represented with '|' for a full bar and '.' for half bar
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * setBarcode PRIVATE: Nothing except the constructor should set the barcode
     * @param barcode   string buffer to set barcode to
     */
    private void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    // Helper functions
    /**
     * getBinary
     * @param zip   the zip code the user intends to encode
     * @return convertedZip   a string of the binary representation of the zip code with the checksum digit added
     */
    private String getBinaryAsSB(int zip){
        int[] digits = Integer.toString(zip).chars().map(c -> c-'0').toArray(); // convert zip to an int array
        StringBuffer sbBin = new StringBuffer();
        for (int digit : digits) {
            sbBin.append(intToPNBinary.get(digit));
        }
        sbBin.append(intToPNBinary.get(getCheckDigit(zip)));
        return sbBin.toString();
    }

    /**
     * getCheckDigit
     * @param zip   zipcode which needs a check digit
     * @return the check digit for the given zip code
     */
    private int getCheckDigit(int zip){
        int sum = 0;
        while(zip > 0){
            sum = sum + zip %10;
            zip = zip / 10;
        }
        return(10 - (sum %10));
    }

    /**
     * convertBZToBarcode
     * @param bz    binary zip code
     * @return String that will represent the barcode
     */
    private String convertBZToBarcode(String bz){
        StringBuffer barcode = new StringBuffer("|"); // start out with |

        for(int i = 0; i < bz.length(); i++){
            if(bz.charAt(i) == '1'){
                barcode.append("|");
            }
            else if(bz.charAt(i) == '0'){
                barcode.append(".");
            }
            else{
                System.out.println("ERROR: Not sure how we got here, but you tried to put something weird into convertBZToBarcode");
            }
        }
        barcode.append("|"); // Add the last pipe
        return barcode.toString();
    }

    /**
     * convertBarcodeToZip
     * @param barcode   the barcode you want to convert to a zip code
     * @return zip  the zipcode the barcode input represents
     */
    static public int convertBarcodeToZip(String barcode){
        // convert from barcode to binary zipcode
        StringBuffer bz = new StringBuffer();
        for(int i = 1; i < barcode.length()-1; i++){ // note we skip the first and last elements or the 'Frame Bars'
            if(barcode.charAt(i) == '|'){
                bz.append('1');
            }
            else if(barcode.charAt(i) == '.'){
                bz.append('0');
            }
            else{
                System.out.println("ERROR: Not sure how we got here, but you tried to put something weird into convertBarcodeToZip");
            }
        }
        // convert from binary zipcode to zipcode
        StringBuffer zipSB = new StringBuffer();
        for(int i = 0; i < bz.length() - 5; i+=5) { // iterate through but skip the check sum digit
            zipSB.append(pnBinaryToInt.get(bz.substring(i, i+5)));
        }
        return Integer.parseInt(zipSB.toString());
    }
}