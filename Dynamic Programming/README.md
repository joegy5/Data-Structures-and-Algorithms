DYNAMIC PROGRAMMING 

 - Dynamic programming (DP) can be an extremely powerful tool for solving complex problems.
 - It splits one complex problem into subproblems that can then be individually solved, with their solutions being used to contribute to the final answer of the original problem. 
 - Characteristics of a DP problem may include:
    - Trying to find the maximum or minimum of something 
    - Trying to find the number of ways to do something 
    - Subproblems OVERLAP (meaning that a solution to one subproblem helps solve another subproblem)
    - The problem has OPTIMAL SUBSTRUCTURE (optimal solutions from subproblems can be used to find the optimal solution to the original problem

 - HOW TO SOLVE A DP PROBLEM:
    1. Define the state variables (the variables that will define what state you are in, like where you are in an array)
    2. Find a data structure or function that can compute answers to subproblems and store those answers for future use (when solving other subproblems)
    3. Find the RECURRENCE RELATION
        - Find how to relate one state to the other (the way to move from one subproblem to the other)
        - This is often the most difficult part of solving the problem.
    4. Find the BASE CASES:
        - Use logical thinking to figure the subproblems that can be solved without using dynamic programming. Solutions to the rest of the subproblems will often build           on these base cases  

    5. OPTIONAL:
        - Find the top-down approach (the recursive solution, where we start with the original problem (the top) and recurse all the way down to the base cases)
        - Use the top-down approach to implement the bottom-up approach (the iterative solution, where we oftens start with the bases cases and build up all the way to             the original problem)


SOURCE:
LeetCode's Dynamic Programming Explore Card (https://leetcode.com/explore/learn/card/dynamic-programming/630/an-introduction-to-dynamic-programming/)
