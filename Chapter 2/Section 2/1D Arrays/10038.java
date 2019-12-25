import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            int n = in.nextInt();
            int sequence[] = new int[n];
            for (int i = 0; i < n; i++) {
                sequence[i] = in.nextInt();
            }
            in.nextLine();

            boolean flag[] = new boolean[n - 1];
            boolean isJolly = true;
            for (int i = 1; i < n; i++) {
                int diff = Math.abs(sequence[i] - sequence[i-1]);
                if (diff > n - 1 || diff == 0 || flag[diff - 1]) {
                    System.out.println("Not jolly");
                    isJolly = false;
                    break;
                }
                flag[diff - 1] = true;
            }
            if (isJolly)
                System.out.println("Jolly");
        }
    }
}
