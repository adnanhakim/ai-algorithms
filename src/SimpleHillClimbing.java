import java.util.*;

/**
 * @author Adnan Hakim
 * @code Simple Hill Climbing (Search Tree)
 * @start S
 * @goal G
 * @output [S, A, C, D, B, F, G]
 */
public class SimpleHillClimbing {
    private static List<Character> result = new ArrayList<>();
    private static boolean found = false;

    private static void simpleHillClimbing(HashMap<Character, List<Character>> graph, HashMap<Character, Integer> heuristic, char current, char goal) {
        if (!found) {
            result.add(current);
            if (current == goal) {
                found = true;
                return;
            }

            for (char neighbour : graph.get(current))
                if (heuristic.get(neighbour) < heuristic.get(current))
                    simpleHillClimbing(graph, heuristic, neighbour, goal);
        }
    }

    public static void main(String[] args) {
        HashMap<Character, List<Character>> graph = new HashMap<>() {{
            put('S', Arrays.asList('A', 'B'));
            put('A', Arrays.asList('C', 'D'));
            put('B', Arrays.asList('E', 'F'));
            put('C', new ArrayList<>());
            put('D', new ArrayList<>());
            put('E', Collections.singletonList('H'));
            put('F', Arrays.asList('I', 'G'));
            put('H', new ArrayList<>());
            put('I', new ArrayList<>());
            put('G', new ArrayList<>());
        }};

        HashMap<Character, Integer> heuristic = new HashMap<>() {{
            put('A', 12);
            put('B', 4);
            put('C', 7);
            put('D', 3);
            put('E', 8);
            put('F', 2);
            put('H', 4);
            put('I', 9);
            put('S', 13);
            put('G', 0);
        }};

        simpleHillClimbing(graph, heuristic, 'S', 'G');
        System.out.println("Simple Hill Climbing: " + result.toString());
    }
}
