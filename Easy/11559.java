import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int b = in.nextInt();
            int h = in.nextInt();
            int w = in.nextInt();
            int prices[] = new int[h];
            int capac[][] = new int[h][w];
            for (int i = 0; i < h; i++) {
                prices[i] = in.nextInt();
                for (int j = 0; j < w; j++) {
                    capac[i][j] = in.nextInt();
                }
            }

            ArrayList<Integer> costs = new ArrayList<Integer>();
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (capac[i][j] >= n) {
                        costs.add(prices[i] * n);
                        break;
                    }
                }
            }

            Collections.sort(costs);
            if (costs.size() > 0 && costs.get(0) <= b) {
                System.out.println(costs.get(0));
            } else {
                System.out.println("stay home");
            }
        }
    }
}
