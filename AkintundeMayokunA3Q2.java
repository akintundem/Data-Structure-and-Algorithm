/**
 * Name of class or program: AkintundeMayokunA3Q2.java
 * COMP 2140 SECTION D01
 * ASSIGNMENT    Assignment 3, question 2
 * @author       Mayokun Moses Akintunde, 7884253
 * @version      12/07/2021
 *
 * PURPOSE: The program is supposed to create a dictionary and store non duplicate words in array and hash tables.
 */


//==============================================================
// A3Q2template.java
//
// COMP 2140 SUMMER 2021 D01
// ASSIGNMENT 3 QUESTION 2
// PROVIDED TEMPLATE
//
// PURPOSE: Compare the performace of three dictionaries (ordered
//          array, open addressing hash table, separate chaining
//          hash table).
//==============================================================

import java.io.*;
import java.util.*;

//==========================================================
// A3Q2template class (main) --- MODIFY TO INCLUDE YOUR NAME BUT OTHERWISE DO NOT CHANGE THIS CLASS
//
// PURPOSE: Compare performance of three dictionaries. Given
//          a text file, time how long it takes to fill each
//          dictionary, one word at a time. Time how long it
//          takes to search each dictionary for a given set
//          of words.
//==========================================================

public class AkintundeMayokunA3Q2 {

