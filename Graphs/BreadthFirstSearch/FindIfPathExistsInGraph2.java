// Problem Description (Same as problem from depth first search, just using breadth first search this time)
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

// INTUITION: 
// Use breadth first search this time, instead of depth first search
// In breadth first search, we traverse all the current vertex's neighbors before going to the neighbors' neighbors. In this way, it is like checking the graph in layers, instead of 
// trying to dig far into a path like with depth first search 
// We first check all the current vertex's neighbors to see if they are equal to the target value. If none of them are, we then recurse to each of those neighbors in another for-loop

class Solution {
    private boolean[] seen;
    Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
    int n;
    boolean found = false; 
    int start, end;
    
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if(edges.length == 0) {
            return true;
        }
        
        this.n = n;
        this.start = source;
        this.end = destination;
        seen = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
    
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
    
        bfs(source);
        return found;
    }
    
    private void bfs(int vertex) {
        
        if(seen[vertex]) {
            return;
        }
    
        seen[vertex] = true;
        
        for(int neighbor : graph.get(vertex)) {
            if(neighbor == end) {
                found = true;
                return;
            }
        }
    
        for(int neighbor : graph.get(vertex)) {
            bfs(neighbor);
        }
    }
}

// Below is the iterative solution. Since this is a breadth first search, we use a queue (first in first out) to store the neighbors of the current vertex that we are looking at 
// The first in first out concept instead of first in last out lets us check all the neighbors of each vertex first instead of going down a specific path before checking other neighbors.

class Solution {
    
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        boolean[] seen = new boolean[n];
        
        if(edges.length == 0) {
            return true;
        }
        
        for(int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
    
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
    
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        
        while(!queue.isEmpty()) {
            int curr = queue.remove();
           
            if(curr == destination) {
                return true;
            }
            
            if(seen[curr]) {
                continue;
            }
            seen[curr] = true;
            
            for(int neighbor : graph.get(curr)) {
                queue.add(neighbor);
            }
        }
    
        return false;
    }
}







