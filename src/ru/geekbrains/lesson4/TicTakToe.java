package ru.geekbrains.lesson4;

import java.util.Scanner;

public class TicTakToe {


    private static final int SIZE = 3;
    private static final int FINISHSIZE = 3;
    private static int counter = 0;
    private static int currentMove = 0;
    private static char[][] field;//= {{' ', 'O', ' '}, {'X', 'O', ' '}, {' ', 'X', 'X'}};
    private static long minimaxMoves;
    private static long minimaxMlnCounter = 0;

    public static void main(String[] args) {

        field = new char[SIZE][SIZE];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = ' ';
            }
        }

        while (true) {


            do {
                makeYourMove();
            } while (!validPlaceChecker());

            placeSymbolSingleCoord(playerNames.USER);

            if (checkTIE(counter)) {
                System.out.println("Game over - TIE");
                fieldPrint();
                break;
            }

            if (checkWinner(currentMove, playerNames.USER)) {
                System.out.println("USER WINS");
                fieldPrint();
                break;
            }

            if (SIZE >= 5) {
                do {
                    currentMove = (int) (Math.random() * (SIZE * SIZE)) + 1;
                } while (!validPlaceChecker());
                placeSymbolSingleCoord(playerNames.PC);
            } else {
                pcAImove();
            }

            System.out.println("AI rounds: " + minimaxMoves);
            minimaxMoves = 0;

            if (checkTIE(counter)) {
                System.out.println("Game over - TIE");
                fieldPrint();
                break;
            }

            //fieldPrint();
            if (checkWinner(currentMove, playerNames.PC)) {
                System.out.println("PC WINS");
                fieldPrint();
                break;
            }
            fieldPrint();


        }
    }

    public static void pcAImove() {
        int score;
        int bestI = 0;
        int bestJ = 0;
        int depth = 0;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == ' ') {
                    field[i][j] = playerNames.PC.gameSymbol;
                    counter++;
                    score = minimaxAI(field, depth, false, i, j, alpha, beta);

                    field[i][j] = ' ';
                    counter--;

                    if (score > bestScore) {
                        bestScore = score;
                        bestI = i;
                        bestJ = j;
                    }

                }
            }
        }
        placeSymboltwoCoords(bestI, bestJ, playerNames.PC);
        currentMove = coordsToCurrentMove(bestI, bestJ);
    }

    public static int minimaxAI(char[][] field, int depth, boolean isMax, int bestI, int bestJ, int alpha, int beta) {
        int score;
        minimaxMoves++;

        if (minimaxMoves > 100000000) {
            minimaxMoves = 0;
            minimaxMlnCounter++;
            fieldPrint();
            System.out.println(minimaxMlnCounter + " 100mln");
        }


        if (checkWinner(coordsToCurrentMove(bestI, bestJ), playerNames.PC)) {
            return 10 + depth;
        }
        if (checkWinner(coordsToCurrentMove(bestI, bestJ), playerNames.USER)) {
            //fieldPrint();
            return -10 + depth;
        }
        if (checkTIE(counter)) {
            return 0;
        }

        if (isMax) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    if (field[i][j] == ' ') {
                        field[i][j] = playerNames.PC.gameSymbol;
                        counter++;
                        score = minimaxAI(field, depth + 1, false, i, j, alpha, beta);
                        field[i][j] = ' ';
                        counter--;

                        alpha = Math.max(alpha, score);
                        if (beta <= alpha) {
                            return depth+10;
                        }

                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    if (field[i][j] == ' ') {
                        field[i][j] = playerNames.USER.gameSymbol;
                        counter++;
                        score = minimaxAI(field, depth + 1, true, i, j, alpha, beta);
                        field[i][j] = ' ';
                        counter--;

                        beta = Math.min(beta, score);
                        if (beta <= alpha) {
                            return depth-10;
                        }

                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }


    public static void makeYourMove() {
        Scanner scan = new Scanner(System.in);


        do {
            System.out.print("Your move (1-" + SIZE * SIZE + "): ");

            while (!scan.hasNextInt()) {
                System.out.print("INT value PLEASE!: ");
                scan.next();
            }
            currentMove = scan.nextInt();
        } while (currentMove < 0 || currentMove > SIZE * SIZE);
    }

    public static void placeSymbolSingleCoord(playerNames PN) {

        int row;
        int column;

        char OvsX;

        OvsX = PN.gameSymbol;

        // Динамически ищу координаты для возможности расширения поля
        column = (currentMove % field.length) - 1;
        if (column == -1) column = field.length - 1;
        row = (int) Math.ceil((double) currentMove / (double) field.length) - 1;

        field[row][column] = OvsX;
        counter++;
    }

    public static void placeSymboltwoCoords(int row, int column, playerNames PN) {

        char OvsX;
        OvsX = PN.gameSymbol;
        field[row][column] = OvsX;
        counter++;
    }

    private static boolean validPlaceChecker() {
        int column;
        int row;

        column = (currentMove % field.length) - 1;
        if (column == -1) column = field.length - 1;
        row = (int) Math.ceil((double) currentMove / (double) field.length) - 1;

        //System.out.println("Duplicating");
        return field[row][column] == ' ';
    }

    public static boolean checkTIE(int counter) {

        return counter == field.length * field.length;

    }

    public static int coordsToCurrentMove(int row, int column) {
        int tmp = 0;
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                {
                    tmp++;
                    if (i == row && j == column) {
                        return tmp;
                    }
                }
            }
        return tmp;
    }


    public static boolean checkWinner(int currentMove, playerNames PN) {

        int row;
        int column;
        int tmpSize = 0;


        column = (currentMove % field.length) - 1;
        if (column == -1) {
            column = field.length - 1;
        }
        row = (int) Math.ceil((double) currentMove / (double) field.length) - 1;


        // 4 блока с идентичным алгоритмом
        // на примере горизонта: прыгаю от поставленного символа влево на "-3" и иду до "+3" считая количество подряд идущих символов

        // Horizon
        for (int c = column - FINISHSIZE + 1; c < column + FINISHSIZE + 1; c++) {
            if (c >= 0 && c < field.length) {
                if (field[row][c] == PN.gameSymbol && field[row][c] != ' ') {
                    tmpSize++;
                    if (tmpSize == FINISHSIZE) return true;
                } else {
                    tmpSize = 0;
                }
            }
        }

        // Vertical
        tmpSize = 0;
        for (int r = row - FINISHSIZE + 1; r < row + FINISHSIZE + 1; r++) {
            if (r >= 0 && r < field.length) {
                if (field[r][column] == PN.gameSymbol && field[r][column] != ' ') {
                    tmpSize++;
                    if (tmpSize == FINISHSIZE) return true;
                } else {
                    tmpSize = 0;
                }
            }
        }


        // RightDiagonal
        tmpSize = 0;
        int c = column - FINISHSIZE + 1;
        for (int r = row - FINISHSIZE + 1; r < row + FINISHSIZE + 1; r++) {
            if (r >= 0 && r < field.length && c >= 0 && c < field.length) {
                if (field[r][c] == PN.gameSymbol && field[r][c] != ' ') {
                    tmpSize++;
                    if (tmpSize == FINISHSIZE) return true;
                } else {
                    tmpSize = 0;
                }
            }
            c++;
        }

        // LeftDiagonal
        tmpSize = 0;
        int r = row + FINISHSIZE - 1;
        for (c = column - FINISHSIZE + 1; c < column + FINISHSIZE + 1; c++) {
            if (c >= 0 && c < field.length && r >= 0 && r < field.length) {
                if (field[r][c] == PN.gameSymbol && field[r][c] != ' ') {
                    tmpSize++;
                    if (tmpSize == FINISHSIZE) return true;
                } else {
                    tmpSize = 0;
                }
            }
            r--;
        }

        return false;
    }

    public static void fieldPrint() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
                System.out.print("");
                if (j < field.length - 1) System.out.print('|');
            }
            System.out.println();

            // Рисование горизонтального разделителя
            //if (i < field.length - 1) {
            //for (int k=0;k<field.length-1;k++){
            //System.out.print("-+");
            //}
            //System.out.println();
            //}
        }
    }

    private static enum playerNames {
        USER('X'), PC('O');
        char gameSymbol;

        playerNames(char gamerSymbol) {
            this.gameSymbol = gamerSymbol;
        }

        int getSymbol() {
            return gameSymbol;
        }
    }

}
