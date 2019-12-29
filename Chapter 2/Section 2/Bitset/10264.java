import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            Corner corners[] = new Corner[1 << n];
            for (int i = 0; i < 1 << n; i++) {
                corners[i] = new Corner(i, in.nextInt());
            }

            for (Corner corner1 : corners) {
                int potency = 0;
                for (int i = 0; i < n; i++) {
                    BitSet tmp = (BitSet) corner1.coord.clone();
                    tmp.flip(i);
                    int index = tmp.toLongArray().length == 0 ? 0 : (int) tmp.toLongArray()[0];
                    potency += corners[index].weight;
                }
                corner1.potency = potency;
            }

            int highest = -1;
            for (Corner corner1 : corners) {
                for (int i = 0; i < n; i++) {
                    BitSet tmp = (BitSet) corner1.coord.clone();
                    tmp.flip(i);
                    int index = tmp.toLongArray().length == 0 ? 0 : (int) tmp.toLongArray()[0];
                    if (corner1.potency + corners[index].potency > highest) {
                        highest = corner1.potency + corners[index].potency;
                    }
                }
            }

            System.out.println(highest);
        }
    }
}

class Corner {
    BitSet coord;
    int weight;
    int potency;
    public Corner(int coord, int weight) {
        this.coord = BitSet.valueOf(new long[] {coord});
        this.weight = weight;
        this.potency = -1;
    }
}
