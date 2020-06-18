#include <bits/stdc++.h>

using namespace std;

long long memo[101][101];

int main() {
     for (int i = 0; i < 101; i++) {
          for (int j = 0; j < 101; j++) {
               memo[i][j] = -1;
          }
     }
     while (true) {
          int n, k;
          scanf("%d%d", &n, &k);
          if (n == 0 && k == 0) break;

          for (int i = 0; i <= n; i++) {
               memo[i][0] = 0;
          }

          memo[0][1] = 1;
          for (int i = 2; i <= k; i++) {
               memo[0][i] = 1;
          }

          for (int i = 1; i <= n; i++) {
               memo[i][1] = 1;
               for (int j = 2; j <= k; j++) {
                    if (memo[i][j] != -1) continue;
                    long long count = 0;
                    for (int m = 0; m <= i; m++) {
                         count = (count + memo[m][j-1]) % 1000000;
                    }
                    memo[i][j] = count;
               }
          }

          printf("%lld\n", memo[n][k]);
     }
}
