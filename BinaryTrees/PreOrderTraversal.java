// PROBLEM DESCRIPTION:

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> ans = new LinkedList<Integer>();
    
    public List<Integer> preorderTraversal(TreeNode node) {
        if(node == null) {
            return ans;
        }
        ans.add(node.val);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
        
        return ans;
    }
}
