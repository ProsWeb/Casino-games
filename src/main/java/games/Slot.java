package games;

import static java.lang.System.out;

public class Slot {

    public static void main(String... __) {

        final int pullCounter = 100;
        final int counterSize = 7;
        final int bet = 10;
        final int prize = 1_000;

        int amount = 100;

        int firstCounter = 1;
        int secondCounter = 1;
        int thirdCounter = 1;

        while (amount > 0) {
            out.printf("У Вас %d$, ставка - %d$%n", amount, bet);
            out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");

            firstCounter = (firstCounter + (int) Math.round(Math.random() * pullCounter)) % counterSize;
            secondCounter = (secondCounter + (int) Math.round(Math.random() * pullCounter)) % counterSize;
            thirdCounter = (thirdCounter + (int) Math.round(Math.random() * pullCounter)) % counterSize;

            out.printf("первый барабан - %d, второй - %d, третий - %d%n",
                        firstCounter, secondCounter, thirdCounter);

            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                amount +=  prize;
                out.printf("Выигрыш %d$, ваш капитал теперь составляет: %d$%n", prize, amount);
            } else {
                amount -= bet;
                out.printf("Проигрыш %d$, ваш капитал теперь составляет: %d$%n", bet, amount);
            }
        }
    }
}