    public static void main(String[] args){

		String inputFile = "C:\\Users\\akint\\IdeaProjects\\moses\\src\\GreatExpectations.txt";
		String searchFile = "C:\\Users\\akint\\IdeaProjects\\moses\\src\\A3Q2TestWords.txt";

		DictionaryOrdered allWordsOrdered; //complete dictionary, using an ordered array
		DictionaryOpen allWordsOpen; //complete dictionary, using open addressing
		DictionaryChain allWordsChain; //complete dictionary, using separate chaining

		long startTime, endTime, elapsedTime;

		//Fill the dictionaries
		System.out.println("\nFilling ordered array dictionary...");
		startTime = System.nanoTime();
		allWordsOrdered = new DictionaryOrdered(100);
		buildOrdered(allWordsOrdered, inputFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to fill the ordered array dictionary with " + allWordsOrdered.getSize() + " words was: " + elapsedTime + " ns.");

		System.out.println("\nFilling open addressing dictionary...");
		allWordsOpen = new DictionaryOpen(100);
		startTime = System.nanoTime();
		buildOpen(allWordsOpen, inputFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to fill the open addressing dictionary with " + allWordsOpen.getSize() + " words was: " + elapsedTime + " ns.");

		System.out.println("\nFilling separate chaining dictionary...");
		allWordsChain = new DictionaryChain(100);
		startTime = System.nanoTime();
		buildChain(allWordsChain, inputFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to fill the separate chaining dictionary with " + allWordsChain.getSize() + " words was: " + elapsedTime + " ns.");

		//Search the dictionaries
		System.out.println("\nSearching ordered array dictionary...");
		startTime = System.nanoTime();
		searchOrdered(allWordsOrdered, searchFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to search the ordered array dictionary was: " + elapsedTime + " ns.");

		System.out.println("\nSearching open addressing dictionary...");
		startTime = System.nanoTime();
		searchOpen(allWordsOpen, searchFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to search the open addressing dictionary was: " + elapsedTime + " ns.");

		System.out.println("\nSearching separate chaining dictionary...");
		startTime = System.nanoTime();
		searchChain(allWordsChain, searchFile);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("The time to search the separate chaining dictionary was: " + elapsedTime + " ns.");

		System.out.println("\nEnd of Processing.");
    }//end main

    //==========================================================
    // buildOrdered
    //
    // PURPOSE: Fill the given dictionary with the given text.
    //
    // PARAMETERS:
    //   allWordsOrdered - the dictionary to be filled
    //   inputFile - the file containing words to add to dictionary
    //
    // RETURNS:
    //   none
    //==========================================================
    static void buildOrdered(DictionaryOrdered allWordsOrdered, String inputFile){
		String temp;
		String[] words;

		try{
			BufferedReader buff = new BufferedReader(new FileReader(new File(inputFile)));

			temp = buff.readLine();
		    while(temp != null){
		    	words = temp.split("[^a-zA-Z�]+");

				for (int i=0; i<words.length; i++)
			      allWordsOrdered.insert(words[i]);

				temp = buff.readLine();
	    	}

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + inputFile);
		}

    }//end buildOrdered

    //==========================================================
    // buildOpen
    //
    // PURPOSE: Fill the given dictionary with the given text.
    //
    // PARAMETERS:
    //   allWordsOpen - the dictionary to be filled
    //   inputFile - the file containing words to add to dictionary
    //
    // RETURNS:
    //   none
    //==========================================================
    static void buildOpen(DictionaryOpen allWordsOpen, String inputFile){
		String temp;
		String[] words;

		try{
			BufferedReader buff = new BufferedReader(new FileReader(new File(inputFile)));

			temp = buff.readLine();
		    while(temp != null){
		    	words = temp.split("[^a-zA-Z�]+");

				for (int i=0; i<words.length; i++)
			      allWordsOpen.insert(words[i]);

				temp = buff.readLine();
		    }

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + inputFile);
		}

    }//end buildOpen

    //==========================================================
    // buildChain
    //
    // PURPOSE: Fill the given dictionary with the given text.
    //
    // PARAMETERS:
    //   allWordsChain - the dictionary to be filled
    //   inputFile - the file containing words to add to dictionary
    //
    // RETURNS:
    //   none
    //==========================================================
    static void buildChain(DictionaryChain allWordsChain, String inputFile){
		String temp;
		String[] words;

		try{
			BufferedReader buff = new BufferedReader(new FileReader(new File(inputFile)));

			temp = buff.readLine();
		    while(temp != null){
		    	words = temp.split("[^a-zA-Z�]+");

				for (int i=0; i<words.length; i++)
			      allWordsChain.insert(words[i]);

				temp = buff.readLine();
		    }

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + inputFile);
		}

    }//end buildChain

    //==========================================================
    // searchOrdered
    //
    // PURPOSE: Search the given dictionary for the given words.
    //
    // PARAMETERS:
    //   allWordsOrdered - the dictionary to be searched
    //   searchFile - the file containing words to look for
    //
    // RETURNS:
    //   none (prints number of words found)
    //==========================================================
    static void searchOrdered(DictionaryOrdered allWordsOrdered, String searchFile){
		String temp;
		int numFound=0;
		int numMissing=0;

		try{

		    Scanner scnner = new Scanner(new File(searchFile));
		    while(scnner.hasNext()){

				temp = scnner.next();
				if (allWordsOrdered.search(temp))
				    numFound++;
				else
				    numMissing++;
		    }
		    System.out.println("Number of words found = " + numFound + ". Number of words not found = " + numMissing + ".");

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + searchFile);
		}

    }//end searchOrdered

    //==========================================================
    // searchOpen
    //
    // PURPOSE: Search the given dictionary for the given words.
    //
    // PARAMETERS:
    //   allWordsOpen - the dictionary to be searched
    //   searchFile - the file containing words to look for
    //
    // RETURNS:
    //   none (prints number of words found)
    //==========================================================
    static void searchOpen(DictionaryOpen allWordsOpen, String searchFile){
		String temp;
		int numFound=0;
		int numMissing=0;

		try{

		    Scanner scnner = new Scanner(new File(searchFile));
		    while(scnner.hasNext()){

				temp = scnner.next();
				if (allWordsOpen.search(temp))
				    numFound++;
				else
				    numMissing++;
		    }
		    System.out.println("Number of words found = " + numFound + ". Number of words not found = " + numMissing + ".");

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + searchFile);
		}

    }//end searchOpen

    //==========================================================
    // searchChain
    //
    // PURPOSE: Search the given dictionary for the given words.
    //
    // PARAMETERS:
    //   allWordsChain - the dictionary to be searched
    //   searchFile - the file containing words to look for
    //
    // RETURNS:
    //   none (prints number of words found)
    //==========================================================
    static void searchChain(DictionaryChain allWordsChain, String searchFile){
		String temp;
		int numFound=0;
		int numMissing=0;

		try{

		    Scanner scnner = new Scanner(new File(searchFile));
		    while(scnner.hasNext()){

				temp = scnner.next();
				if (allWordsChain.search(temp))
				    numFound++;
				else
				    numMissing++;
		    }
		    System.out.println("Number of words found = " + numFound + ". Number of words not found = " + numMissing + ".");

		}//end try
		catch (IOException e){
		    System.out.println("File I/O Error: " + searchFile);
		}

    }//end searchChain

}//end class A3Q2template

