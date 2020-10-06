import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Adnan Hakim
 * @code Steepest Hill Climbing (NQueens with chessboard size 8)
 * @start [0, 0, 0, 0, 0, 0, 0, 0]
 * @goal [1, 3, 5, 7, 2, 0, 6, 4]
 * @output Reached goal state: [1, 7, 5, 0, 2, 4, 6, 3]
 */
public class NQueens {
    private static boolean found = false;

    private static void nQueensHillClimbing(int queen, int[] current) {
        if (found) return;

        if (calculateHeuristic(current) == 0) {
            System.out.println("Reached goal state: " + Arrays.toString(current));
            found = true;
            return;
        }

        if (queen >= current.length) return;

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(NQueens::calculateHeuristic));
        for (int position = 0; position < current.length; position++) {
            current[queen] = position;
            int[] copy = new int[current.length];
            System.arraycopy(current, 0, copy, 0, current.length);
            priorityQueue.offer(copy);
        }

        while (!priorityQueue.isEmpty())
            nQueensHillClimbing(queen + 1, priorityQueue.poll());
    }

    private static int calculateHeuristic(int[] board) {
        int heuristic = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i == j) continue;

                // Same column
                if (board[i] == board[j])
                    heuristic++;

                // Same diagonal
                int offset = j - i;
                if ((board[i] == board[j] - offset) || (board[i] - offset == board[j]))
                    heuristic++;
            }
        }
        return heuristic;
    }

    public static void main(String[] args) {
        int[] start = {0, 0, 0, 0, 0, 0, 0, 0};
        nQueensHillClimbing(0, start);
    }
}
