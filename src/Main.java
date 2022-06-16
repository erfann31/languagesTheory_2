import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class TL2 {
    static int dfa = 0;
    static int i = 0;
    static ArrayList<Product> products = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("products.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] split = data.split(" ");
            String name = split[0];
            int price = Integer.parseInt(split[1]);
            int inventory = Integer.parseInt(split[2]);
            String code = String.valueOf(Integer.parseInt(split[3], 2));
            Product product = new Product(name, price, inventory, code);
            products.add(product);
        }
        myReader.close();

        File myObj1 = new File("history.txt");
        Scanner myReader1 = new Scanner(myObj1);
        while (myReader1.hasNextLine()) {
            String data = myReader1.nextLine();
            sb.append(data);
        }
        myReader1.close();
        menuA();
        System.out.println(sb);
        if (isAccepted(sb.delete(0, 8).toString().toCharArray()) == 1) System.out.println("ACCEPTED");
        else System.out.println("NOT ACCEPTED");
    }

    static Product codeToProduct(String code) {
        for (Product product : products) if (code.equals(product.code)) return product;
        return null;
    }

    static boolean isProductExist(String code) {
        for (Product product : products) {
            if (code.equals(product.code)) {
                if (product.inventory > 0) {
                    sb.append("1");
                    return true;
                }
            }
        }
        sb.append("0");
        return false;
    }

    static void menuA() throws FileNotFoundException {
        System.out.println("Press 1 to turn on and 0 to turn off");
        int input = sc.nextInt();
        if (input == 1) {
            sb.append("1");
            menuB();
        } else {
            sb.append("0");
            try (PrintWriter out = new PrintWriter("history.txt")) {
                out.print(sb);
            }
        }
    }

    static void menuB() throws FileNotFoundException {
        System.out.println("0-Turn off\n1-Buy\n2-Increase inventory");
        int num = sc.nextInt();
        if (num == 0) {
            sb.append("0");
            try (PrintWriter out = new PrintWriter("history.txt")) {
                out.print(sb);
            }
        } else if (num == 1) {
            sb.append("1");
            menuC();
        } else if (num == 2) {
            System.out.println("Increased successfully\n------------------------------------------------------------------");
            menuB();
        }
    }

    static void menuC() throws FileNotFoundException {
        for (int i = 0; i < products.size(); i++) System.out.println((i) + "-" + products.get(i).name);
        System.out.println("Choose a product with code:\npress q to cancel");
        String code = sc.next();
        if (code.equals("q")) {
            sb.append("q");
            menuB();
        } else {
            StringBuilder binaryCode = new StringBuilder(Integer.toBinaryString(Integer.parseInt(code)));
            int num = 4 - binaryCode.length();
            for (int i = 0; i < num; i++) binaryCode.insert(0, "0");
            sb.append(binaryCode);
            Product product = codeToProduct(code);
            assert product != null;
            System.out.println(product.name + " " + product.price + "T");
            System.out.println("1-Accept\n2-Reject\npress q to cancel");
            String num2 = sc.next();
            switch (num2) {
                case "q" -> {
                    sb.append("q");
                    menuB();
                }
                case "1" -> {
                    sb.append("1");
                    if (isProductExist(product.code)) {
                        sb.append("1");
                        System.out.println("Please withdraw your bank card and enter 1\npress q to cancel");
                        String enter = sc.next();
                        sb.append(enter);
                        if (enter.equals("q")) menuB();
                        else if (enter.equals("1")) {
                            System.out.println("Enter your password\npress q to cancel");
                            String password = sc.next();
                            if (password.equals("q")) {
                                sb.append("q");
                                menuB();
                            } else if (password.equals("1234")) {
                                sb.append("1");
                                product.inventory -= 1;
                                sb.append("1");
                                System.out.println("Buy successful\n------------------------------------------------------------------");
                                sb.append("d");
                                try (PrintWriter out = new PrintWriter("products.txt")) {
                                    for (Product value : products) out.print(value.toString());
                                }
                                menuB();
                            } else {
                                while (true) {
                                    sb.append("0");
                                    System.out.println("Enter your password again\npress q to cancel");
                                    password = sc.next();
                                    if (password.equals("q")) {
                                        sb.append("q");
                                        menuB();
                                        break;
                                    } else if (password.equals("1234")) {
                                        sb.append("1");
                                        product.inventory -= 1;
                                        sb.append("1");
                                        System.out.println("Buy successful\n------------------------------------------------------------------");
                                        sb.append("d");
                                        try (PrintWriter out = new PrintWriter("products.txt")) {
                                            for (Product value : products) out.print(value.toString());
                                        }
                                        menuB();
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("This product does not exist\n------------------------------------------------------------------");
                        sb.append("0");
                        menuC();
                    }
                }
                case "2" -> {
                    sb.append("0");
                    menuC();
                }
            }
        }
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
        else if (c == '0') dfa = 2;
        else dfa = -1;
    }

    static void state6(char c) {
        if (c == '1') dfa = 7;
        else if (c == 'q') dfa = 1;
        else dfa = -1;
    }

    static void state7(char c) {
        if (c == '1') dfa = 8;
        else if (c == '0') dfa = 7;
        else if (c == 'q') dfa = 1;
        else dfa = -1;
    }

    static void state8(char c) {
        if (c == '1') dfa = 9;
        else dfa = -1;
    }

    static void state9(char c) {
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
                case 9:
                    state9(str[j]);
                    break;
                default:
                    return 0;
            }
        }
        if (dfa == 0 && i == 0)
            return 1;
        else
            return 0;
    }
}

class Product {
    String name;
    String code;
    int inventory;
    int price;

    Product(String name, int price, int inventory, String code) {
        this.name = name;
        this.code = code;
        this.inventory = inventory;
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder binaryCode = new StringBuilder(Integer.toBinaryString(Integer.parseInt(code)));
        int num = 4 - binaryCode.length();
        for (int i = 0; i < num; i++) binaryCode.insert(0, "0");
        return name + " " + price + " " + inventory + " " + binaryCode + "\n";
    }

}

