import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            while (true) {
                int order[] = Arrays.stream(br.readLine().split(" ", 0)).mapToInt(Integer::parseInt).toArray();
                if (order[0] == 0) break;

                Stack<Integer> station = new Stack<Integer>();
                LinkedList<Integer> A = new LinkedList<Integer>();

                for (int i = 1; i <= n; i++) {
                    A.addLast(i);
                }

                int i;
                for (i = 0; i < n; i++) {
                    if (station.size() > 0 && station.peek() == order[i]) {
                        station.pop();
                    } else {
                        while (A.size() > 0) {
                            int value = A.removeFirst();
                            station.push(value);
                            if (value == order[i]) {
                                break;
                            }
                        }
                        if (A.size() == 0 && station.size() != 0 && station.peek() != order[i]) {
                            System.out.println("No");
                            break;
                        }
                        station.pop();
                    }
                }
                if (station.size() == 0) {
                    System.out.println("Yes");
                }
            }
            System.out.println();
        }
    }
}
