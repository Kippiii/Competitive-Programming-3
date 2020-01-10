import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

          int n = Integer.parseInt(br.readLine());
          for (int i = 0; i < n; i++) {
               ArrayList<LinkedList<Integer>> adj = new ArrayList<LinkedList<Integer>>();
               for (int j = 0; j < 26; j++) {
                    adj.add(new LinkedList<Integer>());
               }
               String in;
               while (!(in = br.readLine()).substring(0, 1).equals("*")) {
                    int p1 = (int) in.charAt(1) - (int) 'A';
                    int p2 = (int) in.charAt(3) - (int) 'A';
                    adj.get(p1).add(p2);
                    adj.get(p2).add(p1);
               }

               boolean[] isPoint = new boolean[26];
               in = br.readLine();
               for (char letter : in.toCharArray()) {
                    if (letter == ',')
                         continue;
                    isPoint[(int) letter - (int) 'A'] = true;
               }

               boolean[] visited = new boolean[26];
               int trees = 0;
               int acorns = 0;
               for (int j = 0; j < 26; j++) {
                    if (!isPoint[j] || visited[j])
                         continue;
                    if (isAcorn(j, adj, visited)) {
                         acorns++;
                    } else {
                         trees++;
                    }
               }

               System.out.println("There are " + trees + " tree(s) and " + acorns + " acorn(s).");
          }
     }

     public static boolean isAcorn(int current, ArrayList<LinkedList<Integer>> adj, boolean[] visited) {
          if (adj.get(current).size() == 0) {
               visited[current] = true;
               return true;
          }

          if (visited[current]) {
               return false;
          }

          visited[current] = true;
          for (int value : adj.get(current)) {
               isAcorn(value, adj, visited);
          }
          return false;
     }
}
