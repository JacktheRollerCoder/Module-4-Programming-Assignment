import java.io.*;
import java.util.Stack;

public class SymbolMatch {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java SymbolMatch <source-code-file>");
            return;
        }

        String filename = args[0];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            Stack<Character> stack = new Stack<>();
            boolean isBalanced = true;
            int character;
            
            while ((character = reader.read()) != -1) {
                char ch = (char) character;
                
                if (ch == '(' || ch == '{' || ch == '[') {
                    stack.push(ch);
                } else if (ch == ')' || ch == '}' || ch == ']') {
                    if (stack.isEmpty()) {
                        isBalanced = false;
                        break;
                    }
                    char top = stack.pop();
                    if (!matches(top, ch)) {
                        isBalanced = false;
                        break;
                    }
                }
            }
            
            // If stack is not empty after processing all characters, it's unbalanced
            if (!stack.isEmpty()) {
                isBalanced = false;
            }

            reader.close();
            System.out.println(isBalanced ? "Balanced" : "Not Balanced");

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Helper method to check if the opening and closing symbols match
    private static boolean matches(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
               (opening == '{' && closing == '}') ||
               (opening == '[' && closing == ']');
    }
}

class Test {
    public static void main(String[] args) {
        System.err.println("Hello world!");
    }
}

class Oops {
    public static void main(String[] args) {
        System.err.println("Oo{p s [! } ]");
    }
}