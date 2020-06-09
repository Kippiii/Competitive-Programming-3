#include <bits/stdc++.h>

using namespace std;

int arr[101][101];

int main() {
     int n;
     scanf("%d", &n);
     for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
               scanf("%d", &arr[i][j]);
          }
     }

     for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
               if (i != 0) arr[i][j] += arr[i-1][j];
               if (j != 0) arr[i][j] += arr[i][j-1];
               if (i != 0 && j != 0) arr[i][j] -= arr[i-1][j-1];
          }
     }

     int max_sum = -99999;
     for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
               for (int k = i; k < n; k++) {
                    for (int l = j; l < n; l++) {
                         int sum = arr[k][l];
                         if (i != 0) sum -= arr[i-1][l];
                         if (j != 0) sum -= arr[k][j-1];
                         if (i != 0 && j != 0) sum += arr[i-1][j-1];
                         if (sum > max_sum) max_sum = sum;
                    }
               }
          }
     }
     printf("%d\n", max_sum);
}
