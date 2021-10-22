/**
 * Name of class or program: AkintundeMayokunA3Q1.java
 * COMP 2140 SECTION D01
 * ASSIGNMENT    Assignment 3, question 1
 * @author       Mayokun Moses Akintunde, 7884253
 * @version      12/07/2021
 *
 * PURPOSE: The program is supposed solve a maze.
 */
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class AkintundeMayokunA3Q1 {
    public static void main(String[] args) {
        System.out.println("Please enter the input file name (.txt files only):");
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        String filename = input.nextLine();
        System.out.println("Processing "+ filename +"...");
         Maze maze = new Maze(filename);
         System.out.println("The initial maze is:");
         System.out.println(maze);
         System.out.println("The path found using a stack is:");
         maze.stackSearch();
         System.out.println(maze);
         System.out.println("Path from start to finish: "+maze.displayStack());
         maze.resetMaze();
         System.out.println("The path found using a queue is:");
         maze.queueSearch();
         System.out.println(maze);
        System.out.println("Path from start to finish: "+maze.displayQueue());
        System.out.println("Processing terminated normally.");
        //C:\Users\akint\IdeaProjects\moses\src\notes.txt
    }
}

class Maze{
    private int row; // number of rows of maze.
    private int column; // number of columns of maze
    private Position[][] positions; // the maze in 2D.
    private Stack path = new Stack(); // stack we were using to save directions.
    private Queue queue; // queue we were using to save directions.

    public Maze(String filename){
        try {
            FileReader inputTxt = new FileReader(new File(filename));
            Scanner file = new Scanner(inputTxt);
            String[] size = file.nextLine().split(" ");
            this.row = Integer.parseInt(size[0]);
            this.column = Integer.parseInt(size[1]);
            positions = new Position[row][column];
            queue = new Queue(this.row * this.column);
            String[] lines = new String[1000];
            int count = 0;
            while (file.hasNext()){
                lines[count++] = file.nextLine();
            }
            insert(lines);
        } catch (Exception e) {
            System.out.println("File not found");
        }
    } //Constructor

    /**
     * insert - it is used to create the maze in 2D.
     * @param line - each line from the file
     */
    public void insert(String[] line){
        for(int i = 0; i < row; i ++){
            for (int j =0; j < column; j++){
                positions[i][j] = new Position(i,j,line[i].charAt(j));
            }
        }
    }

    /**
     * start() is the position of the start
     * @return - retutns the position of the start
     */

    private Position start(){
        Position answer = null;
        for(int i =0; i < row; i++){
            for(int j=0; j<column; j++){
                if(positions[i][j].pSymbol().compareTo("S") == 0){
                    answer = positions[i][j];
                }
            }
        }
        return answer;
    }

    /**
     *  displayQueue - the path taken the maze
     *  @return - returns the path taken the maze
     */

    public Queue displayQueue(){
        return queue;
    }

    /**
     * queueSearch() is the algorithm used to solve the maze and save the direction into a queue.
     */
    public void queueSearch(){
        queueSearch(start());
    }

    /**
     * queueSearch() is the private method algorithm used to solve the maze and save the direction into a queue.
     * @param curr - the start position
     * @return - returns if the maze was solved.
     *
     */

    private boolean queueSearch(Position curr){
    if (curr.pSymbol().compareTo("F") == 0) {
        curr.setVisited(true);
        queue.enqeue(curr);
        return true;
    }//base case
    if(curr.pSymbol().compareTo(".")==0 || curr.pSymbol().compareTo("S")==0){
        queue.enqeue(curr);
        curr.setVisited(true);

        if(curr.pSymbol().compareTo(".")==0) curr.setType(Position.SquareType.PASSED);

        if(curr.getRow() + 1 < row){
            if(queueSearch(positions[curr.getRow() + 1][curr.getColumn()])){
                positions[curr.getRow() + 1][curr.getColumn()].setPrevious(curr);
                return true;
            }//down
        }

        if(curr.getColumn() - 1 >=0){
            if(queueSearch(positions[curr.getRow()][curr.getColumn() - 1])){
                positions[curr.getRow()][curr.getColumn() - 1].setPrevious(curr);
                return true;
            }//left
        }

        if(curr.getColumn() + 1 < column){
            if(queueSearch(positions[curr.getRow()][curr.getColumn() + 1])){
                positions[curr.getRow()][curr.getColumn() + 1].setPrevious(curr);
                return true;
            }//Right
        }

        if(curr.getRow() - 1 >=0){
            if(queueSearch(positions[curr.getRow() - 1][curr.getColumn()])){
                positions[curr.getRow() - 1][curr.getColumn()].setPrevious(curr);
                return true;
            }//up
        }

        if(curr.pSymbol().compareTo("S")!=0)curr.setType(Position.SquareType.PATH);
        queue = de(queue);
        curr.setVisited(false);
        return false;

    }
    return false;
}

