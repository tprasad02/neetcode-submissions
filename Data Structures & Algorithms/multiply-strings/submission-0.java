class Solution {
    public String multiply(String num1, String num2) {
        // Time: O(m * n)
        // Space: O(m + n)
        
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        int[] result = new int[m + n];

        // Decompose:
        // e.g. 123 × 456 = 123 × (400 + 50 + 6)
        // Accumulate each digit product into its place value
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int ones = i + j + 1;
                int tens = i + j;
                int sum = product + result[ones];
                result[ones] = sum % 10;
                result[tens] += sum / 10;
            }
        }
        // Build answer, skipping leading zeros
        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            if (!(sb.length() == 0 && digit == 0)) {
                sb.append(digit);
            }
        }

        return sb.toString();
    }
}