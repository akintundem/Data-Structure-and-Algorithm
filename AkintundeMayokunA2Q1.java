import java.util.*;
import java.io.*;
/**
 * Name of class or program:AkintundeMayokunA2Q1.java
 *
 * COMP 2140 SECTION D01
 * ASSIGNMENT Assignment 2, question 1
 * @author Mayokun Moses Akintunde, 7884253
 * @version 15th of June, 2021
 *
 * PURPOSE: The program will report the execution time for seven sorting algorithms.
 */

/*
 * A2Q1SortingTemplate
 *
 * COMP 2140 SECTION D01 SUMMER 2021
 *
 */

public class AkintundeMayokunA2Q1 {

    // Control the testing
    private static final int ARRAY_SIZE = 10000;
    private static final int SAMPLE_SIZE = 300; // The number of trials in each experiment.

    // Control the randomness
    private static final int NUM_SWAPS = ARRAY_SIZE / 2;
    private static Random generator = new Random( System.nanoTime() );

    // Control the base cases for hybrid quick sort:
    private static final int BREAKPOINT = 50;

    // Controls which sort is tried.
    private static final int INSERTION_SORT = 0;
    private static final int BUBBLE_SORT = 1;
    private static final int SELECTION_SORT = 2;
    private static final int MERGE_SORT = 3;
    private static final int QUICK_SORT = 4;
    private static final int HYBRID_QUICK_SORT = 5;
    private static final int SHELL_SORT = 6;

/*********** main and the method it calls *************************/

    /*******************************************************************
     * main
     *
     * Purpose: Print out "bookend" messages and call the method that
     *          does all the testing of the sorting algorithms.
     *
     ******************************************************************/
    public static void main( String[] args ) {
		System.out.println( "\n\nCOMP 2140 A2Q1 Sorting Test --- Summer 2021\n" );

		testSorts();

		System.out.println( "\nProcessing ends normally\n" );
    } // end main


    /*******************************************************************
     * testSorts
     *
     * Purpose: Run each sorting algorithm SAMPLE_SIZE times,
     *          on an array of size ARRAY_SIZE with NUM_SWAPS
     *          random swaps performed on it.
     *          Compute the arithmetic mean of the timings for each sorting algorithm.
     *
     *          Print the results.
     *
     ******************************************************************/
    private static void testSorts() {

		// Arrays used in timing experiments (create these arrays once)
		int[] array = new int[ARRAY_SIZE]; // array to be sorted
		long[] sortTime = new long[ SAMPLE_SIZE ]; // store timings for multiple runs
	                                           // of a single sorting method
		// Fill array to be sorted with the numbers 0 to ARRAY_SIZE.
		// (The numbers will be randomly swapped before each sort.)
		fillArray( array );

		// Now run the experiments on all the sorts
		System.out.println("Array size: " + ARRAY_SIZE + "\nNumber of swaps: " + NUM_SWAPS);
		System.out.println("Number of trials of each sort: " + SAMPLE_SIZE );

		// Stats for each run
		System.out.println("\nInsertion sort mean: "
			   + tryOneSort( array, sortTime, INSERTION_SORT )
			   + " ns" );
		System.out.println("Bubble sort mean: "
			   + tryOneSort( array, sortTime, BUBBLE_SORT )
			   + " ns" );
		System.out.println("Selection sort mean: "
			   + tryOneSort( array, sortTime, SELECTION_SORT )
			   + " ns" );
		System.out.println("Merge sort mean: "
			   + tryOneSort( array, sortTime, MERGE_SORT )
			   + " ns" );
		System.out.println("Quick sort mean: "
			   + tryOneSort( array, sortTime, QUICK_SORT )
			   + " ns" );
		System.out.println("Hybrid quick sort mean: "
			   + tryOneSort( array, sortTime, HYBRID_QUICK_SORT )
			   + " ns" );
		System.out.println("Shell sort mean: "
			   + tryOneSort( array, sortTime, SHELL_SORT )
			   + " ns" );

    } // end testSorts

/*********** methods called by testSorts *************************/

