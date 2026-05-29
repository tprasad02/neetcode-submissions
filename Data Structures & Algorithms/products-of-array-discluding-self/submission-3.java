class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Compute the product of all nonzero values
        // Zeros must be handled separately since they make the total product 0
        // O(n) time, O(n) space (for the output array)
        int product = nums[0];
        int numZeros = 0;
        int[] prods = new int[nums.length];
       
        // Stores the index of the zero if exactly one exists
        int zeroIdx = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                numZeros++;
                continue;
            }
            product *= nums[i];
        }
                
        // No zeros: divide total product by each element
        if (numZeros == 0) {
            for (int i = 0; i < prods.length; i++) {
                prods[i] = product / nums[i];
            }
        } 
        // Exactly one 0:
        // only the zero index gets the product of nonzero values
        else if (numZeros == 1){ 
            prods[zeroIdx] = product;
        }
        // Else if more than one zero, means every product is 0
        return prods;
    }
}