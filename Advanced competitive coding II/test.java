import java.util.Scanner;

class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
        this.left = this.right = null;
    }
}

public class test{

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
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        String[] elements = (s.trim().split(" "));
        Node root = new Node(Integer.valueOf(elements[0]));
        Node temp = root; int count = 0;

        for(int i = 1; i<(elements.length-1) / 2; i++){
            root.left = new Node(Integer.valueOf(elements[count++]));
            root.right = new Node(Integer.valueOf(elements[count++]));
            if(i%2 != 0){
                root=root.left;
            } else {
                root=root.right;
            }
        }
        root = temp;
        inOrder(root);
        System.out.println();
        recover(root);
        inOrder(root);
    }
}