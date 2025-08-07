import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Scanner;

public class  pps4_1 {

    static class Product{
        protected String productname;
        protected double price;
        protected String category;
        
        public Product(String productname, double price, String category){
            this.productname = productname;
            this.price = price;
            this.category = category;
        }
        public String toString() {
            return "Product Name: " + productname + "\nPrice: $" + price + "\nCategory: " + category;
        }
    }

    static public class ElectronicProduct extends Product {
        private String features;

        public ElectronicProduct(String productName, double price, String features) {
            super(productName, price, "Electronic");
            this.features = features;
        }

        @Override
        public String toString() {
            return super.toString() + "\nFeatures: " + features;
        }
    }
    public static void main(String...args) throws Exception {

        try{
            Class productClass = Class.forName("ElectronicProduct");
            Constructor constructor = productClass.getConstructor(String.class, double.class, String.class);

            Object electronicProduct = constructor.newInstance("Smartphone", 700.0, "Electronics");

            System.out.println(electronicProduct.toString());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}