    /*******************************************************************
     * tryOneSort:
     *
     * Purpose: Get an average run time for a sorting algorithm.
     *
     * Methodology: Run the chosen sorting algorithm SAMPLE_SIZE times,
     *          on an array of size ARRAY_SIZE with NUM_SWAPS
     *          random swaps performed on it.
     *          Return the arithmetic mean of the timings.
     *
     ******************************************************************/
    private static double tryOneSort( int[] array, long[] sortTime, int whichSort ) {

		long start, stop, elapsedTime;  // Time how long each sort takes.

		start = stop = 0; // because the compiler complains that they might
		                  // not have been initialized inside the for-loop

		for ( int i = 0; i < SAMPLE_SIZE; i++ ) {
		    randomizeArray( array, NUM_SWAPS );
		    if ( whichSort == INSERTION_SORT ) {
				start = System.nanoTime();
				insertionSort( array );
				stop = System.nanoTime();
				checkArray(array, "Insertion sort");
		    } else if ( whichSort == BUBBLE_SORT ) {
				start = System.nanoTime();
				bubbleSort( array );
				stop = System.nanoTime();
				checkArray(array, "Bubble sort");
		    } else if ( whichSort == SELECTION_SORT ) {
				start = System.nanoTime();
				selectionSort( array );
				stop = System.nanoTime();
				checkArray(array, "Selection sort");
		    } else if ( whichSort == MERGE_SORT ) {
				start = System.nanoTime();
				mergeSort( array );
				stop = System.nanoTime();
				checkArray(array, "Merge sort");
		    } else if ( whichSort == QUICK_SORT ) {
				start = System.nanoTime();
				quickSort( array );
				stop = System.nanoTime();
				checkArray(array, "Quick sort");
		    } else if ( whichSort == HYBRID_QUICK_SORT ) {
				start = System.nanoTime();
				hybridQuickSort( array );
				stop = System.nanoTime();
				checkArray(array, "Hybrid quick sort");
		    } else if ( whichSort == SHELL_SORT ) {
				start = System.nanoTime();
				shellSort( array );
				stop = System.nanoTime();
				checkArray(array, "Shell sort");
		    }
		    elapsedTime = stop - start;
		    sortTime[i] = elapsedTime;
		} // end for

		return arithmeticMean( sortTime );
    } // end tryOneSort


/********** Add sort methods here ********************/
	/**
	 * The insertionSort() is a non-recursive sorting algorithm that calls its private helper.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 */
	public static void insertionSort(int[] array){
	insertionSort(array, 0, array.length);
}

	/**
	 * The private insertionSort() is a non recursive algorithm that sorts an array by choosing
	 * from a number from the unsorted (Right Part of array) and fixing it in the apprioriate
	 * location in the sorted array (Left Part of array).
	 * @param array : This is the array input that will be sorted into an ascending form.
	 * @param start : The beginning of array which is 0.
	 * @param end : The length of array.
	 */
	private static void insertionSort( int[] array, int start, int end){
	int siftVal; // value you are comparing
	int j; // the length of the sort array
	for(int i =1; i< end; i++){
		siftVal = array[i];
		j = i-1;
		while (j>=0 && array[j] > siftVal){
			array[j+1] = array[j];
			j--;
		}
		array[j+1] = siftVal;
		}
	}

