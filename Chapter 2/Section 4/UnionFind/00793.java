import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] argss) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

          int n = Integer.parseInt(br.readLine());
          br.readLine();
          for(int i = 0; i < n; i++) {
               int c = Integer.parseInt(br.readLine());
               UnionFind uf = new UnionFind(c);
               String command;
               int successes = 0;
               int fails = 0;
               while ((command = br.readLine()) != null && !command.trim().isEmpty()) {
                    String[] args = command.split(" ", 0);
                    if (args[0].equals("c")) {
                         uf.unionSet(Integer.parseInt(args[1]) - 1, Integer.parseInt(args[2]) - 1);
                    } else if (args[0].equals("q")) {
                         if (uf.isSameSet(Integer.parseInt(args[1]) - 1, Integer.parseInt(args[2]) - 1)) {
                              successes++;
                         } else {
                              fails++;
                         }
                    }
               }

               System.out.println(successes + "," + fails);
               if (i < n - 1)
                    System.out.println();
          }
     }
}

class UnionFind {
     private ArrayList<Integer> p, rank, setSize;
     private int numSets;

     public UnionFind(int n) {
          p = new ArrayList<>(n);
          rank = new ArrayList<>(n);
          setSize = new ArrayList<>(n);
          numSets = n;
          for (int i = 0; i < n; i++) {
               p.add(i);
               rank.add(0);
               setSize.add(1);
          }
     }

     public int findSet(int i) {
          if (p.get(i) == i) return i;
          else {
               int tmp = findSet(p.get(i));
               p.set(i, tmp);
               return tmp;
          }
     }

     public boolean isSameSet(int i, int j) {
          return findSet(i) == findSet(j);
     }

     public void unionSet(int i, int j) {
          if (!isSameSet(i, j)) {
               numSets--;
               int x = findSet(i);
               int y = findSet(j);
               if (rank.get(x) > rank.get(y)) {
                    p.set(y, x);
                    setSize.set(x, setSize.get(x) + setSize.get(y));
               } else {
                    p.set(x, y);
                    setSize.set(y, setSize.get(y) + setSize.get(x));
                    if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1);
               }
          }
     }

     public int numDisjointSets() {
          return numSets;
     }

     public int sizeOfSet(int i) {
          return setSize.get(findSet(i));
     }
}
