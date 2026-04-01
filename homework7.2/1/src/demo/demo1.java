package demo;


import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;
public class demo1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a parentheses string: ");
        String input = scanner.nextLine();
        scanner.close();
        boolean isBalanced = checkParentheses(input);
        System.out.println("Balanced? " + isBalanced);
    }
    public static boolean checkParentheses(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (!isMatchingPair(top, c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    private static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')') ||
                (left == '[' && right == ']') ||
                (left == '{' && right == '}');
    }
}
