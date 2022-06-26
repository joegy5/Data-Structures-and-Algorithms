// PROBLEM DESCRIPTION:

// Given a string s, return the longest palindromic substring in s.

// Example 1:

// Input: s = "babad"
// Output: "bab"
// Explanation: "aba" is also a valid answer.

// Example 2:

// Input: s = "cbbd"
// Output: "bb"

// NOTE: I did not come up with this solution entirely on my own

// INTUITION:
// We can treat each character as the possible center of a palindrome. We can then have two pointers, one to go back, and one to go forward from the center of the palindrome to check how long the palindrome is if the current index 
// (possibly along with some other characters) is/are the center.
// To take care of the cases where the length of the palindromic substring is even (where the center of the palindrome is more than one character), our first while-loop inside our for-loop
// will have a pointer that keeps incrementing until it reaches a character that is not equal to the current one, meaning that we have finished finding our center for our current palindrome.
// The next while-loop will have two pointers to check how long the palindromic substring is. 
// The shortest palindromic substring possible is 1, since each character by itself is technically a palindrome. 

// TIME COMPLEXITY: O(n squared), where n is the length of the string 
// SPACE COMPLEXITY: O(1)

class Solution {
    public String longestPalindrome(String s) {
        
        String ans = "";
        int maxLen = 1;
        
        for(int i = 0; i < s.length(); i++) {
            int right = i;
            
            while(right < s.length() && s.charAt(right) == s.charAt(i)) {
                right++;
            }
            int left = i-1;
            while(right < s.length() && left >= 0 && s.charAt(right) == s.charAt(left)) {
                right++;
                left--;
            }
        
            if(right - left - 1 >= maxLen) {
                maxLen = right - left - 1;
                ans = s.substring(left + 1, right);
            }
        }
        return ans;
    } 
}
