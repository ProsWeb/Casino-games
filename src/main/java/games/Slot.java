package games;

import static java.lang.System.out;

public class Slot {

    private static final int PULL_COUNTER = 100;

    private static final int COUNTER_SIZE = 7;

    private static final int BET = 10;

    private static final int PRIZE = 1_000;

    public static void main(String... __) {

        int amount = 100;

        int firstCounter = 1;

        int secondCounter = 1;

        int thirdCounter = 1;

        while (amount > 0) {
            out.printf("У Вас %d$, ставка - %d$%n", amount, BET);
            out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");

            firstCounter = (firstCounter + (int) Math.round(Math.random() * PULL_COUNTER)) % COUNTER_SIZE;
            secondCounter = (secondCounter + (int) Math.round(Math.random() * PULL_COUNTER)) % COUNTER_SIZE;
            thirdCounter = (thirdCounter + (int) Math.round(Math.random() * PULL_COUNTER)) % COUNTER_SIZE;

            out.printf("первый барабан - %d, второй - %d, третий - %d%n",
                        firstCounter, secondCounter, thirdCounter);

            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                amount += PRIZE;
                out.printf("Выигрыш %d$, ваш капитал теперь составляет: %d$%n", PRIZE, amount);
            } else {
                amount -= BET;
                out.printf("Проигрыш %d$, ваш капитал теперь составляет: %d$%n", BET, amount);
            }
        }
    }
}