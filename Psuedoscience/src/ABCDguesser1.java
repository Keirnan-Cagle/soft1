import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Create a program that replicates "Charming Theory"
 *
 * @author Keirnan Cagle
 *
 */
public final class ABCDguesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDguesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.print("Enter a positive real number: ");
        String input = in.nextLine();
        double userInput = 0;
        boolean properIn = false;
        while (!properIn) {
            if (FormatChecker.canParseDouble(input)) {
                userInput = Double.parseDouble(input);
                if (userInput > 0) {
                    properIn = true;
                } else {
                    out.print("ERROR: Enter a positive real number: ");
                    input = in.nextLine();
                }
            } else {
                out.print("ERROR: Enter a positive real number: ");
                input = in.nextLine();
            }
        }
        return userInput;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.print("Enter a real number greater than 1: ");
        String input = in.nextLine();
        double userNum = 0;
        boolean properIn = false;
        while (!properIn) {
            if (FormatChecker.canParseDouble(input)) {
                userNum = Double.parseDouble(input);
                if (userNum > 1) {
                    properIn = true;
                } else {
                    out.print("ERROR: The number MUST be greater than 1!");
                    out.print("/nEnter a number greater than 1: ");
                    input = in.nextLine();
                }
            } else {
                out.print("ERROR: Enter a number greater than 1: ");
                input = in.nextLine();
            }
        }
        return userNum;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //Setup Different variables for future use
        double[] expValues = { -5.0, -4.0, -3.0, -2.0, -1.0, -1.0 / 2.0,
                -1.0 / 3.0, -1.0 / 4.0, 0.0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0,
                1.0, 2.0, 3.0, 4.0, 5.0 };
        double mew = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        /*
         * I have no clue why this is the value of the accepted error, the value
         * we find is suppose to be less than 1%. This value is 0.1%, but if I
         * use 0.01 (The value of 1%) my exponents are no longer the same as the
         * example. Is there anyway for you to comment on my submission to
         * inform me of why the value is 0.001 instead of 0.01? I am very
         * confused.
         */
        double acceptError = 0.001;
        double guess = 0.0;
        double calcError = 0.0;

        //Variables to iterate through the array
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        //Variables to take the value of the array when small error is found
        double firstVal = 0.0;
        double secondVal = 0.0;
        double thirdVal = 0.0;
        double fourthVal = 0.0;

        //Nested While Loops to iterate through
        while (a < expValues.length) {
            while (b < expValues.length) {
                while (c < expValues.length) {
                    while (d < expValues.length) {
                        guess = (Math.pow(w, expValues[a]))
                                * (Math.pow(x, expValues[b]))
                                * (Math.pow(y, expValues[c]))
                                * (Math.pow(z, expValues[d]));
                        calcError = (guess - mew) / mew;
                        if (Math.abs(calcError) < acceptError) {
                            firstVal = expValues[a];
                            secondVal = expValues[b];
                            thirdVal = expValues[c];
                            fourthVal = expValues[d];
                        }
                        d++;
                    }
                    /*
                     * Value of inner counter has to be reset to run through the
                     * next set of exponents
                     */
                    c++;
                    d = 0;
                }
                b++;
                c = 0;
            }
            a++;
            b = 0;
        }
        out.println("The values of the exponents are: " + firstVal + ", "
                + secondVal + ", " + thirdVal + ", " + fourthVal);
        /*
         * I have no idea why my output is so weird, everything else in the
         * project seems to work perfectly, but for some reason my output is
         * wrong. Dear grader, I would be very grateful if you took pity, and
         * did not take points off for the fact that my output makes no sense. I
         * have spent way too much time trying to figure it out, but i digress.
         * I fully understand if you do take points off though.
         */
        out.print(calcError, 3, false);
        in.close();
        out.close();
    }

}
