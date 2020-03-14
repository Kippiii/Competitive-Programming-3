#include <bits/stdc++.h>

using namespace std;

int p, q, r, s, t, u;

double eval(double x) {
     return p * exp(-1 * x) + q * sin(x) + r * cos(x) + s * tan(x) + t * x * x + u;
}

int bisection(bool b) {
     double closest_solution = eval(0.5);
     int closest_x = 5000;
     int high = (closest_solution > 0) ^ b ? closest_x - 1 : 10000;
     int low = (closest_solution < 0) ^ b ? closest_x + 1 : 0;
     while (high >= low) {
          int mid = (high + low) / 2;
          double sol = eval(mid / 10000.0);

          if (abs(sol) < abs(closest_solution)) {
               closest_solution = sol;
               closest_x = mid;
          }

          if ((sol < 0) ^ b)
               low = mid + 1;
          else
               high = mid - 1;
     }
     return closest_x;
}

int main() {
     while (!feof(stdin)) {
          if (scanf("%d%d%d%d%d%d", &p, &q, &r, &s, &t, &u) == EOF) break;
          double eval1 = eval(1.0);
          double eval0 = eval(0.0);
          if ((eval1 > 0 && eval0 > 0) || (eval1 < 0 && eval0 < 0)) {
               printf("No solution\n");
               continue;
          }

          if (eval1 == 0) {
               printf("%.4f\n", 1.0);
               continue;
          }
          if (eval0 == 0) {
               printf("%.4f\n", 0.0);
               continue;
          }

          printf("%.4f\n", bisection(eval0 > 0) / 10000.0);
     }
}
