import java.util.*;

class Node{
    String data;
    Node left;
    Node right;

    Node(String data){
        this.data = data;
        this.left = this.right = null;
    }
}

public class leftview{

    public static void right(Node current, List<String> solution, int depth){
        if(current == null) return;
        
        if(depth == solution.size()){
            solution.add(current.data);
        }
        right(current.left, solution, depth+1);
        right(current.right, solution, depth+1);
    }

    public static List<String> view(Node root){
        List<String> solution = new ArrayList<>();
        right(root, solution, 0);
        return solution;
    }
    public static void main(String[] args){
        Node root = new Node("A");
        root.left = new Node("B");
        root.left.left = new Node("D");
        root.left.right = new Node("E");
        root.right = new Node("C");
        root.right.right = new Node("G");
        root.right.left = new Node("F");
        List<String> solution = view(root);
        System.out.println(solution);
    }
}