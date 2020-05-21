#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> ii;
typedef vector<ii> vii;

int lengths[10005];

int main() {
     while (true) {
          int k;
          scanf("%d", &k);
          if (k == 0) break;
          for (int i = 0; i < k; i++) {
               scanf("%d", lengths + i);
          }

          sort(lengths, lengths + k);
          vii freq_vec;
          int cur_freq = 1;
          for (int i = 1; i < k; i++) {
               if (lengths[i] == lengths[i - 1]) {
                    cur_freq++;
               } else {
                    freq_vec.push_back(make_pair(cur_freq, lengths[i - 1]));
                    cur_freq = 1;
               }
          }
          freq_vec.push_back(make_pair(cur_freq, lengths[k - 1]));

          sort(freq_vec.begin(), freq_vec.end());
          int num_bags = freq_vec[freq_vec.size() - 1].first;
          int max_size = ceil((double) k / num_bags);
          int num_max_bags = (k % num_bags == 0) ? num_bags : k % num_bags;
          vector<vector<int>> bags;
          for (int i = 0; i < num_bags; i++) {
               vector<int> bag;
               bags.push_back(bag);
          }

          int cur_bag = 0;
          for (int i = freq_vec.size() - 1; i >= 0; i--) {
               while ((cur_bag < num_max_bags && (int) bags[cur_bag].size() == max_size) || (cur_bag >= num_max_bags && (int) bags[cur_bag].size() == max_size - 1)) {
                    cur_bag++;
               }
               for (int j = cur_bag; j < cur_bag + freq_vec[i].first; j++) {
                    bags[j].push_back(freq_vec[i].second);
               }
          }

          printf("%d\n", num_bags);
          for (auto bag : bags) {
               for (int num : bag) {
                    printf("%d ", num);
               }
               printf("\n");
          }
     }
}
