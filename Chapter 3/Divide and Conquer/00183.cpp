#include <bits/stdc++.h>

using namespace std;

char buffer[60];

typedef vector<bool> vb;
typedef vector<vb> vvb;

string to_special(int rows, int columns, vvb map, int row_start, int row_end, int column_start, int column_end) {
     bool all_same = true;
     for (int i = row_start; i <= row_end && all_same; i++) {
          for (int j = column_start; j <= column_end && all_same; j++) {
               all_same = map[i][j] == map[row_start][column_start];
          }
     }
     if (all_same) return map[row_start][column_start] ? "1" : "0";

     string str = "D";
     if (row_start == row_end && column_start == column_end) {
          str += map[row_start][column_start] ? "1" : "0";
     } else if (row_start == row_end) {
          int mid = (column_end + column_start) / 2;
          str += to_special(rows, columns, map, row_start, row_end, column_start, mid);
          str += to_special(rows, columns, map, row_start, row_end, mid + 1, column_end);
     } else if(column_start == column_end) {
          int mid = (row_end + row_start) / 2;
          str += to_special(rows, columns, map, row_start, mid, column_start, column_end);
          str += to_special(rows, columns, map, mid + 1, row_end, column_start, column_end);
     } else {
          int row_mid = (row_end + row_start) / 2;
          int column_mid = (column_end + column_start) / 2;
          str += to_special(rows, columns, map, row_start, row_mid, column_start, column_mid);
          str += to_special(rows, columns, map, row_start, row_mid, column_mid + 1, column_end);
          str += to_special(rows, columns, map, row_mid + 1, row_end, column_start, column_mid);
          str += to_special(rows, columns, map, row_mid + 1, row_end, column_mid + 1, column_end);
     }
     return str;
}

void from_special(int rows, int columns, vvb &map, int row_start, int row_end, int column_start, int column_end, int &counter) {
     assert(column_end >= column_start);

     char c;
     scanf(" %c", &c);
     if (c == '0' || c == '1') {
          bool value = c == '1';
          for (int i = row_start; i <= row_end; i++) {
               for (int j = column_start; j <= column_end; j++) {
                    map[i][j] = value;
               }
          }
          return;
     }

     assert(row_start != row_end || column_start != column_end);
     int row_mid = (row_end + row_start) / 2;
     int column_mid = (column_end + column_start) / 2;
     if (row_start == row_end) {
          from_special(rows, columns, map, row_start, row_end, column_start, column_mid, counter);
          from_special(rows, columns, map, row_start, row_end, column_mid + 1, column_end, counter);
          return;
     }
     if (column_start == column_end) {
          from_special(rows, columns, map, row_start, row_mid, column_start, column_end, counter);
          from_special(rows, columns, map, row_mid + 1, row_end, column_start, column_end, counter);
          return;
     }
     from_special(rows, columns, map, row_start, row_mid, column_start, column_mid, counter);
     from_special(rows, columns, map, row_start, row_mid, column_mid + 1, column_end, counter);
     from_special(rows, columns, map, row_mid + 1, row_end, column_start, column_mid, counter);
     from_special(rows, columns, map, row_mid + 1, row_end, column_mid + 1, column_end, counter);
}

int main() {
     while (true) {
          char type;
          scanf(" %c", &type);
          if (type == '#') break;
          int rows, columns;
          scanf("%d%d", &rows, &columns);
          vvb map(rows);
          for (int i = 0; i < rows; i++)
               map[i].resize(columns);
          if (type == 'B') {
               string S = "";
               while ((int) S.size() < rows * columns) {
                    scanf("%s", buffer);
                    S += buffer;
               }
               for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                         map[i][j] = S[i * columns + j] == '1';
                    }
               }
               string solution = to_special(rows, columns, map, 0, rows - 1, 0, columns - 1);
               printf("%c%4d%4d\n", 'D', rows, columns);
               for (int i = 0; i < (int) solution.size(); i++) {
                    if (i % 50 == 0 && i != 0) printf("\n");
                    printf("%c", solution[i]);
               }
               printf("\n");
          } else {
               int counterrr = 0;
               from_special(rows, columns, map, 0, rows - 1, 0, columns - 1, counterrr);
               printf("%c%4d%4d\n", 'B', rows, columns);
               int counter = 0;
               for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                         if (counter++ % 50 == 0 && i != 0) printf("\n");
                         printf("%d", map[i][j] ? 1 : 0);
                    }
               }
               printf("\n");
          }
     }
}
