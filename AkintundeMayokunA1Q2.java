/**
 * Name of class or program: AkintundeMayokunA1Q2
 *
 * COMP 2140 SECTION D01
 * ASSIGNMENT Assignment 1, question 2
 * @author Mayokun Moses Akintunde, 7884253
 * @version 23rd of May, 2021
 *
 * PURPOSE: The program is aimed at solving sudoku boards of a valid size.
 */
import java.util.Scanner;
public class AkintundeMayokunA1Q2 {
    public static void main(String[] args) {
        System.out.println("Please enter a game board, row by row, with - to represent empty cells and spaces between each cell:");
        Scanner input = new Scanner(System.in);
        String inputter = input.nextLine();
        processing(inputter);
        System.out.println("Program terminated normally.");
    }
    /**
     * The processing method is used to validate if the board inputted is an actual sudoku board and then execute an output.
     * @param inputter : The input in form of a string
     */
    private static void processing(String inputter){ 
            Sudoku a1 = new Sudoku(inputter);
            System.out.println("The original board is:");
            System.out.println(a1);
            a1.solve();
            System.out.println("The solution is:");
            System.out.println(a1);
    }
}


class Sudoku {
    private final int SIZE = 9;
    private int column;
    private int row;
    private int[][] grid;

    public Sudoku(String input) {
            String[] table = input.split(" ");
            this.column = (int) Math.sqrt(table.length);
            this.row = (int) Math.sqrt(table.length);
            int[][] sudokugrid = new int[row][column];
            int count =0;
            processing(table, sudokugrid);
            for(int i =0; i<sudokugrid.length; i++){
                for(int j =0; j < sudokugrid[i].length; j++){
                    if(sudokugrid[i][j] > row){
                        count++;
                    }
                 }
            }
            if(count==0 && Math.sqrt(input.split(" ").length) - Math.floor(Math.sqrt(input.split(" ").length)) ==0){ //if the numbers inputted are not greater than row and the size is a perfect square
                this.grid = sudokugrid;
            } else {
                this.row = SIZE;
                this.column = SIZE;
                this.grid = new int[row][column];
            }

    }
    /**
     * The processing method is used to perform every necessary action needed to convert input to sudoku board
     *
     * @param table      : the input in form a table
     * @param sudokugrid : the 2d form of the table which is also known as sudoku board.
     */
    private void processing(String[] table, int[][] sudokugrid) {
        for (int i = 0; i < table.length; i++) {
            if (table[i].compareTo("-") == 0) {
                table[i] = "0";
            }
        }
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                sudokugrid[i][j] = Integer.parseInt(table[(i * this.column) + j]);
            }
        }
    }


    /**
     *The ColumnValid method is an algorithm used to check if the try number can be inserted in the column.
     * @param colCurrent : the column number of the cell
     * @param test : the try number you want to run and see if it passes the constraints
     * @return if the number can be inserted in the column, return true.
     */
    private boolean columnValid(int colCurrent, int test){
        boolean answer = true;
        for(int i =0; i < this.column; i++){
            if(grid[i][colCurrent] == test){
                answer =false;
            }
        }
        return answer;
    }

    /**
     * The rowValid method is an algorithm used to check if the try number can be inserted in the row.
     * @param rowCurrent : the row number of the cell
     * @param test : the try number you want to run and see if it passes the constraints
     * @return if the number can be inserted in the row, return true.
     */
    private boolean rowValid(int rowCurrent, int test){
        boolean answer = true;
        for(int i =0; i < this.row; i++){
            if(grid[rowCurrent][i] == test){
                answer = false;
            }
        }
        return answer;
    } // worked

    /**
     * The gridValid method is an algorithm used to check if the try number can be inserted in the grid
     * @param row : the row number of the cell
     * @param col : the column number of the cell
     * @param test : the try number you want to run and see if it passes the constraints
     * @return if the number can be inserted in the grid, return true.
     */

    private boolean gridValid(int row, int col, int test){
        boolean answer = true;
        int box  = (int) Math.sqrt(this.row);
        int rowGridStart = (row/box)*box;
        int colGridStart = (col/box)*box;
        for(int i =0; i <Math.sqrt(this.row); i++) {
            for (int j = 0; j < Math.sqrt(this.column); j++) {
                if(grid[rowGridStart+i][colGridStart + j] == test){
                    int a  = rowGridStart+i;
                    int b = colGridStart + j;
                    answer = false;
                }
            }
        }
        return answer;
    }

    /**
     * The checkValid method is an algorithm that checks if the try number is not in the grid, column and row.
     * @param row : the row number of the cell
     * @param col : the column number of the cell
     * @param test : the try number you want to run and see if it passes the constraints
     * @return if the try number passes all constraints then the try number is valid
     */
    private boolean checkValid(int row, int col, int test){
        boolean answer = false;
        if(columnValid(col,test)){
            if(rowValid(row, test)){
                if(gridValid(row,col,test)){
                    answer = true;
                }
            }
        }
        return answer;
    }

    /** The solve method is a method that calls the algorithm that is used to solve any sudoku object. */
    public void solve(){
        solveAlgo();
    }

    /**
     * The solveAlgo is the algorithm used to solve any sudoku object.
     * @return Yes if the algorithm solves a provided sudoku board if and
     * No if does not the algorithm solves a provided sudoku board
     */
    private boolean solveAlgo(){
        for (int i =0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if(grid[i][j] ==0) {
                    for(int k = 1; k<this.row+1; k++) {
                        if(checkValid(i,j,k)){
                            grid[i][j] = k; // insert the try number into the board.
                            if(!solveAlgo()){
                                grid[i][j] = 0;
                            } // base case: if the algorithm does not solve a sudoku board with the try number present in the sudoku board, try another that is valid. if all valid numbers fails the algoritm then sudoku board cannot be solved.
                            else {
                                return true;
                            } // continue looping till the end of the sudoku board recursively as far as all the numbers tried pass the algorithm test
                        } // try each of the numbers created by the above loop to see if it can be fitted into the sudoku board. If not, discard number.
                    } // create numbers from 1 to the length of the row of the sudoku board inputted
                    return false; // if the algorithm fails
                } // if a cell is empty while the algorithm is looping around the sudoku board
            } // take each column of the sudoku board
        } // take each row of the sudoku board
        return true; // if all algorithm runs successfully.
    }

    /** The print out/ output for the Sudoku class     */
    public String toString(){
        String answer = "";
        for (int a = 0; a < row; a++){
            for(int b =0; b < column; b++){
                answer += grid[a][b] +" ";
            }
            answer += "\n";
        }
        return answer;
    }
    /**
     *
     * 1) Why did you choose to store the grid the way that you did? (1D vs 2D array, chars vs ints)
     * Answer: I stored my grid in 2D arrays because it is easier to use two for loops to access all data
     * needed or two indexes to access any data in the board. I used ints because it is easier to use
     * mathematical concepts such as " + , -, <, >, = " rather than use methods, ascii numbers etc
     * when working with chars.
     * 2) As you were developing your program, were there any approaches that you considered that
     * you rejected as inefficient (e.g. would make many unnecessary recursive calls)? If yes, explain.
     * If no, explain how you know your initial approach is most efficient.
     * Answer: I was trying to follow the steps in the assignment booklets which advised that we use while loop
     * "while there are empty cells". I realised that my program was running using this while loop but i did
     * not get an output. I then implemented a for loop and it worked. I expect the while loop and for loop
     * to run the same way however, i think it is not the best option in this case since i am doing several
     * recursion so it is taking more time.
     *
     */





}

