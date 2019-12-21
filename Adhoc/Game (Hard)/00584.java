import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            String rolls[] = in.nextLine().split(" ", 0);
            if (rolls[0].equals("Game")) break;

            int score = 0;
            int frame = 0;
            for (int i = 0; frame < 20; i++) {
                if (rolls[i].equals("X")) {
                    score += 10 + rollInt(rolls, i + 1) + rollInt(rolls, i + 2);
                    frame += 2;
                }else if (rolls[i].equals("/")) {
                    score += (10 - ((int)rolls[i - 1].toCharArray()[0] - (int)'0')) + rollInt(rolls, i + 1);
                    frame += 1;
                } else {
                    score += (int)rolls[i].toCharArray()[0] - (int)'0';
                    frame += 1;
                }
            }
            System.out.println(score);
        }
    }

    public static int rollInt(String rolls[], int index) {
        String roll = rolls[index];
        if (roll.equals("X")) {
            return 10;
        } else if (roll.equals("/")) {
            return 10 - ((int)rolls[index - 1].toCharArray()[0] - (int)'0');
        }
        return (int)rolls[index].toCharArray()[0] - (int)'0';
    }
}
