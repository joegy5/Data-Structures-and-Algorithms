// PROBLEM DESCRIPTION: 

// There are n people in a social group labeled from 0 to n - 1. You are given an array logs where logs[i] = [timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.
// Friendship is symmetric. That means if a is friends with b, then b is friends with a. Also, person a is acquainted with a person b if a is friends with b, or a is a friend of someone acquainted with b.
// Return the earliest time for which every person became acquainted with every other person. If there is no such earliest time, return -1.

//Example 1:

// Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], n = 6
// Output: 20190301
// Explanation: 
// The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship groups [0,1], [2], [3], [4], [5].
// The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship groups [0,1], [2], [3,4], [5].
// The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship groups [0,1], [2,3,4], [5].
// The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship groups [0,1,5], [2,3,4].
// The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friends anything happens.
// The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.


// Example 2:

// Input: logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
// Output: 3


// INTUITION:
// We first sort the log entries by their timestamps, so that we can perform each union in order. 
// When everybody becomes friends, it means that the graph only has one root.
// We can check for this in the union function by looking at the roots of the vertices we are trying to unionize. We keep track of the number of roots left using the numIsolated variable.
// If the roots of the two vertices we are trying to unite at the moment via the union function are the same, that means that they are already connected to a component, so we don't
// decrement the numIsolated variable. Otherwise, we do.
// Once numIsolated becomes 1, there is only one root in the graph, meaning that all the vertices are now connected and everyone is friends.

class Solution {
    int[] parents;
    int[] ranks;
    int[] times;
    int numIsolated;
    
    // We can use a hashmap to store what index each timestamp is at in the logs matrix array, so that once we sort our timestamps via the timestamps array, we can have quick access to where each timestamp is in the actual logs matrix array 
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    
    public int earliestAcq(int[][] logs, int n) {
        parents = new int[n];
        ranks = new int[n];
        times = new int[logs.length];
        numIsolated = n;
        
        for(int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    
        for(int i = 0; i < logs.length; i++) {
            times[i] = logs[i][0];
            map.put(logs[i][0], i);
        }
    
        Arrays.sort(times);
        
        for(int i = 0; i < times.length; i++) {
            union(logs[map.get(times[i])][1], logs[map.get(times[i])][2]);
            
            if(numIsolated == 1) return times[i];
        }
        
        
        return -1;
    }
    
    public void union(int vertex1, int vertex2) {
        
        
        int root1 = find(vertex1);
        int root2 = find(vertex2);
            
        if(root1 != root2) {
            numIsolated--; 
            
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
