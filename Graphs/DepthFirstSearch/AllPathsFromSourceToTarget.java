// PROBLEM DESCRIPTION:

// Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

// The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

 

// Example 1:

// Input: graph = [[1,2],[3],[3],[]]
// Output: [[0,1,3],[0,2,3]]
// Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.


// Example 2:

// Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
// Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]


// INTUITION: 
// We already have the graph constructed for us (the graph 2d array), so we do not need to construct our own graph using a list inside a list or a hashmap. 
// We start the dfs algorithm with the first node: 0.
// We also have a boolean array called "seen" to check if we have already seen a current node on our path to the end node.
// If we have already seen it, we can just terminate the current recursive function state at that moment right there. Otherwise, we mark the node that we are currently looking at as seen 
// But first, we check if the current node we are looking at is the end node. If it is, that means that we have found a valid path and can add the current list of nodes in a path to the answer list 
// If not, we then proceed to look at all the neighbors of the current node that we are looking at.
// For each neighbor, we create a new list that represents the path we are going through and add all the contents of the previous list to the new list, add the neighbor after, then do dfs on that neighbor and the new list 

class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    boolean[] seen;
    int n;
    int[][] graph;
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        n = graph.length;
        seen = new boolean[n];
        this.graph = graph;        
 
        dfs(0, new ArrayList<>());
        return ans;
    }

    private void dfs(int curr, List<Integer> currList) {
        if(curr == n - 1) {
            currList.add(curr);
            ans.add(currList);
            Arrays.fill(seen, false);
            return;
        }

        
        if(seen[curr]) {
            return;
        }
        seen[curr] = true;
        
        
        
        for(int neighbor : graph[curr]) {
            List<Integer> newCurrList = new ArrayList<>();
            for(int val : currList) {
                newCurrList.add(val);
            }
            newCurrList.add(curr);
            dfs(neighbor, newCurrList);
            
        }
        
    }
}
