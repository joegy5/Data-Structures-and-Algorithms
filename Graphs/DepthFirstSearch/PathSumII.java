// Problem Description: 

// Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

// A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

 

// Example 1:


// Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
// Output: [[5,4,11,2],[5,8,4,5]]
// Explanation: There are two paths whose sum equals targetSum:
// 5 + 4 + 11 + 2 = 22
// 5 + 8 + 4 + 5 = 22


// Example 2:


// Input: root = [1,2,3], targetSum = 5
// Output: []


// Example 3:

// Input: root = [1,2], targetSum = 0
// Output: []

// INTUITION: 
// We can keep a global list containing lists inside as our answer list that we will return once we are done.
// We then use DFS to search through all the possible paths 
// For each path, we first check if the current node that we are looking at is null or not. If it is, we are not on a valid path anymore, so we just return there 
// Otherwise, if we have reached a leaf node (meaning that its left and right children are both null), we can check if the current sum that we have been keeping track of 
// plus the value of the current node that we are looking at is equal to the target sum. 
// If it is, we can add the current list that we have been using for our current path to the answer list 

// However, if we have not reached the leaf node yet, then we do DFS on the left and/or the right child of the current node that we are looking at.
// We add the current value of the node that we are looking at to the current path that we are on
// Then we copy over our current path list into two other lists (since otherwise we are changing the same list over and over again as we are passing a reference to the same list), then pass each of those lists 
// into two recursive DFS functions, one recursing to the left child, and another one to the right child.

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
    private List<List<Integer>> ans = new ArrayList<>();
    private int target;
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        this.target = targetSum;
        dfs(root, 0, new ArrayList<>());
        return ans;
    }

    public void dfs(TreeNode curr, int sum, List<Integer> path) {
        if(curr == null) {
            return;
        }
        path.add(curr.val);
        
        
        if(curr.left == null && curr.right == null) {
            if(sum + curr.val == target) {
                //path.add(curr.val);
                ans.add(path);
            }
            return;
        }
        
        List<Integer> leftPath = new ArrayList<>();
        for(int val : path) {
            leftPath.add(val);
        }
        dfs(curr.left, sum + curr.val, leftPath);
        
        List<Integer> rightPath = new ArrayList<>();
        for(int val : path) {
            rightPath.add(val);
        }
        dfs(curr.right, sum + curr.val, path);
    }
}






