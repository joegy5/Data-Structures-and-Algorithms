// PROBLEM DESCRIPTION: 

// There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

// A province is a group of directly or indirectly connected cities and no other cities outside of the group.

// You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

// Return the total number of provinces.

// Example 1:


// Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
// Output: 2

  
// Example 2:


// Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
// Output: 3

// INTUITION: 
// Implement the graph data structure using an optimized quick union approach. Update the parents array accordingly by unionizing the cities that are connected, as given by the problem
// Then use a hashmap to keep track of the different roots that are stored in the parents array, so that we can count the number of unique roots such that we can find the total number of provinces 
// as each root has all of the vertices under it that belong to one province and therefore represents a province. We store the number of provinces in a variable, and return that variable when done. 

class Solution {
    private int[] parents;
    private int[] ranks;
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    
    public int findCircleNum(int[][] isConnected) {
        int size = isConnected.length;
        parents = new int[size];
        ranks = new int[size];
        
        for(int i = 0; i < size; i++) {
            ranks[i] = 1;
            parents[i] = i;
        }
        
    
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(isConnected[i][j] == 1 && i != j) {
                    union(i, j);
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < size; i++) {
            if(parents[i] == i) {
                if(!map.containsKey(parents[i])) {
                    ans++;
                }
                map.put(parents[i], map.getOrDefault(parents[i], 0) + 1);
            }
        }
        
        return ans;
    }

    private int find(int vertex) {
        while(parents[vertex] != vertex) {
            vertex = parents[vertex];
        }
    
        return vertex;
    }
    
    private void union(int vertex1, int vertex2) {
        int root1 = find(vertex1);
        int root2 = find(vertex2);
        
        if(root1 != root2) {
            if(ranks[root1] > ranks[root2]) {
                parents[root2] = root1; 
            }
            else if(ranks[root2] > ranks[root1]) {
                parents[root1] = root2;
            }
            else {
                parents[root2] = root1;
                ranks[root1]++;
            }
        }
        
        
    }


    
}
