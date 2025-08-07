import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class FIFO
{
    static int pageFaults(int incomingStream[], int n, int frames)
    {
        System.out.println("Incoming \t Pages");
        HashSet<Integer> s = new HashSet<>(frames);
        Queue<Integer> queue = new LinkedList<>();

        int page_faults = 0;

        for (int i = 0; i < n; i++)
        {
            if (s.size() < frames)
            {
                if (!s.contains(incomingStream[i]))
                {
                    s.add(incomingStream[i]);
                    page_faults++;
                    queue.add(incomingStream[i]);
                }
            }
            else
            {
                if (!s.contains(incomingStream[i]))
                {
                    int val = queue.peek();
                    queue.poll();
                    s.remove(val);
                    s.add(incomingStream[i]);
                    queue.add(incomingStream[i]);
                    page_faults++;
                }
            }
            System.out.print(incomingStream[i] + "\t");
            System.out.print(queue + " \n");
        }

        return page_faults;
    }

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of frames: ");
        int frames = scanner.nextInt();

        System.out.print("Enter the length of the incoming page stream: ");
        int len = scanner.nextInt();

        int incomingStream[] = new int[len];
        System.out.println("Enter the incoming page stream:");
        for (int i = 0; i < len; i++) {
            incomingStream[i] = scanner.nextInt();
        }

        int pageFaults = pageFaults(incomingStream, len, frames);
        int hit = len - pageFaults;

        System.out.println("Page faults: " + pageFaults);
        System.out.println("Page fault Ratio: " + (double) pageFaults / len);
        System.out.println("Hits: " + hit);
        System.out.println("Hit Ratio: " + (double) hit / len);

        scanner.close();
    }
}
