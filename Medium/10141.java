import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        for (int counter = 1; ; counter++) {
            int n = in.nextInt();
            int p = in.nextInt();
            in.nextLine();
            if (n == 0 && p == 0) break; else if (counter != 1) System.out.println();
            for (int i = 0; i < n; i++)
                in.nextLine();

            Proposal props[] = new Proposal[p];
            for (int i = 0; i < p ; i++) {
                String name = in.nextLine();
                double price = in.nextDouble();
                int reqs = in.nextInt();
                in.nextLine();
                props[i] = new Proposal(name, price, reqs, i);
                for (int j = 0; j < reqs; j++)
                    in.nextLine();
            }

            Arrays.sort(props);
            System.out.println("RFP #" + counter);
            System.out.println(props[0].name);
            //System.out.println();
        }
    }
}

class Proposal implements Comparable<Proposal> {
    String name;
    double price;
    int reqs;
    int n;

    public Proposal(String name, double price, int reqs, int n) {
        this.name = name;
        this.price = price;
        this.reqs = reqs;
        this.n = n;
    }

    @Override
    public int compareTo(Proposal p) {
        if (this.reqs == p.reqs) {
            if (this.price == p.price) {
                return (this.n > p.n) ? 1 : -1;
            }
            return (this.price > p.price) ? 1 : -1;
        }
        return (this.reqs < p.reqs) ? 1 : -1;
    }
}
