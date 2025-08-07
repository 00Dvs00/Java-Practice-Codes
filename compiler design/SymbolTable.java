import java.util.*;

public class SymbolTable {  
    static int getSize(String datatype) {
        return switch (datatype) {
            case "int" -> 2;
            case "float" -> 4;
            case "double" -> 8;
            case "char" -> 1;
            case "short" -> 2;
            default -> 0;
        };
    }

    static boolean isValidIdentifier(String token) {
        return token.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a line of code:");
        String line = scanner.nextLine();

        String[] declarations = line.split(";");
        ArrayList<String[]> identifiers = new ArrayList<>();
        int memoryLocation = 0;

        for (String declaration : declarations) {
            String[] tokens = declaration.trim().split("[ ,]+");
            
            if (tokens.length == 0) continue;

            String currentDatatype = tokens[0];
            if (!"int float double char short".contains(currentDatatype)) continue;

            for (int i = 1; i < tokens.length; i++) {
                String token = tokens[i].trim();
                if (isValidIdentifier(token)) {
                    int size = getSize(currentDatatype);
                    identifiers.add(new String[]{
                        String.valueOf(memoryLocation),
                        token,
                        currentDatatype,
                        String.valueOf(size)
                    });
                    memoryLocation += size;
                }
            }
        }

        System.out.println("Location | Identifier | Datatype | Size");
        System.out.println("---------------------------------------");
        for (String[] id : identifiers) {
            System.out.printf("%-10s | %-10s | %-10s | %-4s\n", id[0], id[1], id[2], id[3]);
        }
        scanner.close();
    }
}