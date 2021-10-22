import java.util.Random;

public class BST {
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(1);

        binarySearchTree.insert(10);
        binarySearchTree.insert(11);
        binarySearchTree.insert(6);

        binarySearchTree.insert(5);
        binarySearchTree.insert(7);
        binarySearchTree.insert(8);
        binarySearchTree.insert(9);



        binarySearchTree.insert(2);
        binarySearchTree.insert(3);
        binarySearchTree.insert(4);
        System.out.println(binarySearchTree);
    }
}

class BinarySearchTree{
    static final int COUNT = 10;
    private class Node {


        public int data;
        public Node Left;
        public Node Right;

        public Node(int data){
            this.data = data;
            this.Left = null;
            this.Right = null;
        }

        public boolean searchRecursive(int key){
            boolean found = data == key;
            if(!found){
                if(key < data){
                    if(Left != null){
                        found = Left.searchRecursive(key);
                    }
                } else{
                    if(Right != null){
                        found = Right.searchRecursive(key);
                    }
                }
            }
            return found;
        }

        public int computeDepth(int parentDepth){
            int depth = 0;
            depth = parentDepth+1;
            if(Left != null){
                Left.computeDepth(depth);
            }
            if(Right != null){
                Right.computeDepth(depth);
            }
            return depth;
        }

             void print2DUtil(Node root, int space)
            {
                // Base case
                if (root == null)
                    return;

                // Increase distance between levels
                space += COUNT;

                // Process right child first
                print2DUtil(root.Right, space);

                // Print current node after space
                // count
                System.out.print("\n");
                for (int i = COUNT; i < space; i++)
                    System.out.print(" ");
                System.out.print(root.data + "\n");

                // Process left child
                print2DUtil(root.Left, space);
            }


         void print2D(Node root)
        {
            // Pass initial space count as 0
            print2DUtil(root, 0);
        }

        public void preorderTraversal() {
            System.out.println(data);
            if(Left != null) Left.preorderTraversal();
            if(Right != null) Right.preorderTraversal();
        }

        public void inorderTraversal() {
            if(Left != null) Left.inorderTraversal();
            System.out.println(data);
            if(Right != null) Right.inorderTraversal();
        }

        public void postorderTraversal() {
            if(Left != null) Left.postorderTraversal();
            if(Right != null) Right.postorderTraversal();
            System.out.println(data);
        }

        public void insertBelow(int newItem) {
        if(newItem < data){
            if(Left == null){
                Left = new Node(newItem);
            }  else {
                Left.insertBelow(newItem);
            }
        } else if(newItem > data){
            if(Right == null){
                Right = new Node(newItem);
            } else {
                Right.insertBelow(newItem);
            }
        }

        }

    }
        private Node Root;

        public BinarySearchTree(){
            this.Root = null;
        }

        public void insert(int newItem){
            if(Root == null){
                Root = new Node(newItem);
            } else {
                Node curr = Root;
                Node prev = null;
                while (curr != null && curr.data != newItem ) {
                    prev = curr;
                    if (newItem < curr.data) {
                        curr= curr.Left;
                    } else {
                        curr = curr.Right;
                    }

                if(curr == null){
                    if(newItem < prev.data){
                        prev.Left = new Node(newItem);
                    } else {
                        prev.Right = new Node(newItem);
                    }
                }
                }
            }
        }

        public void insertRecursively(int newItem){
            if(Root == null){
                Root = new Node(newItem);
            } else {
                Root.insertBelow(newItem);
            }
        }


        public boolean search(int key){
            boolean found = false;
            Node curr = Root;
            while (curr != null && !found){
                if(Root.data == key){
                    found = true;
                }
                else if(Root.data < key) {
                    curr = curr.Left;
                }
                else{
                    curr = curr.Right;
                }
            }
            return found;
        }

        public boolean searchRecursive(int key){
            boolean found = false;
            if(Root != null){
                found = Root.searchRecursive(key);
            }
            return found;
        }

        public int computeDepth(){
            if(Root != null){
               return Root.computeDepth(-1);
            }
            return 0;
        }

        public void preorderTraversal(){
            Root.preorderTraversal();
        }

         public void inorderTraversal(){
            Root.inorderTraversal();
        }
         public void postorderTraversal(){
            Root.postorderTraversal();
         }

    private void easyDelete(Node del, Node delParent, Node delChild){
        if (delParent != null) {
            if (del == delParent.left) delParent.left = delChild;
            else delParent.right = delChild;
        }
        else {  //del is the root
            root = delChild;
        }
    }
    private void twoChildrenDelete( Node curr){
        // find the inordersuccessor
        Node is = curr.left; //go right onceNode
        Node isParent= curr;
        while ( is.right!= null ){ //go left as far as you can
            isParent= is;
            is = is.right;
        }
        //delete curr.item
        curr.item= is.item; //copy i.s. item to curr
        easyDelete( is, isParent, is.left); //delete old i.s.node
    }

    public void delete( int key ){
        //search for key
        Node curr= root;
        Node prev= null;
        while ( curr!= null && curr.item!= key ){
            prev= curr;
            if (key < curr.item)curr= curr.right;
            else curr= curr.left;
        }
        if (curr != null){//found key!
            if (curr.left == null){//has no children or only a right child
                easyDelete( curr, prev, curr.right);
            }else if (curr.right == null){ //has only a left child
                easyDelete( curr, prev, curr.left);
            }else { //has two children
                twoChildrenDelete( curr);}
        }
    }


        public String toString(){
            String answer = "";
            if(Root == null){
                answer = "Tree is empty.";
            } else {
                 Root.print2D(Root);
            }
            return answer;
        }





}
