
package CPUScheduling;

import java.util.*;

public class sjf {
    public static void main(String[] args) {
        Scanner myinp = new Scanner(System.in);
        process p = new process();

        int num,ttat = 0,twt = 0,curr = 0,complete = 0;
        float avgtat,avgwt;;
        Arrays.fill(p.comp,0);

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

        while (complete != num) {
            int idx = -1;
            int min = 10000000;
            for (int i = 0; i < num; i++) {
                if (p.at[i] <= curr && p.comp[i] == 0){
                    if ((p.bt[i] < min) || (p.bt[i] == min && p.at[i] < p.at[idx])) {
                        min = p.bt[i];
                        idx = i;
                    }
                }
            }
            if (idx != -1) {
                p.st[idx] = curr;
                p.ct[idx] = p.st[idx] + p.bt[idx];
                p.tat[idx] = p.ct[idx] - p.at[idx];
                p.wt[idx] = p.tat[idx] - p.bt[idx];
                
                ttat += p.tat[idx];
                twt += p.wt[idx];
    
                p.comp[idx] = 1;
                complete++;
                curr = p.ct[idx];
            } else {
                curr++;
            }
        }

        avgtat = (float) ttat / num;
        avgwt = (float) twt / num;

        System.out.println("ID\t"+"AT\t"+"BT\t"+"ST\t"+"CT\t"+"TAT\t"+"WT");

        for (int i = 0; i < num; i++) {
            System.out.println("P"+p.id[i]+"\t"+p.at[i]+"\t"+p.bt[i]+"\t"+p.st[i]+"\t"+p.ct[i]+"\t"+p.tat[i]+"\t"+p.wt[i]+"\n");
        }
        System.out.println("Average TurnAround Time: "+avgtat);
        System.out.println("Average Waiting Time: "+avgwt);

        int temp;
        for(int i = 0; i < num; i++){
            for(int j = i+1; j < num; j++){
                if(p.st[i] > p.st[j]){
                    temp = p.st[i];
                    p.st[i] = p.st[j];
                    p.st[j] = temp;
                    
                    temp = p.id[i];
                    p.id[i] = p.id[j];
                    p.id[j] = temp;
                }
            }
        }    
        
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
