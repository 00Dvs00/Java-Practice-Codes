import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        this.left = this.right = null;
    }
}

public class BoundaryTraversal{
    public static boolean isleaf(Node node){
        return node.left == null && node.right == null;
    }

    public static void AddLeftBoundary(Node root, List<Integer> solution){
        Node current = root.left;
        while(current != null){
            if(!isleaf(current)){
                solution.add(current.data);
            }
            if(current.left != null){
                current = current.left;
            } else {
                current = current.right;
            }
        }
    }   
    
    public static void AddLeaves(Node root, List<Integer> solution){
        if(isleaf(root)){
            solution.add(root.data);
            return;
        }
        if(root.left != null){
            AddLeaves(root.left, solution);
        }
        if(root.right != null){
            AddLeaves(root.right, solution);
        }
    }

    public static void AddRightBoundary(Node root, List<Integer> solution){
        Node current = root.right;
        List<Integer> temp = new ArrayList<>();
        while(current != null){
            if(!isleaf(current)){
                temp.add(current.data);
            }
            if(current.right != null){
                current = current.right;
            } else {
                current = current.left;
            }
        }
        for(int i = temp.size()-1; i>=0; i--){
            solution.add(temp.get(i));
        }
    }

    public static List<Integer> boundary(Node root){
        List<Integer> solution = new ArrayList<>();
        if(root == null) return solution;

        if(!isleaf(root)){
            solution.add(root.data);
        }
        AddLeftBoundary(root, solution);
        AddLeaves(root, solution);
        AddRightBoundary(root, solution);
        return solution;
    }
    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        List<Integer> solution = boundary(root);
        System.out.println(solution);
    }
}