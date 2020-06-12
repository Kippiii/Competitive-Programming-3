#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> ii;

ii computers[8];
double memo[256][8];
int memo2[256][8];

double dist(ii a, ii b) {
     return sqrt(pow(a.first - b.first, 2) + pow(a.second - b.second, 2));
}

double travel(int n, int status, int prev, double path) {
     if (prev != -1 && memo[status][prev] != -1) return path + memo[status][prev];
     if (status == ((1 << (n)) - 1)) {
          return path;
     }
     double sol = DBL_MAX;
     int next = -1;
     for (int i = 0; i < n; i++) {
          if ((status & (1 << i)) == 0) {
               double tmp = travel(n, status | (1 << i), i, dist(computers[(status == 0 ? i : prev)], computers[i]));
               if (tmp < sol) {
                    sol = tmp;
                    next = i;
               }
          }
     }
     memo[status][prev] = sol;
     memo2[status][prev] = next;
     return sol + path;
}

int main() {
     int counter = 1;
     while (true) {
          int n;
          scanf("%d", &n);
          if (n == 0) break;
          printf("**********************************************************\n");
          printf("Network #%d\n", counter++);
          for (int i = 0; i < n; i++) {
               scanf("%d%d", &computers[i].first, &computers[i].second);
          }

          for (int i = 0; i < (1 << n); i++) {
               for (int j = 0; j < n; j++) {
                    memo[i][j] = -1;
                    memo2[i][j] = -1;
               }
          }
          double ans = travel(n, 0, 0, 0.0) + (n - 1) * 16;
          int start = memo2[0][0];
          int status = 0;
          for (int i = 0; i < n - 1; i++) {
               status |= (1 << start);
               printf("Cable requirement to connect (%d,%d) to (%d,%d) is %.2f feet.\n", computers[start].first, computers[start].second, computers[memo2[status][start]].first, computers[memo2[status][start]].second, dist(computers[start], computers[memo2[status][start]]) + 16);
               start = memo2[status][start];
          }
          printf("Number of feet of cable required is %.2f.\n", ans);
     }
}
