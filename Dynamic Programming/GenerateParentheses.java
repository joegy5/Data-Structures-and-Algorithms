// PROBLEM DESCRIPTION:
// Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

// Example 1:

// Input: n = 3
// Output: ["((()))","(()())","(())()","()(())","()()()"]


// Example 2:

// Input: n = 1
// Output: ["()"]


// INTUITION:
// We can use dynamic programming here because we can look smaller subproblems and make a decision for each one of them to find the answer to the original problem
// At each set of parantheses, we can choose to either use an open or closed parantheses.
// If the current number of open parantheses used equals the current number of closed parantheses used, that means we have to use an open parantheses as we can not at any time have more closed parantheses than open parantheses
// Otherwise, we can choose to use either an open parantheses or a closed parantheses. 
    // Since we want to find the total number of possible ways to form well-formed paranthees, we should look at both cases
        // This is our recurrence relation that we can use to recurse from one subproblem to the next. 
// Once again, like with other dynamic programming problems, we can use memoization (a 2D array in this case) to store already calculated subproblems so that we do not have to recompute them again. 

class Solution {
    List<String> ans = new LinkedList<String>();
    
    public List<String> generateParenthesis(int n) {
        helper(n, n, "");
        return ans;
    }

    private void helper(int openParenLeft, int closedParenLeft, String currentCombination) {
        if(openParenLeft == 0 && closedParenLeft == 0) {
            ans.add(currentCombination);
            return;
        }
        
        
        if(openParenLeft == 0) {
            helper(openParenLeft, closedParenLeft - 1, currentCombination + ")");
            return;
        }
    
        if(openParenLeft == closedParenLeft) {
            helper(openParenLeft - 1, closedParenLeft, currentCombination + "(");
            return;
        }
        
        else {  
            helper(openParenLeft, closedParenLeft - 1, currentCombination + ")");
            helper(openParenLeft - 1, closedParenLeft, currentCombination + "(");
        }
    }
}
