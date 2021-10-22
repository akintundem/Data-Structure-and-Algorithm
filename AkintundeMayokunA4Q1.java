/**
 * Name of class or program: AkintundeMayokunA4Q1.java
 * COMP 2140 SECTION D01
 * ASSIGNMENT    Assignment 4, question 1
 * @author       Mayokun Moses Akintunde, 7884253
 * @version      04/08/2021
 *
 * PURPOSE: The program is used to create an expression tree for either variable or numbers for operators "+", "-", "*" and "^"
 */
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class AkintundeMayokunA4Q1 {
    public static void main(String[] args) {
        try {
            System.out.println("Please enter the input file name (.txt files only):");
            Scanner inputTxt = new Scanner(new FileReader(new File("C:\\Users\\akint\\IdeaProjects\\moses\\src\\A4Q1test.txt")));
            String[] table = new String[100];
            int count = 0;
            processing(inputTxt,table,count);
            System.out.println("Program terminated normally.");
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }//main

    /**
     * The processing method is a method used to process the information derived from the file read.
     * @param file : The file read is saved in this scanner.
     * @param lines : The file is saved in a 1D Array and saved outside in the main function.
     * @param count: Used to count the number of lines in the file.
     */
    private static void processing(Scanner file, String[] lines,int count ){
        while (file.hasNext()){
            lines[count++] = file.nextLine();
        } // This is used to save the lines  from the file into a 1D array
        ExpressionTree expressionTree = null; // an instance of an expression tree.
        for(int line = 0; line <count; line++) {

                if (lines[line].split(" ")[0].compareTo("COMMENT") == 0) {
                    System.out.println(lines[line].replace("COMMENT", " ").strip());
                } // If the line begins with comment, Print out whatever that comes after comment.

                else if (lines[line].split(" ")[0].compareTo("NEW") == 0) {
                   expressionTree = new ExpressionTree(lines[line].replace("NEW", " ").strip());
                } // This is used to put either a preorder or postorder sentence after the commandline "NEW" into the constructor of
                    // an Expression tree.

                else if (lines[line].split(" ")[0].compareTo("PRINTPREFIX") == 0) {
                    expressionTree.preorderTraversal();
                    System.out.println();
                }// Print an preorder form of the information stored in the expression tree.

                else if (lines[line].split(" ")[0].compareTo("PRINTPOSTFIX") == 0) {
                    expressionTree.postorderTraversal();
                    System.out.println();
                }// Print an postorder form of the information stored in the expression tree.

                else if (lines[line].split(" ")[0].compareTo("PRINTINFIX") == 0) {
                    expressionTree.inorderTraversal();
                    System.out.println();
                }// Print an inorder form of the information stored in the expression tree.

                else if (lines[line].split(" ")[0].compareTo("SIMPLIFY") == 0) {
                    expressionTree.simplify();
                } //Simplify the information stored in the expression tree.


        } // run each line of the file that was saved in a 1D array called lines
    } //End of Processing.
}

class Node {
    enum NodeType{OPERATOR, VARIABLE, NUMBER;}
    public char operator;
    public String variable;
    public int number;
    public Node left;
    public Node right;
    public NodeType typeOfNode;

    public Node(String input){
        if(input.equals("+") || input.equals("-") || input.equals("*") || input.equals("^") ){
            typeOfNode = NodeType.OPERATOR;
            operator = input.charAt(0);
        } else if (integerCheck(input)){
            typeOfNode = NodeType.NUMBER;
            number = Integer.parseInt(input);
        } else {
                typeOfNode = NodeType.VARIABLE;
                variable = input;
        }
        this.left = null;
        this.right = null;
    }

    /**
     *  integertcheck() is used to check if the input is an integer.
     * @param input: the input you are checking for.
     * @return: returns true if it is an integer and false if it is not an integer.
     */
    static boolean integerCheck(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException error){}
        return false;
    }

    /**
     * preorderTraversal() is a node helper method used to print out the information in the expression tree in the form of a pre order which is Node, Left and Right.
     */

    public void preorderTraversal() {
        if(typeOfNode == NodeType.NUMBER){
            System.out.print(number+ " ");
        } else if(typeOfNode == NodeType.OPERATOR){
            System.out.print(operator+ " ");
        } else {
            System.out.print(variable+ " ");
        }
        // All above is used to print the node if it is either an operator, number or variable.

        if(left != null) left.preorderTraversal();
        if(right != null) right.preorderTraversal();
    }

    /**
     * inorderTraversal() is a node helper method used to print out the information in the expression tree in the form of a inorder which is Left, Node and Right.
     */

    public void inorderTraversal() {
        if(left != null){
            System.out.print("( ");
            left.inorderTraversal();
        }

        if(typeOfNode == NodeType.NUMBER){
            System.out.print(number+ " ");
        } else if(typeOfNode == NodeType.OPERATOR){
            System.out.print(operator+ " ");
        } else {
            System.out.print(variable+ " ");
        }
        // All above is used to print the node if it is either an operator, number or variable.

        if(right != null){
            right.inorderTraversal();
            System.out.print(") ");
        }
    }

    /**
     * postorderTraversal() is a node helper method used to print out the information in the expression tree in the form of a post order which is Left, Right and Node.
     */

    public void postorderTraversal() {
        if(left != null) left.postorderTraversal();
        if(right != null) right.postorderTraversal();

        if(typeOfNode == NodeType.NUMBER){
            System.out.print(number+ " ");
        } else if(typeOfNode == NodeType.OPERATOR){
            System.out.print(operator+ " ");
        } else {
            System.out.print(variable + " ");
        }
        // All above is used to print the node if it is either an operator, number or variable.

    }
}//End of Node

