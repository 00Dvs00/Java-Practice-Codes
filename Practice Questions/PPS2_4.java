import java.util.Scanner;

class PPS2_4{
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
            System.out.println("Enter question number: ");
            c = choice.nextInt();
        }
    } 
    public static void q1(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter string 1: ");
        String str1 = input.nextLine();
        System.out.print("Enter string 2: ");
        String str2 = input.nextLine();
        int compare = str2.compareTo(str1);
        String print = (compare>0) ? ("Str1 is is greater") : (compare<0) ? ("Str2 is is greater") 
        : "Both are Lexicographically equal";
        System.out.println(print);
    }

    public static void q2(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Enmail: ");
        String email = input.nextLine();
        Boolean check1 = email.contains("com");
        Boolean check2 = email.contains("in");
        Boolean check3 = email.contains("net");
        Boolean check4 = email.contains("org");
        String print = (check1==true || check2==true || check3==true || check4==true ) ? "Valid" : "Not Valid" ;
        System.out.println(email + " is " + print);
    }

    public static void q3(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Password: ");
        String pass = input.nextLine();
        String check = (pass.matches(".*(?=.{8,})(?=.*[A-Z])(?=..*[0-9])(?=.*[!@#$%^&*_+]).*")) ? "Valid" : "Invalid";
        System.out.println(check);
    }

    public static void q4(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String str = input.nextLine();
        int length = str.length();
        String print;
        if (length % 2 != 0) {
            System.out.print("Middle element: ");
            print = String.valueOf(str.charAt(length / 2));
        } else {
            int mid = length / 2;
            System.out.print("Middle element: ");
            print = str.substring(mid - 1, mid + 1);
        }
        System.out.println(print);
    }

    public static void q5(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number: ");
        String str = input.nextLine();
        int print = (str.matches(".*[a-zA-Z].*")) ? 0 : Integer.parseInt(str) ;
        System.out.println(print);
    }
}