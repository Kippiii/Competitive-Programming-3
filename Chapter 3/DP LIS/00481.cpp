#include <bits/stdc++.h>

using namespace std;

int A[1000000], L[1000000], L_id[1000000];

int main() {
     int count = 0;
     int L_len = 0;
     while(!feof(stdin)) {
          scanf("%d", A + count);
          int lower = lower_bound(L, L + L_len, A[count]) - L;
          L[lower] = A[count];
          L_id[lower] = count;
          if (lower == L_len) L_len++;
          count++;
     }

     for (int i = L_len - 2; i >= 0; i--) {
          if (L_id[i] > L_id[i+1]) {
               for (int j = L_id[i+1]; j >= 0; j--) {
                    if (A[j] < A[L_id[i+1]] && A[j] > A[L_id[i]]) {
                         L_id[i] = j;
                         break;
                    }
               }
          }
     }

     printf("%d\n", L_len);
     printf("-\n");
     for (int i = 0; i < L_len; i++) {
          printf("%d\n", A[L_id[i]]);
     }
}
