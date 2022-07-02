// NOTE: This is what I learned from leetcode. This is not my own implementation of the disjoint set data structure. 


public class QuickFindDisjointSet {

    private int[] root;

    public QuickFindDisjointSet(int size) {
        root = new int[size]; // Each index of the array represents a vertex. So, the length of the array is determined by
        // the number of vertices in the graph
        for(int i = 0; i < root.length; i++) {
            root[i] = i; // Initially, all vertices are isolated. This means that their root nodes are themselves
        }
    }

    public int find(int vertex) {
        // Since the array stores the roots of each vertex, we just have to return the value of the element at the given index (which is the vertex)
        return root[vertex];
    }

    public void union(int vertex1, int vertex2) {
        int root1 = find(vertex1); // We find the roots of each vertex
        int root2 = find(vertex2);

        if(root1 != root2) { // We then traverse the entire array to set each vertex's original root node to the root that we want
            for (int i = 0; i < root.length; i++) {
                if (root[i] == root2) {
                    root[i] = root1;
                }
            }
        }
    }

    public boolean isConnected(int vertex1, int vertex2) {
        // We check if the roots of the two given vertices are the same. If they are, that means that the vertices are connected.
        // Otherwise, the vertices are not connected

        return find(vertex1) == find(vertex2);
    }
}
