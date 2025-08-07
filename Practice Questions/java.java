import java.util.Scanner;

class java{
    public static void main (String...args){
        Scanner input = new Scanner(System.in);

        String name = input.nextLine();
        System.out.println("Hello");
        System.out.println(name);

        int num1 = input.nextInt();
        int num2 = input.nextInt();
        int num3 = input.nextInt();
        System.out.println("Average is: "+ (num1+num2+num3)/3);

        System.out.println(5+8*6);
        System.out.println((55+9)%9);
        System.out.println(20+ -3*5/8);
        System.out.println(5+15/3*2-8%3);

        final double PI=3.14;
        System.out.print("Enter Radius: ");
        int radius = input.nextInt();
        System.out.println("Area: " + (PI*radius*radius));
        System.out.println("Perimeter: " + (PI*radius*2));

        System.out.println("Enter Name: ");
        System.out.println(name);
        System.out.println("Enter Reg no.: ");
        int reg = input.nextInt();
        System.out.println("Enter Dept ");
        int dept = input.nextInt();
        System.out.println("Enter Age: ");
        int age = input.nextInt();
        System.out.println("Enter mobile num: ");
        int num = input.nextInt();
        System.out.println("Enter gpa: ");
        int gpa = input.nextInt();
    }
}