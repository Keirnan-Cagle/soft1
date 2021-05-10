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
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {

        double guess = x;
        if (x == 0) {
            guess = 0;
        } else {
            while (((Math.abs(guess * guess) - x) / x) > (0.0001 * 0.0001)) {
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

        boolean again = true;

        while (again) {
            out.print("Enter a number: ");
            double userNum = Double.parseDouble(in.nextLine());
            double value = sqrt(userNum);
            out.print("The square root of " + userNum + " is " + value);
            out.print("Would you like to compute another square root?: ");
            String computeAgain = in.nextLine();
            if (computeAgain.charAt(0) != 'y') {
                again = false;
            }
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
