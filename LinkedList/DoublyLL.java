public class DoublyLL {
    class Node{
        private int item;
        private Node prev;
        private Node next;
        public Node(int item,Node prev,Node next){
            this.item =item;
            this.prev = prev;
            this.next = next;
        }
    }
    private Node top;
    public DoublyLL(){
        this.top = null;
    }

    public void prepend(int newItem){
        if(top == null){
            top = new Node(newItem,null,null);
        } else {
            top = new Node(newItem,null, top);
        }
    }

    public void append(int newItem){
            Node curr = top;
            while (curr != null){
                curr = curr.next;
            }
            curr = new Node(newItem,curr,null);
    }

    public void insertOrdered(int newItem){

    }

    public void delete(int key){

    }

    public Node search(int key){
        return null;
    }

    public int getSizeRecursion(){
        return 0;
    }

    public DoublyLL deepCopy(){
        return null;
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
