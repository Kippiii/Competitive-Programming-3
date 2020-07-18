#include <bits/stdc++.h>

using namespace std;

bool grid[200][200];
int color[200][200];

int dirs[] = {1, 0, -1, 0, 0, 1, 0, -1};

int str_to_hex(char h) {
     if (isalpha(h))
          return h - 'a' + 10;
     return h - '0';
}

int floodfill(int h, int w, int x, int y) {
     //printf("%d, %d, %d, %d\n", h, w, x, y);
     int status = 0;
     for (int i = 0; i < 4; i++) {
          int new_x = x + dirs[2*i];
          int new_y = y + dirs[2*i + 1];
          if (new_x < 0 || new_x >= h || new_y < 0 || new_y >= w*4) {
               status = -1;
               continue;
          }
          if (grid[x][y] == grid[new_x][new_y] && color[new_x][new_y] == -1) {
               color[new_x][new_y] = color[x][y];
               int val = floodfill(h, w, new_x, new_y);
               if (status == 0) status = val;
               else if (status != val) status = -1;
          } else if (grid[x][y] != grid[new_x][new_y]) {
               if (status == 0) status = color[new_x][new_y];
               else if (status != color[new_x][new_y]) status = -1;
          }
     }
     return status;
}

int main() {
     int c = 1;
     while (true) {
          int h, w;
          scanf("%d%d", &h, &w);
          if (h == 0 && w == 0) break;
          for (int i = 0; i < h; i++) {
               for (int j = 0; j < w; j++) {
                    char c;
                    scanf(" %c", &c);
                    int num = str_to_hex(c);
                    for (int k = 0; k < 4; k++) {
                         grid[i][j*4 + k] = (num >> (3-k) & 1) != 0;
                    }
               }
          }
          for (int i = 0; i < h; i++) {
               for (int j = 0; j < w*4; j++) {
                    color[i][j] = -1;
               }
          }

          int num_hei = 0;
          int next_color = 1;
          for (int i = 0; i < h; i++) {
               for (int j = 0; j < w*4; j++) {
                    if (grid[i][j] && color[i][j] == -1) {
                         num_hei++;
                         color[i][j] = next_color++;
                         floodfill(h, w, i, j);
                    }
               }
          }

          int hole_count[num_hei];
          for (int i = 0; i < num_hei; i++)
               hole_count[i] = 0;
          for (int i = 0; i < h; i++) {
               for (int j = 0; j < w*4; j++) {
                    if (!grid[i][j] && color[i][j] == -1) {
                         color[i][j] = next_color++;
                         int val = floodfill(h, w, i, j);
                         if (val != -1) hole_count[val-1]++;
                    }
               }
          }

          char str[num_hei + 1];
          str[num_hei] = '\0';
          for (int i = 0; i < num_hei; i++) {
               assert(hole_count[i] >= 0 && hole_count[i] <= 5);
               switch (hole_count[i]) {
                    case 0:
                         str[i] = 'W';
                         break;
                    case 1:
                         str[i] = 'A';
                         break;
                    case 2:
                         str[i] = 'K';
                         break;
                    case 3:
                         str[i] = 'J';
                         break;
                    case 4:
                         str[i] = 'S';
                         break;
                    case 5:
                         str[i] = 'D';
                         break;

               }
          }

          sort(str, str + num_hei);
          printf("Case %d: %s\n", c++, str);
     }
}
