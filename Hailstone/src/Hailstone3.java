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
public final class Hailstone3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone3() {
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
        int maxVal = n;
        while (newNum != 1) {
            if (newNum % 2 == 0) {
                newNum /= 2;
            } else {
                newNum = (3 * newNum) + 1;
            }
            if (newNum > maxVal) {
                maxVal = newNum;
            }
            out.print(" " + newNum);

        }
        out.print("\nThe greatest value is " + maxVal);
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
        out.print("Enter a Number: ");
        int userNum = Integer.parseInt(in.nextLine());
        generateSeries(userNum, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
