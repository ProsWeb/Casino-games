package games;

import org.slf4j.Logger;

import static games.CardUtils.*;

public class Drunkard {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Drunkard.class);

    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];

    private static int[] playersCardTails = new int[2];
    private static int[] playersCardHeads = new int[2];

    private static boolean[] winner = {false, false};

    public static void main(String... __) {

        System.arraycopy(getShuffledCards(), 0, playersCards[0], 0, CARDS_TOTAL_COUNT / 2);

        System.arraycopy(getShuffledCards(), CARDS_TOTAL_COUNT / 2, playersCards[1], 0, CARDS_TOTAL_COUNT / 2);

        playersCardHeads[0] = CARDS_TOTAL_COUNT / 2;

        playersCardHeads[1] = CARDS_TOTAL_COUNT / 2;

        int count = 0;

        while (!(playerCardsIsEmpty(0)
                ||
                playerCardsIsEmpty(1))) {
            count++;
            int cardPlayer1 = getCard(0);
            int cardPlayer2 = getCard(1);

            log.info("Итерация №{} игрок №1 карта: {}; игрок №2 карта: {}",
                    count,
                    cardToString(cardPlayer1),
                    cardToString(cardPlayer2)
            );

            game(cardPlayer1, cardPlayer2);

            int player1cardsCount = countCards(0);
            int player2cardsCount = countCards(1);

            log.info("У игрока №1 {} карт, у игрока №2 {} карт",
                    player1cardsCount, player2cardsCount
            );
        }

        if (winner[0]) {
            log.info("Выиграл первый игрок! Количество произведённых итераций: {}", count);
        } else {
            log.info("Выиграл второй игрок! Количество произведённых итераций: {}", count);
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
        int i = compareCard(cardPlayer1, cardPlayer2);
        if (i == 0) {
            addCard(0, cardPlayer1);
            addCard(1, cardPlayer2);
            log.info("Спор - каждый остаётся при своих!");
        } else if (i > 2) {
            addCard(0, cardPlayer1);
            addCard(0, cardPlayer2);
            log.info("Выиграл игрок 1!");
        } else {
            addCard(1, cardPlayer1);
            addCard(1, cardPlayer2);
            log.info("Выиграл игрок 2!");
        }
    }

    private static int compareCard(int card1, int card2) {
        int result = card1 % PARS_TOTAL_COUNT - card2 % PARS_TOTAL_COUNT;
        return Math.abs(result) == 8 ? -result : result;
    }
}