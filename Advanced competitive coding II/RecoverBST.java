class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
        this.left = this.right = null;
    }
}

public class RecoverBST{

    static Node first = null;
    static Node second = null;
    static Node prev = new Node(Integer.MIN_VALUE);

    public static void recover(Node root){
        Node current = root;

        while (current!=null) {
            if(current.left == null){
                detectswapped(current);
                current = current.right;
            } else {
                Node predecessor = current.left;
                while(predecessor.right != null && predecessor.right != current){
                    predecessor = predecessor.right;
                }

                if(predecessor.right == null){
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    detectswapped(current);
                    current = current.right;
                }
            }
        }

        if(first != null && second != null){
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }
    }

    public static void detectswapped(Node current){
        if(prev!=null && prev.data > current.data){
            if(first == null){
                first = prev;
            }
            second = current;
        }
        prev = current;
    }

    public static void inOrder(Node root){
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
    public static void main(String[] args) {
        Node root = new Node(6);
        root.left = new Node(10);
        root.right = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.right = new Node(12);
        root.right.left = new Node(7);

        inOrder(root);
        System.out.println();
        recover(root);
        inOrder(root);
    }
}