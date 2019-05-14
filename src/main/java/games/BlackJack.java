package games;

import java.io.IOException;

import org.slf4j.Logger;

import static games.CardUtils.*;
import static games.Choice.getCharacterFromUser;

public class BlackJack {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

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
                log.info("Вам выпала карта {}", cardToString(addCard2Player(0)));
            } while (sum(0) < MIN_SUM || sum(0) < MAX_PLAYER_SUM
                    && confirm(String.format("Сумма ваших очков: %d. Берём еще?", sum(0))));

            do {
                log.info("Компьютеру выпала карта {}", cardToString(addCard2Player(1)));
            } while (sum(1) < MIN_SUM);

            while (sum(1) < MAX_COMP_SUM) {
                log.info("Компьютер решил взять ещё и ему выпала карта {}",
                        cardToString(addCard2Player(1)));
            }

            int playerSum = getFinalSum(0);
            int compSum = getFinalSum(1);

            log.info("Сумма ваших очков - {}, компьютера - {}",
                    playerSum, compSum);

            if (playerSum == compSum) {
                continue;
            }
            if (playerSum > compSum) {
                playersMoney[0] += 10;
                playersMoney[1] -= 10;
                log.info("Вы выиграли раунд! Получаете 10$");
            } else {
                playersMoney[1] += 10;
                playersMoney[0] -= 10;
                log.info("Вы проиграли раунд! Теряете 10$");
            }
        }

        if (playersMoney[0] > 0)
            log.info("Вы выиграли! Поздравляем!");
        else
            log.info("Вы проиграли. Соболезнуем...");
    }

    private static void initRound() {
        log.info("У Вас {}$, у компьютера - {}$. Начинаем новый раунд!",
                playersMoney[0], playersMoney[1]);
        cards = getShuffledCards();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCursors = new int[]{0, 0};
        cursor = 0;
    }

    private static int addCard2Player(final int player) {
       int card = cards[cursor];
       int playerCursor = playersCursors[player];
       playersCards[player][playerCursor] = card;

       cursor++;
       playersCursors[player]++;

       return card;
    }

    private static int sum(final int player) {
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

    private static boolean confirm(final String message) throws IOException {
        log.info("{} \"Y\" - да, {любой другой символ} - нет (чтобы выйти из игры, нажмите Ctrl + C",
                message);
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
