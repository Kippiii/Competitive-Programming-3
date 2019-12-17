import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            ArrayList<Integer> speeds = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                speeds.add(in.nextInt());
            }
            int ans = Collections.max(speeds);
            System.out.println("Case " + (i + 1) + ": " + ans);
        }
    }
}
