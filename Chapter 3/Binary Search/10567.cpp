#include <bits/stdc++.h>

using namespace std;

typedef vector<int> vi;
typedef vector<vi> vii;

char buffer[1000005];

int my_hash(char c) {
     if (islower(c))
          return c - 'a';
     return c - 'A' + 26;
}

int binary_ceil(vi vec, int target) {
     int high = (int) vec.size() - 1;
     int low = 0;
     while (high >= low) {
          int mid = (high + low) / 2;
          if (vec[mid] > target)
               high = mid - 1;
          else if (vec[mid] < target)
               low = mid + 1;
          else {
               low = mid;
               break;
          }
     }
     return (low >= (int) vec.size()) ? -1 : low;
}

int main() {
     string S;
     scanf("%s", buffer);
     S = buffer;
     vii indices(52);
     for (int i = 0; i < 52; i++) {
          indices[i].resize(0);
     }
     for (int i = 0; i < (int) S.size(); i++) {
          indices[my_hash(S[i])].push_back(i);
     }

     int Q;
     scanf("%d", &Q);
     while (Q--) {
          string SS;
          scanf("%s", buffer);
          SS = buffer;
          if ((int) indices[my_hash(SS[0])].size() == 0) {
               printf("Not matched\n");
               continue;
          }
          int initial_index = indices[my_hash(SS[0])][0];
          int index = initial_index + 1;
          for (int i = 1; i < (int) SS.size(); i++) {
               char c = SS[i];
               int tmp_index = binary_ceil(indices[my_hash(c)], index);
               if (tmp_index == -1) {
                    printf("Not matched\n");
                    index = -1;
                    break;
               }
               index = indices[my_hash(c)][tmp_index] + 1;
          }
          if (index != -1) {
               printf("Matched %d %d\n", initial_index, index - 1);
          }
     }
}
