import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

          int c = Integer.parseInt(br.readLine());
          for (int i = 0; i < c; i++) {
               String[] polyStr = br.readLine().split(" ", 0);
               int[] poly = new int[polyStr.length];
               for (int j = 0; j < polyStr.length - 1; j++)
                    poly[j] = Integer.parseInt(polyStr[j + 1]);
               int d = Integer.parseInt(br.readLine());
               int k = Integer.parseInt(br.readLine());

               int n = 1;
               int count = d;
               k -= count;
               while (k > 0) {
                    n++;
                    count += d;
                    k -= count;
               }

               pr.printf("%d%n", evalPoly(n, poly));
          }

          pr.close();
     }

     public static long evalPoly(int n, int[] poly) {
          long answer = 0;
          for (int i = 0; i < poly.length; i++) {
               answer += (long) poly[i] * (long) Math.pow(n, i);
          }
          return answer;
     }
}
