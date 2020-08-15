package tictactoe;

import java.util.Random;

public class Easy extends Player {

    public Easy(char playerSign) {
        super(playerSign);
    }

    @Override
    public char[] oneMove(char[] symbols, int emptyCoordinates) {
        System.out.println("Making move level \"easy\"");

        return randomMove(symbols, emptyCoordinates);
    }


    char[] randomMove(char[] symbols, int emptyCoordinates) {
        Random random = new Random();
        int emptyCoordinate = random.nextInt(emptyCoordinates); // from 0 to max 8

        int emptyIndex = 0;
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == '_') {
                emptyIndex++;
            }

            if (emptyIndex - 1 == emptyCoordinate) {
                symbols[i] = playerSign;
                break;
            }
        }

        return symbols;
    }
}
