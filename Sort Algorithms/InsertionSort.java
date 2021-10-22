public class InsertionSort {
    public static void main(String [] args) {
         int[] array = {2,6,9,4,5,8,1,3};
        Descending(array);
        for(int i=0; i<array.length; i++){
            System.out.println(array[i]);
        }
    }
    public static void Descending(int[] array){
        int siftVal;
        int j;
        for(int i =1; i<array.length; i++){
            siftVal = array[i];
            j = i-1;
            while (j>=0 && array[j] < siftVal){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] =siftVal;
        }
    }
    public static void Ascending(int[] array){
        int siftVal; // value you are comparing
        int j; // the length of the sort array
        for(int i =1; i< array.length; i++){
            siftVal = array[i];
            j = i-1;
            while (j>=0 && array[j] > siftVal){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = siftVal;
        }
    }
}

