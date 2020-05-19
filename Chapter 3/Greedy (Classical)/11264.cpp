#include <bits/stdc++.h>

using namespace std;

int coins[1005];

int main() {
     int t;
     scanf("%d", &t);
     while (t--) {
          int n;
          scanf("%d", &n);
          for (int i = 0; i < n; i++) {
               scanf("%d", coins + i);
          }

          int count = 1;
          int sum = 0;
          for (int i = 0; i < n - 1; i++) {
               if (sum + coins[i] < coins[i+1]) {
                    count++;
                    sum += coins[i];
               }
          }

          printf("%d\n", count);
     }
}
