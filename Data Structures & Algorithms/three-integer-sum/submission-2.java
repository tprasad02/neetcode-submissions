class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // For each num in nums, do 2-sum
        // Sort so that we can use 2 pointers easily

        List<List<Integer>> res = new ArrayList<>();
        
        int[] numsCopy = nums.clone();
        Arrays.sort(numsCopy);
        int diff, j, k, sum;

        for (int i = 0; i < numsCopy.length; i++){
            diff = 0 - numsCopy[i];
            j = i+1; // to avoid duplicate triples
            k = numsCopy.length-1;
            if ((i > 0) && (numsCopy[i] == numsCopy[i-1])) {
                continue;
            }
            for (int idx = 0; (j < k); idx++){
                sum = numsCopy[j] + numsCopy[k];
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
                    myList.add(numsCopy[i]);
                    myList.add(numsCopy[j]);
                    myList.add(numsCopy[k]);
                    res.add(myList);
                    j++;
                    k--;

                    // skip duplicates
                    while (j < k && numsCopy[j] == numsCopy[j - 1]) j++;
                    while (j < k && numsCopy[k] == numsCopy[k + 1]) k--;
                }
            } 
        }
        return res;
    }
}
