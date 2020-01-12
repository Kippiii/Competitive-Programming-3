import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

          int caseNum = 0;
          while (true) {
               caseNum++;
               String[] params = br.readLine().split("\\s+");
               int n = Integer.parseInt(params[0]);
               int k = Integer.parseInt(params[1]);
               if (n == 0 && k == 0) break;
               String[] customersStr = br.readLine().split("\\s+");
               int[] customers = new int[n];
               for (int i = 0; i < n; i++)
                    customers[i] = Integer.parseInt(customersStr[i]);
               int m = Integer.parseInt(br.readLine());
               Commonality[] commonalities = new Commonality[m];
               //int[][] common = new int[n][n];
               for (int i = 0; i < m; i++) {
                    String[] command = br.readLine().split("\\s+");
                    int t = Integer.parseInt(command[0]);
                    int count = Integer.parseInt(command[t + 1]);
                    int[] towers = new int[t];
                    for (int j = 0; j < t; j++) {
                         towers[j] = Integer.parseInt(command[j + 1]) - 1;
                    }
                    commonalities[i] = new Commonality(count, towers);
               }
               //pr.printf("%d%n", common[0][1]);

               int maxCustoms = Integer.MIN_VALUE;
               int[] maxLocs = new int[k];
               for (int i = (1 << n) - 1; i >= 0; i--) {
                    ArrayList<Integer> curLocs = new ArrayList<Integer>();
                    int customs = 0;
                    for (int j = n - 1; j >= 0; j--) {
                         if ((i & (1 << j)) > 0) {
                              int j2 = n - 1 - j;
                              customs += customers[j2];
                              curLocs.add(j2);
                         }
                    }
                    //pr.printf("%d%n", curLocs.size());
                    if (curLocs.size() == k) {
                         for (Commonality c : commonalities) {
                              customs -= (c.relevantTowers(curLocs) - 1) * c.amount;
                         }
                         if (customs > maxCustoms) {
                              maxCustoms = customs;
                              for (int j = 0; j < k; j++) {
                                   maxLocs[j] = curLocs.get(j);
                              }
                         }
                    }
               }

               pr.printf("Case Number  %d%n", caseNum);
               pr.printf("Number of Customers: %d%n", maxCustoms);
               pr.printf("Locations recommended:");
               for (int i = 0; i < k; i++) {
                    pr.printf(" %d", maxLocs[i] + 1);
               }
               pr.printf("%n%n");
          }

          pr.close();
     }
}

class Commonality {
     int includes, amount, count;

     public Commonality(int amount, int[] towers) {
          this.amount = amount;
          this.count = towers.length;
          for (int tower : towers) {
               includes |= 1 << tower;
          }
     }

     public int relevantTowers(ArrayList<Integer> towers) {
          int counter = 0;
          for (int tower : towers) {
               if ((includes & (1 << tower)) > 0) {
                    counter++;
               }
          }
          return counter == 0 ? 1 : counter;
     }
}
