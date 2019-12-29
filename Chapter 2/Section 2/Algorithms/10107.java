import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        while (in.hasNextInt()) {
            arr.add(in.nextInt());
            Collections.sort(arr);
            if (arr.size() % 2 == 1) {
                System.out.println(arr.get(arr.size() / 2));
            } else {
                System.out.println((arr.get(arr.size() / 2) + arr.get(arr.size() / 2 - 1)) / 2);
            }
        }
    }
}
