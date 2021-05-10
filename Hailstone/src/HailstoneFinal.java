import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class HailstoneFinal {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private HailstoneFinal() {
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
            length++;
        }
        out.print("\nThe length of the series is " + length);
        out.print("\nThe greatest value in the series is " + maxVal);
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        out.print("Enter a Positive Integer: ");
        String enterInfo = in.nextLine();
        boolean number = false;
        int num = 0;
        while (!number) {
            if (FormatChecker.canParseInt(enterInfo)) {
                num = Integer.parseInt(enterInfo);
                number = true;
            } else {
                out.print("ERROR: Enter a New Positive Integer: ");
            }
        }
        return num;
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
            out.print("Enter a Positive Number: ");
            int userNum = Integer.parseInt(in.nextLine());
            generateSeries(userNum, out);
            out.print("\nWould you like to test another series? (Y/N): ");
            String again = in.nextLine();
            if (again.charAt(0) == 'Y') {
                doAnother = true;
            } else {
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
