#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> ii;

typedef enum {
     N,
     E,
     S,
     W
} dir;

ii get_vect(dir d) {
     ii sol;
     switch (d) {
          case N:
               sol.first = -1;
               sol.second = 0;
               break;
          case E:
               sol.first = 0;
               sol.second = 1;
               break;
          case S:
               sol.first = 1;
               sol.second = 0;
               break;
          case W:
               sol.first = 0;
               sol.second = -1;
               break;
     }
     return sol;
}

dir get_dir(char c) {
     switch (c) {
          case 'N':
               return N;
          case 'S':
               return S;
          case 'L':
               return E;
          case 'O':
               return W;
     }
     return (dir) -1;
}

dir right(dir start) {
     return (dir)((start + 1) % 4);
}

dir left(dir start) {
     return (dir)((start + 3) % 4);
}

int main() {
     while (true) {
          int n, m, s;
          scanf("%d%d%d", &n, &m, &s);
          if (n == 0 && m == 0 && s == 0) break;
          char grid[n][m];
          for (int i = 0; i < n; i++) {
               for (int j = 0; j < m; j++) {
                    scanf(" %c", grid[i] + j);
               }
          }
          char comms[s];
          for (int i = 0; i < s; i++) {
               scanf(" %c", comms + i);
          }

          ii pos;
          dir direction = (dir) -1;
          for (int i = 0; i < n && direction == -1; i++) {
               for (int j = 0; j < m && direction == -1; j++) {
                    if (grid[i][j] == 'N' || grid[i][j] == 'S' || grid[i][j] == 'L' || grid[i][j] == 'O') {
                         pos.first = i;
                         pos.second = j;
                         direction = get_dir(grid[i][j]);
                         grid[i][j] = '.';
                    }
               }
          }

          int stickers = 0;
          for (int i = 0; i < s; i++) {
               //printf("%d, %d, %d, %d\n", direction, pos.first, pos.second, stickers);
               if (comms[i] == 'D') {
                    direction = right(direction);
               } else if (comms[i] == 'E') {
                    direction = left(direction);
               } else {
                    ii vector = get_vect(direction);
                    if (pos.first + vector.first >= n || pos.first + vector.first < 0 || pos.second + vector.second >= m || pos.second + vector.second < 0 || grid[pos.first + vector.first][pos.second + vector.second] == '#') {
                         continue;
                    }

                    pos.first += vector.first;
                    pos.second += vector.second;
                    if (grid[pos.first][pos.second] == '*') {
                         stickers++;
                         grid[pos.first][pos.second] = '.';
                    }
               }
          }

          printf("%d\n", stickers);
     }
}
