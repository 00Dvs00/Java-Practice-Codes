import java.util.*;

public class SJFPreemptive {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int p = input.nextInt();
        int[] arrivaltime = new int[p]; int[] bursttime = new int[p]; int[] id = new int[p];
        int[] rembt = new int[p];
        int[] ct = new int[p]; int[] tat = new int[p]; int[] wt = new int[p];
        System.out.println("Enter arrival time and burst time: ");
        for (int i = 0; i < p; i++) {
            arrivaltime[i] = input.nextInt();
            bursttime[i] = input.nextInt();
            rembt[i] = bursttime[i];
            id[i] = i;
        }
        int q;
        System.out.println("Enter time quantum: ");
        q = input.nextInt();

        List<int[]> processes = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            processes.add(new int[]{arrivaltime[i],bursttime[i],ct[i],id[i],rembt[i]});
        }
        processes.sort(Comparator.comparingInt(a -> a[0]));
        int time = 0;
        int completed = 0;

        while(completed<p){
            List<int[]> readyqueue = new ArrayList<>();
            for (int i = 0; i < processes.size(); i++) {
                if(processes.get(i)[0] <= time && processes.get(i)[4]>0){
                    readyqueue.add(processes.get(i));
                }
            }
            readyqueue.sort((a,b) -> {
                if (a[4] == b[4]){
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[4], b[4]);
            });      

            if(readyqueue.isEmpty()){
                time++;
                continue;
            }

            int index = readyqueue.get(0)[3];

            if(processes.get(index)[4] <= q){
                time += processes.get(index)[4];
                processes.get(index)[4] = 0;
                processes.get(index)[2] = time;
                tat[index] = processes.get(index)[2] - processes.get(index)[0];
                wt[index] = tat[index] - processes.get(index)[1];
                completed++;
                System.out.print("P"+(processes.get(index)[3]+1) +" ");
            } else{
                time += q;
                processes.get(index)[4] -= q;
                System.out.print("P"+(processes.get(index)[3]+1) +" ");
            }
        }

        System.out.println("\nProcess ID\tArrival Time\tBurst Time\tCompletion Time\tTurnAroundTime\tWaitingTime");
        for (int i = 0; i < processes.size(); i++) {
            System.out.println("P"+(processes.get(i)[3]+1) +"\t\t"+processes.get(i)[0] + "\t\t" + processes.get(i)[1] + "\t\t" + processes.get(i)[2]
            + "\t\t" + tat[i] + "\t\t" + wt[i]);
        }
    }
}