	/**
	 *  The bubbleSort() is a non recursive sorting algorithm that looks for the largest number
	 *  in the sorted array and puts it at the end of the array.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 */
	public static void bubbleSort(int[] array){
		int temp;
		for(int i =0; i < array.length; i++){
			for(int j=0; j< array.length-i-1; j++){
				if(array[j] > array[j+1]){
					//swap element
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}// looping through unsorted to find the largest then bubble it outside.
		}// repeat process the number of times the element equal to.
	}
	/**
	 * The mergeSort() is a recursive sorting algorithm that calls its private helper.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 */
	public static void mergeSort(int[] array){
		int[] temp = new int[array.length];
		MergeSort(array,0, array.length, temp);
	}
	/**
	 * The private mergeSort() is a recursive algorithm that splits arrays into smaller
	 * pieces recursively then sorting it before merging all the sorted array into one array.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 * @param start : where you want to start from in array.
	 * @param end : where you want to end in an array.
	 * @param temp : save the sorted algorithms in a temporary array
	 */
	private static void MergeSort(int[] array, int start, int end, int[] temp){
		int mid;
		if(2== end - start){
			if(array[start+1] < array[start]){
				swap(array,start,start+1);
			}
		}//base case
		if(1<end-start){
			mid = start +(end-start)/2;
			MergeSort(array,start,mid,temp);
			MergeSort(array,mid,end,temp);
			Merge(array,start,mid,end,temp);
		}
	}

	/**
	 *The Merge() is a method that is supposed to merge to sorted arrays into one sorted array.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 * @param start : where you want to start from in array.
	 * @param mid :  The index of current item in right half of array.
	 * @param end : where you want to end in an array.
	 * @param temp : save the sorted algorithms in a temporary array
	 */
	private static void Merge(int[] array, int start, int mid, int end, int[] temp){
		int CurrL = start; // index of current item in left half
		int CurrR =mid;  // index of current item in right half
		int CurrT; // index in temp
		for(CurrT = start; CurrT < end; CurrT++){
			if(CurrL < mid && (CurrR >= end || array[CurrL] < array[CurrR])){
				temp[CurrT] = array[CurrL];
				CurrL++;
			}else {
				temp[CurrT] = array[CurrR];
				CurrR++;
			}
		}
		for(int i =start; i < end; i++){
			array[i] =temp[i];
		}
	}
	/*****************************************************************/
	/**
	 * The quickSort() is recursive sorting algorithm that calls its private helper.
	 *@param array : This is the array input that will be sorted into an ascending form.
	 */
	public static void quickSort(int[] array){
		quickSort(array,0,array.length);
	}

	/**
	 * The private quickSort() is a recursive sorting algorithm that sorts an
	 * array by using a pivot which will put numbers less than the pivot to the
	 * left and the numbers bigger than it to the right.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 * @param start : where you want to start from in array.
	 * @param end : where you want to end in an array.
	 */
	private static void quickSort(int[] array,int start,int end){
		int pivotPosn;
		if(2== end - start){
			if(array[start+1] < array[start]){
				swap(array,start,start+1);
			}
		}else if (2 < end - start){
			choosePivot(array, start, end);
			pivotPosn = partition(array, start, end);
			quickSort(array,start,pivotPosn);
			quickSort(array,pivotPosn+1, end);
		}
	}

	/**
	 * The choosePivot() is supposed to choose a pivot using the median of 3 method of an array
	 * from start to end.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 * @param start : where you want to start from in array.
	 * @param last : where you want to end in an array.
	 */
	public static void choosePivot(int[] array, int start, int last){
		int end = last-1;
		int mid = (end-start)/2;
		int middle = end;
		if((array[start] < array[mid] && array[mid] < array[end]) || (array[end] < array[mid] && array[mid]<array[start])){
			middle = mid;
		} else  if((array[mid] < array[start] && array[start] < array[end]) || (array[end] < array[start] && array[start]<array[mid])){
			middle = start;
		}
		swap(array,middle,start);
	}

	/**
	 * The partition() is used to but all numbers lower than the pivot to the left of array.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 * @param start : where you want to start from in array.
	 * @param end : where you want to end in an array.
	 * @return return pivots position in array
	 */
	public static int partition(int[] array, int start, int end){
		int bigStart = start +1;
		int pivot = array[start];
		for (int curr = start + 1; curr < end; curr++){
			if(array[curr]<pivot){
				swap(array,bigStart,curr);
				bigStart++;
			}
		}
		swap(array,start,bigStart-1); // put pivot in the middle
		return bigStart-1; // return pivots position
	}

	/**
	 * The selectionSort() is used to sort an array by finding the smallest in the
	 * unsorted array and swapping the current index of a loop.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 */

	public static void selectionSort(int[] array){
		for(int k = 0; k < array.length-1; k++){
			int minIndex = findMin(array,k,array.length);
			swap(array, k, minIndex);
		}
	}

	/**
	 * The findMin() is used to ind the smallest number in the unsorted array.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 * @param start : where you want to start from in array.
	 * @param end : where you want to end in an array.
	 * @return The index of the smallest number in the array.
	 */
	public static int findMin(int[] array, int start, int end) {
		int min = array[start];
		int minIndex = start;
		for (int i = start + 1; i < end; i++) {
			if (array[i] < min) {
				min = array[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	/**
	 * The shellSort() is used to sort numbers implementing insertion sort for decreasing gaps.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 */
	public static void shellSort( int[] array ){
	int h = 1;
	while (h <= array.length )h = h*3+1;  //Knuth’s sequence
	int temp;
	 while (h>0){
	 	for (int outer=h; outer<array.length; outer++){
	 	//move a[outer] into position
		 temp = array[outer];
		 int inner = outer;
		 while (inner > h-1 && array[inner-h] >= temp){//consider every hthitem
		 	 array[inner] = array[inner-h];
		 	 inner -= h;
		    }
		 array[inner] = temp;
	 	}//end for
		  h = (h-1)/3;//Knuth’s sequence
	 }//end while
}


	/**
	 * The hybridQuickSort() is non-recursive sorting algorithm that calls its private helper.
	 * @param array : This is the array input that will be sorted into an ascending form.
	 */
	public static void  hybridQuickSort(int[] array){
	hybridQuickSort(array,0,array.length);
}

	/**
	 * @param array : This is the array input that will be sorted into an ascending form.
	 * @param start : where you want to start from in array.
	 * @param end : where you want to end in an array.
	 */
	private static void  hybridQuickSort(int[] array,int start,int end) {
		int pivotPosn;
		if (2 == end - start) {
			if (array[start + 1] < array[start]) {
				swap(array, start, start + 1);
			}
		}
		else if (array.length < BREAKPOINT) {
			insertionSort(array,start,end);
		} else if (2 < end - start) {
				choosePivot(array, start, end);
				pivotPosn = partition(array, start, end);
				hybridQuickSort(array, start, pivotPosn);
				hybridQuickSort(array, pivotPosn + 1, end);
			}

	}

	/**
	 * OUTPUT:
	 * COMP 2140 A2Q1 Sorting Test --- Summer 2021
	 *
	 * Array size: 10000
	 * Number of swaps: 5000
	 * Number of trials of each sort: 300
	 *
	 * Insertion sort mean: 1.0061324E7 ns
	 * Bubble sort mean: 1.0470820066666667E8 ns
	 * Selection sort mean: 2.4880994333333332E7 ns
	 * Merge sort mean: 873091.0 ns
	 * Quick sort mean: 703809.3333333334 ns
	 * Hybrid quick sort mean: 597419.6666666666 ns
	 * Shell sort mean: 1054454.0 ns
	 *
	 *
	 * REPORT:
	 * 1. Was insertion sort faster than selection sort? Why or why not?
	 * Answer: The insertion sort algorithm in the best case is an O(n) algorithm but the
	 * Selection sort algorithm is an O(n^2) in all cases. This is because in selection sort,
	 * the two loops runs twice during execution but in insertion sort, if the current number to be added
	 * is after the last sorted number in the sorted part of the array then the loop will run only once
	 * thus the algorithm will only loop once.
	 *
	 *
	 * 2. Was quick sort faster than insertion sort? Why or why not?
	 * Answer: The quick sort algorithm is faster than the the insertion sort. In average cases, the quick sort
	 * algorithm runs in O(nlogn) and the insertion sort O(n^2). This is because in quick sort algorithm
	 * the algorithm is breaking down the array in logn and perform sorting functions before merging
	 * but in Selection Sort algorithm, the algorithm is looping all elements in unsorted part of array
	 * and finding a way to put to loop the minimum element before swapping.
	 *
	 *
	 * 3. Was hybrid quick sort faster than quick sort? Why or why not?
	 * Answer: The hybrid quick sort algorithm is a little faster than the quick sort algorithm.
	 * This is because the hybrid quick sort algorithm also implements the insertion sort algorithm,
	 * thus it makes it easier for the actual quick sort algorithm to run much faster.
	 *
	 * 4. Which sort(s) would you recommend to others, and why?
	 * Answer: I will recommend the Hybrid quick sort algorithm because after multiple test attempt,
	 * I have found out that the hybrid quick sort algorithm sorts the array in the shortest time
	 * possible. I believe this is because it is both implementing the insertion sort and quick sort.
	 *
	 * 5. Which sort(s) would you warn others against using, and why?
	 * Answer: I will advise against using the selection sort algorithm. The execution time
	 * for this algorithm is the longest.
	 *
	 *
	 */


/****************** Other miscellaneous methods ********************/

    /*******************************************************************
     * swap
     *
     * Purpose: Swap the items stored in positions i and j in array.
     *
     ******************************************************************/
    private static void swap( int[] array, int i, int j ) {
		int temp = array[ i ];
		array[ i ] = array[ j ];
		array[ j ] = temp;
    } // end swap


    /*******************************************************************
     * isSorted
     *
     * Purpose: Return true if the input array is sorted into
     *          ascending order; return false otherwise.
     *
     * Idea: If every item is <= to the item immediately after it,
     *       then the whole list is sorted.
     *
     ******************************************************************/
    public static boolean isSorted( int[] array ) {
		boolean sorted = true;

		// Loop through all adjacent pairs in the
		// array and check if they are in proper order.
		// Stops at first problem found.
		for ( int i = 1; sorted && (i < array.length); i++ )
		    sorted = array[i-1] <=  array[i];
		return sorted;
    } // end method isSorted

    /*******************************************************************
     * checkArray
     *
     * Purpose: Print an error message if array is not
     *          correctly sorted into ascending order.
     *          (If the array is correctly sorted, checkArray does nothing.)
     *
     ******************************************************************/
    private static void checkArray(int[] array, String sortType) {
		if ( !isSorted( array ) )
		    System.out.println( sortType + " DID NOT SORT CORRECTLY *** ERROR!!" );
    }

    /*******************************************************************
     * fillArray
     *
     * Purpose: Fills the given array with the numbers 0 to array.length-1.
     *
     ******************************************************************/
    public static void fillArray( int[] array ) {

		for ( int i = 0; i < array.length; i++ ) {
		    array[i] = i;
		} // end for

    } // end fillArray

    /*******************************************************************
     * randomizeArray
     *
     * Purpose: Does numberOfSwaps swaps of randomly-chosen positions
     *          in the given array.
     *
     ******************************************************************/
    public static void randomizeArray( int[] array, int numberOfSwaps ) {
		for ( int count = 0; count < numberOfSwaps; count++ ) {
		    int i = generator.nextInt( array.length );
		    int j = generator.nextInt( array.length );
		    swap( array, i, j );
		}
    } // end randomizeArray


    /*******************************************************************
     * arithmeticMean
     *
     * Purpose: Compute the average of long values.
     *          To avoid long overflow, use type double in the computation.
     *
     ******************************************************************/
    public static double arithmeticMean(long data[]) {
		double sum = 0;
		for (int i = 0; i < data.length; i++)
		    sum += (double)data[i];
		return sum / (double)data.length;
    } // end arithmeticMean

} // end class A2Q1SortingTemplate
