import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Find the square root of a value using Newton's iteration
 *
 * @author Keirnan Cagle
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param epsilon
     *            User Number for the relative error
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {

        double guess = x;
        //If the user number is 0 then the square root is 0
        if (x == 0) {
            guess = 0;
        } else {
            while (((Math.abs(guess * guess) - x) / x) > (epsilon * epsilon)) {
                guess = ((guess + (x / guess)) / 2);
            }
        }

        return guess;
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

        double userNum = 0.0;
        boolean end = false;

        while (!end) {
            out.print("Enter a number to calculate the square root: ");
            userNum = Double.parseDouble(in.nextLine());
            if (userNum >= 0) {
                out.print("Enter a relative error value: ");
                double error = Double.parseDouble(in.nextLine());
                double value = sqrt(userNum, error);
                out.print("The square root of " + userNum + " is " + value);
                out.println();
            } else {
                end = true;
            }

        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
