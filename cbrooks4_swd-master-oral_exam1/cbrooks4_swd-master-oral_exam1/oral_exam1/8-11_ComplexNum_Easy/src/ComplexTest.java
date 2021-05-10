/**
 * ComplexTest
 * a class that will test the functionality of the Complex class
 *
 * @author cbrooks4
 * @version 1.0
 * @since 2/14/2021
 */
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {
    private Complex complex0;
    private Complex complex1;

    @Test
    void testDefaultConstructor(){
        complex0 = new Complex();
        assertEquals(0f, complex0.getReal());
        assertEquals(0f, complex0.getImaginary());
    }
    @Test
    void testConstructor(){
        complex0 = new Complex(100f, 100f);
        assertEquals(100f, complex0.getReal());
        assertEquals(100f, complex0.getImaginary());
    }

    @Test
    void testSetReal() {
        complex0 = new Complex(1f, 1f);
        complex0.setReal(100f);
        assertEquals(100f, complex0.getReal());
    }

    @Test
    void testGetReal() {
        complex0 = new Complex(100f, 100f);
        assertEquals(100f, complex0.getReal());
    }

    @Test
    void testSetImaginary() {
        complex0 = new Complex(100f, 100f);
        complex0.setImaginary(10f);
        assertEquals(10f, complex0.getImaginary());
    }

    @Test
    void testGetImaginary() {
        complex0 = new Complex(100f, 100f);
        assertEquals(100f, complex0.getImaginary());
    }

    @Test
    void testToString() {
        complex0 = new Complex();
        assertEquals("real: " + complex0.getReal() + "\nimaginary: " + complex0.getImaginary(), complex0.toString());
    }

    @Test
    void testAdd() {
        complex0 = new Complex(100f, 100f);
        complex1 = new Complex(20f, 10f);
        complex1 = Complex.add(complex0, complex1);
        assertEquals(120f, complex1.getReal());
        assertEquals(110f, complex1.getImaginary());
    }

    @Test
    void testSubtract() {
        complex0 = new Complex(100f, 100f);
        complex1 = new Complex(100f, 100f);
        complex1 = Complex.subtract(complex0, complex1);
        assertEquals(0f, complex1.getReal());
        assertEquals(0f, complex1.getImaginary());
    }
}