import java.util.Scanner;

class problems{
    public static void main (String...args){
        q1();
        q2();
        q3();
        q4();
    }
        
    public static void q1(){
        Scanner inputt = new Scanner(System.in);
        System.out.print("Enter time in seconds: ");
        int Time = inputt.nextInt();
        int Minutes = Time/60;
        int Sec = Time%60;
        System.out.println(Minutes+" mins "+Sec+" Seconds");
    }

    public static void q2(){
        System.out.print("Enter temp in fahrenheit: ");
        Scanner input = new Scanner(System.in);
        int temp = input.nextInt();
        double celsius = ((5/9)*(temp-32));
        System.out.println("Celsius: "+ celsius);
    }

    public static void q3(){
        System.out.print("Enter temp in Celsius: ");
        Scanner input1 = new Scanner(System.in);
        int temp = input1.nextInt();
        double fahrenheit = (temp*1.8)+32;
        System.out.println("fahrenheit: "+ fahrenheit);
    }

    public static void q4(){
        int sum;
        System.out.println("Enter a number: ");
        Scanner input2 = new Scanner(System.in);
        int num = input2.nextInt();
        if (num>0 && num<1000){
            for (sum=0; num!=0; num=num/10){
                sum = sum + num % 10;
            }
            System.out.println(sum);
        }
    }
}

