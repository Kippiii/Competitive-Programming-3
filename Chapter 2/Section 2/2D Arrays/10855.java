import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int N = in.nextInt();
            int n = in.nextInt();
            in.nextLine();
            if (N == 0 && n == 0) break;
            char big[][] = new char[N][N];
            char small[][] = new char[n][n];
            for (int i = 0; i < N; i++) {
                big[i] = in.nextLine().toCharArray();
            }
            for (int i = 0; i < n; i++) {
                small[i] = in.nextLine().toCharArray();
            }

            System.out.print(countInstances(big, small) + " ");

            char small90[][] = new char[n][n];
            char small180[][] = new char[n][n];
            char small270[][] = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    small270[n - 1 - j][i] = small[i][j];
                    small180[n - 1 - i][n - 1 - j] = small[i][j];
                    small90[j][n - 1 - i] = small[i][j];
                }
            }

            System.out.print(countInstances(big, small90) + " ");
            System.out.print(countInstances(big, small180) + " ");
            System.out.println(countInstances(big, small270));

        }
    }

    public static int countInstances(char big[][], char small[][]) {
        int count = 0;
        for (int i = 0; i < big.length - small.length + 1; i++) {
            for (int j = 0; j < big.length - small.length + 1; j++) {
                boolean flag = true;
                for (int k = 0; k < small.length; k++) {
                    for (int l = 0; l < small.length; l++) {
                        if (small[k][l] != big[i + k][j + l]) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) break;
                }
                if (flag) count++;
            }
        }
        return count;
    }
}
