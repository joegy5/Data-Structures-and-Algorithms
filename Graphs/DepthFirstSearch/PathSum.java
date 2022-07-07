// PROBLEM DESCRIPTION:

// Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

// A leaf is a node with no children.

 

// Example 1:


// Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
// Output: true
// Explanation: The root-to-leaf path with the target sum is shown.


// Example 2:


// Input: root = [1,2,3], targetSum = 5
// Output: false
// Explanation: There two root-to-leaf paths in the tree:
// (1 --> 2): The sum is 3.
// (1 --> 3): The sum is 4.
// There is no root-to-leaf path with sum = 5.


// Example 3:

// Input: root = [], targetSum = 0
// Output: false
// Explanation: Since the tree is empty, there are no root-to-leaf paths.



// INTUITION:
// We can use depth-first search to traverse all paths of the binary tree while keeping track of the current sum for each path. 
// If we reach a leaf node (both of its children are null, which can be checked in the base case of the recursive function), then we can check if the current sum 
// for the path that we have taken plus the value of the leaf node that we are looking at is equal to the target sum. If so, then we can set a global boolean variable 
// equal to true. Once the recursive function is entirely done, we can return whether or not that global boolean variable is true or not.
// We check all paths by recursing to both the left and right children of the current node that we are looking at, if they are not null

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
    boolean found = false;
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }
        
        helper(root, targetSum, 0);   
        return found;
    }

    public void helper(TreeNode node, int targetSum, int currentSum) {
        
        if(node.left == null && node.right == null) {
            if(currentSum + node.val == targetSum) {
                found = true;
            }
            return;
        }
    
        if(node.left != null) {
            
            helper(node.left, targetSum, currentSum + node.val);
        }
    
        if(node.right != null) {
            helper(node.right, targetSum, currentSum + node.val);
        }
    }
}

