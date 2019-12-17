import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int k = in.nextInt();
            if (k==0) break;
            int n = in.nextInt();
            int m = in.nextInt();
            for (int i = 0; i < k; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                if (x > n && y > m)
                    System.out.println("NE");
                else if (x > n && y < m)
                    System.out.println("SE");
                else if (x < n && y > m)
                    System.out.println("NO");
                else if (x < n && y < m)
                    System.out.println("SO");
                else
                    System.out.println("divisa");
            }
        }
    }

}
