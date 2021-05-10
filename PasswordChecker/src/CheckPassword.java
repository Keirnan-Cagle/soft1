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
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        boolean isLength = false;
        boolean isCapital = false;

        if (s.length() < 8) {
            isLength = false;
        } else {
            isLength = true;
        }

        if (isLength) {
            out.print("That password works!");
        } else {
            out.print("That password is too short!");
        }

        while(!isCapital) {
            for(int i = 0; i < s.length(); i++)
            {
                char curr = s.charAt(i);
                if(curr.is)
            }
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

        out.print("Enter a Password: ");
        String passWord = in.nextLine();
        checkPassword(passWord, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
