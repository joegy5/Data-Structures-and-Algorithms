// PROBLEM DESCRIPTION:
// Given a string s, find the longest palindromic subsequence's length in s.

// A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

 

// Example 1:

// Input: s = "bbbab"
// Output: 4
// Explanation: One possible longest palindromic subsequence is "bbbb".

// Example 2:

// Input: s = "cbbd"
// Output: 2
// Explanation: One possible longest palindromic subsequence is "bb".

// NOTE: I did not come up with this solution on my own. 

// INTUITION:
// This is similar to the longest common subsequence problem 
// Like the longest common subsequence problem, we want to find the longest subsequence that meets the conditions.
// In this case, we can have two pointers, one that starts at the beginning of the string and one that starts at the end of the string. 
// If the characters that the two pointers are looking at are the same, that means we can add 2 to the current palindromic subsequence that we have. 
// Otherwise, we want to look at both cases where in one case we increment the left pointer and keep the right pointer the same,
// and the other case where we decrement the right pointer and keep the left pointer the same. 
// From there, we want to take the maximum length of the two subsequences from the segments of the string formed by those two cases.
// This is our recurrence relation that we can use to recurse from one subproblem to the next.
// Once again, we use a storage to memoize our results to avoid unnecessary recomputations. 

// TIME COMPLEXITY: O(N squared), where N is the length of the string
// SPACE COMPLEXITY: O(N squared), where N is the length of the string. 

class Solution {
    int[][] memo;
    String s;
     
    public int longestPalindromeSubseq(String s) {
        this.s = s;
        memo = new int[s.length()][s.length()];
        return helper(0, s.length()-1);
    }

    private int helper(int i, int j) {
        if(i > j) {
            return 0;
        }
        if(i == j) {
            return 1;
        }
    
        if(memo[i][j] == 0) {
            if(s.charAt(i) == s.charAt(j)) {
                memo[i][j] = 2 + helper(i + 1, j - 1);
            }
            else {
                memo[i][j] = Math.max(helper(i+1, j), helper(i, j-1));
            }
        }
    
        return memo[i][j];
    }
}
