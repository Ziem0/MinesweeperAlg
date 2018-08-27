package com.algorithms.minesweeper;

import java.util.Optional;
import java.util.Random;

public class Minesweeper {

    private char[][] board;
    private int rows;
    private int columns;
    private int minesAmount;
    private String name;

    public Minesweeper(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = new char[rows][columns];
        this.minesAmount = Math.round((rows * columns) / 4);
    }

    public void fillBoardWithDots() {
        for (int i = 0; i < rows; i++) {
            for (int y = 0; y < columns; y++) {
                board[i][y] = '.';
            }
        }
    }

    public void addRandomMinesOnBoard() {
        while (minesAmount > 0) {
            int randomX = new Random().nextInt(rows);
            int randomY = new Random().nextInt(columns);
            if (board[randomX][randomY] != '*') {
                board[randomX][randomY] = '*';
                minesAmount--;
            }
        }
    }

    public void changeBoard() {
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                if (board[y][x] != '*') {
                    board[y][x] = calculateDistance(y,x);
                }
            }
        }
    }

    private char calculateDistance(int y, int x) {
        int[] v = new int[]{1, -1, 0};
        char distance = 48;
        for (int a : v) {
            for (int b : v) {
                if (getChar(y + a, x + b).isPresent() && getChar(y + a, x + b).get() == '*') {
                    distance++;
                }
            }
        }
        return distance;
    }

    private Optional<Character> getChar(int row, int column) {
        Character character = null;
        try {
            character = this.board[row][column];
        } catch (IndexOutOfBoundsException e) {

        }
        return Optional.ofNullable(character);
    }

    public void print() {
        for (char[] row : board) {
            for (char box : row) {
                System.out.print(box + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Minesweeper m = new Minesweeper(7, 5);
        m.fillBoardWithDots();
        m.addRandomMinesOnBoard();
        m.changeBoard();
        m.print();
    }
}
