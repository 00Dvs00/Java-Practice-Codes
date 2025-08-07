import java.util.Scanner;

class PPS2_2{
    public static void main(String...args){
        Scanner choice = new Scanner(System.in);
        System.out.println("1.Question1 2.Question2, 3.Question3, 4.Question4 \nEnter the question number you want to solve: ");
        int c = choice.nextInt();
        while(true){
            if (c==1){q1();}
            else if(c==2){q2();}
            else if(c==3){q3();}
            else if(c==4){q4();}
            else if(c>4){break;}
            System.out.print("Enter question number: ");
            c = choice.nextInt();
        }
    }
    
    public static void q1(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int students = input.nextInt();
        int[] array = new int[students];
        int best = 0;
        System.out.print("Enter "+ students + " scores: ");
        for (int i=0; i<students; i++){
            array[i] = input.nextInt();
            if (array[i]>best){
                best = array[i];
            }
        }
        for (int i=0; i<students; i++){
            String grade = (array[i]>=(best-10)) ? "A" 
            : (array[i]>=(best-20)) ? "B" 
            : (array[i]>=(best-30)) ? "C" 
            : "D";
            System.out.println("Student "+ (i+1) + "score is " + (array[i]) + " and grade is " + grade);
        }
    }	 	  	 	  	   	    	      	  	    	    	 	
    
    public static void q2(){
        Scanner input = new Scanner(System.in);
        int count = 0;
        int [] array = new int[10000];
        int num;
        do {
            num = input.nextInt();
            array[count] = num;
            count++;
        }
        while (num != 0);
        
        int max = 0;
        for (int i=0; i<count; i++){
            if(array[i]>max){
                    max = array[i];
            }
        }
        int[] occ = new int[max+1];

        for (int i=0; i<count ; i++){
            for (int j=1; j<count; j++){
                if (array[i]==array[j]){
                    occ[array[i]]++;
                }
            }
        }
        for (int i=0; i<max+1; i++){
            if (occ[i]!=0){
            System.out.println( i + "is :" + occ[i]+" times ");}
        }
    }
    
    public static void q3(){
        Scanner input = new Scanner(System.in);
        int[] nums = new int[10];
        for(int i=0; i<10; i++){	 	  	 	  	   	    	      	  	    	    	 	
            System.out.print("Enter the "+(i+1)+"th number: ");
            nums[i] = input.nextInt();
        }
        int distinct=10;
        for(int i=0; i<10; i++){
            for(int j=i+1; j<10; j++){
                if (nums[i]==nums[j]){
                    distinct--;
                    break;
                }
            }
        }
        System.out.println("Number of distinct numbers: "+distinct);
    }
    
    public static void q4(){
        Scanner input = new Scanner(System.in);
        char[][] students = new char[8][10];
        int nos = 8;
        int noq = 10;
        char[] answers = {'D', 'B', 'D', 'C', 'C', 'D', 'A', 'E', 'A', 'D'};
        for (int i = 0; i < nos; i++) {
            System.out.println("Enter answers for Student " + (i + 1) + ":");
            for (int j = 0; j < noq; j++) {
                students[i][j] = input.next().charAt(0);
            }
        }
        for (int i = 0; i < nos; i++) {
            int score = 0;
            for (int j = 0; j < noq; j++) {
                if (students[i][j] == answers[j]) {
                    score++;
                }
            }
            System.out.println("Student " + (i + 1) + " Scored: " + score + "/" + noq);
        }	 	  	 	  	   	    	      	  	    	    	 	
    }
}