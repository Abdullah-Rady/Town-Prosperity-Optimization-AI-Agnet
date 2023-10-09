import java.util.*;

public class GraphTraversal {

    // Function to perform Breadth-First Search (BFS)
    public static void bfs(List<List<Integer>> adjList, int start) {
        int numVertices = adjList.size();
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int neighbor : adjList.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    // Function to perform Depth-First Search (DFS)
    public static void dfs(List<List<Integer>>adjList, int start) {
        int numVertices = adjList.size();
        boolean[] visited=new boolean[numVertices];
        dfsRecursive(adjList, start, visited);
    }

    private static void dfsRecursive(List<List<Integer>> adjList, int vertex, boolean[] visited) {
        visited[vertex]= true;
        System.out.print(vertex+" ");

        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                dfsRecursive(adjList, neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {

        int mananana=2;
        int numVertices = 6;
        List<List<Integer>> adjList = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }

        // Example graph edges (0-based indexing)
        adjList.get(0).add(1);
        adjList.get(0).add(2);
        adjList.get(1).add(3);
        adjList.get(1).add(4);
        adjList.get(2).add(4);
        adjList.get(3).add(5);
        adjList.get(4).add(5);

        System.out.println("Breadth-First Search (BFS) starting from vertex 0:");
        bfs(adjList, 0);

        System.out.println("\nDepth-First Search (DFS) starting from vertex 0:");
        dfs(adjList, 0);
    }
}
