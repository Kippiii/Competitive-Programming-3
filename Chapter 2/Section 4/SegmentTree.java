import java.util.*;

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
