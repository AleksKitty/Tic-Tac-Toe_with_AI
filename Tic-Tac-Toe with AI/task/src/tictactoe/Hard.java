package tictactoe;

import java.util.ArrayList;
import java.util.HashMap;

public class Hard extends Medium {
    private char opponentSign;

    public Hard(char playerSign) {
        super(playerSign);
    }

    @Override
    public char[] oneMove(char[] symbols, int emptyCoordinates) {
        System.out.println("Making move level \"hard\"");

        opponentSign = playerSign == 'X' ? 'O' : 'X';

        ResultMiniMax resultMiniMax = miniMax(symbols, playerSign);

        symbols[resultMiniMax.index] = playerSign;

        return symbols;
    }

    private ResultMiniMax miniMax(char[] newBoard, char whoPlays) {

        ArrayList<Integer> availableCoordinates = getAvailableCoordinates(newBoard);


        // check if terminate state
        if (availableCoordinates.size() <= 6) {
            // we will calculate right index later
            if (winning(newBoard, playerSign)) {
                return new ResultMiniMax(10);
            } else if (winning(newBoard, opponentSign)) {
                return new ResultMiniMax( -10);
            } else if (availableCoordinates.size() == 0) {
                return new ResultMiniMax(0);
            }
        }

        HashMap<Integer, Integer> moves = new HashMap<>();

        // go through empty spot
        for (Integer spot : availableCoordinates) {
            newBoard[spot] = whoPlays;

            if (whoPlays == playerSign) {

                moves.put(spot, miniMax(newBoard, opponentSign).score);

            } else {

                moves.put(spot, miniMax(newBoard, playerSign).score);

            }

            newBoard[spot] = '_';
        }

        // evaluate result
        int bestMove = -1;
        if (whoPlays == playerSign) {
            int max = Integer.MIN_VALUE;

            for (Integer key : moves.keySet()) {
                if (moves.get(key) > max) {
                    max = moves.get(key);
                    bestMove = key;
                }
            }
        } else {
            int min = Integer.MAX_VALUE;


            for (Integer key : moves.keySet()) {
                if (moves.get(key) < min) {
                    min = moves.get(key);
                    bestMove = key;
                }
            }
        }

        return new ResultMiniMax(bestMove, moves.get(bestMove));
    }


    private ArrayList<Integer> getAvailableCoordinates(char[] symbols) {
        ArrayList<Integer> availableCoordinates = new ArrayList<>();

        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == '_') {
                availableCoordinates.add(i);
            }
        }

        return availableCoordinates;
    }

    private boolean winning(char[] board, char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }

    static class ResultMiniMax {
        int index = -1;
        int score;

        public ResultMiniMax(int score) {
            this.score = score;
        }

        public ResultMiniMax(int index, int score) {
            this.index = index;
            this.score = score;
        }
    }
}
