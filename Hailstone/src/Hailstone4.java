import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone4() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        out.print(n);
        int newNum = n;
        while (newNum != 1) {
            if (newNum % 2 == 0) {
                newNum /= 2;
            } else {
                newNum = (3 * newNum) + 1;
            }
            out.print(" " + newNum);
        }
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        Boolean doAnother = true;
        while (doAnother) {
            out.print("Enter a Number: ");
            int userNum = Integer.parseInt(in.nextLine());
            generateSeries(userNum, out);
            out.print("\nWould you like to test another series? (Y/N): ");
            String again = in.nextLine();
            if (again.charAt(0) == 'Y') {
                doAnother = true;
            } else if (again.charAt(0) == 'N') {
                doAnother = false;
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
