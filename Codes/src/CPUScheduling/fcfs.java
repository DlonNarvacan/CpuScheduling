
package CPUScheduling;

import java.util.*;

class process {
    int id[] = new int[100];
    int at[] = new int[100];
    int bt[] = new int[100];
    int st[] = new int[100];
    int ct[] = new int[100];
    int tat[] = new int[100];
    int wt[] = new int[100];
    int comp[] = new int[100];
    int br[] = new int[100];
    int prio[] = new int[100];
}

public class fcfs {
    public static void main(String[] args) {
        Scanner myinp = new Scanner(System.in);
        process p = new process();

        int num,ttat = 0,twt = 0;
        float avgtat,avgwt;
        

        System.out.print("Number of Process: ");
        num = myinp.nextInt();
        
        for (int i = 0; i < num; i++) {
            System.out.println("==== Process "+(i+1)+" ====\n");
            System.out.print("Arrival Time: ");
            p.at[i] = myinp.nextInt();
            System.out.print("Burst Time: ");
            p.bt[i] = myinp.nextInt();
            p.id[i] = i+1;
        }

        int temp;
        
        for (int i = 0; i < num; i++) {
            for (int j = i+1; j < num; j++) {
                if (p.at[i] > p.at[j]) {
                    temp = p.at[i];
                    p.at[i] = p.at[j];
                    p.at[j] = temp;
                    
                    temp = p.id[i];
                    p.id[i] = p.id[j];
                    p.id[j] = temp;

                    temp = p.bt[i];
                    p.bt[i] = p.bt[j];
                    p.bt[j] = temp;
                }
            }
        }
        
        for (int i = 0; i < num; i++) {
            p.st[i] = (i == 0)?p.at[i]:Math.max(p.ct[i-1],p.at[i]);
            p.ct[i] = p.st[i] + p.bt[i];
            p.tat[i] = p.ct[i] - p.at[i];
            p.wt[i] = p.tat[i] - p.bt[i];
            ttat += p.tat[i];
            twt += p.wt[i];
        }

        System.out.println("ID\t"+"AT\t"+"BT\t"+"ST\t"+"CT\t"+"TAT\t"+"WT");

        for (int i = 0; i < num; i++) {
            System.out.println("P"+p.id[i]+"\t"+p.at[i]+"\t"+p.bt[i]+"\t"+p.st[i]+"\t"+p.ct[i]+"\t"+p.tat[i]+"\t"+p.wt[i]+"\n");
        }

        avgtat = (float) ttat/num;
        avgwt = (float) twt/num;

        System.out.println("Average TurnAround Time: "+avgtat);
        System.out.println("Average Waiting Time: "+avgwt);

        System.out.println("==== Gant Chart ====");
        for (int i = 0; i < num; i++) {
            System.out.print("P"+p.id[i]+"\t");
        }
        System.out.println();
        for (int i = 0; i < num; i++) {
            System.out.print(p.st[i]+"\t");
        }

    }
}
