class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Compute the product of all nonzero values
        // Zeros must be handled separately since they make the total product 0
        // O(n) time, O(1) space (for the output array)
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

// // Solution 2 (more robust than division): prefix and postfix
// // Same complexity (O(n) time, O(1) space)
// public class Solution {
//     public int[] productExceptSelf(int[] nums) {
//         int n = nums.length;
//         int[] res = new int[n];

//         // Prefix pass:
//         // res[i] = product of all elements to the left of i
//         // Avoids division → no zero edge cases, cleaner logic, same O(n)
//         res[0] = 1;
//         for (int i = 1; i < n; i++) {
//             res[i] = res[i - 1] * nums[i - 1];
//         }

//         // Postfix pass:
//         // Multiply right-side product into res[i]
//         int postfix = 1;

//         for (int i = n - 1; i >= 0; i--) {
//             res[i] *= postfix;
//             postfix *= nums[i];
//         }

//         return res;
//     }
// }
