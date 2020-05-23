#include <bits/stdc++.h>

using namespace std;

int nums[1005];

int main() {
     while (true) {
          int n;
          scanf("%d", &n);
          if (n == 0) break;
          for (int i = 0; i < n; i++) {
               scanf("%d", nums + i);
          }

          int all_zero = 1;
          for (int i = 0; i < n; i++) {
               if (nums[i] != 0) {
                    all_zero = 0;
                    break;
               }
          }
          if (all_zero) {
               printf("0\n");
               continue;
          }

          bool first = true;
          for (int i = 0; i < n; i++) {
               if (nums[i] != 0) {
                    if (first)
                         first = false;
                    else
                         printf(" ");
                    printf("%d", nums[i]);
               }
          }
          printf("\n");
     }
}
