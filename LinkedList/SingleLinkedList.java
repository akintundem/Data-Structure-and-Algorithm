public class SingleLinkedList {
    private class Node{
        public int item;
        public Node next;

        public Node(int newItem, Node newNext) {
            this.item = newItem;
            this.next = newNext;
        }
    }

    private Node top;

    public SingleLinkedList() {
        this.top = null;
    }

    public boolean isEmpty(){
        return top==null;
    }
    /** insert **/

    public void insertUnordered(int newItem){
        Node newNode = new Node(newItem,top);
        top = newNode;
    }

    public void insertOrdered(int newItem){
        Node prev = null;
        Node curr = top;
        while (curr != null && curr.item < newItem){ // if the linked list is not empty or not at the end and if you have not found your right space in the linked yet.
            prev = curr;
            curr = curr.next;
        }
        Node newNode = new Node(newItem,curr);
        if(prev != null){
            prev.next = newNode;
        } // if it is not in the beginning of the linked list
        top = newNode;

    }
    // top 2/- 3/- 4/-
    /** search **/
    private Node search(int key){
        Node curr = top;
        while(curr != null && curr.item !=  key) curr = curr.next;
            if(curr!= null && curr.item != key) curr = null;
        return curr;
    }


    /** delete **/

    public void delete(int key){
        Node prev = null;
        Node curr = top;
        while (curr != null && curr.item < key){ // if the linked list is not empty or not at the end and if you have not found your right space in the linked yet.
            prev = curr;
            curr = curr.next;
        } // run the search through the list.
        if(prev != null) {
            prev.next = curr.next;
        } else {
            top = curr.next;
        }// if it is at the beginning of the linked list.
    }
    /** getSize **/

    public int getSize(){
        Node curr = top;
        int count = 0;
        while (curr != null){ // if the linked list is not empty or not at the end and if you have not found your right space in the linked yet.
            curr = curr.next;
            count++;
        } // run the search through the list.
    return count;
    }

    /** copy **/
    public SingleLinkedList copy(){
        Node curr = top;
        SingleLinkedList deepCopy = new SingleLinkedList();
        while (curr != null){ // if the linked list is not empty or not at the end and if you have not found your right space in the linked yet.
           deepCopy.insertOrdered(curr.item);
           curr = curr.next;
        }
        return deepCopy;
    }
    public String toString() {
        String answer = "";
        Node curr = top;
        while (curr != null) {
            answer += curr.item + " ";
            curr = curr.next;
        }
        return answer;
    }
}
