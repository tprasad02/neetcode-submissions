class Solution {
    public boolean hasDuplicate(int[] nums) {
        // Brute force: O(n^2) time, O(1) space
        // for (int i = 0; i < nums.length; i++){
        //     for (int j = i + 1; j < nums.length; j++){
        //         if (nums[i] == nums[j]) {
        //             return true;
        //         }
        //     }
        // }
        // return false;

        // Optimized solution: use HashSet
        // O(n) time, O(n) space
        Set<Integer> seen = new HashSet();
        for (int num: nums){
            if (seen.contains(num)){
                return true;
            }
            else{
                seen.add(num);
            }
        }
        return false;
    }
}