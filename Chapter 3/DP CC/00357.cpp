#include <bits/stdc++.h>

using namespace std;

int coins[] = {1, 5, 10, 25, 50};
long long possible[30005][5];

int main() {
     while (!feof(stdin)) {
          int n;
          if (scanf("%d", &n) == EOF) break;
          possible[0][4] = 1;
          for (int i = 1; i <= n; i++) {
               for (int j = 0; j < 5; j++) {
                    long long sum = 0;
                    if (coins[j] <= i) {
                         for (int k = j; k < 5; k++) {
                              sum += possible[i - coins[j]][k];
                         }
                    }
                    possible[i][j] = sum;
               }
          }
          long long answer = possible[n][0] + possible[n][1] + possible[n][2] + possible[n][3] + possible[n][4];
          if (answer == 1)
               printf("There is only 1 way to produce %d cents change.\n", n);
          else
               printf("There are %lld ways to produce %d cents change.\n", answer, n);
     }
}
