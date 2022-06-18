// *** Problem Description *** 
// A message containing letters from A-Z can be encoded into numbers using the following mapping:

// 'A' -> "1"
// 'B' -> "2"
// ...
// 'Z' -> "26"
// To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

// "AAJF" with the grouping (1 1 10 6)
// "KJF" with the grouping (11 10 6)
// Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

// Given a string s containing only digits, return the number of ways to decode it.

// NOTE: I did not come up with this solution, the comments and explanation are my understanding of the code 

// INTUITION: 
// The key here is that if at any point in time we encounter a single digit that is not 0, or any double digit that is between 1 and 26 inclusive, that digit can be decoded into a character 
// So, as we iterate over the string of numbers, we have a choice: consider the single digit when trying to match it to its corresponding character, or consider the next number 
// along with the current number to consider it as a double digit, and check if that can be mapped to another character. We consider each case, then recurse to the next number (either one step and/or two steps ahead,
// depending on if we considered a single or double digit. If we reach the string for one recursive iteration, it means that the decoding was succesful and we can add 1 to the total number of ways to decode the string of numbers 
// Dynamic Programming is useful here because if we can find the number of ways to decode one segment of the string, we can just add that number of ways to the number of ways to decode the 
// next segment of the string, until we reach the full string and have our final answer 

// *** SOLUTION (RECURSIVE, TOP-DOWN APPROACH)

// TIME COMPLEXITY: O(n)
// SPACE COMPLEXITY: O(1)

class Solution {
    Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
    
    public int numDecodings(String s) {
        return findAnswer(0,s);
    }

    private int findAnswer(int index, String s) {
        // STRATEGY: Recurse to the end of the array, storing values for previous segments of the original string in the cache map. If the end of the array is reached, that means it was a successful decode. 
        
        int n = s.length();
        
        // Base cases: if we reach the end of the string, that means the decoding is successful and there is an additional way that we just found to decode the string 
        if(index == n) {
            return 1;
        }
      // If at any point in time our CURRENT INDEX points at 0, (meaning that it is by itself or it is the leading zero of a double digit), we have to return 0 as 0 does not map to any character 
      // and we cannot have leading zeroes. 
        if(s.charAt(index) == '0') {
            return 0;
        }
        if(index == n-1) {
            return 1;
        }
    
        if(!cache.containsKey(index)) {
            // **** Already takes care of the case where a double digit cannot be mapped to a single character because if that was the case, only the single character digit version of that specific segment of the string will be checked, not the double digit segment anymore. 
            int temp = findAnswer(index+1, s);
            int integer = Integer.parseInt(s.substring(index, index+2));
            if(integer > 0 && integer < 27) {
                temp += findAnswer(index + 2, s);
            }
            
            cache.put(index, temp);
        }
    
        return cache.get(index);
    }
}
