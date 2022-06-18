// *** PROBLEM DESCRIPTION *** 
// You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
// You can either start from the step with index 0, or the step with index 1.
// Return the minimum cost to reach the top of the floor.

// EX: Input: cost = [10,15,20]
//     Output: 15
//     Explanation: You will start at index 1.
//     - Pay 15 and climb two steps to reach the top.
//     The total cost is 15.

// EX: Input: cost = [1,100,1,1,1,100,1,1,100,1]
    // Output: 6
    // Explanation: You will start at index 0.
    // - Pay 1 and climb two steps to reach index 2.
    // - Pay 1 and climb two steps to reach index 4.
    // - Pay 1 and climb two steps to reach index 6.
    // - Pay 1 and climb one step to reach index 7.
    // - Pay 1 and climb two steps to reach index 9.
    // - Pay 1 and climb one step to reach the top.
    // The total cost is 6.

/// INTUITION:
/// At any element in the array (besides the base cases), if we land on the element, we have to pay the cost. We could have gotten to that element from either the latest element
/// or the second-latest element. We want to minimize the cost, so we take the minimum of those two latest elements
// Therefore, our RECCURENCE RELATION is:
// dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2])
// BASE CASES:
// if cost.length == 1, we have to pay the cost of cost[0], because we have no other choice 
// if cost.length == 2, we should pay the minimum of cost[0] and cost[1] to reach the top of the stairs 

// *** SOLUTION 1 (BOTTOM-UP APPROACH) *** 

// TIME COMPLEXITY: O(n)
// SPACE COMPLEXITY: O(n)

// NOTE: I did not come up with these solutions entirely on my own, I did get some help to understand the proper solutions

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if(cost.length == 1) {
            return cost[0];
        }
        if(cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }
        
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        
        
        for(int i = 2; i < cost.length; i++) {
            dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
        }
        // This time, we return the minimum of the last two elements to take care of the case where we initally pay the cost at cost[1] instead of cost[0] because cost[1] is cheaper
        return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
    }
}


// *** SOLUTION 2 (SPACE-OPTIMIZED BOTTOM-UP APPROACH) *** 
// TIME COMPLEXITY: O(n)
// SPACE COMPLEXITY: O(1)

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if(cost.length == 1) {
            return cost[0];
        }
        if(cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }
        
        // we can use two pointers to keep track of the solutions to the last two subproblems, because that is all we need, instead of an entire DP array 
        int first = cost[0];
        int second = cost[1];
        
        for(int i = 2; i < cost.length; i++) {
            int temp = second;
            second = cost[i] + Math.min(first, second);
            first = temp;
        }
    
        return Math.min(first, second);
    }
}






















