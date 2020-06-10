#include <bits/stdc++.h>

using namespace std;

int nums[200];
int meemo[200][11][40];

int count_num(int n, int d, int m, int index, int sum);

int main() {
     int set_num = 1;
     while (true) {
          int n, q;
          scanf("%d%d", &n, &q);
          if (n == 0 && q == 0) break;
          printf("SET %d:\n", set_num++);
          for (int i = 0; i < n; i++) {
               scanf("%d", nums + i);
          }
          for (int qnum = 1; qnum <= q; qnum++) {
               int d, m;
               scanf("%d%d", &d, &m);
               for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                         for (int k = 0; k < 2*d; k++) {
                              meemo[i][j][k] = -1;
                         }
                    }
               }
               int ans = count_num(n, d, m, 0, 0);
               printf("QUERY %d: %d\n", qnum, ans);
          }
     }
}

int count_num(int n, int d, int m, int index, int sum) {
     if (m <= 0) return sum % d == 0;
     if (index >= n) return 0;
     if (meemo[index][m - 1][sum % d + d] != -1) return meemo[index][m - 1][sum % d + d];
     int tmp_sum = count_num(n, d, m - 1, index + 1, sum + nums[index]) + count_num(n, d, m, index + 1, sum);
     meemo[index][m - 1][sum % d + d] = tmp_sum;
     return tmp_sum;
}
