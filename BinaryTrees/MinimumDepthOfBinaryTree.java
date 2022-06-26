// PROBLEM DESCRIPTION:

// Given a binary tree, find its minimum depth.
// The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
// Note: A leaf is a node with no children.

// INTUITION:

// We can solve this problem recursively. Each time we find a node that is not a leaf node, we can recurse down to the next left and right children nodes (depending on which one or ones the current node has)
// If we start off with a root node that is already null, we just return 0.
// Once we find a leaf node, we just return 1 as that leaf node counts as another node when finding the depth of a certain path in the binary tree. 
// We can keep track of the current minimum depth using a global variable. We initally set the global variable to be the largest integer number it can be so that it will most likely get updated to become a smaller number once the recursive method actually starts 


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
    int ans = Integer.MAX_VALUE;
    
    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        if(root.left == null && root.right == null) {
            return 1;
        }
        
        if(root.left != null) {
            ans = Math.min(ans, 1 + minDepth(root.left));
        }
        if(root.right != null) {
            ans = Math.min(ans, 1 + minDepth(root.right));
        }
    
        return ans;
    }
}
