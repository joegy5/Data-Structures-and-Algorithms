// Problem Description:

// You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.

// The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.

// You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.

// Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.

 

// Example 1:


// Input: bombs = [[2,1,3],[6,1,4]]
// Output: 2

// Explanation:
// The above figure shows the positions and ranges of the 2 bombs.
// If we detonate the left bomb, the right bomb will not be affected.
// But if we detonate the right bomb, both bombs will be detonated.
// So the maximum bombs that can be detonated is max(1, 2) = 2.


// Example 2:


// Input: bombs = [[1,1,5],[10,10,5]]
// Output: 1

// Explanation:
// Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.


// Example 3:

// Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
// Output: 5

// Explanation:
// The best bomb to detonate is bomb 0 because:
// - Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
// - Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
// - Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
// Thus all 5 bombs are detonated.



// NOTE: I did not come up with this solution on my own, the intuition is my understanding of the solution

// INTUITION:

// We can represent our situation using a graph.
// Each edge in the graph connects two nodes (bombs) such that at least one of those bombs can cause the other one to detonate (this may not be true in the vice versa situation, meaning that this is a directed graph)
// We could. construct the graph, or we could directly go into depth-first search.

// Our depth first search algorithm goes like this:
// Loop through each bomb (array) inside our bonbs 2D array
   // Perform DFS on each bomb that we are looking at 
// For each bomb, we then check all the bombs in the bomb array 
// we check if any of those bombs are within detonation radius of the current bomb that we are performing DFS on
// If this condition is true, and we haven't already visited the bomb that we are comparing to the current bomb that we are performing DFS on,
// then we can recursively call the DFS function on that bomb.
// First thing we do each time we enter the DFS function is increment the current number of bombs that we can detonate if we just detonated the current bomb that we are starting the DFS function on 
// After the DFS recursive call stack is done, we can have another variable that will keep taking the max of itself and that number representing the current number of bombs that can be detonated by detonated the current bomb that we are looking at 

class Solution {
    int[][] bombs;
    List<List<Integer>> graph = new ArrayList<>();
    int numBombsDetonated;
    
    public int maximumDetonation(int[][] bombs) {
        this.bombs = bombs;
        
        int ans = 0;
        
        for(int i = 0; i < bombs.length; i++) {
            numBombsDetonated = 0;
         // Each time, we set the number of bombs detonated equal to 0 to start our DFS search from each bomb.
         // We also start with a boolean array called "visited" that starts out as containing all false values to show that none of the bombs have been visited when starting the DFS search for each bomb   
            dfs(i, new boolean[bombs.length]);
            ans = Math.max(ans, numBombsDetonated);
        }
        return ans;
    }
    
    public void dfs(int bombIndex, boolean[] visited) {
        numBombsDetonated++;
        visited[bombIndex] = true;
        
        for(int i = 0; i < bombs.length; i++) {
            
            if(inRange(bombs[bombIndex], bombs[i]) && !visited[i]) {
             // Mark each node that we visit as visted in our boolean array to make sure that we skip over already visited bombs to avoid entering infinite recursion.    
             visited[i] = true;
                dfs(i, visited);
            }
        }
        
    }

    public boolean inRange(int[] a, int[] b) {
     // Check if the bomb is in range by using geometry (Pythagorean Theorem to check if the distance between the centers of the bombs that we are comparing is less than or equal to the bomb radius    
     // We use longs instead of ints to prevent overflow 
     long dx = a[0] - b[0], dy = a[1] - b[1], dRadius = a[2];
        return dx * dx + dy * dy <= dRadius * dRadius;
    }
}











