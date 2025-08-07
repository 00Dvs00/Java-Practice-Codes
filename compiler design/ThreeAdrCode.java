import java.util.Scanner;

public class ThreeAdrCode {

    public static void translate(String tac) {
        String[] parts = tac.split("=");
        String result = parts[0].trim();
        String expression = parts[1].replace(';', ' ').trim();

        char operator = ' ';
        if (expression.contains("+")) {
            operator = '+';
        } else if (expression.contains("-")) {
            operator = '-';
        } else if (expression.contains("*")) {
            operator = '*';
        } else if (expression.contains("/")) {
            operator = '/';
        } else {
            System.out.println("Invalid operator.");
            return;
        }

        String[] expressionParts = expression.split("\\" + operator);
        String op1 = expressionParts[0].trim();
        String op2 = expressionParts[1].trim();

        boolean isOp1Float = op1.contains(".");
        boolean isOp2Float = op2.contains(".");
        boolean isOp1Label = isLabel(op1);
        boolean isOp2Label = isLabel(op2);

        System.out.println("Assembly Code: ");

        if (isOp2Float || isNumeric(op2)) {
            System.out.println("MOV #" + op2 + ", R1");
        } else {
            System.out.println("MOV " + op2 + ", R1");
        }

        switch (operator) {
            case '+':
                if (isOp1Float || isOp2Float || isNumeric(op1)) {
                    System.out.println("ADDF " + (isOp1Float || isNumeric(op1) ? "#" : "") + op1 + ", R1");
                } else {
                    System.out.println("ADD " + op1 + ", R1");
                }
                break;
            case '-':
                if (isOp1Float || isOp2Float || isNumeric(op1)) {
                    System.out.println("SUBF " + (isOp1Float || isNumeric(op1) ? "#" : "") + op1 + ", R1");
                } else {
                    System.out.println("SUB " + op1 + ", R1");
                }
                break;
            case '*':
                if (isOp1Float || isOp2Float || isNumeric(op1)) {
                    System.out.println("MULF " + (isOp1Float || isNumeric(op1) ? "#" : "") + op1 + ", R1");
                } else {
                    System.out.println("MUL " + op1 + ", R1");
                }
                break;
            case '/':
                if (isOp1Float || isOp2Float || isNumeric(op1)) {
                    System.out.println("DIVF " + (isOp1Float || isNumeric(op1) ? "#" : "") + op1 + ", R1");
                } else {
                    System.out.println("DIV " + op1 + ", R1");
                }
                break;
            default:
                break;
        }

        if (isOp2Float || isNumeric(op2)) {
            System.out.println("MOVF R1, " + result);
        } else {
            System.out.println("MOV R1, " + result);
        }
    }

    public static boolean isLabel(String operand) {
        return operand.matches("[a-zA-Z][a-zA-Z0-9]*");
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the three Address Code: ");
        String tac = input.nextLine();
        translate(tac);  
        input.close();
    }
}
