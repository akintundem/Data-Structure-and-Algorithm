/**
 * Name of class or program:AkintundeMayokunA1Q1.java
 *
 * COMP 2140 SECTION D01
 * ASSIGNMENT Assignment 1, question 1
 * @author Mayokun Moses Akintunde, 7884253
 * @version 26th of May, 2021
 *
 * PURPOSE: The program will use commands in an input file to add, remove, search and loan books in a library.
 */
import java.io.*;
import java.util.Scanner;
public class AkintundeMayokunA1Q1{
    public static void main (String[] args) {
        Scanner file; // used to input the name of file to be used
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        int count =0; // how many lines in file
        String[] lines = new String[100];
        try{
            System.out.println("Please enter the input file name (.txt files only):");
            String filename  = input.nextLine();
            System.out.println();
            FileReader inputTxt = new FileReader(new File(filename));
            file = new Scanner(inputTxt);
            processing(file,lines,count,filename);
            System.out.println("Program terminated normally.");
        }
        catch (Exception e){
            System.out.println("File not found");
        }
    }

    /** The processing method is used to perform every necessary action required to execute an output.
     * @param file is the file in a scanner class
     * @param lines is the array that contains all the lines inputted from file
     * @param count is the number of lines in the file.
     * @param @filename is the name of file inputted */
     private static void processing(Scanner file, String[] lines,int count, String filename ){
        while (file.hasNext()){
            lines[count++] = file.nextLine();
        }
        System.out.println("Processing file " + filename + "..." + "\n");
        String[][] all = new String[count][4];

        for (int i =0; i < count; i++){
            if(((lines[i].split(",",3).length)) == 3){
                all[i][0] = lines[i].split(" ",3)[0];
                all[i][1] = (lines[i].replace(lines[i].split(" ",3)[0]+ " ","").split(","))[0]; //Last Name
                all[i][2] = (lines[i].split(","))[1].trim(); //First Name
                all[i][3] = (lines[i].split(","))[2].trim(); // Title
            }

            if(((lines[i].split(",",3).length)) == 1){
                all[i][0] = lines[i].split(" ",3)[0];
                all[i][1] = (lines[i].replace(lines[i].split(" ",3)[0]+ " ","").split(","))[0].trim();
            }
        }
        Library library = new Library();
        command(all,library);

        }

    /**
     * The command method is used to control books into a library using the specified command
     * @param all the data inputted in 2d array
     * @param library the library in which commands from all param will execute into the library
     */
    private static void command(String[][] all, Library library){
        for(int i =0; i< all.length; i++){
            if(all[i][0].compareTo("ADD") ==0){
                if(all[i][3].compareTo("") == 0){
                    all[i][3] = "unknown"; // if empty then make it empty
                }if(all[i][2].compareTo("") == 0){
                    all[i][2] = "unknown"; // if empty then make it empty
                }if(all[i][1].compareTo("") == 0){
                    all[i][1] = "unknown"; // if empty then make it empty
                }
                Book book = new Book(all[i][3],all[i][2],all[i][1],true);
                library.addBook(book);
            }else if (all[i][0].compareTo("SEARCHA") ==0){
                System.out.println("Books by "+ all[i][1]+": ");
                System.out.println(library.listByAuthor(all[i][1])); // if you are searching for an author, then run listByAuthor method
            }else if (all[i][0].compareTo("SEARCHT") ==0){
                System.out.println("Books named " + all[i][1] +": ");
                System.out.println(library.listByTitle(all[i][1])); // if you are searching for an title, then run listByTitle method
            }else if (all[i][0].compareTo("GETBOOK") ==0){
                System.out.println("Book loaned:");
                library.loanBook(all[i][1]+all[i][2]); // if you are loaning a book, then run loanBook Method
                System.out.println(all[i][1]+ ", " + all[i][2]+ ", " +all[i][3]);
                System.out.println();
            }else if (all[i][0].compareTo("RETURNBOOK") ==0){
                System.out.println("Book returned:");
                library.returnBook(all[i][1]+all[i][2]); // if you are returning a book, then run returnBook Method
                System.out.println(all[i][1]+ ", " + all[i][2]+ ", " +all[i][3]);
                System.out.println();
            }
        }
      }
    }

class Library {
    private static final int MAX_BOOKS = 100;
    private int countBooks; // the number of books in library
    Book[] listOfBooks; // the library in array
    public Library(){
        listOfBooks = new Book[MAX_BOOKS];
        countBooks =0;
    }

    /** The insertSortLastName method is used to sort books based on Author's Lasttname using ordered sort algorithm. */
    private void insertSortLastName(){
        int j;
        Book key;
        for(int i = 1; i < countBooks; i++){
            key = listOfBooks[i];
            j=i-1;
            while (j>=0 && listOfBooks[j].getGetAuthorLastName().compareTo(key.getGetAuthorLastName()) > 0){
                listOfBooks[j+1] = listOfBooks[j];
                j =j-1;
            }
            listOfBooks[j+1] = key;
        }
    }

