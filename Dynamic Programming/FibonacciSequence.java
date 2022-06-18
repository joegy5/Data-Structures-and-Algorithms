// *** PROBLEM DESCRIPTION ***
// The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, 
// such that each number is the sum of the two preceding ones, starting from 0 and 1. 
// Given n, calculate F(n)


// *** SOLUTION 1 (TOP-DOWN APPROACH) ***
// 
// TIME COMPLEXITY: O(n)
// SPACE COMPLEXITY: O(n)

class Solution {
    Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
    
    public int fib(int n) {
      // BASE CASES: These can be solved without using any dynamic programming, and the rest of the subproblems build from these base cases   
      if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
    
        if(!cache.containsKey(n)) {
          // Cache map is used to memoize results to avoid unnecessary recalculations (to avoid exponential time complexity: 2^n)  
          // RECURRENCE RELATION: since the fibonacci sequence is just adding the two previous numbers, we can add the answers from the last two subproblems to get the answer to the current subproblem
          cache.put(n, fib(n-1) + fib(n-2));
        }
    
        return cache.get(n);
    }
}

// *** SOLUTION 2 (BOTTOM-UP APPROACH) *** 
//
// TIME COMPLEXITY: O(n)
// SPACE COMPLEXITY: O(n)

class Solution {

    public int fib(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
    
        return dp[dp.length - 1];
    }
}

// *** SOLUTION 3 (SPACE-OPTIMIZED BOTTOM-UP APPROACH)

// TIME COMPLEXITY: O(n)
// SPACE COMPLEXITY: O(1)

// INTUITION: 
// At any point in time, we just need the latest and second-latest subproblem solutions. Instead of using an entire array or hashmap, we can reduce our storage to just two pointers,
// reducing the space complexity to O(1). 

class Solution {

    public int fib(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        
        int first = 0;
        int second = 1;
        
        for(int i = 2; i <= n; i++) {
            int temp = second;
            second = first + second;
            first = temp;
        }
    
        return second;
    }
}