//==============================================================
// DictionaryOrdered class
//
// PURPOSE: Store a list of words, in an ordered array.
//
// PUBLIC METHODS: - constructor: public Dictionary(int size)
//                 - public int getSize() - return the current number of words
//                 - public void insert(String newWord) - insert new word in list
//                 - public boolean search(String wordToFind) - search for
//                   given word and return true if found
// FOR TESTING: public void print() - print contents of dictionary
//==============================================================
class DictionaryOrdered{
	private int size; // size of the array.
	private String[] dictionary; // array
	private int counter = 0; // how many words are in the array.

	public DictionaryOrdered(int size){
		this.size = size;
		dictionary = new String[size];
	} //Constructor

	/**
	 *  orderedInsert() is the algorithm used to insert new words in ordered format.
	 * @param newWord - the new word we would like to insert.
	 */

	private void orderedInsert(String newWord){
		if(counter==0){
			dictionary[counter] = newWord;
		}
		else {
			int last = counter - 1;
			boolean foundSpot = false;
			while (last >= 0 && !foundSpot) {
				if (dictionary[last].compareTo(newWord) > 0) {
					dictionary[last + 1] = dictionary[last];
					last--;
				} else {
					foundSpot = true;
				}
				dictionary[last + 1] = newWord;
			}
		}
	}

	/**
	 *  getSize() is the number of words in the hash table.
	 * @return - returns the number of words in the hash table.
	 */

	public int getSize(){
		return --counter;
	}

	/**
	 *  insertionP() is used to do the actual insertion of the new word.
	 * @param newWord - the new word you would like to insert.
	 */

	private void insertionP(String newWord){
		if (!search(newWord)) {
			orderedInsert(newWord);
			++counter;
		}
	}

	/**
	 *  insert() is used to add a given word into the hash table using the Ordered insertion.
	 * @param newWord - the new word you would like to add.
	 */

	public void insert(String newWord){
		newWord = newWord.toLowerCase();
		if( counter != dictionary.length){
			insertionP(newWord);
		}

		else {
			String[] temp = new String[dictionary.length * 2];
			for (int i = 0; i < dictionary.length; i++) {
				temp[i] = dictionary[i];
			} // used to increase the hash table and rehash all words again.
			dictionary = temp;
			insertionP(newWord);
		}
	}

	/**
	 *  search() is is used to look for a word in the hash table by using the hashing algorithm
	 *  for Binary Search.
	 * @param wordToFind - the word you are looking for.
	 * @return - returns yes if the word is found in the hash table and no if the word is not found.
	 */

	public boolean search(String wordToFind){
		wordToFind =wordToFind.toLowerCase();
		int mid = 0;
		int low = 0;
		int high = counter -1;
		while(high>=low){
			mid = (high+low)/2;
			if(dictionary[mid].compareTo(wordToFind) < 0){
				low = mid +1;
			} else if(dictionary[mid].compareTo(wordToFind) > 0){
				high = mid -1;
			}else {
				return true;
			}
		}
		return false;
	}

	/**
	 *  toStrng() is used the generic print for this class.
	 * @return - prints out data of the object.
	 */

	public String toString(){
		String answer ="";
		for(int i =0; i < dictionary.length; i++){
			answer += dictionary[i] +"\n";
		}
		return answer;
	}


}//end class DictionaryOrdered


//==============================================================
// DictionaryOpen class
//
// PURPOSE: Store a list of words, in a hash table using open addressing.
//
// PUBLIC METHODS: - constructor: public Dictionary(int size)
//                 - public int getSize() - return the current number of words
//                 - public void insert(String newWord) - insert new word in list
//                 - public boolean search(String wordToFind) - search for
//                   given word and return true if found
// FOR TESTING: public void print() - print contents of dictionary
//
//==============================================================
class DictionaryOpen{
	private int size;// size of hash table.
	private int count; // how many of words are in the hash table.
	private String[] dictionary; // hash table.
	private String[] temp; // temporary hash table.

	public DictionaryOpen(int size){
		size = primeOutput(size);
		this.size = size;
		dictionary = new String[size];
	} //Constructor.

