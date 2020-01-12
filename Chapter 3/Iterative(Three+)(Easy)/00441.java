import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

          boolean first = true;
          while (true) {
               String[] input = br.readLine().split(" ", 0);
               int k = Integer.parseInt(input[0]);
               if (k == 0) break;
               if (first)
                    first = false;
               else
                    pr.printf("%n");
               int[] S = new int[k];
               for (int i = 0; i < k; i++) {
                    S[i] = Integer.parseInt(input[i + 1]);
               }

               for (int i = 0; i < k - 5; i++) {
                    for (int j = i + 1; j < k - 4; j++) {
                         for (int l = j + 1; l < k - 3; l++) {
                              for (int m = l + 1; m < k - 2; m++) {
                                   for (int n = m + 1; n < k - 1; n++) {
                                        for (int o = n + 1; o < k - 0; o++) {
                                             pr.printf("%d %d %d %d %d %d%n", S[i], S[j], S[l], S[m], S[n], S[o]);
                                        }
                                   }
                              }
                         }
                    }
               }
          }

          pr.close();
     }
}
