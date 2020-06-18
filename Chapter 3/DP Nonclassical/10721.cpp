#include <bits/stdc++.h>

using namespace std;

long long memo[51][51][51];

long long count_bar(int n, int k, int m) {
     if (n < 1 || k < 0 || m < 0) return 0;
     if (memo[n][k][m] != -1) return memo[n][k][m];
     if (k == 1 && n > m) return 0;
     if (n == k) return 1;
     if (m > n - k + 1) return count_bar(n, k, n - k + 1);
     memo[n][k][m] = count_bar(n - 1, k, m) - count_bar(n - m - 1, k - 1, m) + count_bar(n - 1, k - 1, m);
     return memo[n][k][m];
}

int main() {
     while (!feof(stdin)) {
          int n, k, m;
          if (scanf("%d%d%d", &n, &k, &m) == EOF) break;
          for (int i = 0; i <= n; i++) {
               for (int j = 0; j <= k; j++) {
                    for (int l = 0; l <= m; l++) {
                         memo[i][j][l] = -1;
                    }
               }
          }

          printf("%lld\n", count_bar(n, k, m));
     }
}
