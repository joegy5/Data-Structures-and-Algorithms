// PROBLEM DESCRIPTION:

// Suppose you are at a party with n people labeled from 0 to n - 1 and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know the celebrity, but the celebrity does not know any of them.

// Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

// You are given a helper function bool knows(a, b) that tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if they are at the party.

// Return the celebrity's label if there is a celebrity at the party. If there is no celebrity, return -1.

 

// Example 1:


// Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
// Output: 1
// Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.


// Example 2:


// Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
// Output: -1
// Explanation: There is no celebrity.

// SOLUTION 1 (MY SOLUTION):

// We can construct the graph using a list that contains lists. 
// We use nested for-loops and the knows() function to check if there is a relation between the two current people that we are looking at. If there is, we can draw a directed edge 
// between the two people to indicate that one knows the other.

// Once we have constructed the graph, we loop through the graph list and check if any of the lists inside that list has a size equal to n-1. if so, that means everybody else knows the person that is represented by the index of that list 
// Finally, we have to check that the possible candidate does not know anybody. We can check this by using another nested for-loop to see if the candidate is in any of the other lists. 
// if that candidate is, then we return -1 because it means that the candidate knows somebody, which disqualifies them from being the celebrity.
// If that candidate is not in any other list, we can return false.

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        List<List<Integer>> graph = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                
                if(i == j) {
                    continue;
                }
            
                if(knows(i, j)) {
                    graph.get(j).add(i);
                }
            }
        }
        int celeb = -1;
        for(int i = 0; i < n; i++) {
            if(graph.get(i).size() == n - 1) {
                celeb = i;
                break;
            }
        }
        for(List<Integer> person : graph) {
            for(int relation : person) {
                if(relation == celeb) {
                    return -1;
                }
            }
        }
        
        return celeb;
    }
}   

// SOLUTION 2 (NOT MY SOLUTION, much better solution)

// We can reduce the time complexity to O(n) by just using two passes and using a greedy algorithm
// We can set the candidate to equal person number 0, then loop through all the people up to person n-1
// If the current person that we have stored as the candidate knows the current person that we are looking at in our for-loop, then our current candidate is no longer valid,
// but the current person that we are looking at could possible be the candidate.

// Once we are done with the first pass, we move on the the second pass 
// In the second pass, we loop through all the people again. If there is anybody that our current candidate knows, or there is somebody that does not know the candidate, our candidate is not a valid celebrity and we can immediately return -1.
// Otherwise, at the end of the loop, we can return our candidate, which is qualified as the celebrity. 







