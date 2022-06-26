// PROBLEM DESCRIPTION: 
// Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

// Note: You can only move either down or right at any point in time.

// Example 1:

// Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
// Output: 7
// Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

// Example 2:

// Input: grid = [[1,2,3],[4,5,6]]
// Output: 12

// INTUITION:
// At each index in the grid, we have two choices: Either move down, or move right.
// We are trying to find the path to the bottom right corner that gives us the minimum sum, so we want to look at both cases and take the minimum sum that each case gives us (the case where we move right and the case where we move down)
// This is our recurrence relation, and we can build another array that will store our results to various subproblems so that we don't have to calculate the solutions to the same subproblems again.
// We also need to look at some other cases:
// If our row index is equal to 0 or is the last row in the grid, then we have no choice but to move right and take the sum of the current value and the sum located to the left of the current location in the memoization 2D array 
// If our column index is equal to 0 or is the last column in the grid, then we have no choice but to move down and take hte sume of the current value and the sum located above the current value in the memoization array.

// TIME COMPLEXITY: O(MN), where M is the length of the grid and N is the length of each row in the grid 
// SPACE COMPLEXITY: O(MN)


class Solution {
    
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if(m == 1 && n == 1) {
            return grid[0][0];
        }
        
        int[][] dp = new int[m][n];
        
        dp[0][0] = grid[0][0];
        
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i > 0 && j > 0) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            
                else if(i > 0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }
                else if(j > 0) {
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                    
                }
                
            }
            
        }
        
        return dp[m-1][n-1];
    }
} 
