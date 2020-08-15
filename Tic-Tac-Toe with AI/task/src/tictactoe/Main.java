package tictactoe;
import java.util.Scanner;

import static tictactoe.Game.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean inGame = true;
        // 0 - user; 1 - easy; 2 - medium; 3 - hard
        int modeX = -1;
        int modeO = -1;

        printRulesOfTheGame();

        while (inGame) {
            System.out.print("Input command: ");
            String command = scanner.nextLine().trim().replaceAll("\\s+", " ");

            if (command.startsWith("start")) {
                if (command.contains("user") && command.indexOf("user") <= 6) {
                    modeX = 0;
                }

                if (command.contains("user") && command.lastIndexOf("user") >= 10){
                    modeO = 0;
                }

                if (command.contains("easy") && command.indexOf("easy") <= 6) {
                    modeX = 1;
                }

                if (command.contains("easy") && command.lastIndexOf("easy") >= 10){
                    modeO = 1;
                }

                if (command.contains("medium") && command.indexOf("medium") <= 6) {
                    modeX = 2;
                }

                if (command.contains("medium") && command.lastIndexOf("medium") >= 10){
                    modeO = 2;
                }

                if (command.contains("hard") && command.indexOf("hard") <= 6) {
                    modeX = 3;
                }

                if (command.contains("hard") && command.lastIndexOf("hard") >= 10){
                    modeO = 3;
                }
            } else if (command.startsWith("exit")) {
                inGame = false;
            }


            if (inGame) {
                if (modeX == -1 || modeO == -1) {
                    System.out.println("Bad parameters");
                } else {
                    whoPlaysAndPlay(modeX, modeO);
                    modeX = -1;
                    modeO = -1;
                }
            }
        }

        scanner.close();
    }


    private static void printRulesOfTheGame() {
        System.out.println("Rules of the Tic-Tac-Toe game.");

        System.out.println("\nThere are four types of players:");
        System.out.println("1) user");
        System.out.println("2) computer easy");
        System.out.println("3) computer medium");
        System.out.println("4) computer hard");

        System.out.println("\nTo start, please, input, for example, \"start easy medium\"");

        System.out.println("\nCoordinates of the board:");
        printCoordinates();

        System.out.println("\nTo exit, please, input \"exit\"\n\n");
    }

    private static void printCoordinates() {
        for (int i = 3; i >= 1; i--) {
            for (int j = 1; j <= 3; j++) {
                System.out.print(j + " " + i + "   ");
            }
            System.out.println();
        }
    }
}
