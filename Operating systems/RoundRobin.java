import java.util.*;

public class RoundRobin {
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

        Queue<int[]> readyqueue = new LinkedList<>();
        while (completed < p) {
            if (readyqueue.isEmpty()) {
                time++;
                for (int i = 0; i < processes.size(); i++) {
                    if (processes.get(i)[0] <= time && processes.get(i)[4] > 0 
                        && !readyqueue.contains(processes.get(i))) {
                        readyqueue.add(processes.get(i));
                    }
                }
                continue;
            }
            
            int[] currentprocess = readyqueue.poll();
            int index = currentprocess[3];
            
            int executeTime = Math.min(q, currentprocess[4]);
        
            for (int i = 0; i < executeTime; i++) {
                System.out.print("P" + (currentprocess[3] + 1) + " ");
                time++;
                currentprocess[4]--;
                
                for (int j = 0; j < processes.size(); j++) {
                    if (processes.get(j)[0] <= time && processes.get(j)[4] > 0 
                        && !readyqueue.contains(processes.get(j)) 
                        && processes.get(j)[3] != currentprocess[3]) {
                        readyqueue.add(processes.get(j));
                    }
                }
            }
        
            if (currentprocess[4] == 0) {
                currentprocess[2] = time;
                tat[index] = currentprocess[2] - currentprocess[0];
                wt[index] = tat[index] - currentprocess[1];
                completed++;
            } else {
                readyqueue.add(currentprocess);
            }
        }

        System.out.println("\nProcess ID\tArrival Time\tBurst Time\tCompletion Time\tTurnAroundTime\tWaitingTime");
        for (int i = 0; i < processes.size(); i++) {
            System.out.println("P"+(processes.get(i)[3]+1) +"\t\t"+processes.get(i)[0] + "\t\t" + processes.get(i)[1] + "\t\t" + processes.get(i)[2]
            + "\t\t" + tat[i] + "\t\t" + wt[i]);
        }
    }
}