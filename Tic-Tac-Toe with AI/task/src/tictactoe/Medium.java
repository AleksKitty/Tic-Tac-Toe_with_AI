package tictactoe;

import java.util.Arrays;

public class Medium extends Easy {

    public Medium(char playerSign) {
        super(playerSign);
    }

    @Override
    public char[] oneMove(char[] symbols, int emptyCoordinates) {
        System.out.println("Making move level \"medium\"");


        char opponentSign = playerSign == 'X' ? 'O' : 'X';


        int indexWhereMoving = -1;

        if (emptyCoordinates <= 6) {

            // to win
            indexWhereMoving = tryToWin(playerSign, symbols);

            if (indexWhereMoving >= 0) {
                symbols[indexWhereMoving] = playerSign;
            } else {
                // not to lose
                indexWhereMoving = tryToWin(opponentSign, symbols);

                if (indexWhereMoving >= 0) {
                    symbols[indexWhereMoving] = playerSign;
                }
            }
        }

        if (indexWhereMoving < 0) {
            symbols = randomMove(symbols, emptyCoordinates);
        }

        return symbols;
    }

     int checkToWin(char sign, char[] symbols, int shiftRow, int kPlus, int shiftPlus) {

        int [] indexes = new int[3];


        int shift = 0;
        int amountInRow = 0;

        int zeroIndex = -1;


        for (int j = 0; j < 3; j++) {
            for (int k = shift; k < shift + shiftRow; k += kPlus) {
                if (k == shift) {
                    zeroIndex = k;
                }


                if (symbols[k] == sign) {

                    // start with 0
                    if (shiftRow == 3) { // lines
                        indexes[k - shift] = 1;
                    } else if (shiftRow == 7 || shiftRow == 5) { // columns && second diagonal
                        indexes[k / 3] = 1;
                    } else if (shiftRow == 9) { // first diagonal
                        indexes[k / 4] = 1;
                    }

                    amountInRow++;
                } else if (symbols[k] != '_') {
                    break;
                }


                if (amountInRow == 2) {
                    if (indexes[0] == 0) {
                        return zeroIndex;
                    } else if (indexes[1] == 0) {
                        return zeroIndex + kPlus;
                    } else if (symbols[zeroIndex + 2 * kPlus] == '_') {
                        return zeroIndex + 2 * kPlus;
                    }
                }
            }

            Arrays.fill(indexes, 0);
            shift += shiftPlus;
            amountInRow = 0;

            // diagonals
            if (shiftRow == 9) {
                j++; // only two diagonals
                kPlus /= 2;
                shiftRow = 5;
            }
        }

        return -1;
    }


     private int tryToWin(char sign, char[] symbols) {
        // lines
        int indexWhereMoving = checkToWin(sign, symbols, 3, 1, 3);

        // columns
        if (indexWhereMoving < 0) {
            indexWhereMoving = checkToWin(sign, symbols, 7, 3,  1);

        }

        // diagonals
        if (indexWhereMoving < 0) {
            indexWhereMoving = checkToWin(sign, symbols, 9, 4,  2);

        }

        return indexWhereMoving;
    }
}
