class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Brute force: check every pair of integers at i & j
        // O(n^2) time, O(1) space
        int[] answer = new int[2];
        for (int i = 0; i < nums.length; i++){
            // Start from index i+1, so j != i
            for (int j = i+1; j < nums.length; j++){
                if ((nums[i] + nums[j]) == target){
                    answer[0] = i;
                    answer[1] = j;
                }
            }
        }
        return answer;

        // Solution 2: 
    }
}
