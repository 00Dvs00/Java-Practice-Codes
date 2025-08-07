import java.util.*;

public class lexicalAnalyser {
    private static final Set<String> keywords = Set.of("for", "if", "else", "while", "return", "int", "float");

    public static boolean isKeyword(String token) {
        return keywords.contains(token);
    }
    
    public static boolean isValidIdentifier(String token) {
        if (!Character.isLetter(token.charAt(0)) && token.charAt(0) != '_') return false;
        for (char ch : token.toCharArray()) {
            if (!Character.isLetterOrDigit(ch) && ch != '_') return false;
        }
        return true;
    }

    public static void analyzeToken(String token) {
        if (token.isEmpty()) return;
        
        if (isKeyword(token)) {
            System.out.println("Keyword: " + token);
        } else if (token.matches("[a-zA-Z\\d+]+=[a-zA-Z\\d+]+")){
            if(token.contains("=")){
                if(token.matches("=")) {System.out.println("Operator: "+token);}
                String[] temp = token.split("(?==)|(?<==)");
                for (String x : temp) {
                    analyzeToken(x);
                }
            }
        } else if (Character.isLetter(token.charAt(0)) || token.charAt(0) == '_') {
            System.out.println(isValidIdentifier(token) ? "Identifier: " + token : "Invalid Identifier: " + token);
        } else if (Character.isDigit(token.charAt(0))) {
            System.out.println(token.matches("\\d+") ? "Number: " + token : "Invalid Identifier: " + token);        
        } else if (token.matches("\\+\\+|--|[+\\-*/<>!=&|]=?")) {
            System.out.println("Operator: " + token);
        } else if (token.matches("[(),;{}\\[\\]]")) {
            System.out.println("Delimiter: " + token);
        } else if (token.startsWith("\"") && token.endsWith("\"") && token.length() > 1) {
            System.out.println("String Literal: " + token);
        } else {
            System.out.println("Unknown: " + token);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input string: ");
        String input = scanner.nextLine();
        String[] tokens = input.split("\\s+|"+"(?=[(),;{}\\[\\]])|"+"(?<=[(),;{}\\[\\]])|"
        +"(?=<=|>=|==|!=|\\+\\+|--)|"+"(?<=<=|>=|==|!=|\\+\\+|--)");
        for (String token : tokens) {
            analyzeToken(token);    
        }

        scanner.close();
    }
}