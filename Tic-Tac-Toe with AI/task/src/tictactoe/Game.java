package tictactoe;

public class Game {

    private static char[] symbols = new char[9]; // array for all 9 symbols

    private static int emptyCoordinates = 9;

    private static Player playerX = null;
    private static Player playerO;

    private static void play() {

        input(); // empty field

        output(); // print field

        char whoIsNow = 'X'; // who starts

        String result = "Game not finished";

        while ("Game not finished".equals(result)) {

            if (whoIsNow == 'X') {

                symbols = playerX.oneMove(symbols, emptyCoordinates);

                whoIsNow = 'O';

            } else {

                symbols = playerO.oneMove(symbols, emptyCoordinates);

                whoIsNow = 'X';
            }

            emptyCoordinates--;

            output();// print field


            result = check(); // give current result
        }

        System.out.println(result); // print result

        emptyCoordinates = 9;
    }

    // 0 - user; 1 - easy; 2 - medium; 3 - hard
    public static void whoPlaysAndPlay(int modeX, int modeO) {
        playerX = whoPlays(modeX, 'X');
        playerO = whoPlays(modeO, 'O');

        play();
    }

    private static Player whoPlays(int mode, char playerSign) {
        Player player;

        if (mode == 0) {
            player = new User(playerSign);
        } else if (mode == 1) {
            player = new Easy(playerSign);
        } else if (mode == 2){
            player = new Medium(playerSign);
        } else if (mode == 3) {
            player = new Hard(playerSign);
        } else {
            System.out.println("Exception");
            player = new Easy(playerSign);
        }

        return player;
    }


    private static void input() {
        for (int i = 0; i < symbols.length ; i++) {
            symbols[i] = "_________".charAt(i);
        }
    }

    private static void output() {

        System.out.println("---------");
        int index = 0;
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(symbols[index++] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static String check() {

        int amountX = 0;
        int amountO = 0;

        int xInLine = 0;
        int oInLine = 0;

        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == 'X') {
                amountX++;

                if ((i % 3 == 0 && (symbols[i + 1] == 'X' && symbols[i + 2] == 'X')) ||
                        (i < 3 && (symbols[i + 3] == 'X' && symbols[i + 6] == 'X'))) {
                    xInLine++;
                }

            } else if (symbols[i] == 'O') {
                amountO++;

                if ((i % 3 == 0 && (symbols[i + 1] == 'O' && symbols[i + 2] == 'O')) ||
                        (i < 3 && (symbols[i + 3] == 'O' && symbols[i + 6] == 'O'))) {

                    oInLine++;
                }
            }
        }

        if ((symbols[0] == 'X' && symbols[4] == 'X' && symbols[8] == 'X') ||
                (symbols[2] == 'X' && symbols[4] == 'X' && symbols[6] == 'X')) {
            xInLine++;
        } else if ((symbols[0] == 'O' && symbols[4] == 'O' && symbols[8] == 'O') ||
                (symbols[2] == 'O' && symbols[4] == 'O' && symbols[6] == 'O')){
            oInLine++;
        }

        String result;

        if (Math.abs(amountX - amountO) >= 2) {
            result = "Impossible";
        } else if (xInLine > 0 && oInLine > 0) {
            result = "Impossible";
        } else if (xInLine > 0) {
            result = "X wins";
        } else if (oInLine > 0) {
            result = "O wins";
        } else if (amountX + amountO < symbols.length) {
            result = "Game not finished";
        } else {
            result = "Draw";
        }

        return result;
    }
}
