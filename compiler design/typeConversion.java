import java.util.HashMap;
import java.util.Scanner;

public class typeConversion {

    private static final String[] PRIMITIVE_TYPES = {"int", "string", "float", "double", "char", "boolean", "long", "short", "byte"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter type declarations and assignment:");
        String input = scanner.nextLine();

        HashMap<String, String> variableTypes = new HashMap<>();

        String[] statements = input.split(";");

        for (String statement : statements) {
            statement = statement.trim();

            if (isDeclaration(statement)) {
                processDeclaration(statement, variableTypes);
            } else if (statement.contains("=")) {
                processAssignment(statement, variableTypes);
            }
        }

        scanner.close();
    }

    private static boolean isDeclaration(String statement) {
        for (String type : PRIMITIVE_TYPES) {
            if (statement.startsWith(type)) {
                return true;
            }
        }
        return false;
    }

    private static void processDeclaration(String declaration, HashMap<String, String> variableTypes) {
        String[] parts = declaration.split("\\s+");
        String type = parts[0];
        String[] variables = parts[1].split(",");

        for (String variable : variables) {
            variable = variable.trim();
            variableTypes.put(variable, type);
        }
    }

    private static void processAssignment(String assignment, HashMap<String, String> variableTypes) {
        String[] parts = assignment.split("=");
        String lhs = parts[0].trim();
        String rhs = parts[1].trim();

        if (variableTypes.containsKey(lhs)) {
            String lhsType = variableTypes.get(lhs);
            String[] rhsVariables = rhs.split("[+\\-*/]");
            boolean matched = true;

            for (String rhsVar : rhsVariables) {
                rhsVar = rhsVar.trim();
                if (variableTypes.containsKey(rhsVar)) {
                    String rhsType = variableTypes.get(rhsVar);
                    if (!lhsType.equals(rhsType)) {
                        System.out.println(lhsType + " " + lhs + " & " + rhsType + " " + rhsVar + " --> type mismatched");
                        matched = false;
                    } else {
                        System.out.println(lhsType + " " + lhs + " & " + rhsType + " " + rhsVar + " --> matched");
                    }
                } else {
                    System.out.println("Error: One or more variables are not declared in the expression: " + rhs);
                    matched = false;
                }
            }

            if (matched) {
                System.out.println(lhsType + " " + lhs + " & " + lhsType + " " + rhs + " --> matched");
            }
        } else {
            System.out.println("Error: Variable " + lhs + " is not declared.");
        }
    }
}
