import java.util.*;

/**
 * @author Adnan Hakim
 * @code Depth First Traversal of Graph
 * @start A
 * @goal P
 * @output [A, E, I, M, N, O, P]
 */
public class DFS {
    private static List<Character> result = new ArrayList<>();
    private static boolean found = false;

    private static void dfs(HashMap<Character, List<Character>> graph, char start, char target) {
        visit(graph, start, new HashSet<>(), target);
    }

    private static void visit(HashMap<Character, List<Character>> graph, char curr, HashSet<Character> visited, char target) {
        if (!found) {
            if (curr == target) found = true;
            visited.add(curr);
            result.add(curr);
            for (char neighbour : graph.get(curr))
                if (!visited.contains(neighbour))
                    visit(graph, neighbour, visited, target);
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

        dfs(graph, 'A', 'P');
        System.out.println(result.toString());
    }
}
