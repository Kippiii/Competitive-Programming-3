import java.util.*;
import java.io.*;

public class Main {

     public static StringBuilder out;

     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          out = new StringBuilder();

          int test = Integer.parseInt(br.readLine());
          for (int i = 0; i < test; i++) {
               out.append("Case " + (i + 1) + ":\n");
               int m = Integer.parseInt(br.readLine());
               StringBuilder pirateStr = new StringBuilder();
               for (int j = 0; j < m; j++) {
                    int t = Integer.parseInt(br.readLine());
                    String str = br.readLine();
                    for (int k = 0; k < t; k++) {
                         pirateStr.append(str);
                    }
               }
               char[] pirateChars = pirateStr.toString().toCharArray();
               int[] pirates = new int[pirateChars.length];
               for (int j = 0; j < pirateStr.length(); j++) {
                    pirates[j] = (int) pirateChars[j] - (int) '0';
               }

               SegmentTree st = new SegmentTree(pirates);
               int q = Integer.parseInt(br.readLine());
               int questionCounter = 1;
               for (int j = 0; j < q; j++) {
                    String[] query = br.readLine().split(" ", 0);
                    if(query[0].equals("F")) {
                         st.update(Integer.parseInt(query[1]), Integer.parseInt(query[2]), 1);
                    } else if (query[0].equals("E")) {
                         st.update(Integer.parseInt(query[1]), Integer.parseInt(query[2]), 0);
                    } else if (query[0].equals("I")) {
                         st.update(Integer.parseInt(query[1]), Integer.parseInt(query[2]), 2);
                    } else if (query[0].equals("S")) {
                         out.append("Q" + (questionCounter++) + ": " + st.rsq(Integer.parseInt(query[1]), Integer.parseInt(query[2])) + "\n");
                    }
               }
          }
          System.out.print(out);
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
          return a + b;
     }

     private void build(int p, int L, int R) {
          if (L == R)
               st[p] = A[L];
          else {
               int m = (L+R)/2;
               build(l(p), L, m);
               build(r(p), m + 1, R);
               st[p] = conquer(st[l(p)], st[r(p)]);
          }
     }

     private void propagate(int p, int L, int R) {
          if (lazy[p] != -1) {
               if (lazy[p] == 2) {
                    st[p] = R - L + 1 - st[p];
                    if (L != R) {
                         if (lazy[l(p)] == 2)
                              lazy[l(p)] = -1;
                         else if (lazy[l(p)] != -1)
                              lazy[l(p)] = (lazy[l(p)] == 1) ? 0 : 1;
                         else
                              lazy[l(p)] = 2;

                         if (lazy[r(p)] == 2)
                              lazy[r(p)] = -1;
                         else if (lazy[r(p)] != -1)
                              lazy[r(p)] = (lazy[r(p)] == 1) ? 0 : 1;
                         else
                              lazy[r(p)] = 2;
                    } else {
                         A[L] = (A[L] == 0) ? 1 : 0;
                    }
               } else {
                    st[p] = lazy[p] == 1 ? R - L + 1 : 0;
                    if (L != R)
                         lazy[l(p)] = lazy[r(p)] = lazy[p];
                    else {
                         A[L] = lazy[p];
                    }
               }
               lazy[p] = -1;
          }
     }

     private int rsq(int p, int L, int R, int i, int j) {
          propagate(p, L, R);
          if (i > j) return -1;
          //System.out.println("L = " + L + ", R = " + R + ", st[p] = " + st[p]);
          if (L >= i && R <= j) return st[p];
          int m = (L+R)/2;
          return conquer(rsq(l(p), L, m, i, Math.min(m, j)), rsq(r(p), m+1, R, Math.max(i, m+1), j));
     }

     private void update(int p, int L, int R, int i, int j, int val) {
          propagate(p, L, R);
          if (i > j) return;
          if (L > j || R < i) return;
          if (L >= i && R <= j) {
               if (val == 2) {
                    A[L] = (A[L] == 1) ? 0 : 1;
                    if (lazy[p] == 2)
                         lazy[p] = -1;
                    else if (lazy[p] != -1)
                         lazy[p] = (lazy[p] == 1) ? 0 : 1;
                    else
                         lazy[p] = val;
               } else {
                    A[L] = val;
                    lazy[p] = val;
               }
               propagate(p, L, R);
          } else {
               int m = (L+R)/2;
               update(l(p), L, m, i, Math.min(m, j), val);
               update(r(p), m+1, R, Math.max(i, m+1), j, val);
               st[p] = conquer(st[l(p)], st[r(p)]);
          }
     }

     private void invert(int p, int L, int R, int i, int j) {
          propagate(p, L, R);
          if (i > j) return;
          if (L > j || R < i) return;
          if (L >= i && R <= j) {
               A[L] = (A[L] == 0) ? 1 : 0;
               st[p] = R - L + 1 - st[p];
               if (L != R) {
                    int m = (L+R)/2;
                    invert(l(p), L, m, i, Math.min(m, j));
                    invert(r(p), m+1, R, Math.max(i, m+1), j);
               }
          } else {
               int m = (L+R)/2;
               invert(l(p), L, m, i, Math.min(m, j));
               invert(r(p), m+1, R, Math.max(i, m+1), j);
               st[p] = conquer(st[l(p)], st[r(p)]);
          }
     }

     public int rsq(int i, int j) {
          return rsq(1, 0, n - 1, i, j);
     }

     public void update(int i, int j, int val) {
          update(1, 0, n - 1, i, j, val);
     }

     public void invert(int i, int j) {
          invert(1, 0, n - 1, i, j);
     }
}
