public class QueueSLL {
    public static void main(String[] args) {
        QueueSinglyLL queueSinglyLL = new QueueSinglyLL();
        queueSinglyLL.Enqueue(1);
        queueSinglyLL.Enqueue(2);
        queueSinglyLL.Enqueue(3);
        queueSinglyLL.Enqueue(4);
        queueSinglyLL.Dequeue();
        queueSinglyLL.Dequeue();
        queueSinglyLL.Dequeue();
        queueSinglyLL.Dequeue();
        System.out.println(queueSinglyLL.isEmpty());
        System.out.println(queueSinglyLL);
    }
}

class QueueSinglyLL{
    private Node top;
    private Node tail;

    private class Node {
        public int item;
        public Node next;

        public Node(int newItem, Node newNext) {
            this.item = newItem;
            this.next = newNext;
        }
    }

    public QueueSinglyLL(){
        top = null;
        tail = null;
    }

    public void Enqueue(int newItem){
        if(top == null){
            top = tail = new Node(newItem,top);
        }else{
            Node newNode = new Node(newItem,null);
            tail.next= newNode;
            tail = newNode;
        }
    }

    public int Dequeue(){
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
        String answer = "<";
        Node curr = top;
        while (curr != null){
            answer += curr.item + ", ";
            curr = curr.next;
        }
        return answer + '<';
    }

}
