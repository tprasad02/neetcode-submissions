class Solution {
    public int[] twoSum(int[] nums, int target) {
        // // Brute force: check every pair of integers at i & j
        // // O(n^2) time, O(1) space
        // int[] answer = new int[2];
        // for (int i = 0; i < nums.length; i++){
        //     // Start from index i+1, so j != i
        //     for (int j = i+1; j < nums.length; j++){
        //         if ((nums[i] + nums[j]) == target){
        //             answer[0] = i;
        //             answer[1] = j;
        //         }
        //     }
        // }
        // return answer;

        // Solution 2: Use HashMap to check if difference exists (stored with its index)
        // Note that difference = target - nums[i]
        // If there is a match, difference should equal nums[j]
        Map<Integer, Integer> differences = new HashMap<>();
        int[] answer = new int[2];
        int j = 0;
        for (int i = 0; i < nums.length; i++){
            if (differences.containsKey(target-nums[i])){
                j = differences.get(target-nums[i]);
                if (i < j){
                    answer[0] = i;
                    answer[1] = j;
                }
                else {
                    answer[0] = j;
                    answer[1] = i;
                }
                return answer;
            }
            else {
                differences.put(nums[i], i);
            }
        }
        return answer;
    }
}
