// Intuition:
// Instead of doing a linear search, we can reduce the time complexity 
// by defining boundaries starting with the beginning and the ends of the array
// We then find the middle of those boundaries and check if it is in the target 
// If the value at the middle index is less than the target,
// that means that the index of the target value is somewhere between the middle index and the end index
// Otherwise, it means that the index of the target value is somewhere between the beginning index and the middle index
// We can keep updating the beginning and ending indices, constantly halving the size of the segment of the array that we are looking at 
// This reduces the time complexity of a linear search, which is O(n), to O(log n), where the base of the log is 2, as each
// time the search window that we are looking at in the array is being divided by 2.

public class Main {

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 7, 8, 12, 25, 32};
        int left = 0, right = nums.length - 1;
        int target = 25;
        
        while(left <= right) {
            int middle = left + (right - left)/2;
            if(nums[middle] == target) {
                System.out.println("The target number is at index " + middle);
            }
            else if(nums[middle] > target) {
                right = middle - 1;
            }
            else {
                left = middle + 1;
            }
        }
        System.out.println("Sorry, the target number is not in the array!");
    }
}
