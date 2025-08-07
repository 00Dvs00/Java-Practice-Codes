package inventory;

interface Stockable { 
    String getbookname();
    int getStockQuantity(); 
} 

public class BookInventory implements Stockable{ 

    private int stock; 
    private String book; 

    public BookInventory(int stock, String book){
        this.stock = stock;
        this.book = book;
    }


    public String getbookname(){
        return book;
    }
    
    
    public int getStockQuantity(){ 
        return stock;
    }
}