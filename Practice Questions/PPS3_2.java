import java.util.Scanner;

class BMI {
    private double weight;
    private double height;
    private double bmi;
    private String grade;

    public void BMIcalculator() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter height (in cm): ");
        height = input.nextDouble();
        System.out.println("Enter weight (in kg): ");
        weight = input.nextDouble();
        bmi = calculateBMI();
        grade = calculateGrade(bmi);
    }

    private double calculateBMI() {
        double BMI = (weight / (height * height)) * 10000;
        return BMI;
    }

    private String calculateGrade(double bmi) {
        String grade = (bmi < 18.5) ? ("U") :
                (bmi >= 18.5 && bmi < 25) ? ("N") :
                (bmi >= 25.0 && bmi < 30) ? ("H") :
                (bmi >= 30) ? ("O") : ("Error");
        return grade;
    }

    public void displayBMI() {
        String formattedBMI = String.format("%.2f", bmi);
        System.out.println(formattedBMI + " " + grade);
    }
}

class Stock {
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;

    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public void setPreviousClosingPrice(double price) {
        previousClosingPrice = price;
    }

    public void setCurrentPrice(double price) {
        currentPrice = price;
    }

    public double getChangePercentage() {
        return (Math.abs(currentPrice - previousClosingPrice) / previousClosingPrice) * 100;
    }


    public void display() {
        System.out.println("Stock Symbol: " + symbol);
        System.out.println("Stock Name: " + name);
        System.out.println("Previous Closing Price: " + previousClosingPrice);
        System.out.println("Current Price: " + currentPrice);
    }

}

public class PPS3_2 {
    public static void main(String... args) {
        Scanner choice = new Scanner(System.in);
        System.out.println("1.Question1 2.Question2, 3.Question3, 4.Question4, 5.Question5 \nEnter the question number you want to solve: ");
        int c = choice.nextInt();
        while(true){
            if (c==1){q1();}
            else if(c==2){q2();}
            else if(c>2){break;}
            System.out.println("Enter question number: ");
            c = choice.nextInt();
        }
    }

    public static void q1(){
        BMI b = new BMI();
        b.BMIcalculator();
        b.displayBMI();
    }

    public static void q2(){
        Stock s = new Stock("ORCL", "Oracle Corporation");
        s.setPreviousClosingPrice(34.5);
        s.setCurrentPrice(34.35);

        double changePercentage = s.getChangePercentage();

        System.out.println("Price Change Percentage: " + String.format("%.2f", changePercentage) + "%");
        s.display();
    }
}