    /**
     * de() is the algorithm that makes the queue that act like a stack.
     * @param queue - queue before popping like a stack.
     * @return - returns queue after popping like a stack.
     */
    public Queue de(Queue queue){
            Queue temp = new Queue(row*column);
            int i =0;
            while (i < queue.front()-1){
                temp.enqeue(queue.deque());
            }
            return temp;
    }

    /**
     * stackSearch() is the algorithm used to solve the maze and save the direction into a stack.
     */
    public void stackSearch(){
        stackSearch(start());
    }

    /**
     *  displayStack() - the path taken the maze
     * @return - returns the path taken the maze
     */
    public Stack displayStack(){
        return path;
    }

    /**
     *  stackSearch is the private method algorithm used to solve the maze and save the direction into a stack.
     * @param curr - the start position
     * @return - returns if the maze was solved.
     */
    private boolean stackSearch(Position curr) {
         if (curr.pSymbol().compareTo("F") == 0) {
             curr.setVisited(true);
             path.push(curr);
             return true;
         }//base case

         if(curr.pSymbol().compareTo(".")==0 || curr.pSymbol().compareTo("S")==0){
             path.push(curr);
             curr.setVisited(true);
             if(curr.pSymbol().compareTo(".")==0) curr.setType(Position.SquareType.PASSED);
             if(curr.getRow() + 1 < row){
                 if(stackSearch(positions[curr.getRow() + 1][curr.getColumn()])){
                     positions[curr.getRow() + 1][curr.getColumn()].setPrevious(curr);
                     return true;
                 }//down
             }
             if(curr.getColumn() + 1 < column){
                 if(stackSearch(positions[curr.getRow()][curr.getColumn() + 1])){
                     positions[curr.getRow()][curr.getColumn() + 1].setPrevious(curr);
                     return true;
                 }//Right
             }

             if(curr.getColumn() - 1 >=0){
                 if(stackSearch(positions[curr.getRow()][curr.getColumn() - 1])){
                     positions[curr.getRow()][curr.getColumn() - 1].setPrevious(curr);
                     return true;
                 }//left
             }

             if(curr.getRow() - 1 >=0){
                 if(stackSearch(positions[curr.getRow() - 1][curr.getColumn()])){
                     positions[curr.getRow() - 1][curr.getColumn()].setPrevious(curr);
                     return true;
                 }//up
             }

            if(curr.pSymbol().compareTo("S")!=0)curr.setType(Position.SquareType.PATH);
            path.pop();
            curr.setVisited(false);
            return false;
         }
        return false;
        }

    /**
     *  resetMaze - it is used to reset the maze to its original look
     */

    public void resetMaze(){
        for(int i =0; i < row; i++){
            for (int j =0; j < column; j++){
                if(positions[i][j].pSymbol().compareTo("*") == 0){
                    positions[i][j] = new Position(i,j,'.'); // change every "*" in the maze to "."
                }
            }
        }
    }

    /**
     *  toStrng() is used the generic print for this class.
     * @return - prints out data of the object.
     */

    public String toString(){
        String answer ="";
        for(int i = 0; i < row; i ++){
            for (int j =0; j < column; j++){
                answer += positions[i][j].pSymbol();
            }
            answer += "\n";
        }
        return answer;
    }
}

class Position{
    private int row;
    private int column;
    private SquareType type; //type of square type according to enum
    private boolean visited; // is the position visited or not
    private Position previous = null; // what is the previous position to this.

    enum SquareType {
        START, FINISH, PATH, WALL,PASSED
    } // enum

    public Position(int row, int column, char type){
        this.row = row;
        this.column = column;
        this.previous = null;
        this.visited = false;
        if(type == 'S'){
            this.type = SquareType.START;
        }else if(type == 'F'){
            this.type = SquareType.FINISH;
        } else if (type == '.'){
            this.type = SquareType.PATH;
        } else if (type  == '#'){
            this.type = SquareType.WALL;
        }
        else if (type  == '*'){
            this.type = SquareType.PASSED;
        }
    }// Constructor

    /**
     *  getRow - is the row location of the position in the 2D arrau
     * @return - returns the row location of the position in the 2D arrau
     */
    public int getRow() {
        return row;
    }

    /**
     * getColumn is the column location of the position in the 2D arrau
     * @return - returns the column location of the position in the 2D arrau
     */
    public int getColumn() {
        return column;
    }

