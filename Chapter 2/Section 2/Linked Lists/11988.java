import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text;

        while ((text=br.readLine()) != null) {
            LinkedList<String> list = new LinkedList<String>();

            text += "[";
            String tmp = "";
            boolean append = false;
            for (char c : text.toCharArray()) {
                if (c == '[' || c == ']') {
                    if (append) {
                        list.addFirst(tmp);
                    } else {
                        list.addLast(tmp);
                    }
                    tmp = "";

                    if (c == '[') {
                        append = true;
                    } else {
                        append = false;
                    }
                } else {
                    tmp += c;
                }
            }

            for (Object s : list)
                System.out.print((String) s);
            System.out.println();
        }
    }
}
