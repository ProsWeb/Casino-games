package games;

import static java.lang.System.out;

public class Drunkard {

    private static final int PARS_TOTAL_COUNT = Par.values().length;

    //private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;

    public static void main(String... __) {

        out.println("Масть 36-й карты - " + getSuit(35));

        out.println("Размерность 36-й карты - " + getPar(35));

        out.println(toString(35));
    }

    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    private static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

}