    /** The insertSortFirstName method is used to sort books based on Author's firstname using ordered sort algorithm. */
    private void insertSortFirstName(){
        int j;
        Book key;
        for(int i = 1; i < countBooks; i++){
            key = listOfBooks[i];
            j=i-1;
            while (j>=0 && listOfBooks[j].getAuthorFirstName().compareTo(key.getAuthorFirstName()) > 0){
                listOfBooks[j+1] = listOfBooks[j];
                j =j-1;
            }
            listOfBooks[j+1] = key;
        }
    }

    /** The insertSortTitle method is used to sort books based on title using ordered sort algorithm. */
    private void insertSortTitle(){
        int j;
        Book key;
        for(int i = 1; i < countBooks; i++){
            key = listOfBooks[i];
            j=i-1;
            while (j>=0 && listOfBooks[j].getBookTitle().compareTo(key.getBookTitle()) > 0){
                listOfBooks[j+1] = listOfBooks[j];
                j =j-1;
            }
            listOfBooks[j+1] = key;
        }
    }

    /** The addBook method is used to add books to a library then sorting it out based on a chronology.
     * @param newBook is the name of the new book added to the Library.*/
    public void addBook(Book newBook){
       listOfBooks[countBooks] = newBook;
       countBooks++;
       insertSortLastName();
       insertSortFirstName();
       insertSortTitle();
        }

    /** The listByAuthor method is used to list books in an ascending form based on the Author's name provided.
     * @param author is the Author of the book you will be listing.
     * @return  All the books that was found in the bible based on the Author's name provided. */
    public String listByAuthor(String author){
        String answer = "";
        for(int i =0;i < countBooks; i++){
            if(listOfBooks[i].getGetAuthorLastName().compareTo(author) == 0){ //compare LastName to the inputted Author
                answer += listOfBooks[i] + "\n";
            }
        }
        return answer;
    }

    /** The listByTitle method is used to list books in an ascending form based on the title provided.
     * @param title is the title of the book you will be listing.
     * @return  All the books that was found in the bible based on the title provided. */
    public String listByTitle(String title){
        String answer = "";
        for(int i =0;i < countBooks; i++){
            if((listOfBooks[i].getBookTitle()).compareTo(title)==0){ //compare title to the inputted Author
                answer += listOfBooks[i] + "\n";
            }
        }
        return answer;
    }

    /** The loanBook method is used to loan a book from the library
     * @param author is the name of the author whose book you are loaning.
     * @return Yes if the book was loaned and No if the book was not loaned  */
    public boolean loanBook(String author){
        boolean answer = false;
        for(int i =0;i < countBooks; i++){
            if((listOfBooks[i].getGetAuthorLastName()+" "+listOfBooks[i].getAuthorFirstName()).compareTo(author)==0 ||listOfBooks[i].getGetAuthorLastName().compareTo(author) ==0){//compare LastName or LastNameFirstName to the inputted Author
                if(listOfBooks[i].isLoan()){
                    listOfBooks[i].setLoan(false);
                    answer = true;
                }
            }
        }
        return answer;
    }

    /** The returnBook method is used to return a book that was loaned back into the library
     * @param author is the name of the author whose book you are returning.
     * @return Yes if the book was returned and No if the book was not added  */
    public boolean returnBook(String author) {
        boolean answer = false;
        for(int i =0;i < countBooks; i++){
            if((listOfBooks[i].getGetAuthorLastName()+" "+listOfBooks[i].getAuthorFirstName()).compareTo(author)==0 ||listOfBooks[i].getGetAuthorLastName().compareTo(author) ==0 ){ //compare LastName or LastNameFirstName to the inputted Author
                if(!listOfBooks[i].isLoan()){
                    listOfBooks[i].setLoan(true);
                    answer = true;
                }
            }
        }
        return answer;
    }

    /** The Printer/Output for Library*/
    public String toString(){
        String result = "";
        for(int i =0; i < countBooks;i++){
            result+=listOfBooks[i]+"\n";
        }
        return result + "";
    }
}


class Book {
    private String bookTitle; // name of book
    private String authorFirstName; // first name of the book's author
    private String getAuthorLastName; // last name of the book's author
    private boolean loan; // is book loaned?

    public Book(String bookTitle,
                String authorFirstName,
                String getAuthorLastName,
                boolean Loan){
        this.bookTitle = bookTitle;
        this.authorFirstName = authorFirstName;
        this.getAuthorLastName = getAuthorLastName;
        this.loan = Loan;
    }

    /** The getter for instance variable "Book Title" */
    public String getBookTitle() {
        return bookTitle;
    }

    /** The getter for instance variable "Author FirstName" */
    public String getAuthorFirstName() {
        return authorFirstName;
    }

    /** The setter for instance variable "Loan" */
    public void setLoan(boolean Loan) {
        loan = Loan;
    }

    /** The getter for instance variable "Loan" */
    public boolean isLoan() {
        return loan;
    }

    /** The getter for instance variable "Author LastName" */
    public String getGetAuthorLastName() {
        return getAuthorLastName;
    }
    /** Book Printer/output */
    public String toString(){
        return getAuthorLastName + ", " + authorFirstName  +", " + bookTitle ;
    }
}