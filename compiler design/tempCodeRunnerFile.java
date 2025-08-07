import java.util.*;

public class CommonSubexpressionOptimizer {

    // Map to keep track of sub-expressions and their assigned variable names
    private static Map<String, String> expressionToVariableMap = new HashMap<>();

    // Method to optimize a list of assignment statements by removing redundant assignments
    public static List<String> optimizeStatements(List<String> statements) {
        List<String> optimizedStatements = new ArrayList<>();

        for (String statement : statements) {
            String[] parts = statement.split("=", 2);
            String variable = parts[0].trim();
            String expression = parts[1].trim().replace(";", ""); // Remove semicolon for easier matching

            // Check if the expression already exists in the map
            if (expressionToVariableMap.containsKey(expression)) {
                // If a common sub-expression exists, reuse the variable without creating a new assignment
                String commonVar = expressionToVariableMap.get(expression);

                // Replace the redundant variable (like 'c') with the common variable (like 'b') in future statements
                for (int i = 0; i < optimizedStatements.size(); i++) {
                    optimizedStatements.set(i, optimizedStatements.get(i).replace(" " + variable + " ", " " + commonVar + " "));
                }

                // No need to add this variable as it’s redundant
                variable = commonVar;
            } else {
                // If no common sub-expression, add the current expression to the map
                expressionToVariableMap.put(expression, variable);
                optimizedStatements.add(variable + " = " + expression + ";");
            }
        }

        return optimizedStatements;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> statements = new ArrayList<>();

        System.out.println("Enter each statement on a new line. Type 'done' to finish input:");

        // Read user input until "done" is typed
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            statements.add(input);
        }

        // Optimize statements for common sub-expressions
        List<String> optimizedStatements = optimizeStatements(statements);

        // Print optimized statements
        System.out.println("\nOptimized Statements:");
        for (String stmt : optimizedStatements) {
            System.out.println(stmt);
        }
    }
}
