// PROBLEM Description:

//Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

 

//Example 1:

//Input: s = "leetcode"
//Output: 0


//Example 2:

//Input: s = "loveleetcode"
//Output: 2


// Example 3:

//Input: s = "aabb"
// Output: -1

// INTUITION:
// We can use a bucket array of a fixed size 27 to keep track of how frequent different letters come up in the input string.
// We also will use a hash map to keep track of the index where each letter was seen first.

// We then loop through the string and populate the bucket array using the ascii values of each character that we encounter minus lowercase "a" as the index that points to how frequent the character appears in the array.
// We then loop through the bucket array and check if the frequency of a character is equal to one (meaning it does not repeat). If that is the case, we look to our hash map to 
// check the index of the place where the current character first appears in the array. We keep doing this and find the minumunm index of a character that is not repeating.

class Solution {
    public int firstUniqChar(String s) {
        int[] bucket = new int[27];
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for(int i = 0; i < s.length(); i++) {
            bucket[s.charAt(i) - 'a']++;
            if(!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < 27; i++) {
            if(bucket[i] == 1) {
                ans = Math.min(ans, map.get((char)(i + 'a')));
            }
        }
    
        return ans == Integer.MAX_VALUE ? -1: ans;
    }
}
