import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            ArrayList<Integer> scenario = new ArrayList<Integer>();
            boolean flag = false;
            while (true) {
                String line = in.nextLine();
                if (line.equals("0 0 0")) {
                    flag = true;
                    break;
                }
                String strs[] = line.split(" ", 0);
                for (String str : strs) {
                    scenario.add(Integer.valueOf(str));
                }
                if (scenario.get(scenario.size() - 1) == 0) {
                    scenario.remove(scenario.size() - 1);
                    break;
                }
            }
            if (flag) break;

            int startTime = Collections.min(scenario) * 2;
            int time;
            for (time = startTime; time <= 5*60*60; time++) {
                boolean flag2 = true;
                for (int cycle : scenario) {
                    if (!isGreen(cycle, time)) {
                        flag2 = false;
                        break;
                    }
                }
                if (flag2) {
                    System.out.printf("%02d:%02d:%02d%n", time/3600, (time%3600)/60, time%60);
                    break;
                }
            }
            if (time > 5*60*60)
                System.out.println("Signals fail to synchronise in 5 hours");
        }
    }

    public static boolean isGreen(int cycle, int time) {
        return (time / cycle) % 2 == 0 && (time % cycle) < cycle - 5;
    }
}