class Queue{
    private node top;
    private node tail;
    private int count =0;

    private class node {
        public Node item;
        public node next;

        public node(Node newItem, node newNext) {
            this.item = newItem;
            this.next = newNext;
        }
    }

    public Queue(){
        top = null;
        tail = null;
    }//Constructor

    /**
     * Enqueue is used to add a new item to the end of the queue.
     * @param newItem the new node that will be added to the end of the queue.
     * @return return if true if the item was successfully added to the queue.
     */

    public boolean Enqueue(Node newItem){
        if(top == null){
            top = tail = new node(newItem,top);
            count++;
            return true;
        }else{
            node newNode = new node(newItem,null);
            tail.next= newNode;
            tail = newNode;
            count++;
            return true;
        }
    }

    /**
     * Dequeue() is Used to remove the first item added to the queue
     * @return return the top item that is removed at the top of the queue.
     */

    public Node Dequeue(){
        Node answer = top.item;
        top = top.next;
        count--;
        return answer;
    }

    /**
     * peek() is the top of the item in the queue.
     * @return returns the node at the top of the item in the queue.
     */
    public Node peek(){
        return top.item;
    }

    /**
     * Count() used to count the number of nodes in the Queue
     * @return returns the number of nodes in the queue
     */

    public int getCount() {
        return count;
    }

    /**
     * Checks if Queue is empty or not
     * @return returns true if empty and false if not empty.
     */
    public boolean isEmpty(){
        return top == null;
    }

    /**
     * peek2() check the 2nd item in the queue
     * @return returns the 2nd item in the queue
     */

    public Node peek2(){
        return top.next.item;
    }

    /**
     * peek3() check the 3rd item in the queue
     * @return returns the 3rd item in the queue
     */

    public Node peek3(){
        return top.next.next.item;
    }

    /**
     * toString() it is used to print out the object
     * @return return a string format of the object you are working with.
     */
    public String toString(){
        String answer = "<";
        node curr = top;
        while (curr != null){
            answer += curr.item.typeOfNode + ", ";
            curr = curr.next;
        }
        return answer + '<';
    }
} // End of Queue

