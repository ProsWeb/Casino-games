package games;

import java.io.IOException;

import static java.lang.System.*;

public class Choice {

    public static void main(String... __) throws IOException {

        out.println("Выберите игру:\n1 - \"однорукий бандит\", 2 - \"пьяница\"");
        switch (in.read()) {
            case '1':
                Slot.main();
                break;
            case '2':
                Drunkard.main();
                break;
            default:
                out.println("Игры с таким номером нет!");
        }
    }
}
