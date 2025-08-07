import java.util.*;

public class ConstantFoldingOptimizer {
    static class Token {
        enum Type { NUM, VAR, OP, BOOL }
        Type type;
        String val;
        boolean isNewline;
        Token(Type t, String v, boolean nl) { type = t; val = v; isNewline = nl; }
    }

    static List<Token> tokenize(String code) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder curr = new StringBuilder();

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                curr.append(c);
            } else if ("+-*/&|!".indexOf(c) >= 0) {
                if (curr.length() > 0) {
                    addToken(curr, tokens);
                }
                tokens.add(new Token(Token.Type.OP, String.valueOf(c), false));
            } else if (Character.isLetter(c) || c == '_') {
                if (curr.length() > 0 && Character.isDigit(curr.charAt(0))) {
                    tokens.add(new Token(Token.Type.NUM, curr.toString(), false));
                    curr.setLength(0);
                }
                curr.append(c);
            } else {
                if (curr.length() > 0) {
                    addToken(curr, tokens);
                }
                if (c == ';') {
                    tokens.add(new Token(Token.Type.VAR, ";", true));
                } else if (!Character.isWhitespace(c)) {
                    tokens.add(new Token(Token.Type.VAR, String.valueOf(c), false));
                } else {
                    tokens.add(new Token(Token.Type.VAR, " ", false));
                }
            }
        }
        if (curr.length() > 0) {
            addToken(curr, tokens);
        }
        return tokens;
    }

    static void addToken(StringBuilder curr, List<Token> tokens) {
        String val = curr.toString();
        if (val.equals("true") || val.equals("false")) {
            tokens.add(new Token(Token.Type.BOOL, val, false));
        } else if (val.equals("boolean")) {
            tokens.add(new Token(Token.Type.VAR, "boolean", false));
        } else if (Character.isDigit(curr.charAt(0))) {
            tokens.add(new Token(Token.Type.NUM, val, false));
        } else {
            tokens.add(new Token(Token.Type.VAR, val, false));
        }
        curr.setLength(0);
    }

    static String eval(List<Token> tokens) {
        boolean hasVar = tokens.stream().anyMatch(t -> t.type == Token.Type.VAR && !t.val.equals(" "));
        if (hasVar) return tokens.stream().map(t -> t.val).reduce("", String::concat);

        try {
            double result = Double.parseDouble(tokens.get(0).val);
            for (int i = 1; i < tokens.size(); i += 2) {
                String op = tokens.get(i).val;
                double num = Double.parseDouble(tokens.get(i + 1).val);
                switch (op) {
                    case "+": result += num; break;
                    case "-": result -= num; break;
                    case "*": result *= num; break;
                    case "/": result /= num; break;
                }
            }
            return result == (long)result ? String.valueOf((long)result) : String.valueOf(result);
        } catch (Exception e) {
            return tokens.stream().map(t -> t.val).reduce("", String::concat);
        }
    }

    static String optimize(String code) {
        List<Token> tokens = tokenize(code);
        List<Token> expr = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        boolean lastWasSpace = false;

        for (Token t : tokens) {
            if (t.type == Token.Type.NUM || t.type == Token.Type.OP) {
                expr.add(t);
            } else {
                if (!expr.isEmpty()) {
                    String evaluated = eval(expr);
                    result.append(evaluated);
                    expr.clear();
                }
                if (t.val.equals(" ")) {
                    if (!lastWasSpace) {
                        result.append(t.val);
                    }
                    lastWasSpace = true;
                } else {
                    result.append(t.val);
                    lastWasSpace = false;
                }
                if (t.isNewline) {
                    result.append('\n');
                }
            }
        }
        if (!expr.isEmpty()) result.append(eval(expr));

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder code = new StringBuilder();
        System.out.println("Enter code (END to finish):");
        String line;
        while (!(line = sc.nextLine()).equals("END")) code.append(line).append("\n");
        System.out.println("\nOptimized:");
        System.out.println(optimize(code.toString()));
        sc.close();
    }
}