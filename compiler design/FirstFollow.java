import java.util.*;

public class FirstFollow {
    private Map<String, Set<String>> first = new HashMap<>();
    private Map<String, Set<String>> follow = new HashMap<>();
    private Map<String, List<List<String>>> productions = new HashMap<>();
    private Set<String> nonTerminals = new HashSet<>();
    private Set<String> terminals = new HashSet<>();

    public void addProduction(String nonTerminal, String production) {
        nonTerminals.add(nonTerminal);
        productions.putIfAbsent(nonTerminal, new ArrayList<>());
        List<String> prodList = Arrays.asList(production.split(" "));
        productions.get(nonTerminal).add(prodList);
        for (String symbol : prodList) {
            if (!nonTerminals.contains(symbol) && !symbol.equals("ε")) {
                terminals.add(symbol);
            }
        }
        first.putIfAbsent(nonTerminal, new HashSet<>());
        follow.putIfAbsent(nonTerminal, new HashSet<>());
    }

    public void calculateFirst() {
        boolean changed;
        do {
            changed = false;
            for (String nonTerminal : nonTerminals) {
                for (List<String> production : productions.get(nonTerminal)) {
                    for (String symbol : production) {
                        if (terminals.contains(symbol)) {
                            // If terminal, add to First
                            if (first.get(nonTerminal).add(symbol)) {
                                changed = true;
                            }
                            break; // Stop after adding terminal
                        } else if (nonTerminals.contains(symbol)) {
                            // If non-terminal, add its First set
                            Set<String> firstSet = first.get(symbol);
                            if (firstSet != null) { // Ensure firstSet is initialized
                                for (String firstSymbol : firstSet) {
                                    if (!firstSymbol.equals("ε") && first.get(nonTerminal).add(firstSymbol)) {
                                        changed = true;
                                    }
                                }
                                // If ε is in First(symbol), continue to next symbol
                                if (firstSet.contains("ε")) {
                                    continue;
                                }
                            }
                        }
                        break; // Stop after processing the first symbol
                    }
                    // If the whole production can derive ε
                    if (production.stream().allMatch(sym -> first.get(sym) != null && first.get(sym).contains("ε"))) {
                        if (first.get(nonTerminal).add("ε")) {
                            changed = true;
                        }
                    }
                }
            }
        } while (changed);
    }

    public void calculateFollow(String startSymbol) {
        follow.get(startSymbol).add("$"); // Start symbol follow set contains $
        boolean changed;
        do {
            changed = false;
            for (String nonTerminal : nonTerminals) {
                for (List<String> production : productions.get(nonTerminal)) {
                    for (int i = 0; i < production.size(); i++) {
                        String symbol = production.get(i);
                        if (nonTerminals.contains(symbol)) {
                            // Check for the next symbol
                            if (i + 1 < production.size()) {
                                String nextSymbol = production.get(i + 1);
                                // If next symbol is a terminal, add it to Follow(symbol)
                                if (terminals.contains(nextSymbol)) {
                                    if (follow.get(symbol).add(nextSymbol)) {
                                        changed = true;
                                    }
                                } else if (nonTerminals.contains(nextSymbol)) {
                                    for (String firstSym : first.get(nextSymbol)) {
                                        if (!firstSym.equals("ε") && follow.get(symbol).add(firstSym)) {
                                            changed = true;
                                        }
                                    }
                                    // If ε is in First(nextSymbol)
                                    if (first.get(nextSymbol).contains("ε")) {
                                        for (String followSym : follow.get(nonTerminal)) {
                                            if (follow.get(symbol).add(followSym)) {
                                                changed = true;
                                            }
                                        }
                                    }
                                }
                            } else {
                                // If at the end of production, add Follow(nonTerminal)
                                for (String followSym : follow.get(nonTerminal)) {
                                    if (follow.get(symbol).add(followSym)) {
                                        changed = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } while (changed);
    }

    public void displayFirst() {
        System.out.println("First sets:");
        for (Map.Entry<String, Set<String>> entry : first.entrySet()) {
            System.out.println("First(" + entry.getKey() + ") = " + entry.getValue());
        }
    }

    public void displayFollow() {
        System.out.println("Follow sets:");
        for (Map.Entry<String, Set<String>> entry : follow.entrySet()) {
            System.out.println("Follow(" + entry.getKey() + ") = " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        FirstFollow ff = new FirstFollow();

        // Adding the provided grammar productions
        ff.addProduction("E", "T E'");
        ff.addProduction("E'", "+ T E' ε");
        ff.addProduction("T", "F T'");
        ff.addProduction("T'", "* F T' ε");
        ff.addProduction("F", "id | ( E )");

        ff.calculateFirst();
        ff.calculateFollow("E");

        ff.displayFirst();
        ff.displayFollow();
    }
}
