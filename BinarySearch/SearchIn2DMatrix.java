// PROBLEM DESCRIPTION: 

//Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

//Integers in each row are sorted from left to right.
//The first integer of each row is greater than the last integer of the previous row.

// EX:
// Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
// Output: true

// INTUITION:
// First we can do a binary search to find which row the target value is in.
// We compare the validity of a row based on its first and last values.
// If the first value is already greater than the target, then our current row is too far below the correct row (as all the other values in that row will be even greater)
// If the last value is already less than the target, it means the current row is too far above the correct row (as all the other values in that row will be less than the last value of the row)
// Once we find the correct row, we can binary search the row to find the actual element.

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int leftRow = 0, rightRow = matrix.length - 1;
        
        while(leftRow <= rightRow) {
            int middleRow = leftRow + (rightRow - leftRow)/2;
            
            if(matrix[middleRow][0] > target) {
                rightRow = middleRow - 1;
                continue;
            }
            if(matrix[middleRow][matrix[0].length - 1] < target) {
                leftRow = middleRow + 1;
                continue;
            }
            
            else {
                int left = 0, right = matrix[middleRow].length - 1;
            
                while(left <= right) {
                    int middle = left + (right - left)/2;
                    int curr = matrix[middleRow][middle];
                    
                    if(curr == target) {
                        return true;
                    }
                    else if(curr > target) {
                        right = middle - 1;
                    }
                    else {
                        left = middle + 1;
                    }
                }   
                return false;
            }
        }
        return false;
        
    }
}
