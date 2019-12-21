import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            boolean palindrome = isPalindrome(str);
            boolean mirror = isMirror(str);
            if (palindrome && mirror) {
                System.out.println(str + " -- is a mirrored palindrome.");
            } else if (palindrome) {
                System.out.println(str + " -- is a regular palindrome.");
            } else if (mirror) {
                System.out.println(str + " -- is a mirrored string.");
            } else {
                System.out.println(str + " -- is not a palindrome.");
            }
            System.out.println();
        }
    }

    public static boolean isPalindrome(String str) {
        for (int i = 0; i < (int)Math.ceil(str.length()/2.0); i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMirror(String str) {
        for (int i = 0; i < (int)Math.ceil(str.length()/2.0); i++) {
            switch(str.charAt(i)) {
                case 'A': case 'H': case 'I': case 'M': case 'O': case 'T': case 'U': case 'V': case 'W': case 'X': case 'Y': case '1': case '8':
                    if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                        return false;
                    }
                    break;
                case 'E':
                    if (str.charAt(str.length() - i - 1) != '3') {
                        return false;
                    }
                    break;
                case 'J':
                    if (str.charAt(str.length() - i - 1) != 'L') {
                        return false;
                    }
                    break;
                case 'L':
                    if (str.charAt(str.length() - i - 1) != 'J') {
                        return false;
                    }
                    break;
                case 'S':
                    if (str.charAt(str.length() - i - 1) != '2') {
                        return false;
                    }
                    break;
                case 'Z':
                    if (str.charAt(str.length() - i - 1) != '5') {
                        return false;
                    }
                    break;
                case '2':
                    if (str.charAt(str.length() - i - 1) != 'S') {
                        return false;
                    }
                    break;
                case '3':
                    if (str.charAt(str.length() - i - 1) != 'E') {
                        return false;
                    }
                    break;
                case '5':
                    if (str.charAt(str.length() - i - 1) != 'Z') {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }
}
