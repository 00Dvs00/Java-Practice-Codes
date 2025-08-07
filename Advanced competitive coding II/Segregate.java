import java.util.Scanner;


class Node{
    Node next;
    int data;
    public Node(int data){
        this.next = null;
        this.data = data;
    }
}

class linkedList{
    Node head;
    public linkedList(){
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

    public void seg(){
        Node odd = head;
        Node even = head.next;
        Node evenhead = head.next;

        if(head == null || head.next == null) return;

        while(even != null && even.next != null){
            odd.next = odd.next.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }
        odd.next = evenhead;
    }
}

public class Segregate {

    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        linkedList list = new linkedList();
        String string = input.nextLine();
        String[] nums = string.trim().split(" ");
        for(String num : nums){
            list.add(Integer.parseInt(num));
        }
        list.seg();
        list.display();
        input.close(); 
    }
}   
