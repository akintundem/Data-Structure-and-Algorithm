public class MaxHeap{
    public static void main(String[] args) {
    Heap maxHeap = new Max(11);
    maxHeap.insert(14);
    maxHeap.insert(9);
    maxHeap.insert(13);
    maxHeap.insert(8);
    maxHeap.insert(2);
    maxHeap.insert(12);
    maxHeap.insert(11);
    maxHeap.insert(1);
    System.out.println(maxHeap);
    System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap);
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.deleteMax());
    System.out.println(maxHeap);


    }
}

class Heap {
    private int[] heap;
    private int heapSize;

    public Heap( int maxSize ) {
        heap = new int[ maxSize ];
        heapSize = 0;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize >= heap.length;
    } // end isFull


    private static int parent( int i ) {
        return (i-1)/2;
    } // end parent


    public static int rightChild( int i ) {
        return 2 * i + 2;
    } // end rightChild


    public static int leftChild( int i ) {
        return 2 * i + 1;
    } // end leftChild


    public void insert( int priority ) {
        if ( ! isFull() ) {
            heap[ heapSize ] = priority;
            heapSize++;
            siftUp( heapSize-1 );
        } // end if
    } // end insert

    public int deleteMax(){
        int max = 0;
        if(!isEmpty()){
            max = heap[0];
            swap(heap, 0, heapSize - 1);// swap first and last
            heap[heapSize - 1] = 0;
            heapSize--;
            siftDown(heap, 0);
        }
        return max;
    }

    public int peek(){
        return heap[0];
    }

    private int maxChild(int left, int right){
        if(heap[left]== Math.max(heap[left],heap[right])){
            return left;
        }
        return right;
    }

    private void siftUp( int index ) {
        int i = index;
        while ( i > 0 && heap[parent( i )] < heap[i]) {
            swap(heap,i,parent( i ));
            i = parent( i );
        } // end while
    } // end siftUp

    private void siftDown( int[] heap,int i ) {
        int largest = i;
        int leftChild = leftChild(i);
        int rightChild = rightChild(i);
        if(leftChild< heapSize && rightChild < heapSize){
            int large = maxChild(leftChild,rightChild);
            if(heap[large] > heap[largest]){
                largest = large;
            }
            if(largest != i){
                swap(heap,i,largest);
                siftDown(heap,largest);
            }
        }
    }
    private static void swap( int[] array, int i, int j ) {
        int temp = array[ i ];
        array[ i ] = array[ j ];
        array[ j ] = temp;
    } // end swap

    public String toString(){
        String answer ="";
        for(int i = 0; i < heapSize; i++){
            answer += heap[i]+ " ";
        }
        return answer;
    }
} // end class MaxHeap