import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws IOException {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

          int set = Integer.parseInt(br.readLine());
          for (int i = 0; i < set; i++) {
               int params[] = Arrays.stream(br.readLine().split(" ", 0)).mapToInt(Integer::parseInt).toArray();
               int n = params[0];
               int s = params[1];
               int q = params[2];

               LinkedList<Integer> stations[] = new LinkedList[n];
               for (int j = 0; j < n; j++) {
                    stations[j] = new LinkedList<Integer>();
               }
               Stack<Integer> carrier = new Stack<Integer>();

               int numCargo = 0;
               for (int j = 0; j < n; j++) {
                    int queue[] = Arrays.stream(br.readLine().split(" ", 0)).mapToInt(Integer::parseInt).toArray();
                    numCargo += queue[0];
                    for (int k = 1; k < queue.length; k++) {
                         stations[j].addLast(queue[k]);
                    }
               }

               int time = 0;
               int current = 1;
               while (numCargo > 0) {
                    while (carrier.size() > 0 && (stations[current - 1].size() < q || carrier.peek() == current)) {
                         time++;
                         int cargo = carrier.pop();
                         if (cargo == current) {
                              numCargo--;
                         } else {
                              stations[current - 1].addLast(cargo);
                         }
                    }

                    while (stations[current - 1].size() > 0 && carrier.size() < s) {
                         time++;
                         int cargo = stations[current - 1].removeFirst();
                         carrier.push(cargo);
                    }

                    if (numCargo > 0) {
                         time += 2;
                         current = current % n + 1;
                    }
               }

               System.out.println(time);
          }
     }
}
