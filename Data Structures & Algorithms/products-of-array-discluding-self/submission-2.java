class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Find product of all nums; then for each element,
        // divide the product by the element and add to a new array
        // 0 is a special case because 0 multiplication makes whole product 0
        int product = nums[0];
        int numZeros = 0;
        int[] prods = new int[nums.length];
        int idx = 0;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == 0){
                idx = i;
                numZeros++;
                continue;
            }
            product *= nums[i]; // product omitting 0
        }

        if (numZeros > 1) { // if more than one 0, all entries should be 0
            return prods;
        }
        else if (numZeros == 0){
            for (int i = 0; i < prods.length; i++){
                prods[i] = (product/nums[i]);
            }
        }
        else { 
        // only one 0, in which case prod is product omitting 0
        // and leave all other entries as 0;
            prods[idx] = product;
        }
        return prods;
    }
}  
