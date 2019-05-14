package games;

import java.io.IOException;

import static games.CardUtils.*;
import static games.Choice.getCharacterFromUser;
import static java.lang.System.*;

public class BlackJack {

    private static int[] cards; // Основная колода
    private static int cursor; // Счётчик карт основной колоды

    private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
    private static int[] playersCursors; // курсоры карт игроков. Индекс - номер игрока

    private static int[] playersMoney = {100, 100};

    private static final int MAX_VALUE = 21;
    private static final int MAX_CARDS_COUNT = 8;
    private static final int MIN_SUM = 11;
    private static final int MAX_PLAYER_SUM = 20;
    private static final int MAX_COMP_SUM = 17;

    public static void main(String... __) throws IOException {
        while (!(playersMoney[0] == 0 || playersMoney[1] == 0)) {

            initRound();

            do {
                out.printf("Вам выпала карта %s%n", CardUtils.toString(addCard2Player(0)));
            } while (sum(0) < MIN_SUM || (sum(0) < MAX_PLAYER_SUM
                    && confirm(String.format("Сумма ваших очков: %d. Берём еще?", sum(0)))));

            do {
                out.printf("Компьютеру выпала карта %s%n", CardUtils.toString(addCard2Player(1)));
            } while (sum(1) < MIN_SUM);

            while (sum(1) < MAX_COMP_SUM) {
                out.printf("Компьютер решил взять ещё и ему выпала карта %s%n",
                        CardUtils.toString(addCard2Player(1)));
            }

            int playerSum = getFinalSum(0);
            int compSum = getFinalSum(1);

            out.printf("Сумма ваших очков - %d, компьютера - %d%n",
                    playerSum,compSum);

            if (playerSum == compSum) {
                continue;
            }
            if (playerSum > compSum) {
                playersMoney[0] += 10;
                playersMoney[1] -= 10;
                out.println("Вы выиграли раунд! Получаете 10$");
            } else {
                playersMoney[1] += 10;
                playersMoney[0] -= 10;
                out.println("Вы проиграли раунд! Теряете 10$");
            }
        }

        if (playersMoney[0] > 0)
            out.println("Вы выиграли! Поздравляем!");
        else
            out.println("Вы проиграли. Соболезнуем...");
    }

    private static void initRound() {
        out.println("\nУ Вас " + playersMoney[0] + "$, у компьютера - " + playersMoney[1] + "$. Начинаем новый раунд!");
        cards = getShuffledCards();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCursors = new int[]{0, 0};
        cursor = 0;
    }

    private static int addCard2Player(int player) {
       int card = cards[cursor];
       int playerCursor = playersCursors[player];
       playersCards[player][playerCursor] = card;

       cursor++;
       playersCursors[player]++;

       return card;
    }

    private static int sum(int player) {
        int sum = 0;
        int playerCursor = playersCursors[player];

        for (int i = 0; i < playerCursor; i++) {
            sum += value(playersCards[player][i]);
        }

        return sum;
    }

    private static int getFinalSum(int player) {
        return sum(player) <= MAX_VALUE ? sum(player) : 0;
    }

    private static boolean confirm(String message) throws IOException {
        out.println(message + " \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
        switch (getCharacterFromUser()) {
            case 'Y':
            case 'y': return true;
            default: return false;
        }
    }

    private static int value(int card) {
        switch (getPar(card)) {
            case JACK:  return 2;
            case QUEEN: return 3;
            case KING:  return 4;
            case SIX:   return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE:  return 9;
            case TEN:   return 10;
            case ACE:
            default:    return 11;
        }
    }
}
