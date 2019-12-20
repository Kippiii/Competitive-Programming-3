import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String[] hand = in.nextLine().split(" ", 0);
            int[] suitCounter = new int[4];
            boolean[] stopped = new boolean[4];
            for (String card : hand) {
                suitCounter[suitToInt(card.substring(1))] += 1;
            }

            int score = 0;
            for (String card : hand) {
                if (card.substring(0, 1).equals("A")) {
                    score += 4;
                    stopped[suitToInt(card.substring(1))] = true;
                } else if (card.substring(0, 1).equals("K")) {
                    score += 3;
                    if (suitCounter[suitToInt(card.substring(1))] == 1)
                        score--;
                    else
                        stopped[suitToInt(card.substring(1))] = true;
                } else if (card.substring(0, 1).equals("Q")) {
                    score += 2;
                    if (suitCounter[suitToInt(card.substring(1))] <= 2)
                        score--;
                    else
                        stopped[suitToInt(card.substring(1))] = true;
                } else if (card.substring(0, 1).equals("J")) {
                    score += 1;
                    if (suitCounter[suitToInt(card.substring(1))] <= 3)
                        score--;
                }
            }

            if (score >= 16 && stopped[0] && stopped[1] && stopped[2] && stopped[3]) {
                System.out.println("BID NO-TRUMP");
                continue;
            }

            for (int suit : suitCounter) {
                if (suit == 2)
                    score++;
                if (suit <= 1)
                    score += 2;
            }

            int highestSuit = 0;
            for (int i = 1; i < 4; i++) {
                if (suitCounter[highestSuit] < suitCounter[i]) {
                    highestSuit = i;
                }
            }

            if (score < 14)
                System.out.println("PASS");
            else
                System.out.println("BID " + intToSuit(highestSuit));
        }
    }

    public static int suitToInt(String suit) {
        if (suit.equals("S")) return 0;
        if (suit.equals("H")) return 1;
        if (suit.equals("D")) return 2;
        return 3;
    }

    public static String intToSuit(int suit) {
        if (suit == 0) return "S";
        if (suit == 1) return "H";
        if (suit == 2) return "D";
        return "C";
    }
}
