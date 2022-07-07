// PROBLEM DESCRIPTION:

// You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

// struct Node {
//   int val;
//   Node *left;
//   Node *right;
//   Node *next;
// }
// Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

// Initially, all next pointers are set to NULL.

// Example 1:


// Input: root = [1,2,3,4,5,6,7]
// Output: [1,#,2,3,#,4,5,6,7,#]
// Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.


// Example 2:

// Input: root = []
// Output: []

// INTUITION (Non-constant space solution):

// We can keep track of what layer we are on using a hashmap (key is an integer representing the layer, value is a list containing all the nodes in that layer)
// Our base case of our recursive function is that if the current node we are looking at is null (meaning we have gone past the leaf nodes), then we can just return and terminate the current recursive function call there.
// Otherwise, we add the children of the current node that we are looking at to the map with the current layer that we are on, then recurse to those children and adding 1 to the current layer that we are on. 

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    Map<Integer, List<Node>> layers = new HashMap<Integer, List<Node>>();
    
    public Node connect(Node root) {
        List<Node> list1 = new ArrayList<Node>();
        list1.add(root);
        layers.put(0, list1);
        
        bfs(root, 1);
        
        
        for(List<Node> layer : layers.values()) {
            for(int i = 0; i < layer.size() - 1; i++) {
                if(layer.get(i) != null) {
                    layer.get(i).next = layer.get(i+1);    
                }
            }
        }
    
        return root;
    }

    public void bfs(Node node, int currentLayer) {
        if(node == null) {
            return;
        }
        if(layers.get(currentLayer) == null) {
            layers.put(currentLayer, new ArrayList<Node>());
        }
        
        layers.get(currentLayer).add(node.left);
        layers.get(currentLayer).add(node.right);
        bfs(node.left, currentLayer + 1);
        bfs(node.right, currentLayer + 1);
        
    }
}

// INTUITION (Constant space solution)

// NOTE: I did not come up with this solution. The explanation is my understanding of the solution
// 
// We have two types of connections that we need to make, with one being more straightforward than the other:
// 1. Set the "next" pointer of the left child of the current node we are looking at to the right child (tempHead.left.next = tempHead.right)
// 2. Connecting nodes that have different parents. 
      // This can be done by using the next pointer of the previous layer
      // If two nodes are connected via the next pointer, then the right child of the left node and the left child of the right node must be connected as well
      // We can connect these nodes by looking at which nodes from the previous layers are connected via the "next" pointer 
      // we can set the "next" pointer of the right child of the current node that we are looking at equal to the left child of the node next to the current node that we are looking at 
// we can then traverse to the node connected to the current node that we are looking at by using the "next pointer" again. 
// To do this, we first start with a node that represents the left most node of the current layer that we are on. 
// We keep going in the while loop until this node is a leaf node (its left child is null)
// We then set a temporary head traverser node equal to the left most node (which is where we start the linked-list-like layer traversal)
// Then we enter the second while loop, where we keep making both types of connections until we reach the end of the layer and updating our traversal node equal to its next value according to its "next" pointer 
// After exiting the inner while loop, we then update the leftmost pointer by setting equal to its left child, entering the next layer and restarting the process all over again. 

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
    
        Node leftMost = root;
        
        while(leftMost != null && leftMost.left != null) {
            Node tempHead = leftMost;
            
            while(tempHead != null) {
       
                tempHead.left.next = tempHead.right;    
                
                if(tempHead.next != null) {
                    tempHead.right.next = tempHead.next.left;    
                }
                
                tempHead = tempHead.next;
            }
            leftMost = leftMost.left;
        }
    
        return root;
    }
}










