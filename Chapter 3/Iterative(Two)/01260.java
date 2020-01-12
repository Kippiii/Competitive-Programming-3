import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

          int t = Integer.parseInt(br.readLine());
          for (int i = 0; i < t; i++) {
               int n = Integer.parseInt(br.readLine());
               String[] Astr = br.readLine().split(" ", 0);
               int[] A = new int[n];
               for (int j = 0; j < n; j++) {
                    A[j] = Integer.parseInt(Astr[j]);
               }

               int sum = 0;
               for (int j = 1; j < n; j++) {
                    for (int k = 0; k < j; k++) {
                         if (A[k] <= A[j]) {
                              sum++;
                         }
                    }
               }

               pr.printf("%d%n", sum);
          }

          pr.close();
     }
}
