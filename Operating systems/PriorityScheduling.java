import java.util.*;

public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int p = input.nextInt();
        int[] arrivaltime = new int[p]; int[] bursttime = new int[p]; int[] id = new int[p]; int[] priority = new int[p];
        int[] ct = new int[p]; int[] tat = new int[p]; int[] wt = new int[p];
        System.out.println("Enter arrival time and burst time and priority: ");
        for (int i = 0; i < p; i++) {
            arrivaltime[i] = input.nextInt();
            bursttime[i] = input.nextInt();
            priority[i] = input.nextInt();
            id[i] = i+1;
        }
        List<int[]> processes = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            processes.add(new int[]{arrivaltime[i],bursttime[i],ct[i],id[i],priority[i]});
        }
        List<int[]> sublist = processes.subList(1,processes.size());
        sublist.sort(Comparator.comparingInt(a -> a[4]));
        
        processes.get(0)[2] = processes.get(0)[0] + processes.get(0)[1];
        tat[0] = processes.get(0)[2] - processes.get(0)[0];
        wt[0] = tat[0] -  processes.get(0)[1];

        for(int i = 1; i<processes.size(); i++){
            if(processes.get(i)[0]>=processes.get(i-1)[2]){
                processes.get(i)[2] = processes.get(i)[0] + processes.get(i)[1];
            }
            else{
                processes.get(i)[2] = processes.get(i-1)[2] + processes.get(i)[1];
            }
            tat[i] = processes.get(i)[2] - processes.get(i)[0];
            wt[i] = tat[i] -  processes.get(i)[1];
        }

        System.out.println("\nProcess ID\tArrival Time\tBurst Time\tCompletion Time\tTurnAroundTime\tWaitingTime");
        for (int i = 0; i < processes.size(); i++) {
            System.out.println("P"+processes.get(i)[3]+"\t\t"+processes.get(i)[0] + "\t\t" + processes.get(i)[1] + "\t\t" + processes.get(i)[2]
            + "\t\t" + tat[i] + "\t\t" + wt[i]);
        }
    }
}
