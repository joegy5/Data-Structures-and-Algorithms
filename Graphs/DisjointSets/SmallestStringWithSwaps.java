// PROBLEM DESCRIPTION:

// You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

// You can swap the characters at any pair of indices in the given pairs any number of times.

// Return the lexicographically smallest string that s can be changed to after using the swaps.

 

// Example 1:

// Input: s = "dcab", pairs = [[0,3],[1,2]]
// Output: "bacd"
// Explaination: 
// Swap s[0] and s[3], s = "bcad"
// Swap s[1] and s[2], s = "bacd"


// Example 2:

// Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
// Output: "abcd"
// Explaination: 
// Swap s[0] and s[3], s = "bcad"
// Swap s[0] and s[2], s = "acbd"
// Swap s[1] and s[2], s = "abcd"


// Example 3:

// Input: s = "cba", pairs = [[0,1],[1,2]]
// Output: "abc"
// Explaination: 
// Swap s[0] and s[1], s = "bca"
// Swap s[1] and s[2], s = "bac"
// Swap s[0] and s[1], s = "abc"

// NOTE: This solution is slow when s and pairs are very large, but works for regular sizes, and is my own solution.  

// INTUITION:
// We can notice that if we build a graph using the connected indices (according to the pairs list), we can swap any characters within those graphs indirectly by swapping characters that are directly swappable according to the pairs list
// So, we can build a graph, store each string subsequence that has indices that are part of the same component in the graph, 
// sort each of those subsequences, and build the new string that places each character at their correct position. 

class Solution {
    int[] parents;
    int[] ranks;
    String[] array;
    
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        parents = new int[s.length()];
        ranks = new int[s.length()];
        
        array = new String[s.length()];
       
        
        for(int i = 0; i < parents.length; i++) {
            parents[i] = i;
            ranks[i] = 1;
            array[i] = "";
        }
    
        for(int i = 0; i < pairs.size(); i++) {
            union(pairs.get(i).get(0), pairs.get(i).get(1));
        }
      
    
        for(int i = 0; i < parents.length; i++) {
            int root = find(i);
            array[root] += s.charAt(i);
        }
    
        for(int i = 0; i < array.length; i++) {
            if(array[i] != null) {
                char[] tempArray = array[i].toCharArray();
                Arrays.sort(tempArray);
                array[i] = new String(tempArray);
            }
        }

        char[] resultString = new char[s.length()];

        for(int i = 0; i < array.length; i++) {
            if(array[i] != "") {
                int k = 0;
                for(int j = 0; j < parents.length; j++) {
                    if(find(j) == i) {
                        resultString[j] = array[i].charAt(k);
                        k++;
                    }
                    
                }           
            }
        }
        
        return new String(resultString);
    }
    
    public void union(int vertex1, int vertex2) {
        int root1 = find(vertex1);
        int root2 = find(vertex2);
        
        if(root1 != root2) {
            
            if(ranks[root1] > ranks[root2]) {
                parents[root2] = root1; 
            }
        
            else if(ranks[root1] < ranks[root2]) {
                parents[root1] = root2;
            }
        
            else {
                parents[root2] = root1;
                ranks[root1]++;
            }
        }
    }

    public int find(int vertex) {
        if(parents[vertex] == vertex) {
            return vertex;
        }
        return parents[vertex] = find(parents[vertex]);
    }
} 
