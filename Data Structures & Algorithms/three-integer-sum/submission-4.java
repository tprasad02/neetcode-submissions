class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // For each num in nums, do 2-sum
        // Sort so that we can use 2 pointers easily
        // O(n^2) time, O(1) space (not including output array)

        List<List<Integer>> res = new ArrayList<>();
        
        Arrays.sort(nums);
        int diff, j, k, sum;

        for (int i = 0; i < nums.length; i++){
            diff = 0 - nums[i];
            j = i+1;
            k = nums.length-1;
            if ((i > 0) && (nums[i] == nums[i-1])) {
                continue;
            }
            for (int idx = 0; (j < k); idx++){
                sum = nums[j] + nums[k];
                if ((i == j)) {
                    j++;
                    continue;
                }
                if (i == k){
                    k--;
                    continue;
                }
                if (sum < diff) {
                    j++;
                }
                else if (sum > diff) {
                    k--;
                }
                else if (sum == diff) {
                    List<Integer> myList = new ArrayList<>();
                    myList.add(nums[i]);
                    myList.add(nums[j]);
                    myList.add(nums[k]);
                    res.add(myList);
                    j++;
                    k--;

                    // skip duplicates
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            } 
        }
        return res;
    }
}
