package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {


    private static boolean checkFieldState(char[][] field, boolean result) {

        if (checkLine(field, 'X') && checkLine(field, 'O')) {
            System.out.println("Impossible");
            result = true;
        } else if (checkLine(field, 'X')) {
            System.out.println("X wins");
            result = true;
        } else if (checkLine(field, 'O')) {
            System.out.println("O wins");
            result = true;
        } else if (Math.abs(countChar(field, 'X') - countChar(field, 'O')) > 1) {
            System.out.println("Impossible");
            result = true;
        } else if (isFull(field)) {
            System.out.println("Draw");
            result = true;
        } else {
//            System.out.println("Game not finished");
            result = false;
        }
        return result;


    }

    private static void showTheField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[1].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }


    public static boolean checkLine(char[][] arrCh, char ch) {
        for (int i = 0; i < arrCh.length; i++) {
            if ((arrCh[i][0] == ch && arrCh[i][1] == ch && arrCh[i][2] == ch) ||
                    (arrCh[0][i] == ch && arrCh[1][i] == ch && arrCh[2][i] == ch)) {
                return true;
            }
        }
        return (arrCh[0][0] == ch && arrCh[1][1] == ch && arrCh[2][2] == ch) ||
                (arrCh[2][0] == ch && arrCh[1][1] == ch && arrCh[0][2] == ch);
    }

    public static boolean isFull(char[][] arrCh) {
        boolean full = true;
        int empty = 0;
        for (int i = 0; i < arrCh.length; i++) {
            for (int j = 0; j < arrCh[1].length; j++) {
                if (arrCh[i][j] == ' ') {
                    ++empty;
                }
            }
        }
        if(empty > 0){
            full = false;
        }
        return full;
    }

    public static int countChar(char[][] arrCh, char ch) {
        int count = 0;
        for (int i = 0; i < arrCh.length; i++) {
            for (int j = 0; j < arrCh[i].length; j++) {
                if (arrCh[i][j] == ch) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void inputCoordinates(Scanner sc, char[][] field, char player) {
        boolean goodInput = false;

        while (!goodInput) {
            System.out.print("Enter the coordinates:");

            try {
                int xInput = sc.nextInt();
                int yInput = sc.nextInt();

                if (xInput - 1 >= 0 && xInput - 1 <= field.length - 1 && yInput - 1 >= 0 && yInput - 1 <= field[1].length - 1) {
//                    switch (player){
//                        case "playerOne":
//                        case "playerTwo":
//                    }
                    if (field[field.length - yInput][xInput - 1] != 'X' && field[field.length - yInput][xInput - 1] != 'O') {
                        field[field.length - yInput][xInput - 1] = player;
                        goodInput = true;
                       showTheField(field);
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }

                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }


            } catch (InputMismatchException e) {
                sc.next(); /*need to remember this little fella*/
                System.out.println("You should enter numbers");

            }

        }
    }

    public static void main(String[] args) {

        char playerOne = 'X';
        char playerTwo = 'O';

        Scanner sc = new Scanner(System.in);

        char[][] field = new char[3][3];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[1].length; j++) {
                field[i][j] = ' ';
            }

        }

        boolean result = false;

        char player = playerOne;
        showTheField(field);

        while (!result) {


            inputCoordinates(sc, field, player);

            if (checkFieldState(field, result)) {
                result = true;
            }

            if(player == playerOne){
                player = playerTwo;
            }else {
                player = playerOne;
            }


        }


    }
}
