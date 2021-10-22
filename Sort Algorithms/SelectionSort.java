public class SelectionSort {
    public static void main(String [] args) {
        int[] array = {0,5,4,6,1,2,10,563,23,223,3453,12,34,5,4,23,23,4,54,5,6,23,1};
       // System.out.println(array.length);
       // System.out.println(findmin(array,0, array.length));
        Ascending(array);
        for(int i =0; i<array.length; i++){
           System.out.println(array[i]);
        }
    }

    public static int findmin(int[] array, int start, int end) {
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

    private static void swap( int[] array, int i, int j ) {
        int temp = array[ i ];
        array[ i ] = array[ j ];
        array[ j ] = temp;
    } // end swap

    public static void Ascending(int[] array){
        for(int k = 0; k < array.length-1; k++){
            int minIndex = findmin(array,k,array.length);
            swap(array, k, minIndex);
        }
    }
}
