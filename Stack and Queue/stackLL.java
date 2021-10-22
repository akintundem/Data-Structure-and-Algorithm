public class stackLL {
    public static void main(String[] args) {
        stackLinkedList stackLinkedList = new stackLinkedList();
        stackLinkedList.push(1);
        stackLinkedList.push(2);
        stackLinkedList.push(3);
        stackLinkedList.pop();
        stackLinkedList.pop();
        stackLinkedList.pop();
        System.out.println(stackLinkedList.isEmpty());
        System.out.println(stackLinkedList);
    }
}

class stackLinkedList{
    private Node top;

    private class Node {
        public int item;
        public Node next;

        public Node(int newItem, Node newNext) {
            this.item = newItem;
            this.next = newNext;
        }
    }

    public stackLinkedList(){
        top = null;
    }

    public void push(int newItem){
        top = new Node(newItem,top);
    }

    public int pop(){
        int answer = top.item;
        top = top.next;
        return answer;
    }

    public int Top(){
        return top.item;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public String toString(){
        String answer = "< ";
        Node curr = top;
        while (curr != null){
            answer += curr.item + ", ";
            curr = curr.next;
        }
        return answer + "]";
    }
}
