import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws IOException {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

          int n = Integer.parseInt(br.readLine());
          br.readLine();
          for (int i = 0; i < n; i++) {
               TreeMap<String, Integer> treeCount = new TreeMap<String, Integer>();
               ArrayList<String> trees = new ArrayList<String>();
               String tree;
               int counter = 0;
               while ((tree = br.readLine()) != null && !tree.trim().equals("")) {
                    counter++;
                    if (treeCount.containsKey(tree)) {
                         treeCount.put(tree, treeCount.get(tree) + 1);
                    } else {
                         treeCount.put(tree, 1);
                         trees.add(tree);
                    }
               }

               Collections.sort(trees);
               for (String t : trees) {
                    System.out.printf("%s %.4f%n", t, (double) treeCount.get(t) / counter * 100);
               }
               if (i < n - 1)
                    System.out.println();
          }
     }
}
