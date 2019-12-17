import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int[] emp = new int[3];
            emp[0] = in.nextInt();
            emp[1] = in.nextInt();
            emp[2] = in.nextInt();
            Arrays.sort(emp);
            System.out.println("Case " + (i + 1) + ": " + emp[1]);
        }
    }

}
