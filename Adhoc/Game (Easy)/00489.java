import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int round = in.nextInt();
            in.nextLine();
            if (round == -1) break;
            String solution = in.nextLine();
            String guesses = in.nextLine();

            boolean solutionLetters[] = new boolean[26];
            boolean guessed[] = new boolean[26];

            for (char c : solution.toCharArray()) {
                solutionLetters[(int) c - (int) 'a'] = true;
            }

            int strikes = 0;
            for (char c : guesses.toCharArray()) {
                if (strikes >= 7) break;
                int i = (int) c - (int) 'a';
                if (!solutionLetters[i] && !guessed[i]) {
                    strikes++;
                }
                guessed[i] = true;
            }

            System.out.println("Round " + round);

            boolean won = true;
            for (int i = 0; i < 26; i++) {
                if (solutionLetters[i] && !guessed[i]) {
                    won = false;
                    break;
                }
            }

            if (won) {
                System.out.println("You win.");
            } else if (strikes < 7) {
                System.out.println("You chickened out.");
            } else {
                System.out.println("You lose.");
            }
        }
    }
}