	/**
	 *  primeOutput() is used to try different numbers in the algorithm till you find the prime number.
	 * @param size - the different numbers you are trying
	 * @return - the first prime number from the initial size give.
	 */

	private int primeOutput(int size){
		while (!prime(size)){
			size++;
		}
		return size;
	}

	/**
	 *  prime() is the given algorithm used to check if a number is a prime number.
	 * @param size - the number we would like to change
	 * @return - returns yes if a prime number and no if it is not.
	 */

	private boolean prime(int size){
		int j =2;
		while (j*j <= size){
			if(size%j == 0){
				return false;
			}
			j++;
		}
		return true;
	}

	/**
	 * hornerMethod() is the hash function used to find the hash code of the string for the new word.
	 * @param words - the string we would like to find the hash code for.
	 * @param newArraySize - size of the dictionary/hash table array
	 * @return - returns the hash code for the new word.
	 */

	private int hornerMethod(String words,int newArraySize){
		final int CONSTANT = 27;
		int sum =0;
		for(int i =0; i<=words.length()-1; i++){
			if((int) words.charAt(i)>=96 && (int) words.charAt(i) <=122) {
				sum += ((int) words.charAt(i) - 96);
			}
			if(i != words.length()-1) {
				sum = (sum * CONSTANT)%newArraySize;
			}
		}
		return sum;
	}

	/**
	 *  getSize() is the number of words in the hash table.
	 * @return - returns the number of words in the hash table.
	 */

	public int getSize(){
		return count;
	}

	/**
	 *  compressionMap() is used to find the empty index through converting hash code into Integer index that is available.
	 * @param dictionary - the hash table we are inserting a new word to.
	 * @param newWord - the new word we would like to add.
	 * @param newArraySize - the size of the hash table.
	 * @return - the integer index available.
	 */

	private int compressionMap(String[] dictionary,String newWord, int newArraySize){
		final int CONSTANT = 41;
		newWord = newWord.toLowerCase();
		int IntegerHash = hornerMethod(newWord,newArraySize);
		int insert = (IntegerHash%newArraySize)%newArraySize;
		int i =1;
		while (dictionary[insert] != null){
			insert = (IntegerHash%newArraySize + (i * (CONSTANT - IntegerHash%CONSTANT)) % newArraySize);
			i++;
			if(insert > newArraySize-1){
				insert = insert - newArraySize;
			}
		}
		return insert;
	}

	/**
	 * inserter() is the brain for the insertion of hash table
	 * @param dict - the hash table we are inserting a new word to.
	 * @param newWord - the new word we would like to add.
	 * @param newArraySize - the size of the hash table.
	 */

	private void inserter(String[] dict, String newWord, int newArraySize){
		dict[compressionMap(dict,newWord,newArraySize)] = newWord;
	}

	/**
	 *  insert() is used to add a given word into the hash table using the Open Addressing concept.
	 * @param newWord - the new word you would like to add.
	 */

	public void insert(String newWord){
		newWord = newWord.toLowerCase();
		if (newWord.compareTo("")!= 0 && newWord.compareTo(" ")!= 0 && !search(newWord)) { // if the word is not empty or not a duplicate.
			if(count < 0.6*dictionary.length){
				inserter(dictionary,newWord,dictionary.length);
				count++;
			} else {
				size = primeOutput(dictionary.length * 2);
				temp = new String[size];
				for (int i = 0; i < dictionary.length; i++) {
					if(dictionary[i] != null){
						inserter(temp,dictionary[i],size);
					}
				} // used to increase the hash table and rehash all words again.
				dictionary = new String[primeOutput(size)];
				dictionary = temp;
				inserter(dictionary,newWord,size);
				count++;
			}
		}
	}

	/**
	 *  initialsearch() used to check if the hash code as an index in the hash table.
	 * @param insert - hash code as an index.
	 * @param wordToFind - check if the word you are searching for in hash code as an index is found in that index.
	 * @return - yes if found and no if not found.
	 */

	private boolean initialSearch(int insert,String wordToFind){
		if(dictionary[insert] != null && dictionary[insert].compareTo(wordToFind) == 0) {
			return true;
		}
		return false;
	}

	/**
	 *  search() is is used to look for a word in the hash table by using the hashing algorithm
	 *  for Open Addressing.
	 * @param wordToFind - the word you are looking for.
	 * @return - returns yes if the word is found in the hash table and no if the word is not found.
	 */

