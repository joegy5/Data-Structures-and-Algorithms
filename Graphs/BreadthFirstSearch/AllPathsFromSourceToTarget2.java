// PROBLEM DESCRIPTION (Same as problem from depth first search, but we are using breadth first search this time)

// Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

// The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

 

// Example 1:


// Input: graph = [[1,2],[3],[3],[]]
// Output: [[0,1,3],[0,2,3]]
// Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

  
// Example 2:


// Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
// Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]


// NOTE: I did not come up with this solution entirely on my own.

// INTUITION (ITERATIVE SOLUTION): 
// Since we are using breadth first search this time, we will use a queue instead of a stack. 
// The main logic is to put each current path that we create using the neighbors of the current vertex we are looking at into the queue if the last value of 
// the path that we previously retrieved from the queue is equal to n - 1. Otherwise, we add the current path that we have created (including the neighbor this time) 
// back into the queue.
// Since we are looking for all the possible paths, and the graph is guaranteed to be acyclic (meaning that it does not have any cycles where two or more nodes have 2 connections (one to the other, and one from the other to the one)),
// we actually do not need a boolean array to check which nodes that we have already visited. 

class Solution {
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        Queue<List<Integer>> queue = new LinkedList<>();
        boolean[] seen = new boolean[n];
        List<List<Integer>> ans = new ArrayList<>();
        
        List<Integer> start = new ArrayList<>();
        start.add(0);
        queue.add(start);
        
        
        while(!queue.isEmpty()) {
            List<Integer> path = queue.poll(); 
            int lastVal = path.get(path.size() - 1);
            
            if(lastVal == n - 1) {
                ans.add(path);
                continue;
            }
            
            
            for(int neighbor : graph[lastVal]) {
                
                List<Integer> curr = new ArrayList<>(path);
                
                curr.add(neighbor);
                seen[neighbor] = true;
                
                queue.add(curr);
            }
        }
    
        return ans;
    } 
}
