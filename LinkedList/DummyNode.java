public class DummyNode {
    class Node{
        private int item;
        private Node next;
        public Node(int item, Node next){
            this.item =item;
            this.next = next;
        }
    }
    private Node top;
    private Node tail;
    public DummyNode(){
        tail = new Node(Integer.MAX_VALUE, null);
        top = new Node(Integer.MIN_VALUE,tail);
    }
    public void insertOrdered(int newItem){
        Node prev =  null;
        Node curr = top;
        while (curr != null && curr.item < newItem){
            prev = curr;
            curr = curr.next;
        }
        prev.next = new Node(newItem,curr);
    }

    public void deleteUnOrdered(int key){
        Node prev =  null;
        Node curr = top;
        while (curr!=null && curr.item != key){
            prev = curr;
            curr = curr.next;
        }
        if(curr != null) {
            prev.next = curr.next;
        }
    }

    public void unOrderedInsert(int newItem){
            top.next = new Node(newItem, top.next);
    }

    public int getSize(){
        Node curr = top;
        int count = 0;
        while (curr != null){ // if the linked list is not empty or not at the end and if you have not found your right space in the linked yet.
            curr = curr.next;
            count++;
        } // run the search through the list.
        return count-2;
    }

    public Node search(int key){
        Node curr = top;
        while (curr!=null && curr.item != key){
            curr = curr.next;
        }
        return curr;
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

    public DummyNode copy(){
        Node curr = top;
        DummyNode deepCopy = new DummyNode();
        while (curr != null){ // if the linked list is not empty or not at the end and if you have not found your right space in the linked yet.
            deepCopy.insertOrdered(curr.item);
            curr = curr.next;
        }
        return deepCopy;
    }
}
