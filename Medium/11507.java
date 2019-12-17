import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int l = in.nextInt();
            in.nextLine();
            if (l == 0) break;

            String[] bends = in.nextLine().split(" ", 0);

            String pointing = "+x";
            String facing = "-y";

            HashMap<String, String[]> turns = new HashMap<String, String[]>();
            String[] xArr = {"+y", "+z", "-y", "-z"};
            turns.put("x", xArr);
            String[] yArr = {"+x", "-z", "-x", "+z"};
            turns.put("y", yArr);
            String[] zArr = {"+y", "-x", "-y", "+x"};
            turns.put("z", zArr);

            for (int j = l - 2; j >= 0; j--) {
                String bend = bends[j];
                if (bend.equals("No")) continue;

                if (bend.equals("+y")) { //up
                    String temp = pointing;
                    pointing = flipSign(facing.substring(0, 1)) + facing.substring(1, 2);
                    facing = temp;
                } else if (bend.equals("-y")) { //down
                    String temp = facing;
                    facing = flipSign(pointing.substring(0, 1)) + pointing.substring(1, 2);
                    pointing = temp;
                } else if (bend.equals("+z") || bend.equals("-z")) { //right || left
                    String[] turnArr = turns.get(facing.substring(1, 2));
                    int index = -1;
                    for (int i = 0; i < 4; i++) {
                        if (turnArr[i].equals(pointing)) {
                            index = i;
                            break;
                        }
                    }

                    int delta = facing.substring(0, 1).equals("+") ? 1 : -1;
                    delta *= bend.substring(0, 1).equals("+") ? 1 : -1;
                    index = (index + delta + 4) % 4;

                    pointing = turnArr[index];
                }
            }
            System.out.println(pointing);
        }
    }
    public static String flipSign(String sign) {
        return sign.equals("+") ? "-" : "+";
    }
    public static String getAxis(String a1, String a2) {
        if (!a1.equals("x") && !a2.equals("x"))
            return "x";
        else if (!a1.equals("y") && !a2.equals("y"))
            return "y";
        return "z";
    }
}
