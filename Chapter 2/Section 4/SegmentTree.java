import java.util.*;

class SegmentTree {
     private int st[], A[], n;
     private int left(int p) {
          return p << 1;
     }
     private int right(int p) {
          return (p << 1) + 1;
     }

     public SegmentTree(int[] _A) {
          A = _A;
          n = A.length;
          st = new int[4*n];
          for (int i = 0; i < 4*n; i++) st[i] = 0;
          build(1, 0, n - 1);
     }

     private void build(int p, int l, int r) {
          if (l == r)
               st[p] = l;
          else {
               build(left(p), l, (l + r) / 2);
               build(right(p), (l + r) / 2 + 1, r);
               int p1 = st[left(p)];
               int p2 = st[right(p)];
               st[p] = (A[p1] <= A[p2]) ? p1 : p2;
          }
     }

     private int rmq(int p, int l, int r, int i, int j) {
          if (i > r || j < l) return -1;
          if (l >= i && r <= j) return st[p];

          int p1 = rmq(left(p), l, (l + r) / 2, i, j);
          int p2 = rmq(right(p), (l + r) / 2 + 1, r, i, j);

          if (p1 == -1) return p2;
          if (p2 == -1) return p1;
          return (A[p1] <= A[p2]) ? p1 : p2;
     }

     private int updatePoint(int p, int l, int r, int i, int newValue) {
          if (i > r || i < l)
               return st[p];

          if (l == i && r == i) {
               A[i] = newValue;
               return l;
          }

          int p1 = updatePoint(left(p), l, (l + r) / 2, i, newValue);
          int p2 = updatePoint(right(p), (l + r) / 2 + 1, r, i, newValue);

          return st[p] = (A[p1] <= A[p2]) ? p1 : p2;
     }

     public int rmq(int i, int j) {
          return rmq(1, 0, n - 1, i, j);
     }

     public int updatePoint(int i, int newValue) {
          return updatePoint(1, 0, n - 1, i, newValue);
     }
}
