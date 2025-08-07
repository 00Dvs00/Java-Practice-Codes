import java.util.*;

public class priority_scheduling {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of processes:");
        int p = input.nextInt();
        int[] priority = new int[p] ;
        int[] Arival_time = new int[p];
        int[] Burst_time = new int[p];
        int lp_position = 0;
        int hp_position = 0;

        for(int i=0; i<priority.length; i++){
            System.out.print("Enter the priority of process "+(i+1)+" :"); 
            priority[i] = input.nextInt();
            System.out.print("Enter the Arival time of process "+(i+1)+" :");
            Arival_time[i] = input.nextInt();
            System.out.print("Enter the Burst time of process "+(i+1)+" :");
            Burst_time[i] = input.nextInt();
            System.out.println("------------------------------------------");
            if(priority[i]<priority[lp_position]){
                lp_position = i;
            }
            if(priority[i]>priority[hp_position]){
                hp_position = i;
            }
        }

        System.out.println(lp_position+" "+hp_position);
        input.close();
    }
}