package tictactoe;

import java.util.Scanner;

public class User extends Player {
    private static final Scanner scanner = new Scanner(System.in);

    public User(char playerSign) {
        super(playerSign);
    }

    public char[] oneMove(char[] symbols, int emptyCoordinates) {
        int a;
        int b;
        int index;
        boolean rightCoordinates = false;

        System.out.print("Enter the coordinates: ");
        String coordinates = scanner.nextLine();

        while (!rightCoordinates) {
            try {
                if (coordinates.length() == 3) {
                    a = Integer.parseInt(String.valueOf(coordinates.charAt(0)));
                    b = Integer.parseInt(String.valueOf(coordinates.charAt(2)));

                    index = Math.abs(b - 3) * 3 + (a - 1);

                    if (a > 3 || a < 1 || b > 3 || b < 1) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else if (symbols[index] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        symbols[index] = playerSign;
                        rightCoordinates = true;
                    }
                } else {
                    System.out.println("You should enter numbers!");
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println("You should enter numbers!");
            }

            if (!rightCoordinates) {
                System.out.print("Enter the coordinates: ");
                coordinates = scanner.nextLine();
            }
        }

        return symbols;
    }
}
