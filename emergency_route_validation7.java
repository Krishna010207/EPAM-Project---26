import java.util.*;

public class emergency_route_validation7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int D = sc.nextInt();

        // Adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        // Read edges
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Distance array (-1 means unvisited)
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();

        // BFS from city 1
        queue.add(1);
        dist[1] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : adj.get(node)) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[node] + 1;
                    queue.add(neighbor);
                }
            }
        }

        // Count cities with distance ≤ D
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] != -1 && dist[i] <= D) {
                count++;
            }
        }

        System.out.println(count);
    }
}