	public boolean search(String wordToFind){
		wordToFind =wordToFind.toLowerCase();
		int constant = 41;
		int IntegerHash = hornerMethod(wordToFind,dictionary.length);
		int insert = IntegerHash%dictionary.length;
		int i =1;
		if(initialSearch(insert,wordToFind)){
			return true;
		} else {
			while (dictionary[insert] != null) {
				insert = (IntegerHash % dictionary.length + (i * (constant - IntegerHash % constant)) % dictionary.length);
				i++;
				if (insert > dictionary.length - 1) {
					insert = insert - dictionary.length;
				}
				if (dictionary[insert] != null && dictionary[insert].compareTo(wordToFind) == 0) {
					return true;
				}

			}
		}
		return false;
	}

	/**
	 *  toStrng() is used the generic print for this class.
	 * @return - prints out data of the object.
	 */

	public String toString(){
		String answer ="";
		for(int i =0; i < dictionary.length; i++){
			answer += dictionary[i] +"\n";
		}
		return answer;
	}


}//end class DictionaryOpen


//==============================================================
// DictionaryChain class
//
// PURPOSE: Store a list of words, in a hash table using separate
//          chaining. Words are converted to lowercase as inserted.
//
// PUBLIC METHODS: - constructor: public Dictionary(int size)
//                 - public int getSize() - return the current number of words
//                 - public void insert(String newWord) - insert new word in list
//                 - public boolean search(String wordToFind) - search for
//                   given word and return true if found
// FOR TESTING: public void print() - print contents of dictionary
//
//==============================================================
class DictionaryChain{
	private int size; // initial of size hash table array.
	private int count; // how many words are in the hash table array.
	private SingleLinkedList[] temp; // temporary hash table used for rehashing.
	private SingleLinkedList[] dictionary; // hash table array.

	public DictionaryChain(int size){
		size = primeOutput(size);
		this.size = size;
		dictionary = new SingleLinkedList[size];
	} //Constructor

	/**
	 *  primeOutput() is used to try different numbers in the algorithm till you find the prime number.
	 * @param size - the different numbers you are trying
	 * @return - the first prime number from the initial size give.
	 */

	private int primeOutput(int size){
		while (!prime(size)){
			size++;
		}
		return size;
	}

	/**
	 * hornerMethod() is the hash function used to find the hash code of the string for the new word.
	 * @param words - the string we would like to find the hash code for.
	 * @param newArraySize - size of the dictionary/hash table array
	 * @return - returns the hash code for the new word.
	 */

	private int hornerMethod(String words,int newArraySize){
		int a = 27; // constant for the horner method
		int sum =0;
		for(int i =0; i<=words.length()-1; i++){
			if((int) words.charAt(i)>=96 && (int) words.charAt(i) <=122) { // you are only dealing with small letter words.
				sum += ((int) words.charAt(i) - 96);
			}
			if(i != words.length()-1) {
				sum = (sum * a)%newArraySize;
			}
		} // horner method
		return sum;
	}

	/**
	 *  prime() is the given algorithm used to check if a number is a prime number.
	 * @param size - the number we would like to change
	 * @return - returns yes if a prime number and no if it is not.
	 */

	private boolean prime(int size){
		int j =2;
		while (j*j <= size){
			if(size%j == 0){
				return false;
			}
			j++;
		}
		return true;
	}

	/**
	 *   getSize() is the current amount of words in the hashtable.
	 * @return - returns the current amount of words in the hashtable.
	 */
	public int getSize(){
		return count;
	}

	/**
	 *  inserter() is the beginning brain that starts the insertion process of the new word to the hash table.
	 * @param dictionary - the dictionary/hash table we would like to add a new word to.
	 * @param newWord - the new word we would like to add.
	 * @param newArraySize - the current size of the hashtable.
	 */
	private void inserter(SingleLinkedList[] dictionary,String newWord, int newArraySize){
		double IntegerHash = hornerMethod(newWord,newArraySize);
		int insert = (int) (IntegerHash%newArraySize);
		if(dictionary[insert] == null){
			dictionary[insert] = new SingleLinkedList();
		}
		dictionary[insert].insertUnordered(newWord);
	}

