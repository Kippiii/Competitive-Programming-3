import java.util.*;
import java.io.*;

public class Main {
     public static StringBuilder out;
     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          out = new StringBuilder();

          while (true) {
               String init = br.readLine();
               if (init == null) break;
               String[] params = init.split(" ", 0);
               int n = Integer.parseInt(params[0]);
               int k = Integer.parseInt(params[1]);
               String[] seqStr = br.readLine().split(" ", 0);
               FenwickTree ft = new FenwickTree(n);
               for (int i = 0; i < n; i++) {
                    int value = Integer.parseInt(seqStr[i]);
                    int toUp;
                    if (value < 0) {
                         toUp = 1;
                    } else if (value > 0) {
                         toUp = 0;
                    } else {
                         toUp = -100000;
                    }
                    ft.update(i + 1, toUp);
               }

               for (int i = 0; i < k; i++) {
                    String[] command = br.readLine().split(" ", 0);
                    if (command[0].equals("C")) {
                         int value = Integer.parseInt(command[2]);
                         int toUp;
                         if (value < 0) {
                              toUp = 1;
                         } else if (value > 0) {
                              toUp = 0;
                         } else {
                              toUp = -100000;
                         }
                         int index = Integer.parseInt(command[1]);
                         ft.update(index, toUp - ft.rsq(index, index));
                    } else if (command[0].equals("P")) {
                         int sum = ft.rsq(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                         if (sum < 0) {
                              out.append("0");
                         } else if (sum % 2 == 0) {
                              out.append("+");
                         } else {
                              out.append("-");
                         }
                    }
               }
               out.append("\n");
          }

          System.out.print(out);
     }
}

class FenwickTree {
     private ArrayList<Integer> ft;

     private int LSOne(int s) {
          return (s & (-s));
     }

     public FenwickTree(int n) {
          ft = new ArrayList<>();
          for (int i = 0; i <= n; i++)
               ft.add(0);
     }

     public int rsq(int j) {
          int sum = 0;
          for (; j > 0; j -= LSOne(j))
               sum += ft.get(j);
          return sum;
     }

     public int rsq(int i, int j) {
          return rsq(j) - rsq(i - 1);
     }

     public void update(int i, int v) {
          for (; i < (int) ft.size(); i += LSOne(i))
               ft.set(i, ft.get(i) + v);
     }
}
