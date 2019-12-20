import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        for (int i = 0; i < count; i++) {
            String piece = in.next();
            int m = in.nextInt();
            int n = in.nextInt();

            if (piece.equals("r") || piece.equals("Q")) {
                System.out.println(Math.min(m, n));
            } else if (piece.equals("k")) {
                System.out.println((int)Math.ceil(m*n/2.0));
            } else if (piece.equals("K")) {
                System.out.println((int)Math.ceil(m/2.0)*(int)Math.ceil(n/2.0));
            }
        }
    }
}
