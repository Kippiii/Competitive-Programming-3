import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int months = in.nextInt();
            if (months < 0) break;
            double down = in.nextDouble();
            double loan = in.nextDouble();
            int n = in.nextInt();
            double deps[] = new double[months + 1];
            for (int i = 0, currentMonth = 0; i < n; i++) {
                int month = in.nextInt();
                double dep = in.nextDouble();
                deps[month] = dep;
                for (int begin = currentMonth; currentMonth < month; currentMonth++)
                    deps[currentMonth] = deps[begin];

                if (i == n - 1) {
                    for (; currentMonth <= months; currentMonth++)
                        deps[currentMonth] = dep;
                }
            }

            int today = 0;
            double value = (loan + down) * (1 - deps[0]);
            double payment = loan / months;
            while (value <= loan) {
                //System.out.println(deps[i]);
                today++;
                value -= deps[today] * value;
                loan -= payment;
            }
            System.out.println(today + " month" + (today == 1 ? "" : "s"));
        }
    }
}
