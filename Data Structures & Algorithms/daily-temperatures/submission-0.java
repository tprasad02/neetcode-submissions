public class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];

        // Stores unresolved days as [temperature, index]
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            int t = temperatures[i];
            // Current day resolves all previous colder days
            while (!stack.isEmpty() && t > stack.peek()[0]) {
                int[] pair = stack.pop();
                // Number of days until a warmer temperature
                res[pair[1]] = i - pair[1];
            }
            // This day is now waiting for a warmer future day
            stack.push(new int[]{t, i});
        }
        return res;
    }
}