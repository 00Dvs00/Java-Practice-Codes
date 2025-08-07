import java.util.Scanner;

import books.Novel;
import inventory.BookInventory;

public class BookstoreApp {

    static class MysteryNovel extends Novel {
        @Override
        public void read() {
            System.out.println("Reading a Mystery Novel.");
        }
    }

    static class Bookstore extends BookInventory {
        
        Bookstore(int stock, String book) {
            super(stock,book);
        }

        @Override
        public String getbookname() {
            return super.getbookname();
        }

        @Override
        public int getStockQuantity(){
            return super.getStockQuantity();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Book Name: ");
        String book = input.nextLine();
        System.out.print("Enter Quantity: ");
        int stock = input.nextInt();

        Bookstore bookstore = new Bookstore(stock,book);

        String bookName = bookstore.getbookname();

        int stockQuantity = bookstore.getStockQuantity();
        if (bookstore.getStockQuantity()>0){
            stockQuantity = bookstore.getStockQuantity() - 1;
        }

        MysteryNovel mysteryNovel = new MysteryNovel();

        System.out.println("------------");
        if (stockQuantity>0){
            mysteryNovel.read();
        }
        System.out.println("Book Name: " + bookName);
        System.out.println("Available Stock Quantity of the book: " + stockQuantity);
    }
}