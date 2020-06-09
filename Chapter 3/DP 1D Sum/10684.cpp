#include <bits/stdc++.h>

using namespace std;

int main() {
     while (true) {
          int n;
          scanf("%d", &n);
          if (n == 0) break;
          int sum = 0, ans = 0;
          for (int i = 0; i < n; i++) {
               int cur;
               scanf("%d", &cur);
               sum += cur;
               if (sum > ans) ans = sum;
               if (sum < 0) sum = 0;
          }

          if (ans <= 0) {
               printf("Losing streak.\n");
          } else {
               printf("The maximum winning streak is %d.\n", ans);
          }
     }
}