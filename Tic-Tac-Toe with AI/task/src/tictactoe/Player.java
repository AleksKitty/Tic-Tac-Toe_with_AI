package tictactoe;

public abstract class Player {
    // parent for all type of players

    public char playerSign;

    public Player(char playerSign) {
        this.playerSign = playerSign;
    }

    public abstract char[] oneMove(char[] symbols, int emptyCoordinates);
}
