import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarcodesTest {
    private Postnet pn0;
    private UPC_A upc;

    // Postnet Tests
    @org.junit.jupiter.api.Test
    void getZipcode() {
        pn0 = new Postnet(52240);
        assertEquals(52240, pn0.getZipcode());
    }

    @org.junit.jupiter.api.Test
    void setZipcode() {
        pn0 = new Postnet(52240);
        pn0.setZipcode(52246);
        assertEquals(52246, pn0.getZipcode());
    }

    @org.junit.jupiter.api.Test
    void getBinZip() {
        pn0 = new Postnet(52240);
        assertEquals("010100010100101010011100010001", pn0.getBinZip());
    }

    @org.junit.jupiter.api.Test
    void getBarcode() {
        pn0 = new Postnet(52240);
        assertEquals("|.|.|...|.|..|.|.|..|||...|...||", pn0.getBarcode());
    }

    @org.junit.jupiter.api.Test
    void convertBarcodeToZip() {
        assertEquals(52240, Postnet.convertBarcodeToZip("|.|.|...|.|..|.|.|..|||...|...||"));
    }

    // UPC-A Tests
    @Test
    void getProductCode() {
        upc = new UPC_A("01254667375");
        assertEquals("01254667375", upc.getProductCode());
    }

    @Test
    void getUpc() {
        upc = new UPC_A("01254667375");
        assertEquals("10100011010011001001001101100010100011010100101111010111101110110111101011101101100010100011101",
                upc.getUpc());
    }

    @Test
    void convertUpcToProductCode() {
        assertEquals("01254667375", UPC_A.convertUpcToProductCode("10100011010011001001001101100010100011010100101111010111101110110111101011101101100010100011101"));
    }
}