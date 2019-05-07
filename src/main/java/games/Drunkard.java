package games;

import static java.lang.System.out;

import org.apache.commons.math3.util.MathArrays;

public class Drunkard {

    private static final int PARS_TOTAL_COUNT = Par.values().length;

    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;

    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];

    private static int[] deck = new int[CARDS_TOTAL_COUNT];

    private static int[] playersCardTails = new int[2];
    private static int[] playersCardHeads = new int[2];

    private static boolean[] winner = {false, false};

    public static void main(String... __) {

        for (int i = 0; i < CARDS_TOTAL_COUNT; i++) {
            deck[i] = i;
        }

        MathArrays.shuffle(deck);

        System.arraycopy(deck, 0, playersCards[0], 0, CARDS_TOTAL_COUNT / 2);

        System.arraycopy(deck, CARDS_TOTAL_COUNT / 2, playersCards[1], 0, CARDS_TOTAL_COUNT / 2);

        playersCardHeads[0] = CARDS_TOTAL_COUNT / 2;

        playersCardHeads[1] = CARDS_TOTAL_COUNT / 2;

        int count = 0;

        while (!(playerCardsIsEmpty(0)
                ||
                playerCardsIsEmpty(1))) {
            count++;
            int cardPlayer1 = getCard(0);
            int cardPlayer2 = getCard(1);

            out.printf("Итерация №%d игрок №1 карта: %s; игрок №2 карта: %s%n",
                    count,
                    toString(cardPlayer1),
                    toString(cardPlayer2)
            );

            game(cardPlayer1, cardPlayer2);

            int player1cardsCount = countCards(0);
            int player2cardsCount = countCards(1);

            out.printf("У игрока №1 %d карт, у игрока №2 %d карт%n",
                    player1cardsCount, player2cardsCount
            );
        }

        if (winner[0]) {
            out.printf("Выиграл первый игрок! Количество произведённых итераций: %d%n", count);
        } else {
            out.printf("Выиграл второй игрок! Количество произведённых итераций: %d%n.", count);
        }
    }

    private static boolean playerCardsIsEmpty(final int playerIndex) {
        int tail = playersCardTails[playerIndex];
        int head = playersCardHeads[playerIndex];

        return tail == head;
    }

    private static int getCard(final int player) {
        int tail = playersCardTails[player];
        int card = playersCards[player][tail];

        playersCards[player][tail] = 0;
        playersCardTails[player] = incrementIndex(tail);

        return card;
    }

    private static void addCard(final int player, final int card) {
        int head = playersCardHeads[player];

        playersCards[player][head] = card;
        playersCardHeads[player] = incrementIndex(head);

        for (int i = 0; i < winner.length; i++) {
            winner[i] = false;
        }
        winner[player] = true;
    }

    private static int countCards(final int player) {
        int count;
        int tail = playersCardTails[player];
        int head = playersCardHeads[player];

        if (head >= tail) {
            count = head - tail;
        } else {
            count = head + CARDS_TOTAL_COUNT - tail;
        }

        if (count == 0 && winner[player]) {
            return CARDS_TOTAL_COUNT;
        }

        return count;
    }

    private static int incrementIndex(final int i) {
        return (i + 1) % CARDS_TOTAL_COUNT;
    }

    private static void game(final int cardPlayer1, final int cardPlayer2) {
        Par cardPlayer1Par = getPar(cardPlayer1);

        Par cardPlayer2Par = getPar(cardPlayer2);

        int i = compareCard(cardPlayer1Par, cardPlayer2Par);
        if (i == 0) {
            addCard(0, cardPlayer1);
            addCard(1, cardPlayer2);
            out.println("Спор - каждый остаётся при своих!");
        } else if (i == 1) {
            addCard(0, cardPlayer1);
            addCard(0, cardPlayer2);
            out.println("Выиграл игрок 1!");
        } else {
            addCard(1, cardPlayer1);
            addCard(1, cardPlayer2);
            out.println("Выиграл игрок 2!");
        }
    }

    private static int compareCard(final Par card1Par, final Par card2Par) {
        if (card1Par.equals(Par.SIX) && card2Par.equals(Par.ACE)) {
            return 1;
        }

        if (card1Par.equals(Par.ACE) && card2Par.equals(Par.SIX)) {
            return -1;
        }

        return Integer.compare(card1Par.ordinal(), card2Par.ordinal());
    }

    private static Suit getSuit(final int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    private static Par getPar(final int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static String toString(final int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

}
