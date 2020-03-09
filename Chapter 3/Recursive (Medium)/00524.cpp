#include <bits/stdc++.h>

using namespace std;

int primes[11] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};

void solve(int n, int solution[], int v = 0, int used = 0) {
     if (v >= n) {
          int solved = false;
          for (int i = 0; i < 11 && !solved; i++)
               solved = primes[i] == solution[n - 1] + solution[0];
          if (!solved) return;
          for (int i = 0; i < n; i++)
               printf("%d ", solution[i]);
          printf("\n");
     }
     if (v == 0) {
          solution[0] = 1;
          used |= 2;
          solve(n, solution, v + 1, used);
          return;
     }
     int prev = solution[v - 1];
     for (int i = 0; i < 11; i++) {
          int num = primes[i] - prev;
          if (!(used & (1 << num)) && num <= n && num > 0) {
               //printf("%d, %d\n", prev, num);
               used |= 1 << num;
               solution[v] = num;
               solve(n, solution, v + 1, used);
               used ^= 1 << num;
          }
     }
}

int main() {
     int counter = 1;
     while (!feof(stdin)) {
          int n;
          scanf("%d", &n);
          int solution[n];
          printf("Case %d: \n", counter++);
          solve(n, solution);
          printf("\n");
     }
}
