import java.util.*;

public class Tester {
     public static void main(String[] args) {
          int[] A = {18, 17, 13, 19, 15, 11, 20, 99};       // make n a power of 2
          SegmentTree st = new SegmentTree(A);

          System.out.printf("              idx    0, 1, 2, 3, 4, 5, 6, 7\n");
          System.out.printf("              A is {18,17,13,19,15,11,20,oo}\n");
          System.out.printf("RMQ(1, 3) = %d\n", st.rmq(1, 3));      // index 2
          System.out.printf("RMQ(4, 7) = %d\n", st.rmq(4, 7));      // index 5
          System.out.printf("RMQ(3, 4) = %d\n", st.rmq(3, 4));      // index 4

          st.update(5, 5, 77);                           // update A[5] to 77
          System.out.printf("              idx    0, 1, 2, 3, 4, 5, 6, 7\n");
          System.out.printf("Now, modify A into {18,17,13,19,15,77,20,oo}\n");
          System.out.printf("RMQ(1, 3) = %d\n", st.rmq(1, 3));      // remains index 2
          System.out.printf("RMQ(4, 7) = %d\n", st.rmq(4, 7));      // now index 4
          System.out.printf("RMQ(3, 4) = %d\n", st.rmq(3, 4));      // remains index 4

          st.update(0, 3, 30);                           // update A[0..3] to 30
          System.out.printf("              idx    0, 1, 2, 3, 4, 5, 6, 7\n");
          System.out.printf("Now, modify A into {30,30,30,30,15,77,20,oo}\n");
          System.out.printf("RMQ(1, 3) = %d\n", st.rmq(1, 3));      // [0,1,2,3] all correct
          System.out.printf("RMQ(4, 7) = %d\n", st.rmq(4, 7));      // remains index 4
          System.out.printf("RMQ(3, 4) = %d\n", st.rmq(3, 4));      // remains index 4

          st.update(3, 3, 7);                            // update A[3] to 7
          System.out.printf("              idx    0, 1, 2, 3, 4, 5, 6, 7\n");
          System.out.printf("Now, modify A into {30,30,30, 7,15,77,20,oo}\n");
          System.out.printf("RMQ(1, 3) = %d\n", st.rmq(1, 3));      // now index 3
          System.out.printf("RMQ(4, 7) = %d\n", st.rmq(4, 7));      // remains index 4
          System.out.printf("RMQ(3, 4) = %d\n", st.rmq(3, 4));      // now index 3
     }
}

class SegmentTree {
     private int st[], A[], n, lazy[];
     private int l(int p) {
          return p << 1;
     }
     private int r(int p) {
          return (p << 1) + 1;
     }

     public SegmentTree(int[] _A) {
          A = _A;
          n = A.length;
          st = new int[4*n];
          lazy = new int[4*n];
          for (int i = 0; i < 4*n; i++) {st[i] = 0; lazy[i] = -1;}
          build(1, 0, n - 1);
     }

     private int conquer(int a, int b) {
          if (a == -1) return b;
          if (b == -1) return a;
          return A[a] <= A[b] ? a : b;
     }

     private void build(int p, int L, int R) {
          if (L == R)
               st[p] = L;
          else {
               int m = (L+R)/2;
               build(l(p), L, m);
               build(r(p), m + 1, R);
               st[p] = conquer(st[l(p)], st[r(p)]);
          }
     }

     private void propagate(int p, int L, int R) {
          if (lazy[p] != -1) {
               st[p] = L;
               if (L != R)
                    lazy[l(p)] = lazy[r(p)] = lazy[p];
               else
                    A[L] = lazy[p];
               lazy[p] = -1;
          }
     }

     private int rmq(int p, int L, int R, int i, int j) {
          propagate(p, L, R);
          if (i > j) return -1;
          if (L >= i && R <= j) return st[p];
          int m = (L+R)/2;
          return conquer(rmq(l(p), L, m, i, Math.min(m, j)), rmq(r(p), m+1, R, Math.max(i, m+1), j));
     }

     private void update(int p, int L, int R, int i, int j, int val) {
          propagate(p, L, R);
          if (i > j) return;
          if (L >= i && R <= j) {
               A[L] = val;
               lazy[p] = val;
               propagate(p, L, R);
          } else {
               int m = (L+R)/2;
               update(l(p), L, m, i, Math.min(m, j), val);
               update(r(p), m+1, R, Math.max(i, m+1), j, val);
               st[p] = conquer(st[l(p)], st[r(p)]);
          }
     }

     public int rmq(int i, int j) {
          return rmq(1, 0, n - 1, i, j);
     }

     public void update(int i, int j, int val) {
          update(1, 0, n - 1, i, j, val);
     }
}
