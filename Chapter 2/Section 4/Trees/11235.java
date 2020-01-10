import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) throws Exception {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

          while (true) {
               String[] vars = br.readLine().split(" ", 0);
               int n = Integer.parseInt(vars[0]);
               if (n == 0) break;
               int q = Integer.parseInt(vars[1]);
               String[] numsStr = br.readLine().split(" ", 0);
               int[] nums = new int[n];
               for (int i = 0; i < n; i++) {
                    nums[i] = Integer.parseInt(numsStr[i]);
               }

               int[] freq = new int[200005];
               int[] counts = new int[n];
               int previous = -100005;
               int counter = 1;
               for (int i = 0; i < n; i++) {
                    freq[100000 + nums[i]]++;

                    if (nums[i] == previous) {
                         counts[i] = counter++;
                    } else {
                         counter = 1;
                         counts[i] = counter++;
                         previous = nums[i];
                    }
               }
               int[] freqNums = new int[n];
               for (int i = 0; i < n; i++) {
                    freqNums[i] = -1 * freq[100000 + nums[i]];
               }

               SegmentTree st = new SegmentTree(freqNums);
               for (int i = 0; i < q; i++) {
                    String[] query = br.readLine().split(" ", 0);
                    int q1 = Integer.parseInt(query[0]) - 1;
                    int q2 = Integer.parseInt(query[1]) - 1;
                    if (nums[q1] == nums[q2]) {
                         System.out.println(q2 - q1 + 1);
                         continue;
                    }
                    int leftFreq = freq[100000 + nums[q1]] - counts[q1] + 1;
                    int rightFreq = counts[q2];
                    int index = st.rmq(q1 + leftFreq, q2 - rightFreq);
                    int middleFreq = (index != -1) ? freq[100000 + nums[index]] : -1;

                    System.out.println(Math.max(leftFreq, Math.max(rightFreq, middleFreq)));
               }
          }
     }
}

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
