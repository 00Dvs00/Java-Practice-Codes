import java.util.Scanner;

class PPS2{
    public static void main(String...args){
        Scanner choice = new Scanner(System.in);
        System.out.println("1.Question1 2.Question2, 3.Question3, 4.Question4, 5.Question5 \nEnter the question number you want to solve: ");
        int c = choice.nextInt();
        while(true){
            if (c==1){q1();}
            else if(c==2){q2();}
            else if(c==3){q3();}
            else if(c==4){q4();}
            else if(c==5){q5();}
            else if(c>5){break;}
            System.out.print("Enter question number: ");
            c = choice.nextInt();
        }
    }

    public static void q1(){
        System.out.print("Enter a Number: ");
        Scanner input = new Scanner(System.in);
        float Num = input.nextFloat();
        if (Num==0){
            System.out.println("Zero");
        }
        else{
            String Print = (Num>0) ? "Positive" 
            : "Negative";
            float Absolute = Math.abs(Num);
            if (Absolute < 1){
                System.out.println("Samll");
            }
            else if(Absolute > 1000000){
                System.out.println("Large");
            }
            System.out.println(Print);
        }
    }

    public static void q2(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first number: ");
        float num1 = input.nextFloat();
        System.out.print("Enter second number: ");
        float num2 = input.nextFloat();
        if (Math.round(num1)==Math.round(num2)){
            System.out.println("Same");
        }
    }

    public static void q3(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter registration Number: ");
        String reg = input.nextLine();
        char year = reg.charAt(0);
        String Print = (year=='1') ? "First" : (year=='2') ? "Second" : (year=='3') ? "Thrid" : "Fourth";
        System.out.println(Print);
    }

    public static void q4(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Two numbers: ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        String cp= (gcd(num1,num2)==1)  ? "co-prime" : "Not Co-prime" ;
        System.out.println(cp);
    }

    static int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }
    
    public static void q5(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a 4 digit number: ");
        int number = input.nextInt();
        int part1 = number/100;
        int part2 = number%100;
        int temp;
        temp = part1%10; part1 = part1/10; part1 =(temp*10)+part1; temp=0;
        temp = part2%10; part2 = part2/10; part2 =(temp*10)+part2; temp=0;
        number = (part1*100)+part2;
        System.out.println(number);
    }
}

