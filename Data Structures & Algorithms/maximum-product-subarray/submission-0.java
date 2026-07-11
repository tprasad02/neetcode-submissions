class Solution {
    public int maxProduct(int[] nums) {
        // O(n) time, O(1) space
        // maxProd[i] = maximum product subarray that ends at index i
        // minProd[i] = minimum product subarray that ends at index i
        
        // We keep both because a negative product can become the
        // largest product if we multiply by another negative number
        int maxProd = nums[0];
        int minProd = nums[0];

        // Store the best product found so far
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int prevMax = maxProd;
            int prevMin = minProd;

            // Three possibilities:
            // 1. Start a new subarray at curr
            // 2. Extend the previous maximum product
            // 3. Extend the previous minimum product
            // The min can become the max if curr is negative
            maxProd = Math.max(curr,
                        Math.max(curr * prevMax, curr * prevMin));

            // Same choices, but we keep the smallest product
            // This is important because a very negative number
            // might become a very large positive number later
            minProd = Math.min(curr,
                        Math.min(curr * prevMax, curr * prevMin));

            result = Math.max(result, maxProd);
        }
        return result;
    }
}