	/**
	 *  insert() is used to add a given word into the hash table using the seperate chaining concept.
	 * @param newWord - the new word you would like to add.
	 */

	public void insert(String newWord) {
		newWord = newWord.toLowerCase();
		if (newWord.compareTo("")!= 0 && newWord.compareTo(" ")!= 0 && !search(newWord)) { // if the word is not empty or not a duplicate.
			if (count < 2 * dictionary.length) {
				inserter(dictionary, newWord,dictionary.length);
				count++;
			} else {
				size = primeOutput(dictionary.length * 2);
				temp = new SingleLinkedList[size]; // temporary hash table's array
				for (int i = 0; i < dictionary.length; i++) {
					if (dictionary[i] != null) {
						Node curr = dictionary[i].getTop();
						while (curr != null) {
							inserter(temp, curr.item,size);
							curr = curr.next;
						}
					}
				} // used to increase the hash table and rehash all words again.
				dictionary = new SingleLinkedList[size];
				dictionary = temp;
				inserter(dictionary, newWord,size);
				count++;
			}
		}
	}

	/**
	 *  search() is is used to look for a word in the hash table by using the hashing algorithm
	 *  for Seperate chaining.
	 * @param wordToFind - the word you are looking for.
	 * @return - returns yes if the word is found in the hash table and no if the word is not found.
	 */

	public boolean search(String wordToFind){
		wordToFind =wordToFind.toLowerCase();
		double IntegerHash = hornerMethod(wordToFind,dictionary.length);
		int insert = (int) (IntegerHash%dictionary.length);
		if(dictionary[insert] != null) {
			if (dictionary[insert].search(wordToFind) != null && dictionary[insert].search(wordToFind).item.compareTo(wordToFind) == 0) return true;
		}
		return false;
	}

	/**
	 *  toStrng() is used the generic print for this class.
	 * @return - prints out data of the object.
	 */

	public String toString(){
		String answer ="";
		for(int i =0; i < dictionary.length; i++){
			answer += dictionary[i] +"\n";
		}
		return answer;
	}

}//end class DictionaryChain


class Node{
	public String item; // item / value of current node.
	public Node next; // the node next to the current node.

	public Node(String newItem, Node newNext) {
		this.item = newItem;
		this.next = newNext;
	} //constructor
}//end class Node

class SingleLinkedList {
	private Node top; // top Node of the singly linked list.


	public SingleLinkedList() {
		this.top = null;
	} //Constructor for singly linked list.

	/**
	 *  isEmpty() is used to check if the singly linked list is empty.
	 * @return - return true is there are no nodes in the singly linked list and false if there are at least one node
	 * in the singly linked list.
	 */
	public boolean isEmpty(){
		return top==null;
	}

	/**
	 *  insertUnordered() is used to insert a new item into the beginning of a singly linked list.
	 * @param newItem - the new item you want to add to the singly linked list.
	 */
	public void insertUnordered(String newItem){
		Node newNode = new Node(newItem,top);
		top = newNode;
	}

	/**
	 *  search() used to search through an unordered singly linked list for a node when given a key.
	 * @param key - the key is used to find the node in the singky linked list.
	 * @return - returns the node if found and null if the node is not found.
	 */
	public Node search(String key){
		Node curr = top;
		while(curr != null && curr.item.compareTo(key) != 0) curr = curr.next; // if the current node is not null and the current node value is not equal to key.
		return curr;
	}

	/**
	 * getSize() is used to calculate how many nodes are there in the singly linked list.
	 * @return - return the size of an object's linked list.
	 */
	public int getSize(){
		Node curr = top;
		int count = 0;
		while (curr != null){ // if the linked list is not empty or not at the end and if you have not found your right space in the linked yet.
			curr = curr.next;
			count++;
		} // run the search through the list.
		return count;
	}

	/**
	 * getTop() is used to return the top Node/first Node in a singlylinked list.
	 * @return - returns the top Node or first Node of the object.
	 */

	public Node getTop() {
		return top;
	}

	/**
	 *  toStrng() is used the generic print for this class.
	 * @return - prints out data of the object.
	 */

	public String toString() {
		String answer = "";
		Node curr = top;
		while (curr != null) {
			answer += curr.item +" ";
			curr = curr.next;
		}
		return answer;
	}
} //end class SingleLinkedList

