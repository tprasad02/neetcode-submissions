public class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        // Sliding window and two pointers do not work because each bar's
        // largest rectangle depends on the first shorter bar on both sides
        // A monotonic stack finds these boundaries in O(n)

        // Monotonic increasing stack of bar indices
        Stack<Integer> stack = new Stack<>();

        // Traverse all bars, plus one extra iteration to process
        // any remaining bars in the stack
        for (int i = 0; i <= n; i++) {

            // A shorter bar marks the right boundary for taller bars
            while (!stack.isEmpty() &&
                   (i == n || heights[stack.peek()] >= heights[i])) {
                int height = heights[stack.pop()];
                // Width is between the nearest smaller bars on both sides
                int width;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }
                maxArea = Math.max(maxArea, height * width);
            }
            // Maintain increasing bar heights in the stack
            stack.push(i);
        }
        return maxArea;
    }
}