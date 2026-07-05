class Solution {
    public boolean isHappy(int n) {
        int num = n;
        int sum;
        // If the same sum is encountered twice, there exists a cycle
        Set<Integer> visited = new HashSet<>();
        while (num != 1) {
            sum = 0;
            while (num != 0) {
                sum += (int) Math.pow((num % 10), 2); // extract rightmost digit
                num = num / 10; // remove rightmost digit
            }
            if (visited.contains(sum)) {
                return false;
            } else {
                visited.add(sum);
            }
            num = sum;
        }
        return true;
    }
}