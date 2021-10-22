import java.util.Random;

public class hybridQuickSort {
    private static final int BREAKPOINT = 50;
    public static void main(String [] args) {
        int[] array = {2,6,9,4,5,8,1,32,6,9,4,5,8,1,3};
        hybridQuickSort(array);
        for(int i =0; i<array.length; i++){
            System.out.println(array[i]);
        }
    }
    public static void  hybridQuickSort(int[] array){
        hybridQuickSort(array,0,array.length);
    }

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

    private static void  hybridQuickSort(int[] array,int start,int end) {
        int pivotPosn;
        if (array.length < BREAKPOINT) {
            insertionSort(array,0,array.length);
        }  else {

            if (2 == end - start) {
                if (array[start + 1] < array[start]) {
                    swap(array, start, start + 1);
                }
            } else if (2 < end - start) {
                choosePivot(array, start, end);
                pivotPosn = partition(array, start, end);
                hybridQuickSort(array, start, pivotPosn);
                hybridQuickSort(array, pivotPosn + 1, end);
            }
        }

    }
    /**
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
     **/
    public static void choosePivot(int arr[],int start,int end){
        Random rand = new Random();
        int pivot = rand.nextInt(end-start)+start;
        int temp =arr[pivot];
        arr[pivot]=arr[start];
        arr[start]=temp;
    }

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


    public static void swap(int[] array, int i , int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
