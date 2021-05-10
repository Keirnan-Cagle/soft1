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
public final class Hailstone2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone2() {
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
        //Print the entered Number
        out.print(n);
        //Create a new int that holds the changing number
        int newNum = n;
        int length = 1;
        while (newNum != 1) {
            if (newNum % 2 == 0) {
                newNum /= 2;
            } else {
                newNum = (3 * newNum) + 1;
            }
            out.print(" " + newNum);
            length++;
        }
        out.print("\nThe length of the series is " + length);
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
        out.print("Enter a Positive Number: ");
        int userNum = Integer.parseInt(in.nextLine());
        generateSeries(userNum, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
