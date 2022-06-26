// PROBLEM DESCRIPTION:

//Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

//A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

// For example, "ace" is a subsequence of "abcde".
// A common subsequence of two strings is a subsequence that is common to both strings.

// Example 1:

// Input: text1 = "abcde", text2 = "ace" 
// Output: 3  
// Explanation: The longest common subsequence is "ace" and its length is 3.

// Example 2:

// Input: text1 = "abc", text2 = "abc"
// Output: 3
// Explanation: The longest common subsequence is "abc" and its length is 3.

// Example 3:

// Input: text1 = "abc", text2 = "def"
// Output: 0
// Explanation: There is no such common subsequence, so the result is 0.

// NOTE: I did not come up with this solution on my own

// INTUITION:
// We can use dynamic programming here because we can split our big problem into little subproblems by comparing each character of the two strings.
// Our recurrence relation:
// If the two current indexes have matching characters between the two strings, that means we can add 1 to the current length of the common subsequence that we are looking at
// and can just move both indexes to the next character of their corresponding strings.
// Otherwise, we have to look at both cases where we can move either index (only one of them is moved), while the other stays the same, and recurse to that case using a helper function
// We can define our helper function as the recursive function that takes in the current indices of the two strings. We start at the beginning of both strings, hence our first helper call being "helper(0,0)"
// We also use a 2D array to memoize our results to avoid any possible unnecessary recomputations. 


class Solution {
    int[][] memo;
    int m, n;
    String t1, t2;
    
    public int longestCommonSubsequence(String text1, String text2) {
        t1 = text1;
        t2 = text2;
        m = t1.length();
        n = t2.length();
        memo = new int[m][n];
        return helper(0,0);
    }

    private int helper(int i, int j) {
        if(i == m || j == n) {
            return 0;
        }
        if(memo[i][j] == 0) {
            if(t1.charAt(i) == t2.charAt(j)) {
                memo[i][j] = 1 + helper(i + 1, j + 1);
            }
        
            else {
                memo[i][j] = Math.max(helper(i+1, j), helper(i, j+1));
            }
        }
    
        return memo[i][j];
    }
}
