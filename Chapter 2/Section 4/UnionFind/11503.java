import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

          int n = Integer.parseInt(br.readLine());
          for (int i = 0; i < n; i++) {
               int f = Integer.parseInt(br.readLine());
               int counter = 0;
               TreeMap<String, Integer> nums = new TreeMap<String, Integer>();
               int[][] cons = new int[f][2];
               for (int j = 0; j < f; j++) {
                    String[] names = br.readLine().split(" ", 0);
                    if (!nums.containsKey(names[0])) {
                         nums.put(names[0], counter++);
                    }
                    if (!nums.containsKey(names[1])) {
                         nums.put(names[1], counter++);
                    }
                    cons[j][0] = nums.get(names[0]);
                    cons[j][1] = nums.get(names[1]);
               }

               UnionFind uf = new UnionFind(counter);
               for (int[] connection : cons) {
                    uf.unionSet(connection[0], connection[1]);
                    System.out.println(uf.sizeOfSet(connection[0]));
               }
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
