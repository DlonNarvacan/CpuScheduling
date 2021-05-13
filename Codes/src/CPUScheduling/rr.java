package CPUScheduling;

import java.util.*;

class myprocess {

    int id[] = new int[100];
    int at[] = new int[100];
    int bt[] = new int[100];
    int st[] = new int[100];
    int ct[] = new int[100];
    int tat[] = new int[100];
    int wt[] = new int[100];
    int comp[] = new int[100];
    int rt[] = new int[100];
    int br[] = new int[100];
    int prio[] = new int[100];
    int bt_rem[] = new int[100];
}

public class rr {

    public static void main(String[] args) {
        Scanner myinp = new Scanner(System.in);
        int a, b, quantum_time, num, ttat = 0, twt = 0, trt = 0, index, total_idle_time = 0;
        float avgtat, avgtwt;
        myprocess p = new myprocess();

        System.out.print("Number of Process: ");
        num = myinp.nextInt();
        System.out.print("Quantum time: ");
        quantum_time = myinp.nextInt();

        for (int i = 0; i < num; i++) {
            System.out.println("==== Process " + (i + 1) + " ====\n");
            System.out.print("Arrival Time: ");
            p.at[i] = myinp.nextInt();
            System.out.print("Burst Time: ");
            p.bt[i] = myinp.nextInt();
            p.bt_rem[i] = p.bt[i];
            p.id[i] = i + 1;
        }

        LinkedList<Integer> q = new LinkedList<Integer>();
        LinkedList<Integer> gant = new LinkedList<Integer>();
        LinkedList<Integer> id_gant = new LinkedList<Integer>();
        int current_time = 0;
        q.offer(0);
        int completed = 0;
        int[] mark = new int[100];

        mark[0] = 1;

        for (a = 0; a < num; a++) {
            b = a + 1;
            while (b < num) {
                if (p.at[b] < p.at[a]) {
                    sort(a, b, p.at, p.bt, p.id);
                }
                b += 1;
            }
        }

        current_time = p.at[0];

        while (completed != num) {
            index = q.peek();
            q.poll();

            if (!gant.contains(current_time)) {
                gant.add(current_time);
            }

            if (p.bt_rem[index] == p.bt[index]) {
                p.st[index] = Math.max(current_time, p.at[index]);
                total_idle_time += p.st[index] - current_time;
                current_time = p.st[index];
            }

            if (p.bt_rem[index] - quantum_time > 0) {
                p.bt_rem[index] -= quantum_time;
                current_time += quantum_time;
            } else {
                current_time += p.bt_rem[index];
                p.bt_rem[index] = 0;
                completed++;

                p.ct[index] = current_time;
                p.tat[index] = p.ct[index] - p.at[index];
                p.wt[index] = p.tat[index] - p.bt[index];
                p.rt[index] = p.st[index] - p.at[index];

                ttat += p.tat[index];
                twt += p.wt[index];
                trt += p.rt[index];
            }

            id_gant.add(p.id[index]);
            gant.add(current_time);

            for (int i = 1; i < num; i++) {
                if (p.bt_rem[i] > 0 && p.at[i] <= current_time && mark[i] == 0) {
                    q.offer(i);
                    mark[i] = 1;
                }
            }
            if (p.bt_rem[index] > 0) {
                q.offer(index);
            }

            if (q.isEmpty()) {
                for (int i = 1; i < num; i++) {
                    if (p.bt_rem[i] > 0) {
                        q.offer(i);
                        mark[i] = 1;
                        break;
                    }
                }
            }

        }

        avgtat = (float) ttat / num;
        avgtwt = (float) twt / num;

        System.out.println("ID\t" + "AT\t" + "BT\t" + "ST\t" + "CT\t" + "TAT\t" + "WT");

        for (int i = 0; i < num; i++) {
            System.out.println("P" + p.id[i] + "\t" + p.at[i] + "\t" + p.bt[i] + "\t" + p.st[i] + "\t" + p.ct[i] + "\t"
                    + p.tat[i] + "\t" + p.wt[i] + "\n");
        }

        System.out.println("Average TurnAround Time: " + avgtat);
        System.out.println("Average Waiting Time: " + avgtwt);

        System.out.println("==== Gant Chart ====");

        for (Integer ids : id_gant) {
            System.out.print("P" + ids + "\t");
        }
        System.out.println();
        for (Integer gants : gant) {
            System.out.print(gants + "\t");
        }
    }

    public static void sort(int a, int b, int[] at, int[] bt, int[] id) {
        int temp;
        temp = at[a];
        at[a] = at[b];
        at[b] = temp;

        temp = bt[a];
        bt[a] = bt[b];
        bt[b] = temp;

        temp = id[a];
        id[a] = id[b];
        id[b] = temp;
    }
}