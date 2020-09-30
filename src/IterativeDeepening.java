import java.util.*;

/**
 * @author Adnan Hakim
 * @code Iterative Deepening of Graph
 * @start A
 * @goal P
 * @output For height 7: Searching from A to P up to height 7
 * For limit 1 path: [A]
 * For limit 2 path: [A, E, B]
 * For limit 3 path: [A, E, I, F, B, C]
 * For limit 4 path: [A, E, I, M, J, F, B, G]
 * For limit 5 path: [A, E, I, M, N, J, F, K, B, C, D, H, G]
 * For limit 6 path: [A, E, I, M, N, O, J, F, B, C, D, G]
 * For limit 7 path: [A, E, I, M, N, O, P]
 * Node found
 */
public class IterativeDeepening {
    private static List<Character> result = new ArrayList<>();
    private static boolean found = false;

    private static void dls(HashMap<Character, List<Character>> graph, char start, char target, int limit) {
        visit(graph, start, new HashSet<>(), target, limit);
    }

    private static void visit(HashMap<Character, List<Character>> graph, char curr, HashSet<Character> visited, char target, int limit) {
        if (!found && limit > 0) {
            limit--;
            if (curr == target) found = true;
            visited.add(curr);
            result.add(curr);
            for (char neighbour : graph.get(curr))
                if (!visited.contains(neighbour))
                    visit(graph, neighbour, visited, target, limit);
        }
    }

    public static void main(String[] args) {
        HashMap<Character, List<Character>> graph = new HashMap<>() {{
            put('A', Arrays.asList('E', 'B'));
            put('B', Arrays.asList('A', 'C', 'F'));
            put('C', Arrays.asList('B', 'D', 'G'));
            put('D', Arrays.asList('C', 'H'));
            put('E', Arrays.asList('A', 'I', 'F'));
            put('F', Arrays.asList('E', 'B', 'G', 'J'));
            put('G', Arrays.asList('F', 'C', 'H', 'K'));
            put('H', Arrays.asList('D', 'G', 'L'));
            put('I', Arrays.asList('M', 'J', 'E'));
            put('J', Arrays.asList('I', 'F', 'K', 'N'));
            put('K', Arrays.asList('J', 'G', 'L', 'O'));
            put('L', Arrays.asList('K', 'H', 'P'));
            put('M', Arrays.asList('N', 'I'));
            put('N', Arrays.asList('M', 'O', 'J'));
            put('O', Arrays.asList('N', 'P', 'K'));
            put('P', Arrays.asList('O', 'L'));
        }};

        int maxLimit = 7;
        char source = 'A', target = 'P';
        System.out.println("Searching from " + source + " to " + target + " up to height " + maxLimit);
        for (int limit = 1; limit <= maxLimit; limit++) {
            dls(graph, source, target, limit);
            System.out.println("For limit " + limit + " path: " + result.toString());
            result.clear();
            if (found) break;
        }
        System.out.println(found ? "Node found" : "Node not found");
    }
}
