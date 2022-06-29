package com.company;

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
