class Solution {
    public int[] plusOne(int[] digits) {
        // Start from the least significant digit (the end of the array)
        for (int i = digits.length - 1; i >= 0; i--) {
            // Add one to the current digit
            digits[i]++;
            // If the digit becomes 10, we need to carry the 1
            // to the digit on the left and reset this digit to 0
            if (digits[i] == 10) {
                digits[i] = 0;
            } else {
                // No carry is needed, so we're done
                return digits;
            }
        }
        // If we made it here, every digit was a 9
        // (e.g. 9 -> 10, 99 -> 100, 999 -> 1000)
        // so create a new array that is one digit longer
        int[] result = new int[digits.length + 1];
        // The leading digit is 1, and the rest are already 0 by default
        result[0] = 1;
        return result;
    }
}