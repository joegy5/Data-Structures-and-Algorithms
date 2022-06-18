// *** PROBLEM DESCRIPTION *** 
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

 

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]

// NOTE: This is not my solution. The explanation is my understanding of the solution. The optimized solution is the classic solution that many job interviewers may look for if they ask this question.

// INTUITION:
// We could just create a for loop inside a for loop. For each element, check the array for all the elements to see if any of them add up with the current element to equal the target value.
// However, the time complexity of this is quadratic (O(n^2)). We can do better by using a hashmap
// We can store the current value we are looking at in the array if we have not already found another element that when subtracted from target, equals the current element.
// The key will be the element value, and the value will be the index of the element so that we can store the indices in the final answer array (of length 2)

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] ans = new int[2];
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                ans[0] = i;
                ans[1] = map.get(target - nums[i]);
                return ans;
            }
            else {
                map.put(nums[i], i);    
            }
            
        }
    
        return ans;
    }
}
