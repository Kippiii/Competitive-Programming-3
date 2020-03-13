#include <bits/stdc++.h>

using namespace std;

typedef vector<int> vi;
typedef vector<vi> vii;

vii adjlist;

void solve(int n, int arr[], int arrcounter, vector<bool> bs, int bscounter, vi &solution) {
     while (n > bscounter && bs[bscounter] == true) {
          bscounter++;
     }

     if (n <= bscounter) {
          if ((int) solution.size() < arrcounter) {
               solution.erase(solution.begin(), solution.end());
               solution.insert(solution.begin(), arr, arr + arrcounter);
          }
     } else {
          for (; bscounter < (int) bs.size(); bscounter++)
               if (bs[bscounter] == false) {
                    vector<bool> tmpbs = bs;
                    tmpbs[bscounter] = true;
                    for (auto element : adjlist[bscounter]) {
                         tmpbs[element] = true;
                    }
                    arr[arrcounter] = bscounter;
                    solve(n, arr, arrcounter + 1, tmpbs, bscounter + 1, solution);
               }
     }
}

int main() {
     int m;
     scanf("%d", &m);
     for (int i = 0; i < m; i++) {
          int n, k;
          scanf("%d%d", &n, &k);

          for (int j = 0; j < n; j++)
               adjlist.resize(n);

          for (int j = 0; j < k; j++) {
               int n1, n2;
               scanf("%d%d", &n1, &n2);
               adjlist[n1 - 1].push_back(n2 - 1);
               adjlist[n2 - 1].push_back(n1 - 1);
          }

          int arr[n];
          vector<bool> bs(n, false);
          vi solution;
          solve(n, arr, 0, bs, 0, solution);
          printf("%d\n", (int) solution.size());
          printf("%d", solution[0] + 1);
          for (int j = 1; j < (int) solution.size(); j++) {
               printf(" %d", solution[j] + 1);
          }
          printf("\n");
          adjlist.erase(adjlist.begin(), adjlist.end());
     }
}
