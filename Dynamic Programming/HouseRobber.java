// *** PROBLEM DESCIPTION *** 
// You are a professional robber planning to rob houses along a street. 
// Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that 
// adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

// Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

// EX: Input: nums = [1,2,3,1]
    // Output: 4
    // Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    // Total amount you can rob = 1 + 3 = 4.

// EX: Input: nums = [2,7,9,3,1]
    // Output: 12
    // Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
    // Total amount you can rob = 2 + 9 + 1 = 12.


// INTUITION:
    // Base Cases:
        // 1. If nums.length == 1, that means that we should just rob the one house (the only other choice is to not rob that house, resulting in no money)
        // 2. If nums.length == 2, that means we should rob whichever house gives us more money, as we cannot rob both due to the security system. 
    // If nums.length == 3, it gets more interesting
          // If we rob the first house, we cannot rob the second house. However, we can rob the third house. So, the max amount of money we would get is 
          // Math.max(nums[0] + nums[2], nums[1])
               // THIS LEADS TO OUR RECURRENCE RELATION: we can build off this case: taking the max of the current amount of money robbed if we had robbed the second-latest house + the amount of money we could get 
               // robbing the current house, or the amount of money robbed if we had robbed the latest house behind the current house
               // In Code (if we had a DP array to store the values): 
                  // dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1])


// *** SOLUTION 1 (BOTTOM-UP) ***

// TIME COMPLEXITY: O(n)
// SPACE COMPLEXITY O(n)

// NOTE: I did not come up with these solutions on my own, I did get help to understand the proper solutions

class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        
        int[] dp = new int[nums.length];
        
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for(int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
    
        return dp[dp.length - 1];
    }
}


// *** SOLUTION 2 (SPACE-OPTIMIZED BOTTOM-UP APPROACH) *** 

// TIME COMPLEXITY: O(n)
// SPACE COMPLEXITY: O(1)

// Like in the Fibonacci Sequence problem, we can use two pointers instead of an entire array, as at any point in time we just need the solutions to the last two subproblems. 
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        
        int[] dp = new int[nums.length];
        
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        
        for(int i = 2; i < dp.length; i++) {
            int temp = second;
            second = Math.max(second, first + nums[i]);
            first = temp;
        }
    
        return second;
    }
}






