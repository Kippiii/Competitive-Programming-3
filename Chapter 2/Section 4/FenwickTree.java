import java.util.*;

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
