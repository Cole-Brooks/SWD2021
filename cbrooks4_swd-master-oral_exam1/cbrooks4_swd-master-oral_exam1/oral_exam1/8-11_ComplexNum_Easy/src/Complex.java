/**
 * Complex:
 * A class that represents complex numbers
 *
 * @author cbrooks4
 * @version 1.0
 * @since 2/14/2021
 *
 */
public class Complex{
    // member variables
    /**
     * real:
     * represents the real part of a complex number
     */
    private float real;
    /**
     * imaginary:
     * represents the imaginary part of the complex number.  It should be noted that this portion should
     * be understood to be * sqrt(-1)
     */
    private float imaginary;

    // constructors

    /**
     * Complex
     * constructor with parameters for the real and imaginary portions of the complex number it will represent
     * @param real  the real portion of the complex number
     * @param imaginary the imaginary portion of the complex number
     */
    Complex(float real, float imaginary){
        setReal(real);
        setImaginary(imaginary);
    }

    /**
     * Complex
     * constructor with default parameters which default to 0f for both the real and imaginary
     * portions of the complex number it represents
     */
    Complex(){
        setReal(0f);
        setImaginary(0f);
    }

    // member functions
    /**
     * setReal
     * @param real
     *              The new value to set the real portion of this complex number
     */
    public void setReal(float real) {
        this.real = real;
    }
    /**
     * getReal
     * @return float
     *              The real portion of this complex number
     */
    public float getReal() {
        return real;
    }
    /**
     * setImaginary
     * @param imaginary
     *               the new imaginary portion of this complex number
     */
    public void setImaginary(float imaginary) {
        this.imaginary = imaginary;
    }
    /**
     * getImaginary
     * @return float
     *              The imaginary portion of this complex number
     */
    public float getImaginary() {
        return imaginary;
    }

    /**
     * toString
     * @return a string to print which represents the complex number
     */
    public String toString(){
        return("real: " + real + "\nimaginary: " + imaginary);
    }

    /**
     * add
     *              Adds two complex numbers
     * @param c1    first complex number
     * @param c2    second complex number
     * @return Complex
     *              The sum of the two complex numbers which were added
     */
    public static Complex add(Complex c1, Complex c2){
        return(new Complex(c1.getReal() + c2.getReal(), c1.getImaginary() + c2.getImaginary()));
    }

    /**
     * subtract
     *              Subtracts the second complex number given from the first
     * @param c1    the first complex number
     * @param c2    the complex number to subract from the first complex number
     * @return Complex
     *              The difference of the two complex numbers which were subtracted
     */
    public static Complex subtract(Complex c1, Complex c2){
        return(new Complex(c1.getReal() - c2.getReal(), c1.getImaginary() - c2.getImaginary()));
    }
}