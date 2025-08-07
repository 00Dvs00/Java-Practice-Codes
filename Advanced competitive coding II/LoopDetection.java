import java.util.*;

class Node{
    int data;
    Node next;
    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

class linkedList{
    Node head;
    public linkedList(){
        this.head = null;
    }

    public void add(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
        } else {
            Node current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void display(){
        Node current = head;
        while(current != null){
            System.out.print(current.data+" -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class LoopDetection{

    public static void detect(Node head){
        Node slow = head;
        Node fast = head;
        while(slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                System.out.println("Loop detected");
                return;
            }
        }
        System.out.println("No Loop Detected");
    }
    public static void main(String[] args){
        linkedList list = new linkedList();
        Scanner input = new Scanner(System.in);
        String string = input.nextLine();
        String[] nums = string.trim().split(" ");
        for(String num : nums){
            list.add(Integer.parseInt(num));
        }
        list.display();
        list.head.next.next = list.head;
        detect(list.head);
        input.close();
    }
}