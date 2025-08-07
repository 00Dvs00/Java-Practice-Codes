import java.util.*;
import java.util.AbstractMap.SimpleEntry;

class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
        this.left = this.right = null;
    }
}

public class topview{

    public static List<Integer> view(Node root){
        List<Integer> solution = new ArrayList<>();
        if(root == null) return solution;
        Map<Integer, Integer> mpp = new TreeMap<>();
        Queue<SimpleEntry<Node, Integer>> q = new LinkedList<>();
        q.add(new SimpleEntry<>(root, 0));
        while(!q.isEmpty()){
            SimpleEntry<Node, Integer> pair = q.poll();
            Node node = pair.getKey();
            int line = pair.getValue();
            if(!mpp.containsKey(line)){
                mpp.put(line, node.data);
            }
            if(node.left != null){
                q.add(new SimpleEntry<>(node.left, line -1));
            }
            if(node.right != null){
                q.add(new SimpleEntry<>(node.right, line +1));
            }
        }

        for(Map.Entry<Integer,Integer> entry : mpp.entrySet()){
            solution.add(entry.getValue());
        }
        return solution;
    }   
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(10);
        root.left.left.right = new Node(5);
        root.left.left.right.right = new Node(6);
        root.right = new Node(3);
        root.right.right = new Node(10);
        root.right.left = new Node(9);

        List<Integer> solution = view(root);
        System.out.println(solution);
    }
}