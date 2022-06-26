

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
