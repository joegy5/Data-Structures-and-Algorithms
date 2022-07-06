// PROBLEM DESCRIPTION: 

// Given a reference of a node in a connected undirected graph.

// Return a deep copy (clone) of the graph.

// Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

// class Node {
//     public int val;
//     public List<Node> neighbors;
// }
 

// Test case format:

// For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

// An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

// The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

 

// Example 1:


// Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
// Output: [[2,4],[1,3],[2,4],[1,3]]
// Explanation: There are 4 nodes in the graph.
// 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
// 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
// 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
// 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).


// Example 2:


// Input: adjList = [[]]
// Output: [[]]
// Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.


// Example 3:

// Input: adjList = []
// Output: []
// Explanation: This an empty graph, it does not have any nodes.


// NOTE: This is not my own solution, the explanation is my understanding of the solution.
// 
// INTUITION:
// We can use depth first search recursively to explore all the nodes and add them to the clone graph. 
// The main logic is to recursively check each neighbor of the current node that we are looking at. But first, we use a hashmap to keep track of the nodes that we have already visited 
// Each key in the hashmap represents the original node, and each value is the clone of that node. 
// That way, if we encounter a node that we have already visited before, we can just add its clone as a neighbor to another node that we are looking at without recursing back to the visited node again, causing an infinite loop
// Since we are recursing through each neighbor of each node, at some point we will encounter nodes that don't have some neighbors, meaning that those neighbors are just null. If that is the case, we can just return the null node 
// At the end of each recursive call, we return the clone node so that we can add it as a neighbor to other nodes that we are looking at. 

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    private Map<Node, Node> seen = new HashMap<Node, Node>();
    
    
    public Node cloneGraph(Node node) {
        if(node == null) {
            return node;
        }
    
        if(seen.containsKey(node)) {
            return seen.get(node);
        }
        
        Node clone = new Node(node.val);
        seen.put(node, clone);
        
        for(Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
    
        return clone;
    }
}
