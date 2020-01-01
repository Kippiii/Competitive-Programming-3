import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws IOException {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          int n = Integer.parseInt(br.readLine());
          for (int i = 0; i < n; i++) {
               int params[] = Arrays.stream(br.readLine().split(" ", 0)).mapToInt(Integer::parseInt).toArray();
               int b = params[0];
               int sg = params[1];
               int sb = params[2];

               PriorityQueue<Integer> green = new PriorityQueue<Integer>();
               PriorityQueue<Integer> blue = new PriorityQueue<Integer>();

               for (int j = 0; j < sg; j++) {
                    green.add(-1 * Integer.parseInt(br.readLine()));
               }
               for (int j = 0; j < sb; j++) {
                    blue.add(-1 * Integer.parseInt(br.readLine()));
               }

               while (green.size() > 0 && blue.size() > 0) {
                    int battle[][] = new int[b][2];

                    Object greenTmp;
                    for (int counter = 0; counter < b && (greenTmp = green.poll()) != null; counter++) {
                         battle[counter][0] = (int)greenTmp * -1;
                    }

                    Object blueTmp;
                    for (int counter = 0; counter < b && (blueTmp = blue.poll()) != null; counter++) {
                         battle[counter][1] = (int)blueTmp * -1;
                    }

                    for (int j = 0; j < b; j++) {
                         if (battle[j][0] > battle[j][1]) {
                              green.add(-1 * (battle[j][0] - battle[j][1]));
                         } else if (battle[j][0] < battle[j][1]) {
                              blue.add(-1 * (battle[j][1] - battle[j][0]));
                         }
                    }
               }

               if (green.size() == 0 && blue.size() == 0) {
                    System.out.println("green and blue died");
               } else if (blue.size() == 0) {
                    System.out.println("green wins");
                    while (green.size() > 0) {
                         System.out.println(green.poll() * -1);
                    }
               } else {
                    System.out.println("blue wins");
                    while (blue.size() > 0) {
                         System.out.println(blue.poll() * -1);
                    }
               }

               if (i < n - 1) System.out.println();
          }
     }
}
