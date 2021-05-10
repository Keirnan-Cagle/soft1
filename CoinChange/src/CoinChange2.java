import java.util.Arrays;

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
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {
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

        out.print("Enter an amount of change: ");
        int originalChange = Integer.parseInt(in.nextLine());
        int newChange = originalChange;
        int[] coinValues = { 100, 50, 25, 10, 5, 1 };
        int[] coins = new int[coinValues.length];

        for (int i = 0; i < coinValues.length; i++) {
            coins[i] = newChange / coinValues[i];
            newChange = newChange % coinValues[i];
        }
        out.print("Coins Needed: " + Arrays.toString(coins));
        /*
         * int dollars = 0; int halfDollars = 0; int quarters = 0; int dimes =
         * 0; int nickels = 0; int pennies = 0; Code from part 1 if (newChange
         * >= 100) { dollars = newChange / 100; newChange = newChange % 100; }
         * if (newChange >= 50) { halfDollars = newChange / 50; newChange =
         * newChange % 50; } if (newChange >= 25) { quarters = newChange / 25;
         * newChange = newChange % 25; } if (newChange >= 10) { dimes =
         * newChange / 10; newChange = newChange % 10; } if (newChange >= 5) {
         * nickels = newChange / 5; newChange = newChange % 5; } if (newChange
         * >= 1) { pennies = newChange / 1; newChange = 0; }
         *
         * out.print("Dollars: " + dollars); out.print("\nHalf-Dollars: " +
         * halfDollars); out.print("\nQuarters: " + quarters);
         * out.print("\nDimes: " + dimes); out.print("\nNickels: " + nickels);
         * out.print("\nPennies: " + pennies);
         */
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
