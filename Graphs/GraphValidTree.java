// PROBLEM DESCRIPTION: 

// You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

// Return true if the edges of the given graph make up a valid tree, and false otherwise.

 

// Example 1:


// Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
// Output: true


// Example 2:


// Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
// Output: false


// NOTE: I did not come up with this solution on my own.

// INTUITION:

// The main thing to realize here is the definition of a tree. We can define a tree in the most helpful way for our problem by stating that:
// 1. It must have exactly n - 1 edges
     // Any more edges would guarantee a cycle (multiple connections to a vertex)
      // Any less edges would make it impossible to have a fully connected graph, meaning that we cannot have a valid tree. 
      // We can check this by looking at the length of the edges array. The length of this array gives us the number of edges, so if the length is not equal to n, then we cannot have a valid tree. 

// 2. It must be fully connected
      // We can directly check for this in our union function
      // If we are given two vertices that are supposedly connected (according the the edges array), but already have the same root node, that means that both of them already have a connection to another vertex 
      // This means that there would be a cycle, and we can just immediately return false by checking if the root nodes of the two given vertices are equal or not. 

class Solution {
    int[] parents;
    int[] ranks;
    
    public boolean validTree(int n, int[][] edges) {
        parents = new int[n];
        ranks = new int[n];
        
        if(edges.length != n - 1) {
            return false;
        }
        
        for(int i = 0; i < n; i++) {
            ranks[i] = 1;
            parents[i] = i;
            
        }
    
        for(int[] edge : edges) {
            if(!union(edge[0], edge[1])) {
                return false;
            }
        }
    
        return true;
    }

    private boolean union(int vertex1, int vertex2) {
        
        int root1 = find(vertex1);
        int root2 = find(vertex2);
        
        if(root1 != root2) {
            if(ranks[root1] > ranks[root2]) {
                parents[root2] = root1;
            }
        
            else if(ranks[root1] < ranks[root2]) {
                parents[root1] = root2;
            }
            
            else {
                parents[root2] = root1;
                ranks[root1]++;
            }
            return true;
        }
        
        return false;
    }

    private int find(int vertex) {
        while(vertex != parents[vertex]) {
            vertex = parents[vertex];
        }
    
        return vertex;
    }
}
