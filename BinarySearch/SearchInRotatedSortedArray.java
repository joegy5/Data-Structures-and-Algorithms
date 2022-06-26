// PROBLEM DESCRIPTION:

// There is an integer array nums sorted in ascending order (with distinct values).

// Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

// Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

// You must write an algorithm with O(log n) runtime complexity.

//Example 1:

//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4

//Example 2:

//Input: nums = [4,5,6,7,0,1,2], target = 3
//Output: -1

//Example 3:

//Input: nums = [1], target = 0
//Output: -1

// NOTE: I did not come up with this solution entirely on my own. 

// INTUITION:
// We can first use binary search to find where the pivot is (the last ascending number before the next number is smaller)
// We find our middle index, and compare it to the first and last elements of the array
// if the value at the middle index is greater than the value of the next element to the right, we found our pivot
// Otherwise:
// If the value at the middle index is greater than the last value of the array, then the pivot is somewhere between the last value and the middle index 
// Otherwise, the pivot is between the beginning index and the middle index
// We then update our left and right pointers accordingly. 

// Once we find the pivot, we can do a binary search of the segment of the array to the left of the pivot, and another binary search on the right segment of the pivot
// This is because both sides will have elements in ascending order
// We then return which side has the target. If the target is not found on either side, we return -1; 


class Solution {
    public int search(int[] nums, int target) {
        int pivot = -1;
        
        int left = 0, right = nums.length - 1, middle = -1;
        while(left <= right) {
            middle = left + (right - left)/2; // Slight modification of finding the middle element to avoid overflow when right and left values are very large. 
            if(middle < nums.length - 1 && nums[middle] > nums[middle + 1]) {
                pivot = middle;
                break;
            }
            else {
                if(nums[middle] > nums[nums.length - 1]) {
                    left = middle + 1;
                }
            
                else {
                    right = middle - 1;
                }
            }
        }
    
    
        if(pivot == -1) {
            return regularBinarySearch(nums, 0, nums.length - 1, target);
        }
    
        else {
            int leftSearch = regularBinarySearch(nums, 0, middle, target);
            int rightSearch = regularBinarySearch(nums, middle + 1, nums.length - 1, target);
            
            if(leftSearch != -1) {
                return leftSearch;
            }
            if(rightSearch != -1) {
                return rightSearch;
            } 
            return -1;
        }
    
    }

    private int regularBinarySearch(int[] nums, int left, int right, int target) {
        while(left <= right) {
            int middle = left + (right - left)/2;
            
            if(nums[middle] == target) {
                return middle;
            }
        
            else if(nums[middle] < target) {
                left = middle + 1;
            }
        
            else {
                right = middle - 1;
            }
        }
    
        return -1;
    }
} 






