//This code implements the TSP using branch and bound approach. 
// The code uses Adjancency Matrix and the user can alter the distances with user rather than enterng them manually
import java.util.Arrays;
import java.util.PriorityQueue;

public class TSPBranchAndBound {
    private final int[][] graph;
    private final int n;
    private int[] bestPath;
    private int bestCost;

    public TSPBranchAndBound(int[][] graph) {
        this.graph = graph;
        this.n = graph.length;
        this.bestPath = new int[n + 1];
        this.bestCost = Integer.MAX_VALUE;
    }

    // Function to calculate the lower bound for the node
    private int calculateLowerBound(int[] path, int level) {
        int cost = 0;

        // Cost of the current path
        for (int i = 0; i < level - 1; i++) {
            cost += graph[path[i]][path[i + 1]];
        }

        // Add the minimum edge cost for each node
        for (int i = level; i < n; i++) {
            int minEdge = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (graph[i][j] < minEdge && i != j) {
                    minEdge = graph[i][j];
                }
            }
            cost += minEdge;
        }

        return cost;
    }

    // Branch and Bound method to solve TSP
    public void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.bound - b.bound);
        Node root = new Node(new int[n + 1], 0, 0, 0);
        root.path[0] = 0;
        root.bound = calculateLowerBound(root.path, 1);
        pq.add(root);

        while (!pq.isEmpty()) {
            Node min = pq.poll();
            if (min.bound < bestCost) {
                for (int i = 1; i < n; i++) {
                    if (!min.contains(i)) {
                        Node child = min.clone();
                        child.path[min.level + 1] = i;
                        child.level++;
                        if (child.level == n - 1) {
                            child.path[n] = 0;
                            child.cost += graph[child.path[child.level - 1]][i] + graph[i][0];
                            if (child.cost < bestCost) {
                                bestCost = child.cost;
                                bestPath = Arrays.copyOf(child.path, n + 1);
                            }
                        } else {
                            child.cost += graph[child.path[child.level - 1]][i];
                            child.bound = child.cost + calculateLowerBound(child.path, child.level + 1);
                            if (child.bound < bestCost) {
                                pq.add(child);
                            }
                        }
                    }
                }
            }
        }
    }

    // Get the best cost
    public int getBestCost() {
        return bestCost;
    }

    // Get the best path
    public int[] getBestPath() {
        return bestPath;
    }

    // Node class for Branch and Bound
    static class Node implements Cloneable {
        int[] path;
        int cost;
        int bound;
        int level;

        Node(int[] path, int cost, int bound, int level) {
            this.path = Arrays.copyOf(path, path.length);
            this.cost = cost;
            this.bound = bound;
            this.level = level;
        }

        boolean contains(int node) {
            for (int i = 0; i <= level; i++) {
                if (path[i] == node) return true;
            }
            return false;
        }

        @Override
        protected Node clone() {
            return new Node(this.path, this.cost, this.bound, this.level);
        }
    }

    public static void main(String[] args) {
        // This is the adjacency matrix. You can alter the distances if you like.
        int[][] graph = {
            {0, 29, 20, 21, 16},
            {29, 0, 15, 17, 28},
            {20, 15, 0, 18, 23},
            {21, 17, 18, 0, 12},
            {16, 28, 23, 12, 0}
        };

        TSPBranchAndBound tsp = new TSPBranchAndBound(graph);
        tsp.solve();
        System.out.println("Minimum cost: " + tsp.getBestCost());
        System.out.println("Path: " + Arrays.toString(tsp.getBestPath()));
    }
}
