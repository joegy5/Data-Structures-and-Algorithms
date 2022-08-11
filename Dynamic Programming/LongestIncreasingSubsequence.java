// PROBLEM DESCRIPTION:

// Given an integer array nums, return the length of the longest strictly increasing subsequence.

// A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

// Example 1:

// Input: nums = [10,9,2,5,3,7,101,18]
// Output: 4
// Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.


// Example 2:

// Input: nums = [0,1,0,3,2,3]
// Output: 4


// Example 3:

// Input: nums = [7,7,7,7,7,7,7]
// Output: 1

// NOTE: This is not my own solution. The explanation is my understanding of the solution.

// INTUITION:
// We can start with the last element in the array. The longest increasing subsequence we can construct with that last element is just of length 1, because there are no elements after it.
// We can use a nested for-loop (the outer loop iterating backward, the inner one iterating forward) to find the length of the longest increasing subsequence we can construct if the current element we are looking at in 
// the outer for-loop was the first element. 
// We can use this answer to calculate the longest increasing subsequence if the previous element was first, and so on, meaning that this is a dynamic programming problem (overlapping subproblems).
// Our condition inside the nested for-loops is to see if the outer-loop element is smaller than the inner-loop element. If it is, then we can check if we can construct a longer increasing subseqence using that inner-loop element.
// If we can, our length of the longest increasing subsequence starting from the current outer-loop element is 1 + the length of the longest increasing subsequence starting from the inner-loop element 

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ans = 1;
        
        for(int i = nums.length - 1; i >= 0; i--) {
            for(int j = i+1; j < nums.length; j++) {
                if(nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                    
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        
        return ans;
    }
}
