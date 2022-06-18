// *** PROBLEM DESCRIPTION *** 
// You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
// Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
// Return the maximum number of points you can earn by applying the above operation some number of times.

// Example 1:

// Input: nums = [3,4,2]
// Output: 6
// Explanation: You can perform the following operations:
// - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
// - Delete 2 to earn 2 points. nums = [].
// You earn a total of 6 points.

// Example 2:

// Input: nums = [2,2,3,3,3,4]
// Output: 9
// Explanation: You can perform the following operations:
// - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
// - Delete a 3 again to earn 3 points. nums = [3].
// - Delete a 3 once more to earn 3 points. nums = [].
// You earn a total of 9 points.

// NOTE: I did not come up with this solution myself, the added comments and explanations are my understanding of the solution

// INTUITION:
// nums can contain duplicates. If we take one of the duplicates from the array, we can delete all the elements in the array that are one less and one greater than the value of the duplicate
// After that, we can just take the rest of the duplicates with no consequences, because all of the other consecutive elements are already deleted ]
// So, we should make a hashmap that stores the total number of points offered by each value in the array.
// We can then define a helper function that takes in a number n as a parameter, with n representing a number in the array. When calling the function the first time, 
// we will pass in the largest value of the array to automatically take care of the cases where we have to delete values 1 greater than the current value (as there are no values greater than the max value)
// If we take the current value n from the array, we can get as many points as stated in the hashmap PLUS the number of points of the element that is 2 less than the current element.
// But we cannot have the points from the hashmap for the value 1 less than the current value, as stated in the problem description. 

// This leads to our RECURRENCE RELATION: 
// If our hashmap is called "points" and our additional function is called "helper", the max amount of points for a given value is 
// Math.max(points.getOrDefault(n, 0) + helper(n-2), helper(n-1)). 
// We can then use another hashmap (called memo) to store the answers to already solved subproblems to avoid unnecessary recalculations. 

// BASE CASES:
// If the only value in nums is 0, then we cannot gain any points, so we just return zero from our helper function
// If there are only 1s and 0s in the array, we should always take the 1s, as they give more points than the zeroes.

// *** SOLUTION 1 (TOP-DOWN APPROACH) *** 
// TIME COMPLEXITY: O(maxNumber), where maxNumber represents the largest value in the array
// SPACE COMPLEXITY: O(maxNumber)

class Solution {
    Map<Integer, Integer> points = new HashMap<Integer, Integer>();
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
    
    public int deleteAndEarn(int[] nums) {
        int max = nums[0];
        for(int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            max = Math.max(max, num);
        }
    
        return helper(max);
    }

    public int helper(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return points.getOrDefault(1, 0);
        }
        
        if(!memo.containsKey(n)) {
            memo.put(n, Math.max(points.getOrDefault(n, 0) + helper(n-2), helper(n-1)));
        }
        
        return memo.get(n);
    }
}

// *** SOLUTION 2 (SPACE-OPTIMIZED BOTTOM-UP APPROACH)

// TIME COMPLEXITY: O(maxNumber)
// SPACE COMPLEXITY: O(1)

// We can use two pointers to store the solutions to the last two subproblems, and have the pointers start out with the base cases.
// The recurrence relation is still the same, and is actually similar to the House Robber Problem

class Solution {
    Map<Integer, Integer> points = new HashMap<Integer, Integer>();
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public int deleteAndEarn(int[] nums) {
        int max = nums[0];
        
        for(int num : nums) {
            max = Math.max(num, max);
            points.put(num, points.getOrDefault(num, 0) + num);
        }
        
        int first = 0;
        int second = points.getOrDefault(1, 0);
        
        for(int i = 2; i <= max; i++) {
            int temp = second;
            second = Math.max(points.getOrDefault(i, 0) + first, second);
            first = temp;
        }
    
        return second;
    }

}












