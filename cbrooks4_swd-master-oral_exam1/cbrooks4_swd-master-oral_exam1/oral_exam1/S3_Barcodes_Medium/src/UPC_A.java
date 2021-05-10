import java.util.Map;

public class UPC_A{
    // Fields
    /**
     * intToUpcBin:
     *  A map for conversion from integer to upc binary.
     */
    static final Map<String,String> intToUPCBin = Map.of(
            "0","0001101",
            "1","0011001",
            "2","0010011",
            "3","0111101",
            "4","0100011",
            "5","0110001",
            "6","0101111",
            "7","0111011",
            "8","0110111",
            "9","0001011"
    );
    /**
     * upcBinToInt:
     *  A map for conversion back from upc binary to integer.
     */
    static final Map<String,String> upcBinToInt = Map.of(
            "0001101","0",
            "0011001","1",
            "0010011","2",
            "0111101","3",
            "0100011","4",
            "0110001","5",
            "0101111","6",
            "0111011","7",
            "0110111","8",
            "0001011","9"
    );
    /**
     * productCode:
     *  The object's reference to its product code
     */
    String productCode;
    /**
     * upc:
     *  A string of binary digits which represents a bar code
     */
    String upc;

    // constructors
    /**
     * UPC_A
     * @param productCode   the product code the user intends to create a barcode for
     */
    public UPC_A(String productCode){
        if(productCode.length() == 11){
            setProductCode(productCode);
            setUpc(produceUpc(productCode).toString());
        }
        else{
            System.out.println("Error: this object was improperly initialized and may behave in unintended ways.  UPC-A objects" +
                    " should be initialized with an 11 digit product code.");
        }
    }
    // public methods

    /**
     * setProductCode PRIVATE: Should not be set anywhere but in the constructor
     * @param productCode   code to identify product
     */
    private void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * getProductCode
     * @return  productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * getUpc
     * @return upc  the barcode to be printed somewhere on the object
     */
    public String getUpc() {
        return upc;
    }

    /**
     * setUpc PRIVATE: Should not be set anywhere but in the constructor
     * @param upc   the stringBuffer upc which will represent the barcode
     */
    private  void setUpc(String upc) {
        this.upc = upc;
    }

    /**
     * convertUpcToProductCode
     * @param upc   the upc you want to convert to a product code
     * @return ProductCode
     */
    public static String convertUpcToProductCode(String upc){
        StringBuffer productCode = new StringBuffer();
        for(int i = 3; i < 38; i+=7){ // get first six digits of product code
            productCode.append(upcBinToInt.get(upc.substring(i, i + 7)));
        }
        for(int i = 43; i < upc.length()-10; i+=7){ // get last 5 digits
            productCode.append(upcBinToInt.get(upc.substring(i,i+ 7)));
        }
        return productCode.toString();
    }
    // helper functions - no java docs for these guys
    private StringBuffer produceUpc(String productCode){
        StringBuffer upc = new StringBuffer("101"); // append start rail
        for(int i = 0; i < productCode.length(); i++){
            if(i == 5){ // append mid rail
                upc.append("01010");
            }
            upc.append(intToUPCBin.get(String.valueOf(productCode.charAt(i))));
        }
        upc.append(getCheckDigit(productCode)); // append check digit
        upc.append("101"); // append end rail
        return upc;
    }

    private String getCheckDigit(String productCode){
        int pc = Integer.parseInt(productCode);
        int sum = 0;
        while(pc > 0){
            sum = sum + pc %10;
            pc = pc / 10;
        }
        return(intToUPCBin.get(String.valueOf(10 - (sum %10))));
    }
}