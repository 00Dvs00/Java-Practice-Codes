import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExpressionSeparator {
    public static void main(String[] args) {
        Scanner userinput = new Scanner(System.in);
        String input = userinput.nextLine();
        String expression = input.split("=")[1];
        
        ArrayList<String> expressions = separateExpressions(expression);
        
        System.out.println("Enter the Input String : " + input);
        System.out.println("The Expressions available in the given statements are");
        for (int i = 0; i < expressions.size(); i++) {
            System.out.println("Expression " + (i + 1) + ": " + expressions.get(i));
        }

        findAndPrintCommonExpressions(expressions);
    }

    private static ArrayList<String> separateExpressions(String expression) {
        ArrayList<String> expressions = new ArrayList<>();
        
        for (int i = 0; i < expression.length() - 2; i++) {
            char first = expression.charAt(i);
            char op = expression.charAt(i + 1);
            char second = expression.charAt(i + 2);
            
            if (Character.isLetterOrDigit(first) && (op == '+' || op == '-' || op == '*') && Character.isLetterOrDigit(second)) {
                expressions.add("" + first + op + second);
            }
        }
        return expressions;
    }

    private static void findAndPrintCommonExpressions(ArrayList<String> expressions) {
        HashMap<String, ArrayList<Integer>> expressionMap = new HashMap<>();
        boolean hasCommonExpressions = false;

        for (int i = 0; i < expressions.size(); i++) {
            String expr = expressions.get(i);
            expressionMap.putIfAbsent(expr, new ArrayList<>());
            expressionMap.get(expr).add(i + 1);
        }

        System.out.println("The Common Expression Available in given statement are");
        for (Map.Entry<String, ArrayList<Integer>> entry : expressionMap.entrySet()) {
            ArrayList<Integer> occurrences = entry.getValue();
            if (occurrences.size() > 1) {
                hasCommonExpressions = true;
                for (int j = 0; j < occurrences.size() - 1; j++) {
                    System.out.println("Expression" + occurrences.get(j) + " & Expression" + occurrences.get(j + 1) + " --> " + entry.getKey());
                }
            }
        }

        if (!hasCommonExpressions) {
            System.out.println("No common expressions found.");
        }
    }
}
