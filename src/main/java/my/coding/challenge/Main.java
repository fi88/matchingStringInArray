package my.coding.challenge;

import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("  Type a list of strings, separated by space, then hit ENTER:");
        String input = scanner.nextLine();
        String[] strings = input.split(" ");
        System.out.println("  Now, give a new string, the program will find any match for this new string from the given list:");
        String newStr = scanner.nextLine();
        Finder finder = new Finder(strings);
        System.out.println("  All string(s) which match \'" + newStr + "\' are:");
        finder.find(newStr).stream().forEach(m -> System.out.print(m + " "));
    }
}
