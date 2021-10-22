import java.util.Random;

public class quickSortDescending {
    public static void main(String [] args) {
        int[] array = {2,6,9,4,5,8,1,3};
        quickSortter(array);
        for(int i =0; i<array.length; i++){
            System.out.println(array[i]);
        }
    }
    public static void quickSortter(int[] array){
        quickSortter(array,0,array.length);
    }
    private static void quickSortter(int[] array,int start,int end){
        int pivotPosn;
        if(2== end - start){
            if(array[start+1] < array[start]){
                swap(array,start,start+1);
            }
        }else if (2 < end - start){
            choosePivot(array, start, end);
            pivotPosn = partition(array, start, end);
            quickSortter(array,start,pivotPosn);
            quickSortter(array,pivotPosn+1, end);
        }
    }
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
            if(array[curr]>pivot){
                swap(array,curr,bigStart);
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
