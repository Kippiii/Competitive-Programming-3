#include <bits/stdc++.h>

using namespace std;

int speed[1001][10];
int memo[1001][10];

int main() {
     int n;
     scanf("%d", &n);
     while (n--) {
          int x;
          scanf("%d", &x);
          for (int i = 9; i >= 0; i--) {
               for (int j = 0; j < x / 100; j++) {
                    scanf("%d", &speed[j][i]);
               }
          }

          for (int i = 1; i < 10; i++) {
               memo[x / 100][i] = 999999;
          }
          memo[x / 100][0] = 0;

          for (int i = x / 100 - 1; i >= 0; i--) {
               for (int j = 0; j <= 9; j++) {
                    int min_fuel = INT_MAX;
                    if (j != 9) min_fuel = min(min_fuel, memo[i + 1][j + 1] - speed[i][j] + 60);
                    min_fuel = min(min_fuel, memo[i + 1][j] - speed[i][j] + 30);
                    if (j != 0) min_fuel = min(min_fuel, memo[i + 1][j - 1] - speed[i][j] + 20);
                    memo[i][j] = min_fuel;
               }
          }

          printf("%d\n\n", memo[0][0]);
     }
}
