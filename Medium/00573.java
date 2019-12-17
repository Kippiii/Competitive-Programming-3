import java.util.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int h = in.nextInt();
            if (h == 0) break;
            int u = in.nextInt();
            int d = in.nextInt();
            int f = in.nextInt();

            boolean flag = false;
            if (u > h) {
                System.out.println("success on day 1");
                flag = true;
            }

            double currentHeight = u - d;
            double increase = u;
            double decrease = u * (f/100.0);
            int day;
            for (day = 1; !flag; day++) {
                //System.out.println("day: " + day + ", currentHeight: " + currentHeight);
                if (currentHeight < 0) {
                    System.out.println("failure on day " + day);
                    break;
                }
                increase = Math.max(increase - decrease, 0);
                //System.out.println(increase);
                currentHeight += increase;
                if (currentHeight > h) {
                    System.out.println("success on day " + (day + 1));
                    break;
                }
                currentHeight -= d;
            }
        }
    }
}
