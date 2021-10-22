public class StackArray {
    public static void main(String[] args) {
        stackArr stackArray = new stackArr(10);
        stackArray.push(1);
        stackArray.push(2);
        stackArray.push(3);
        stackArray.push(4);
        stackArray.pop();
        stackArray.pop();
        stackArray.pop();
        stackArray.pop();
        System.out.println(stackArray.isEmpty());
        System.out.println(stackArray);
    }
}

class stackArr{
    int top = -1;
    int[] stack;
    public stackArr(int size){
        stack = new int[size];
    }

    public void push(int newItem){
    stack[++top] = newItem;
    }

    public int pop(){
        int answer = stack[top];
        stack[top] = 0;
        top--;
        return answer;
    }

    public int Top(){
        return stack[top];
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public String toString(){
        String answer = "[";
        for (int i = 0; i < stack.length; i++){
            answer += stack[i] + ", ";
        }
        return answer + ">";
    }
}
