#include <bits/stdc++.h>

using namespace std;

int morning[105];
int afternoon[105];

int main() {
     while (true) {
          int n, d, r;
          scanf("%d%d%d", &n, &d, &r);
          if (n == 0)
               break;
          for (int i = 0; i < n; i++) {
               scanf("%d", morning + i);
          }
          for (int i = 0; i < n; i++) {
               scanf("%d", afternoon + i);
          }
          sort(morning, morning + n);
          sort(afternoon, afternoon + n);

          int hours = 0;
          for (int i = 0; i < n; i++) {
               int length = morning[i] + afternoon[n - 1 - i];
               hours += max(0, length - d);
          }

          printf("%d\n", hours * r);
     }
}
