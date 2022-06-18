// *** PROBLEM DESCRIPTION *** 
// Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

// You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

// Example 1:

// Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"]
// Output: ["Shogun"]
// Explanation: The only restaurant they both like is "Shogun".
 
// Example 2:

// Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["KFC","Shogun","Burger King"]
// Output: ["Shogun"]
// Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).

// NOTE: I did not come up with this solution entirely on my own

// INTUITION:
// Create a hashmap and traverse the first list, storing each element and its index in the map
// Traverse the second array, keeping track of the minimum sum of the two indexes, if an element is found in both lists.
// If the sum of the two indices is lower than the current minimum sum, we clear the entire answer list as those strings are no longer valid, and add the new element 
// However, if the sum = the current minimum sum, we also add that element to the current list without clearing anything, as asked to do so in the problem description. 

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<String> result = new LinkedList<String>();
        
        for(int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        
        int minSum = Integer.MAX_VALUE;
        
        for(int i = 0; i < list2.length; i++) {
            if(map.containsKey(list2[i])) {
                if(map.get(list2[i]) + i < minSum) {
                    result.clear();
                    result.add(list2[i]);
                    minSum = map.get(list2[i]) + i;
                }
                else if(map.get(list2[i]) + i == minSum) {
                    result.add(list2[i]);
                }
            }
        }
    
        return result.toArray(new String[result.size()]);
    }
}
