import java.util.*;

/**
 * @author Adnan Hakim
 * @code Depth Limited Search of Graph
 * @start A
 * @goal P
 * @output For limit 6: Searching from A to P up to height 6
 * Node not found
 * Path: [A, E, I, M, N, O, J, F, B, C, D, G]
 * @output For limit 7: Searching from A to P up to height 7
 * Node found
 * Path: [A, E, I, M, N, O, P]
 */
public class DLS {
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

        int limit = 7;
        char source = 'A', target = 'P';
        dls(graph, source, target, limit);
        System.out.println("Searching from " + source + " to " + target + " up to height " + limit);
        System.out.println(result.contains(target) ? "Node found" : "Node not found");
        System.out.println("Path: " + result.toString());
    }
}
