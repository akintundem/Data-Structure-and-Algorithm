/**
 * Name of class or program: AkintundeMayokunA5Q1.java
 * COMP 2140 SECTION D01
 * ASSIGNMENT    Assignment 5, question 1
 * @author       Mayokun Moses Akintunde, 7884253
 * @version      11/08/2021
 *
 * PURPOSE: The program is to simulate a Hospital's Emergency Room.
 */
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class AkintundeMayokunA5Q1 {
    public static void main(String[] args) {
        try {
            System.out.println("Please enter the input file name (.txt files only):");
            Scanner inputTxt = new Scanner(new FileReader(new File((new Scanner("patients.txt")).nextLine())));
            processing(inputTxt);
            System.out.println("Program terminated normally.");
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }//main

    /**
     * processing() will take in the information from the .txt file and send it to the receptionOffice for processing.
     * @param file - The .txt file that was scanned
     */
    private static void processing(Scanner file){
        String[] lines = new String[100];
        int count = 0;
        while (file.hasNext()){
            lines[count++] = file.nextLine();
        }
        receptionOffice(count,lines);
    }

    /**
     * receptionOffice() is used to handle the doctor's and patient schedule
     * @param count - How many patients are expected to join.
     * @param lines - All information concerning all our patients.
     */
        private static void receptionOffice(int count, String[]lines ){
            int clock = 0; // This is the real time clock clicking. We will assume 1000000 is when the clock will end.
            int patients = 0; // used to count the current amount of patient processed in the reception office
            Heap hospital = new Heap(100); // This is used to sort out patients based on priority
            Patient newPatient; // Every new patients information created.
            boolean doctorInOffice = true; // Is the doctor in the office?
            int doctorTime = 0; // The amount of time doctor is using to take care of patients
            final int CLOCKFINALTIME = 1000000; // Assume final time of clock
            while (clock < CLOCKFINALTIME ){
                if( patients < count && Integer.parseInt(lines[patients].split(" ")[0]) == clock ) {
                    newPatient = new Patient(Integer.parseInt(lines[patients].split(" ")[1]), Integer.parseInt(lines[patients].split(" ")[2]), Integer.parseInt(lines[patients].split(" ")[0])); //Create new Patient
                    hospital.insert(newPatient); //Add that patient to the priority list
                    System.out.println("Patient " + newPatient.getPatientNo() + " arrived at time = " + newPatient.getArrivalTime() + " with urgency = " + newPatient.getUrgency() + " and treatment time = " + newPatient.getTreatmentTime() + ".");
                    patients++;
                } // Reception's office
                //Doctor's Office
                if (clock == doctorTime){
                    doctorInOffice = false;
                    System.out.println("Doctor is available at time = " + clock);
                } // When the doctor is done with a patient, then doctor should be out of the office.
                else if(!hospital.isEmpty() && !doctorInOffice){
                    if(hospital.peek().getArrivalTime() > doctorTime){
                        doctorTime = hospital.peek().getArrivalTime(); // Use the arrival time
                        System.out.println("Doctor is available at time = " + clock);
                        System.out.println("Patient " + hospital.peek().getPatientNo() + " in for treatment at time = " + doctorTime + " with urgency = " + hospital.peek().getUrgency() + " and treatment time = " + hospital.peek().getTreatmentTime() + ".");
                        doctorTime += hospital.peek().getTreatmentTime(); // add the treatment time
                        hospital.deleteMax(); // Remove the most important patient at the top of the priority list
                        doctorInOffice = true;
                    } // If doctor is free and not currently with a patient
                    else {
                        System.out.println("Patient " + hospital.peek().getPatientNo() + " in for treatment at time = " + doctorTime + " with urgency = " + hospital.peek().getUrgency() + " and treatment time = " + hospital.peek().getTreatmentTime() + ".");
                        doctorTime += hospital.peek().getTreatmentTime();// add the treatment time
                        hospital.deleteMax();// Remove the most important patient at the top of the priority list
                        doctorInOffice = true;
                    } //If doctor is currently with a patient
                }
                clock++;
            }//clock
    } //Loop
}

class Patient{
    private int patientNo;
    private static int number = 1;
    private int urgency;
    private int treatmentTime;
    private int arrivalTime;

    public Patient(int urgency, int treatmentTime,int arrivalTime){
        this.patientNo = number++;
        if(urgency>=1 && urgency<=10){
            this.urgency = urgency;
        }
        this.treatmentTime = treatmentTime;
        this.arrivalTime = arrivalTime;
    } // Constructor

    public int getArrivalTime() {
        return arrivalTime;
    } // getter for arrival time

    public int getPatientNo() {
        return patientNo;
    } // getter for patient number

    public int getUrgency() {
        return urgency;
    } //getter for urgency of patient

    public int getTreatmentTime() {
        return treatmentTime;
    } // getter for treatment time

    /**
     * toString() it is used to print out the object
     * @return return a string format of the object you are working with.
     */
    public String toString(){
        return "Patient " + patientNo + ": " + "\n" + "Urgency of Patient's Condition: " + urgency + "\n" + "Expected Treatment time: " + treatmentTime + "\n \n" ;
    }
}


class Heap {
    private Patient[] heap;
    private int heapSize;

    public Heap( int maxSize ) {
        heap = new Patient[ maxSize ];
        heapSize = 0;
    } //Constructor

    /**
     * isEmpty() is used to check if Heap is empty
     * @return True if empty and False and if it is not empty
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * isFull() is used to check if Heap is full
     * @return True if full and False and if it is not full
     */
    public boolean isFull() {
        return heapSize >= heap.length;
    } // end isFull

    /**
     * parent() is used to calculate the parent of a node
     * @param i - the index of the node
     * @return - the index of the parent of the given node.
     */
    private static int parent( int i ) {
        return (i-1)/2;
    } // end parent

    /**
     * rightChild() is used to calculate the right child of a parent
     * @param i - the index of the node
     * @return - the index of the right child of the given parent.
     */
    public static int rightChild( int i ) {
        return 2 * i + 2;
    } // end rightChild

    /**
     * leftChild() is used to calculate the left child of a parent
     * @param i - the index of the node
     * @return - the index of the left child of the given parent.
     */
    public static int leftChild( int i ) {
        return 2 * i + 1;
    } // end leftChild

    /**
     * insert() is used to insert a patient into a Heap
     * @param priority - the patient you would like to insert
     */
    public void insert( Patient priority ) {
        if ( ! isFull() ) {
            heap[ heapSize ] = priority;
            heapSize++;
            siftUp( heapSize-1 ); // used to sort out based on priority
        } // end if
    } // end insert

    /**
     * deleteMax() is used to delete the root which his the highest priority
     * @return - the root/highest priority deleted
     */
    public Patient deleteMax(){
        Patient max = null;
        if(!isEmpty()){
        max = heap[0];
        swap(heap, 0, heapSize - 1);// swap first and last
        heap[heapSize - 1] = null;
        heapSize--;
        siftDown(heap, 0);
        }
        return max;
    }

    /**
     * peek() is used to check the root of the heap
     * @return - returns the root of the heap
     */
    public Patient peek(){
        return heap[0];
    }

    /**
     * maxChild() used to check the highest priority between siblings
     * @param left - left child
     * @param right - right child
     * @return - returns the highest priority between siblings
     */
    private int maxChild(int left, int right){
        if(heap[left].getUrgency() == Math.max(heap[left].getUrgency(),heap[right].getUrgency())){
            return left;
        }
        return right;
    }

    /**
     * siftUp is used to order heap from bottom to the top after a new patient has been added
     * @param index - the index of the new added patient
     */
    private void siftUp( int index ) {
        int i = index;
        while ( i > 0 && heap[parent( i )].getUrgency() < heap[i].getUrgency() ) {
            swap(heap,i,parent( i ));
            i = parent( i );
        } // end while
    } // end siftUp

    /**
     * siftDown() is used to order heap from top to bottom after the the root/highest priority patient has been deleted
     * @param heap - the heap in question
     * @param i - the index of the bottom patient
     */
    private void siftDown( Patient[] heap,int i ) {
        int largest = i;
        int leftChild = leftChild(i);
        int rightChild = rightChild(i);
        if(leftChild< heapSize && rightChild < heapSize){
            int large = maxChild(leftChild,rightChild);
            if(heap[large].getUrgency() > heap[largest].getUrgency()){
                largest = large;
            }
            if(largest != i){
                swap(heap,i,largest);
                siftDown(heap,largest);
            }
        }
    }

    /**
     * swap() is used to swap two elements.
     * @param array - the array in question
     * @param i - the first index
     * @param j - the second index
     */
    private static void swap( Patient[] array, int i, int j ) {
        Patient temp = array[ i ];
        array[ i ] = array[ j ];
        array[ j ] = temp;
    } // end swap

    /**
     * toString() it is used to print out the object
     * @return return a string format of the object you are working with.
     */
    public String toString(){
        String answer ="";
        for(int i = 0; i < heapSize; i++){
            answer += heap[i]+ " ";
        }
        return answer;
    }
} // end class MaxHeap