class Stack{
    private node top;

    private class node {
        public Node item;
        public node next;

        public node(Node newItem, node newNext) {
            this.item = newItem;
            this.next = newNext;
        }
    }

    public Stack(){
        top = null;
    }//Constructor

    /**
     * push() it is used to add new item to the top of the stack
     * @param newItem the new item you will like to add to the top of the stack
     */

    public void push(Node newItem){
        top = new node(newItem,top);
    }

    /**
     * pop() it is used to remove the item at the top of the item
     * @return the item removed at the top of the stack
     */

    public Node pop(){
        Node answer = top.item;
        top = top.next;
        return answer;
    }

    /**
     * Peek() is the item at the top of the stack
     * @return
     */

    public Node peek(){
        return top.item;
    }

    /**
     * isEmpty() is used to check if stack is empty.
     * @return returns true if empty and false if it is not empty.
     */
    public boolean isEmpty(){
        return top == null;
    }

    /**
     * toString() it is used to print out the object
     * @return return a string format of the object you are working with.
     */

    public String toString(){
        String answer = "< ";
        node curr = top;
        while (curr != null){
            answer += curr.item.typeOfNode + ", ";
            curr = curr.next;
        }
        return answer + "]";
    }
} //End of stack

class ExpressionTree{
    Queue queue = new Queue(); //This is the queue
    Stack stack = new Stack(); //This is the stack
    Node root;

    public ExpressionTree(String newInput){
        if(newInput.split("")[0].compareTo("+")==0 || newInput.split("")[0].compareTo("-")==0 || newInput.split("")[0].compareTo("*")==0 || newInput.split("")[0].compareTo("^")==0){
            root = preFix(newInput);
        }//prefix
        else{
            root= postFix(newInput);
        }//postfix
    } //Constructor

    /**
     * preFix() is used create an expression tree from a prefix inserted in the constructor
     * @param newInput the preorder line inserted
     * @return returns the root of the node of the expression tree.
     */

    private Node preFix(String newInput){
        for(String line  : newInput.split(" ")){
            if(line.compareTo(" ") !=0) {
                queue.Enqueue(new Node(line));
            }
        } //Enqueue all data in the line into a new Queue first.

        while (queue.getCount() != 1){
            if(queue.peek().left != null && queue.peek().right != null){
                queue.Enqueue(queue.Dequeue());
            }//if operand or an operator has children

            else if (queue.peek().typeOfNode == Node.NodeType.OPERATOR && (queue.peek().left == null &&  queue.peek().right == null) ) {
                if( (queue.peek2().left == null && queue.peek2().right == null  && queue.peek3().left == null  && queue.peek3().right == null) && (queue.peek2().typeOfNode == Node.NodeType.OPERATOR || queue.peek3().typeOfNode == Node.NodeType.OPERATOR)){
                    queue.Enqueue(queue.Dequeue());
                } // if peek 2 and peek 3 has no child

                else {
                    Node operator = queue.Dequeue();
                    Node opr1 = queue.Dequeue();
                    Node opr2 = queue.Dequeue();

                    operator.left = opr1;
                    operator.right = opr2;

                    queue.Enqueue(operator);
                } // if peek 2 and peek 3 have a child

            } // if either off peek 2 and peek 3 have a child.

            else if((queue.peek().typeOfNode == Node.NodeType.NUMBER || queue.peek().typeOfNode == Node.NodeType.VARIABLE ) && (queue.peek().left == null &&  queue.peek().right == null) ) {
                queue.Enqueue(queue.Dequeue());
            }//if operand does not have a child

        }// if the queue has greater than one

        return queue.peek(); // the new root of the expression tree.

    } //End of Prefix

    /**
     * postFix()is used create an expression tree from a postfix inserted in the constructor
     * @param newInput the postorder line inserted
     * @return returns the root of the node of the expression tree.
     */

