package ru.geekbrains.lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class TicTacToeGui implements ActionListener {


    final int SIZE = 3;
    int FINISHSIZE = 3;
    int counter = 0;
    int currentMove = 0;
    char[][] field;//= {{' ', 'O', ' '}, {'X', 'O', ' '}, {' ', 'X', 'X'}};
    long minimaxMoves;
    long minimaxMlnCounter = 0;

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    boolean isOver = false;

    TicTacToeGui() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BaZ TicTacToe AI");
        frame.setSize(300, 300);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setResizable(false);

        textfield.setBackground(Color.black);
        textfield.setForeground(Color.white);
        textfield.setFont(new Font("Verdana", Font.BOLD, 30));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Try to win ;)");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 100, 100);
        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(Color.black);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Verdana", Font.PLAIN, 45));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.black);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        field = new char[SIZE][SIZE];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = ' ';
            }
        }

        RUN();

    }

    public static void main(String[] args) {

        TicTacToeGui TTT = new TicTacToeGui();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (counter == 9 || isOver) {
            restartClear();
        } else {

            for (int i = 0; i < 9; i++) {
                if (e.getSource() == buttons[i]) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(Color.white);
                        buttons[i].setText("X");
                        currentMove = i + 1;

                        placeSymbolSingleCoord(playerNames.USER);

                        if (checkTIE(counter)) {

                            if (checkWinner(currentMove, playerNames.USER)) {
                                System.out.println("You WIN");
                                textfield.setForeground(Color.green);
                                textfield.setText("You WIN");
                                isOver = true;
                                fieldPrint();
                                break;
                            }


                            System.out.println("Game over - TIE");
                            textfield.setForeground(Color.orange);
                            textfield.setText("Game over - TIE");
                            isOver = true;
                            fieldPrint();
                            break;
                        }


                        fieldPrint();

                        player1_turn = false;

                        PCturn();

                        if (checkWinner(currentMove, playerNames.PC)) {
                            System.out.println("PC WINS");
                            textfield.setForeground(Color.red);
                            textfield.setText("PC WINS");
                            isOver = true;
                            fieldPrint();
                            break;
                        }

                        if (checkTIE(counter)) {
                            System.out.println("Game over - TIE");
                            textfield.setForeground(Color.orange);
                            textfield.setText("Game over - TIE");
                            isOver = true;
                            fieldPrint();
                            break;
                        }

                        fieldPrint();

                    }
                }
            }
        }
    }

    public void RUN() {

        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("Try to win ;)");
        } else {
            player1_turn = false;
            //textfield.setText("AI turn");
            PCturn();
        }
    }

    void PCturn() {

        if (counter == 0) {
            do {
                currentMove = (int) (Math.random() * (SIZE * SIZE)) + 1;
            } while (!validPlaceChecker());
            placeSymbolSingleCoord(playerNames.PC);
        } else {
            pcAImove();
        }

        buttons[currentMove - 1].setForeground(Color.WHITE);
        buttons[currentMove - 1].setText("O");

        player1_turn = true;
    }

    void pcAImove() {
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

    int minimaxAI(char[][] field, int depth, boolean isMax, int bestI, int bestJ, int alpha, int beta) {
        int score;

        if (checkWinner(coordsToCurrentMove(bestI, bestJ), playerNames.USER)) {
            //fieldPrint();
            return -10 + depth;
        }
        if (checkWinner(coordsToCurrentMove(bestI, bestJ), playerNames.PC)) {
            return 10 + depth;
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
                            return depth + 10;
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
                            return depth - 10;
                        }

                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    boolean checkTIE(int counter) {

        return counter == field.length * field.length;
    }

    void placeSymbolSingleCoord(playerNames PN) {

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

    void placeSymboltwoCoords(int row, int column, playerNames PN) {

        char OvsX;
        OvsX = PN.gameSymbol;
        field[row][column] = OvsX;
        counter++;
    }

    boolean validPlaceChecker() {
        int column;
        int row;

        column = (currentMove % field.length) - 1;
        if (column == -1) column = field.length - 1;
        row = (int) Math.ceil((double) currentMove / (double) field.length) - 1;

        //System.out.println("Duplicating");
        return field[row][column] == ' ';
    }

    int coordsToCurrentMove(int row, int column) {
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

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
    }

    void fieldPrint() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
                System.out.print("");
                if (j < field.length - 1) System.out.print('|');
            }
            System.out.println();
        }
    }

    boolean checkWinner(int currentMove, playerNames PN) {

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
                    if (tmpSize == FINISHSIZE) {
                        return true;
                    }
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
                    if (tmpSize == FINISHSIZE) {
                        return true;
                    }
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

    void restartClear() {

        textfield.setForeground(Color.white);
        textfield.setText("Try to win ;)");

        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            button_panel.setBackground(Color.black);
        }
        counter = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = ' ';
            }
        }
        isOver = false;

        RUN();
    }

    private enum playerNames {
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