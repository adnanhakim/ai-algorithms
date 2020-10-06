import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch {
    private static boolean found = false;

    private static void aStar(int[][] current, int[][] goal, int gScore) {
        if (found) return;

        for (int[] row : current)
            System.out.println(Arrays.toString(row));
        System.out.println();

        if (calculateHeuristic(current, goal) == 0) {
            System.out.println("G Score: " + gScore);
            System.out.println("Reached goal state");
            found = true;
            return;
        }

        PriorityQueue<int[][]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> calculateHeuristic(a, goal)));
        int[] index = indexOfSpace(current);
        int row = index[0], col = index[1];

        // Move space above
        if (row > 0)
            priorityQueue.offer(copy(current, row, col,row - 1, col));

        // Move space below
        if (row < current.length - 1)
            priorityQueue.offer(copy(current, row, col, row + 1, col));

        // Move space to the left
        if (col > 0)
            priorityQueue.offer(copy(current, row, col, row, col - 1));

        // Move space to the right
        if (col < current[0].length - 1)
            priorityQueue.offer(copy(current, row, col, row, col + 1));

        while (!priorityQueue.isEmpty())
            aStar(priorityQueue.poll(), goal, gScore + 1);
    }

    private static int calculateHeuristic(int[][] current, int[][] goal) {
        int heuristic = 0;
        for (int i = 0; i < current.length; i++)
            for (int j = 0; j < current[0].length; j++)
                if (current[i][j] != goal[i][j])
                    heuristic++;
        return heuristic;
    }

    private static int[] indexOfSpace(int[][] current) {
        for (int i = 0; i < current.length; i++)
            for (int j = 0; j < current[0].length; j++)
                if (current[i][j] == 0)
                    return new int[]{i, j};
        return new int[]{0, 0};
    }

    private static int[][] copy(int[][] current, int currentRow, int currentCol, int swapRow, int swapCol) {
        int[][] copy = new int[current.length][current[0].length];
        for (int i = 0; i < current.length; i++)
            System.arraycopy(current[i], 0, copy[i], 0, current[0].length);
        copy[currentRow][currentCol] = current[swapRow][swapCol];
        copy[swapRow][swapCol] = current[currentRow][currentCol];
        return copy;
    }

    public static void main(String[] args) {
        int[][] start = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        aStar(start, goal, 0);
    }
}
