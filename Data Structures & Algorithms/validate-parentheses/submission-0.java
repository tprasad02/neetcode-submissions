public class Solution {
    public boolean isValid(String s) {
        // O(n) time, O(n) space
        Stack<Character> stack = new Stack<>();
        // Maps an opening bracket to the closing bracket
        // that should eventually match it
        Map<Character, Character> brackets = new HashMap<>();
        brackets.put('(', ')');
        brackets.put('[', ']');
        brackets.put('{', '}');

        for (char c : s.toCharArray()) {
            // If we see an opening bracket, save it for later
            if (brackets.containsKey(c)) {
                stack.push(c);
            }
            // Otherwise, we must have encountered a closing bracket
            else {
                // No opening bracket available to match it
                if (stack.isEmpty()) {
                    return false;
                }
                // Get the most recent unmatched opening bracket
                char open = stack.pop();

                // Check whether the current closing bracket 
                // is the one expected for this opening bracket
                if (brackets.get(open) != c) {
                    return false;
                }
            }
        }
        // If anything is left in the stack, some opening brackets were never closed
        return stack.isEmpty();
    }
}