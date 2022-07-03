// There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

// You want to determine if there is a valid path that exists from vertex source to vertex destination.

// Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

// Example 1:


// Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
// Output: true
// Explanation: There are two paths from vertex 0 to vertex 2:
// - 0 → 1 → 2
// - 0 → 2


// Example 2:


// Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
// Output: false
// Explanation: There is no path from vertex 0 to vertex 5.


// NOTE: This is not my solution, the explanation is my understanding of the solution. 

// INTUITION:
// We can use a hashmap that matches each vertex to a list containing all of its neighbors. 
// We can then use a recursive helper function to go through each key in the map (each vertex) starting from the start vertex (given by the problem)
// We can then iterate over each neighbor of the current vertex that we are looking at, and call the recursive function again on each of those neighbors.
// Before looping through the neighbors though, we have to keep track of what vertices we have already visited. This is to take care of graphs with cycles and make sure 
// that the path we are currently recursing through doesn't have an already visited vertex yet (meaning that there was already another path with that vertex). 
// If at any time we find the destination vertex, we can just set a flag boolean variable equal to true and the next time we recurse through the helper function, we can break out of the function by checking the state of that flag variable. 

class Solution {
    private Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
    
    private boolean[] visited;
    private int start, end;
    private boolean found = false;
    
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if(source == destination) return true;
        
        this.start = source;
        this.end = destination;
        this.visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<Integer>());
        }
    
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
    
        dfs(start);
        return found;
    }

    private void dfs(int start) {
        if(visited[start] || found) {
            return;
        }
        visited[start] = true;
        for(int neighbor : graph.get(start)) {
            if(neighbor == end) {
                found = true;
                break;
            }
        
            if(!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}
