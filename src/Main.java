import java.util.Scanner;

class GFG {
    static int dfa = 0;
    static int i = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (isAccepted(input.toCharArray()) == 1) System.out.println("ACCEPTED");
        else System.out.println("NOT ACCEPTED");
        System.out.println(dfa);
        System.out.println(i);
    }

    static void start(char c) {
        if (c == '1') dfa = 1;
        else if (c == '0') dfa = 0;
        else dfa = -1;
    }

    static void state1(char c) {
        if (c == '0') dfa = 0;
        else if (c == '1') dfa = 2;
        else dfa = -1;
    }

    static void state2(char c) {
        if (c == '1' || c == '0') dfa = 3;
        else if (c == 'q') dfa = 1;
        else dfa = -1;
    }

    static void state3(char c) {
        if (i < 3) {
            if (c == '1' || c == '0') {
                dfa = 3;
                i++;
            }
        } else {
            if (c == '1') {
                dfa = 4;
                i = 0;
            } else if (c == '0') {
                dfa = 2;
                i = 0;
            } else if (c == 'q') {
                dfa = 1;
                i = 0;
            } else dfa = -1;
        }
    }

    static void state4(char c) {
        if (c == '1') dfa = 5;
        else if (c == '0') dfa = 2;
        else if (c == 'q') dfa = 1;
        else dfa = -1;
    }

    static void state5(char c) {
        if (c == '1') dfa = 6;
        else if (c == 'q') dfa = 1;
        else dfa = -1;
    }

    static void state6(char c) {
        if (c == '1') dfa = 7;
        else if (c == '0') dfa = 6;
        else if (c == 'q') dfa = 1;
        else dfa = -1;
    }

    static void state7(char c) {
        if (c == '1') dfa = 8;
        else dfa = -1;
    }

    static void state8(char c) {
        if (c == 'd') dfa = 1;
        else dfa = -1;
    }

    static int isAccepted(char[] str) {
        int j, len = str.length;
        for (j = 0; j < len; j++) {
            switch (dfa) {
                case 0:
                    start(str[j]);
                    break;
                case 1:
                    state1(str[j]);
                    break;
                case 2:
                    state2(str[j]);
                    break;
                case 3:
                    state3(str[j]);
                    break;
                case 4:
                    state4(str[j]);
                    break;
                case 5:
                    state5(str[j]);
                    break;
                case 6:
                    state6(str[j]);
                    break;
                case 7:
                    state7(str[j]);
                    break;
                case 8:
                    state8(str[j]);
                    break;
                default:
                    return 0;
            }
        }
        if (dfa == 1 && i == 0)
            return 1;
        else
            return 0;
    }
}