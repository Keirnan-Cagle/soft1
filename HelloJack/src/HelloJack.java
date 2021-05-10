import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Take an input of a name, and then have the computer say Hello to the name.
 *
 * @author Keirnan Cagle
 *
 */
public final class HelloJack {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private HelloJack() {
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

        out.print("Enter your name: ");
        String name = in.nextLine();
        out.print("Hello " + name);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
