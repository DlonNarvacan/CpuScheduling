
package CPUScheduling;

import java.util.*;

public class Pqnp {

    public static void main(String[] args) {
        // TODO code application logic here
    Scanner myinp = new Scanner(System.in);
        process p = new process();

        int num,ttat = 0,twt = 0,curr = 0;
        float avgtat,avgwt;
        Arrays.fill(p.comp,0);

        System.out.print("Number of Process: ");
        num = myinp.nextInt();
        
        for (int i = 0; i < num; i++) {
            System.out.println("==== Process "+(i+1)+" ====\n");
            System.out.print("Arrival Time: ");
            p.at[i] = myinp.nextInt();
            System.out.print("Burst Time: ");
            p.bt[i] = myinp.nextInt();
            System.out.print("Priority Label: ");
            p.prio[i] = myinp.nextInt();
            p.id[i] = i+1;
        }

        for (int i = 0; i < num; i++) {
            if (i == 0) {
                p.st[i] = p.at[i];
                p.ct[i] = p.at[i] + p.bt[i];
                p.wt[i] = p.st[i] - p.at[i];
                p.tat[i] = p.ct[i] - p.at[i];
                ttat += p.tat[i];
                twt += p.wt[i];
            }else{
                p.st[i] = p.ct[i-1];
                p.ct[i] = p.st[i] + p.bt[i];
                p.wt[i] = p.st[i] - p.at[i];
                p.tat[i] = p.ct[i] - p.at[i];
                ttat += p.tat[i];
                twt += p.wt[i];
            }
        }
        
        
        avgtat = (float) ttat / num;
        avgwt = (float) twt / num;

        System.out.println("ID\t"+"AT\t"+"BT\t"+"PL\t"+"ST\t"+"CT\t"+"TAT\t"+"WT");

        for (int i = 0; i < num; i++) {
            System.out.println("P"+p.id[i]+"\t"+p.at[i]+"\t"+p.bt[i]+"\t"+p.prio[i]+"\t"+p.st[i]+"\t"+p.ct[i]+"\t"+p.tat[i]+"\t"+p.wt[i]+"\n");
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
