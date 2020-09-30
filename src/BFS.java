import java.util.*;

/**
 * @author Adnan Hakim
 * @code Breadth First Traversal of Graph
 * @start A
 * @goal P
 * @output [A, B, E, C, F, I, D, G, J, M, H, K, N, L, O, P]
 */
public class BFS {
    private static List<Character> result = new ArrayList<>();

    private static void bfs(HashMap<Character, List<Character>> graph, char start, char target) {
        HashSet<Character> visited = new HashSet<>();
        Queue<Character> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            if (!visited.contains(curr)) {
                visited.add(curr);
                result.add(curr);
                if (curr == target) break;
                for (char neighbour : graph.get(curr))
                    queue.offer(neighbour);
            }
        }
    }

    public static void main(String[] args) {
        HashMap<Character, List<Character>> graph = new HashMap<>() {{
            put('A', Arrays.asList('B', 'E'));
            put('B', Arrays.asList('A', 'C', 'F'));
            put('C', Arrays.asList('B', 'D', 'G'));
            put('D', Arrays.asList('C', 'H'));
            put('E', Arrays.asList('A', 'F', 'I'));
            put('F', Arrays.asList('E', 'B', 'G', 'J'));
            put('G', Arrays.asList('F', 'C', 'H', 'K'));
            put('H', Arrays.asList('D', 'G', 'L'));
            put('I', Arrays.asList('E', 'J', 'M'));
            put('J', Arrays.asList('I', 'F', 'K', 'N'));
            put('K', Arrays.asList('J', 'G', 'L', 'O'));
            put('L', Arrays.asList('K', 'H', 'P'));
            put('M', Arrays.asList('I', 'N'));
            put('N', Arrays.asList('M', 'J', 'O'));
            put('O', Arrays.asList('N', 'K', 'P'));
            put('P', Arrays.asList('O', 'L'));
        }};

        bfs(graph, 'A', 'P');
        System.out.println(result.toString());
    }
}