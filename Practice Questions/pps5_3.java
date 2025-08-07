import java.util.InputMismatchException;
import java.util.Scanner;

class item{
    public String item;
    public double price;

    item(String item, double price){
        this.item = item;
        this.price = price;
    }
}

class shopping_cart{

    int max = 1000;

    public item[] Items;
    int count = 0;

    shopping_cart(){
        this.Items = new item[max];
    }

    public void add(item items) {
        if (count < max) {
            Items[count] = items;
            count++;
        } else {
            System.out.println("Cart is full. Cannot add more items.");
        }
    }
    
}

public class pps5_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        shopping_cart cart = new shopping_cart();

        while (true) {
            System.out.println("Enter Item name (to quit type 'q' ): ");
            String itemName = input.nextLine();

            if (itemName.equals("q")) {
                break;
            }

            try{
                System.out.println("Enter price: ");
                double itemPrice = input.nextDouble();
                input.nextLine();

                item newItem = new item(itemName, itemPrice);
                cart.add(newItem);
            }
            catch(ArithmeticException | InputMismatchException e){
                System.out.println("Enter a Positive Value for price!!");
            }
        }

        System.out.println("Items in the shopping cart:");
        try{
            for (int i = 0; i < cart.count; i++) {
                item item = cart.Items[i];
                System.out.println(item.item + " $" + item.price);
            }
        }
        catch(NullPointerException e){
                System.out.println("Item not found in database!!");
            }
    }   
}
