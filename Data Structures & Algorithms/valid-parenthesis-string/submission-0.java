class Solution {
    public boolean checkValidString(String s) {
        // Time: O(n)
        // Space: O(n)
        
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // Store the index of every left parenthesis
            if (c == '(') {
                left.push(i);
            }
            // Store the index of every asterisk
            else if (c == '*') {
                star.push(i);
            }
            // Match every right parenthesis with a left parenthesis first,
            // otherwise use an asterisk as a left parenthesis
            else {
                if (!left.isEmpty()) {
                    left.pop();
                } else if (!star.isEmpty()) {
                    star.pop();
                } else {
                    return false;
                }
            }
        }
        // Match any remaining left parentheses with asterisks
        while (!left.isEmpty() && !star.isEmpty()) {
            // The asterisk must come after the left parenthesis
            if (left.peek() < star.peek()) {
                left.pop();
                star.pop();
            } else {
                return false;
            }
        }
        return left.isEmpty();
    }
}

