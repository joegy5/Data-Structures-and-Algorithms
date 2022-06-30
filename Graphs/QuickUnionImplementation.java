// NOTE: This is what I learned from leetcode. This is not my own implementation of the disjoint set data structure. 

public class QuickUnionDisjointSet {

    private int[] parent;

    public QuickUnionDisjointSet(int size) {
        parent = new int[size];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = i; // Initially, each vertex's parent node is itself and therefore is a root of itself also
        }
    }

    public int find(int vertex) {
        int temp = vertex; // This function will keep setting the current vertex (temp) equal to its parent node,
        // until the current vertex = its parent node. When this happens it is because the current vertex is a root node,
        // meaning that we have found our root node.
        while(temp != parent[temp]) {
            temp = parent[temp];
        }

        return temp;
    }

    public void union(int vertex1, int vertex2) {
        // We find the roots of each vertex using the find function, then set the root of one set equal to the root of another set to unionize the set

        int root1 = find(vertex1);
        int root2 = find(vertex2);

        parent[root2] = root1;
    }

    public boolean isConnected(int vertex1, int vertex2) {
        // Simply check if the two given vertices have the same root. If they do, that means that they are connected.
        // Otherwise, they are not connected

        return find(vertex1) == find(vertex2);
    }
}



// Our current quick union set is a little slow. We can optimize it in two ways.
// 1. Optimize the union function using ranks. 
    // We can keep track of the height of each root node using an array called "ranks." 
    // When unionizing two sets of vertices, we first check which root node has the larger max height. That will be the new root node of the unionized set.
    // This is to minimize the height of the trees that we are forming within the graph to make the find function more efficient, since a taller tree means that we would have to traverse more parent nodes before finding the root node
    // Joining a larger set underneath the root node of a smaller set will just unneccessarily increase the height of the conjoined set more than it needs to be.

// 2. Compress the paths from different vertices to the root node.
    // We can use recursion such that each time we look at a current vertex in the find function, we also set that vertex's parent node equal to the root node in the parent array.

// Here is the optimized disjoint set data structure below: 

public class UnionByRankDisjointSet [
  private int[] parents;
  private int[] ranks;
  // Now we keep track of the ranks of each parent node 
// using another array called "ranks"


  public UnionByRankDisjointSet(int size) {
    parents = new int[size];
    ranks = new int[size];
    for(int i = 0; i < size; i++) {
      parents[i] = i;
      ranks[i] = 1;
      // We initialize the two arrays like before, except now we also initialize the ranks array such that each value initially is 1. 
      // This is because initially, all the vertices are 
      // isolated, meaning that the height of each vertex is 1 (itself).
    }
  }

  // PATH COMPRESSION OPTIMIZATION

  // We can use recursion to change the parent node of each current vertex that we are looking at to be equal to the root node of that current vertex.
// we can use recursion such that each time, the current vertex we are looking at changes. Each current vertex that we look at will also have its parent node changed to be equal to the root node. 
// This will make the find function more efficient the next time it is called because the max height of the tree/graph is reduced.

  public int find(int vertex) {
    // Base Case:
    if(parents[vertex] == vertex) {
      return vertex;
    }  
    // Same logic as before to find the root node. 
    return parents[vertex] = find(parents[vertex]);
  }

  // QUICK UNION OPTIMIZATION

  public boolean union(int vertex1, int vertex2) {
    int root1 = find(vertex1);
    int root2 = find(vertex2);
    
    if(root1 != root2) {
      
      // We know check if which root node has a greater height using the ranks array. 
      // The root that has the greater max height will then be the root node of the unionized set.
      if(ranks[root1] > ranks[root2]) {
        parents[root2] = root1;
      }
     
      // If the two max heights are equal, then 
      // either node can be chose to be the new root. 

      // The rank of the new root node will also have to be inremented by 1 in this case because 
      // if the other set with the same max size is added to the current set, then the size of the 
      // other set plus the extra root node that the set is being joined to will make the max height of the root node one larger than before. 
      else if(ranks[root1] < ranks[root2]) {
        parents[root1] = root2;
      }
    
      else {
        parents[root2] = root1;
        ranks[root1]++;
      }
    }
  }

  public boolean isConnected(int vertex1, int vertex2) {
    return find(vertex1) == find(vertex2);
  }
}