    private Node postFix(String newInput){
        for(String line  : newInput.split(" ")){
            if(line.compareTo(" ") !=0) {
                if (line.compareTo("+") == 0 || line.compareTo("-") == 0 || line.compareTo("*") == 0 || line.compareTo("^") == 0) {
                    Node pop1 = stack.pop();
                    Node pop2 = stack.pop();
                    Node newNode = new Node(line);
                    newNode.left = pop2;
                    newNode.right = pop1;
                    stack.push(newNode);
                }//if it is an operator
                else {
                    stack.push(new Node(line));
                }
            }
        }
        return stack.pop();// the new root of the expression tree.
    } //End of Postfix

    /**
     * preorderTraversal() is used to print out the information in the expression tree in the form of a pre order which is Node, Left, Right.
     */

    public void preorderTraversal(){
        root.preorderTraversal();
    }

    /**
     * inorderTraversal() is used to print out the information in the expression tree in the form of a inrder which isLeft, Node, Right.
     */

    public void inorderTraversal(){
        root.inorderTraversal();
    }

    /**
     * postorderTraversal() is used to print out the information in the expression tree in the form of a post order which is Node, Left, Right.
     */

    public void postorderTraversal(){
        root.postorderTraversal();
    }

    /**
     * simplify() is used to simplify information in the constructor
     */

    public void simplify(){
        root = simplifyin(root);
        System.out.println("Tree simplified");
    }

    private Node simplifyin(Node root){
        if(root == null){
            return null;
        } //if node is Empty, it should be null

        if(root.left == null && root.right == null){
            return root;
        } // if it is a leaf, return leaf

        Node Lval = simplifyin(root.left);
        Node Rval = simplifyin(root.right);

        if(Lval.typeOfNode == Node.NodeType.NUMBER && Rval.typeOfNode == Node.NodeType.NUMBER) {
            if (root.operator == '+') {
                return new Node(String.valueOf(Lval.number + Rval.number));
            }
            if (root.operator == '-') {
                return new Node(String.valueOf(Lval.number - Rval.number));
            }
            if (root.operator == '^') {
                return new Node(String.valueOf((int) Math.pow(Lval.number, Rval.number)));
            }
            if (root.operator == '*') {
                return new Node(String.valueOf(Lval.number * Rval.number));
            }
        }// if they are both numbers

        else if(Lval.typeOfNode == Node.NodeType.NUMBER && (Rval.typeOfNode == Node.NodeType.OPERATOR || Rval.typeOfNode == Node.NodeType.VARIABLE)){
            if(Lval.number == 0 && (root.operator == '+')){
                return Rval;
            } else if(Lval.number == 0 && (root.operator == '^' || root.operator == '*')){
                return new Node(String.valueOf(0));
            }else if(Lval.number == 1 && (root.operator == '^')){
                return new Node(String.valueOf(1));
            }else if(Lval.number == 1 && (root.operator == '*')){
                return Rval;
            }else {
                root.left = Lval;
                root.right = Rval;
                return root;
            }
        } //operator or variable in right

        else if((Lval.typeOfNode == Node.NodeType.OPERATOR || Lval.typeOfNode == Node.NodeType.VARIABLE) && Rval.typeOfNode == Node.NodeType.NUMBER) {
            if(Rval.number == 0 && (root.operator == '+' || root.operator == '-')){
                return Lval;
            } else if(Rval.number == 0 && root.operator == '^'){
                return new Node(String.valueOf(1));
            }else if(Rval.number == 0 && root.operator == '*'){
                return new Node(String.valueOf(0));
            }else if(Rval.number == 1 && (root.operator == '^'||root.operator=='*')){
                return Lval;
            }else {
                root.left = Lval;
                root.right = Rval;
                return root;
            }

        } //operator or variable in left


        else {
            root.left = Lval;
            root.right = Rval;
            return root;
        } // number + operator, operator + number

        return null;
    } //End of Simplification
} //End of Expression Tree
