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
