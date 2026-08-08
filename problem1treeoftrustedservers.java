import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class problem1treeoftrustedservers {
    static int N;
    static long K;
    static long[] keys;
    static List<List<Integer>> adj;
    static int trusted_count = 0;

    static void dfs(int u, int parent, long current_xor) {
        
        if (current_xor > K) { 
            trusted_count++;
        }

        for (int v : adj.get(u)) {
            if (v != parent) {
                dfs(v, u, current_xor ^ keys[v]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        String firstToken = sc.next();
        if (firstToken == null) return;
        
        N = Integer.parseInt(firstToken);
        K = sc.nextLong();

        keys = new long[N + 1];
        for (int i = 1; i <= N; ++i) {
            keys[i] = sc.nextLong();
        }

        adj = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; ++i) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        
        for (int v : adj.get(1)) {
            dfs(v, 1, keys[v]);
        }
        

        System.out.println(trusted_count);
    }

    
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}