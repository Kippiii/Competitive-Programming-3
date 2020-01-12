import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

          String line;
          while ((line = br.readLine()) != null) {
               String[] params = line.split("\\s+");
               int n = Integer.parseInt(params[0]);
               int count = Integer.parseInt(params[1]);
               int[] tracks = new int[count];
               for (int i = 0; i < count; i++)
                    tracks[i] = Integer.parseInt(params[2 + i]);

               int[] answer = createTape(tracks, n, 0, 0, 0);
               for (int i = 0; i < count; i++) {
                    if ((answer[1] & (1 << i)) > 0) {
                         pr.printf("%d ", tracks[i]);
                    }
               }
               pr.printf("sum:%d%n", answer[0]);
          }

          pr.close();
     }

     public static int[] createTape(int[] tracks, int n, int index, int sum, int onTape) {
          int[] answer = new int[2];
          if (index >= tracks.length) {
               answer[0] = -1;
               answer[1] = -1;
               return answer;
          }
          if (sum + tracks[index] <= n) {
               int[] max = new int[2];
               for (int i = index + 1; i < tracks.length; i++) {
                    int[] values = createTape(tracks, n, i, sum + tracks[index], onTape | (1 << index));
                    if (values[0] > max[0]) {
                         max[0] = values[0];
                         max[1] = values[1];
                    }
               }
               int[] values = createTape(tracks, n, index + 1, sum, onTape);
               if (values[0] > max[0])
                    answer = values;
               else
                    answer = max;
               if (sum + tracks[index] > answer[0]) {
                    answer[0] = sum + tracks[index];
                    answer[1] = onTape | (1 << index);
               }
          } else {
               answer[0] = sum;
               answer[1] = onTape;
          }
          return answer;
     }
}
