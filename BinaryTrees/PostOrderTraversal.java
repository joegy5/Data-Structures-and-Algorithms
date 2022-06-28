// PROBLEM DESCRIPTION: Given the root of a binary tree, return the postorder traversal of its nodes' values.

// A postorder traversal is where we look at the left child node, then the right child node, then the actual root node last. 

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
    
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) {
            return ans;
        }
    
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        ans.add(root.val);
        
        return ans;
    }
}

// ITERATIVE SOLUTION
// NOTE: I did not come up with this solution on my own

// INTUITION: 
// Here we can take advantange of the addFirst function of the List interface, which adds a given object to the beginning of the list.
// We add TreeNodes to the stack in the reverse order that we want them to be in the list, and then in the next iteration of the while loop, we simply add the top element of the stack to the beginning of the list
// So that it gets added in the right order 
// We then repeat the process again until the stack is empty, meaning that we have finished looking at all the elements. 

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        
        List<Integer> ans = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        if(root == null) {
            return ans;
        }
        
        
        stack.push(root);
        
        while(!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            ans.addFirst(temp.val);
            
            if(temp.left != null) {
                stack.push(temp.left);
            }
            if(temp.right != null) {
                stack.push(temp.right);
            }
        }
    
        return ans;
    }
}