    /**
     *  setType() used to set the type of the current position
     * @param type - the type of the current position
     */
    public void setType(SquareType type) {
        this.type = type;
    }

    /**
     *  setVisited - sets if the current position as been visited.
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     *  setPrevious - sets the previous position of the current node.
     * @param previous - the previous position of the current node.
     */
    public void setPrevious(Position previous) {
        this.previous = previous;
    }

    /**
     *  getPrevious() - the previous position to the current node
     * @return - returns the previous position to the current node
     */
    public Position getPrevious() {
        return previous;
    }

    /**
     *  pSymbol is the symbol of the current position.
     * @return - returns the symbol of the current position.
     */

    public String pSymbol(){
        String answer  =  "#";
        if(type == SquareType.START){
            answer = "S";
        }else if(type == SquareType.FINISH){
            answer = "F";
        } else if (type == SquareType.PATH){
            answer = ".";
        } else if(type == SquareType.PASSED) {
            answer = "*";
        }
        return answer;
    }

    /**
     *  toStrng() is used the generic print for this class.
     * @return - prints out data of the object.
     */

    public String toString(){
        return "(" + row+ ", " + column + ")";
    }
}
class Stack {
    private Node top; // top node of the stack.

    private class Node {
        public Position item; // value of current node
        public Node next; // next node to current node

        public Node(Position newItem, Node newNext) {
            this.item = newItem;
            this.next = newNext;
        } //Constructor
    }// end of Node

    public Stack() {
        this.top = null;
    } // Constructor

    /**
     *  isEmpty() is the stack empty or not
     * @return - returns yes if stack is empty and no if it is not.
     */

    public boolean isEmpty() {
        return top == null;
    }

    /**
     *  push() is add a new node to the top of the stack.
     * @param newItem - new node you would like to add.
     */

    public void push(Position newItem) {
        Node newNode = new Node(newItem, top);
        top = newNode;
    }

    /**
     * pop() is remove the node at the top of the stack.
     */

    public void pop() {
        if (!isEmpty()) {
            top = top.next;
        }
    }

    /**
     *  top() is the top node on top of the stack
     * @return - returns top node on top of the stack
     */

    public Position top() {
        Node answer = null;
        if (!isEmpty()) {
            answer = top;
        }
        return answer.item;
    }

    /**
     *  reverseLL - it is used to reverse the single linked list.
     * @param head - the top node
     * @return - tail node which is now the head node after reversing.
     */

    private Node reverseLL(Node head){
        Node prev = null;
        Node curr = head;
        Node next = null;
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     *  toStrng() is used the generic print for this class.
     * @return - prints out data of the object.
     */

    public String toString() {
        String answer = "";
        Node curr = reverseLL(top);;
        while (curr != null) {
            answer += curr.item + " ";
            curr = curr.next;
        }
        return answer;
    }

} //end of Stack

class Queue {
    private Position[] queue;
    private int top =0; // number of nodes in the queue.
    private int bottom =0; // the end of the queue.
    private int capacity; // how large is the array

    public Queue(int MAX_SIZE){
        this.capacity = MAX_SIZE;
        this.queue = new Position[MAX_SIZE];
    } //Constructor

    /**
     *  enqueue - add new node to the front of the node.
     * @param newItem - new item added.
     */

    public void enqeue(Position newItem){
        if(top < capacity ) {
            queue[top] = newItem;
            top++;
        }
    }

    /**
     * The index of the node in the front of the queue.
     * @return
     */

    public int front() {
        return top;
    }

    /**
     *  isEmpty - checks if queue is empty or not.
     * @return - Yes if it is empty and No if it is not empty.
     */

    public boolean isEmpty(){
        return queue[bottom]==null;
    }

    /**
     *  dequeue - removes the last node in the array.
     * @return - the removed  node
     */

    public Position deque(){
        Position answer = queue[bottom];
        if(!isEmpty()) {
            for (int i = 0; i < top - 1; i++) {
                queue[i] = queue[i + 1];
            } // shift all from right to left after deleting the last node in the queue
            queue[top] = null;
            top--;
        }
        return answer;
    }

    /**
     *  back() - last node in the queue
     * @return - returns the last node in the queue
     */

    public Position back(){
        return queue[bottom];
    }

    /**
     *  toStrng() is used the generic print for this class.
     * @return - prints out data of the object.
     */

    public String toString(){
        String answer  = "";
       for(int i =0; i<top; i++){
           answer += queue[i] + " ";
       }
        return answer;
    }
} //end of Queue.

