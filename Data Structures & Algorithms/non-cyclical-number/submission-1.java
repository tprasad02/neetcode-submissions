class Solution {
    public boolean isHappy(int n) {
        int num = n;
        int sum;
        // Stores every intermediate sum we've seen
        // If a sum repeats, the process has entered a cycle, never reaches 1
        Set<Integer> visited = new HashSet<>();
        while (num != 1) {
            sum = 0;
            while (num != 0) {
                int digit = num % 10; // extract the rightmost digit
                sum += digit * digit; // add its square to the running sum
                num /= 10; // remove the rightmost digit
            }
            if (visited.contains(sum)) {
                return false;
            }
            visited.add(sum);
            num = sum;
        }
        return true;
    }
}