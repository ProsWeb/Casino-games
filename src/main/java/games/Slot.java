package games;

import org.slf4j.Logger;

public class Slot {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);

    private static final int PULL_COUNTER = 100;

    private static final int COUNTER_SIZE = 7;

    private static final int BET = 10;

    private static final int PRIZE = 1000;

    public static void main(String... __) {

        int amount = 100;

        int firstCounter = 1;

        int secondCounter = 1;

        int thirdCounter = 1;

        while (amount > 0) {
            log.info("У Вас {}$, ставка - {}", amount, BET);
            log.info("Крутим барабаны! Розыгрыш принёс следующие результаты:");

            firstCounter = (firstCounter + (int) Math.round(Math.random() * PULL_COUNTER)) % COUNTER_SIZE;
            secondCounter = (secondCounter + (int) Math.round(Math.random() * PULL_COUNTER)) % COUNTER_SIZE;
            thirdCounter = (thirdCounter + (int) Math.round(Math.random() * PULL_COUNTER)) % COUNTER_SIZE;

            log.info("первый барабан - {}, второй - {}, третий - {}",
                        firstCounter, secondCounter, thirdCounter);

            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                amount += PRIZE;
                log.info("Выигрыш {}$, ваш капитал теперь составляет: {}", PRIZE, amount);
            } else {
                amount -= BET;
                log.info("Проигрыш {}$, ваш капитал теперь составляет: {}", BET, amount);
            }
        }
    }
}
