import java.util.*;

class Node{
    Node next;
    Node prev;
    int data;
    public Node(int data){
        this.next = null;
        this.prev = null;
        this.data = data;
    }
}

class DoublyLinkedList{
    Node head;
    public DoublyLinkedList(){
        this.head = null;
    }

    public void add(int data){
        Node newnode = new Node(data);
        if(head == null){
            head = newnode;
        } else {
            Node current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = newnode;
            newnode.prev = current;
        }
    }

    public void display(){
        Node current = head;
        while(current != null){
            System.out.print(current.data+" -> ");
            current = current.next;
        }
        System.out.print("null");
    }
}

public class BitonicSort {
    public static void main(String[] args){
        DoublyLinkedList list = new DoublyLinkedList();
        Scanner input = new Scanner(System.in);
        String string = input.nextLine();
        String[] nums = string.trim().split(" ");
        for(String num : nums){
            list.add(Integer.parseInt(num));
        }
        list.display();
        input.close();
    }
}
