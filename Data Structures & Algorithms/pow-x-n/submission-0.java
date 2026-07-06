class Solution {
    public double myPow(double x, int n) {
        // Convert to long to safely handle Integer.MIN_VALUE
        long power = n;
        // Handle negative exponents
        if (power < 0) {
            x = 1 / x;
            power = -power;
        }
        double ans = 1.0;
        while (power > 0) {
            // If the current exponent is odd,
            // include one copy of the current base
            if (power % 2 == 1) {
                ans *= x;
            }
            // Square the base
            x *= x;
            // Halve the exponent
            power /= 2;
        }
        return ans;
    }
}