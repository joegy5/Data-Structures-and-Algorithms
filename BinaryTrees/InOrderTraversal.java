// PROBLEM DESCRIPTION: 
// Given the root of a binary tree, return the preorder traversal of its nodes' values.

// An inorder traversal is where we first visit the node left of the node we are currently looking at, then we look at the actual node we are on, then we look at the node to the right of the node we are currently looking at.


// RECURSIVE SOLUTION: 
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
    
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) {
            return ans;
        }
        
        if(root.left != null) {
            inorderTraversal(root.left);
            
        }
        ans.add(root.val);
        inorderTraversal(root.right);
        return ans;
    }
}

// ITERATIVE SOLUTION 
// NOTE: I did not come up with this solution on my own. 

// INTUITION:
// Use a stack to keep track of root nodes that have children. We keep iterating to the left node of each current node and adding it to the stack so that we can come back to that node later to see if it also has a right child 
// Once we used all the elements in the stack and line temp = temp.right (line 83) makes the temp node become null, we are done and can return the resulting list.

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

    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        TreeNode temp = root;
        while(temp != null || !stack.isEmpty()) {
            while(temp != null) {
                stack.push(temp);
                
                temp = temp.left;
                
            }
            
            if(!stack.isEmpty()) {
                temp = stack.pop();
            }
            ans.add(temp.val);
            temp = temp.right;
        }
        return ans;
    }
}


