public class MergeSortAscending {
    public static void main(String [] args) {
        int[] array = {6, 2, 9, 4, 5, 8, 1, 3};
        MergeSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    /**
    public static void MergeSort(int[] array){
        int[] temp = new int[array.length];
        MergeSort(array,0, array.length, temp);
    }
    private static void MergeSort(int[] array, int start, int end, int[] temp){
        int mid;
        if(1<end-start){
            mid = start +(end-start)/2;
            MergeSort(array,start,mid,temp);
            MergeSort(array,mid,end,temp);
            Merge(array,start,mid,end,temp);
        }
    }
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
}
**/

 public class MergeSortDescending {
 public static void main(String[] args) {
 int[] array = {6, 2, 9, 4, 5, 8, 1, 3};
 int[] array1 = {50, 10};
 mergeSort(array1);
 for (int i = 0; i < array1.length; i++) {
 System.out.println(array1[i]);
 }
 }

 public static void mergeSort(int[] array) {
 int[] temp = new int[array.length];
 MergeSort(array, 0, array.length, temp);
 }
 private static void swap( int[] array, int i, int j ) {
 int temp = array[ i ];
 array[ i ] = array[ j ];
 array[ j ] = temp;
 } // end swap

 private static void MergeSort(int[] array, int start, int end, int[] temp) {
 int mid;
 if(2== end - start){
 if(array[start+1] < array[start]){
 swap(array,start,start+1);
 }
 }


 if (1 < end - start) {
 mid = start + (end - start) / 2;
 MergeSort(array, start, mid, temp);
 MergeSort(array, mid, end, temp);
 Merge(array, start, mid, end, temp);
 }

 }

 private static void Merge(int[] array, int start, int mid, int end, int[] temp) {
 int CurrL = start; // index of current item in left half
 int CurrR = mid;  // index of current item in right half
 int CurrT; // index in temp
 for (CurrT = start; CurrT < end; CurrT++) {
 if (CurrL < mid && (CurrR >= end || array[CurrL] < array[CurrR])) {
 temp[CurrT] = array[CurrL];
 CurrL++;
 } else {
 temp[CurrT] = array[CurrR];
 CurrR++;
 }
 }
 for (int i = start; i < end; i++) {
 array[i] = temp[i];
 }
 }
 }
 