public class CircularLL {
    public static void main(String[] args) {
        CircularLL circle = new CircularLL();
        circle.insertUnOrdered(2);
        System.out.println(circle);
    }
    class Node{
        private int item;
        private Node next;
        public Node(int item, Node next){
            this.item =item;
            this.next = next;
        }
    }
    private Node top;
    public CircularLL(){
        top = null;
    }

    public void insertUnOrdered(int newItem){
        if(top == null){
            top = new Node(newItem,null);
            top.next = top;
        }
        else {
            top.next = new Node(newItem,top.next);
        }
    }

    public void insertOrdered(int newItem){
        if(top == null){
            top = new Node(newItem,null);
            top.next = top;
        } else{
            Node prev =  null;
            Node curr = top;
            do{
                prev = curr;
                curr = curr.next;
                if( curr.item > newItem){
                    prev.next = new Node(newItem,curr);
                }
            } while (curr != top);
             }
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
