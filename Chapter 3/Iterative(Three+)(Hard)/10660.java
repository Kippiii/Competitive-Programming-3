import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

          int t = Integer.parseInt(br.readLine());
          for (int i = 0; i < t; i++) {
               int[] city = new int[25];
               int n = Integer.parseInt(br.readLine());
               for (int j = 0; j < n; j++) {
                    String[] params = br.readLine().split(" ", 0);
                    city[Integer.parseInt(params[0]) * 5 + Integer.parseInt(params[1])] = Integer.parseInt(params[2]);
               }

               int[] minLocs = new int[5];
               int minVal = Integer.MAX_VALUE;
               int[] curLocs = new int[5];
               for (curLocs[0] = 0; curLocs[0] < 21; curLocs[0]++) {
                    for (curLocs[1] = curLocs[0] + 1; curLocs[1] < 22; curLocs[1]++) {
                         for (curLocs[2] = curLocs[1] + 1; curLocs[2] < 23; curLocs[2]++) {
                              for (curLocs[3] = curLocs[2] + 1; curLocs[3] < 24; curLocs[3]++) {
                                   for (curLocs[4] = curLocs[3] + 1; curLocs[4] < 25; curLocs[4]++) {
                                        //pr.printf("%s", (curLocs[0] == 0 && curLocs[1] == 1 && curLocs[2] == 4 && curLocs[3] == 20 && curLocs[4] == 24) ? "Hey\n" : "");
                                        int sum = 0;
                                        for (int j = 0; j < 25; j++) {
                                             int min = Integer.MAX_VALUE;
                                             for (int k = 0; k < 5; k++) {
                                                  int dist = manDist(j, curLocs[k]) * city[j];
                                                  min = Math.min(min, dist);
                                                  //sum += dist;
                                             }
                                             sum += min;
                                        }
                                        if (sum < minVal) {
                                             minVal = sum;
                                             for (int j = 0; j < 5; j++)
                                                  minLocs[j] = curLocs[j];
                                        }
                                   }
                              }
                         }
                    }
               }

               //pr.printf("%d%n", minVal);

               pr.printf("%d %d %d %d %d%n", minLocs[0], minLocs[1], minLocs[2], minLocs[3], minLocs[4]);
          }

          pr.close();
     }

     public static int manDist(int i, int j) {
          return Math.abs(i % 5 - j % 5) + Math.abs(i / 5 - j / 5);
     }
}
