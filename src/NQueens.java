import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Adnan Hakim
 * @code Steepest Hill Climbing (NQueens with chessboard size 8)
 * @start [0, 0, 0, 0, 0, 0, 0, 0]
 * @goal [1, 3, 5, 7, 2, 0, 6, 4]
 * @output [0, 0, 0, 0, 0, 0, 0, 0]
 * [1, 0, 0, 0, 0, 0, 0, 0]
 * [1, 3, 0, 0, 0, 0, 0, 0]
 * [1, 3, 5, 0, 0, 0, 0, 0]
 * [1, 3, 5, 7, 0, 0, 0, 0]
 * [1, 3, 5, 7, 2, 0, 0, 0]
 * [1, 3, 5, 7, 2, 0, 0, 0]
 * [1, 3, 5, 7, 2, 0, 6, 0]
 * [1, 3, 5, 7, 2, 0, 6, 4]
 * Reached goal state: [1, 3, 5, 7, 2, 0, 6, 4]
 */
public class NQueens{
    private static boolean found = false;

    private static void nQueensHillClimbing(int queen, int[] current, int[] goal) {
        if (found) return;
        System.out.println(Arrays.toString(current));

        if (calculateHeuristic(current, goal) == 0) {
            System.out.println("Reached goal state: " + Arrays.toString(goal));
            found = true;
            return;
        }

        if (queen >= current.length) return;

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> calculateHeuristic(a, goal)));
        for (int position = 0; position < current.length; position++) {
            current[queen] = position;
            int[] copy = new int[current.length];
            System.arraycopy(current, 0, copy, 0, current.length);
            priorityQueue.offer(copy);
        }

        while (!priorityQueue.isEmpty())
            nQueensHillClimbing(queen + 1, priorityQueue.poll(), goal);
    }

    private static int calculateHeuristic(int[] current, int[] goal) {
        int heuristic = 0;
        for (int i = 0; i < current.length; i++)
            if (current[i] != goal[i])
                heuristic++;
        return heuristic;
    }

    public static void main(String[] args) {
        int[] start = {0, 0, 0, 0, 0, 0, 0, 0};
        int[] goal = {1, 3, 5, 7, 2, 0, 6, 4};
        nQueensHillClimbing(0, start, goal);
    }
}
