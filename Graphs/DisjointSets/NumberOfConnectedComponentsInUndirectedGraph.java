// PROBLEM DESCRIPTION: 

// You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

// Return the number of connected components in the graph.

// Example 1:


// Input: n = 5, edges = [[0,1],[1,2],[3,4]]
// Output: 2


// Example 2:


// Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
// Output: 1

// INTUITION:

// We can check the number of connected components by counting the number of unique root nodes of the graph. We create the graph, unionize all the vertices according to the edges array,
// and then use a hashset to count the number of unique root nodes (as all the other vertices of the current component will be connected to the root) to count the number of connected components.
// We then return the number of unique root nodes as our answer. 

class Solution {
    int[] parents;
    int[] ranks;
    Set<Integer> set = new HashSet<Integer>();
    
    public int countComponents(int n, int[][] edges) {
        parents = new int[n];
        ranks = new int[n];
        
        for(int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    
        for(int[] edge : edges) {
            union(edge[0], edge[1]);
        }
        for(int i = 0; i < parents.length; i++) {
        }
        
        for(int i = 0; i < n; i++) {
            if(parents[i] == i) {
               set.add(i); 
            }
        }
    
        return set.size();
    }

    public void union(int vertex1, int vertex2) {
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
        }
    }

    public int find(int vertex) {
        while(vertex != parents[vertex]) {
            vertex = parents[vertex];
        }
    
        return vertex;
    }
}
