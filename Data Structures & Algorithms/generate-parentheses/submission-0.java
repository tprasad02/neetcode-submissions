class Solution {
    public List<String> generateParenthesis(int n) {
        // Stores all valid parenthesis combinations
        List<String> res = new ArrayList<>();
        // Build the current string one character at a time
        dfs(0, 0, new StringBuilder(), res, n);
        return res;
    }

    private void dfs(int open, int close, StringBuilder cur,
                     List<String> res, int n) {
        // Base case: we've used all n opening and n closing parentheses
        if (cur.length() == 2 * n) {
            res.add(cur.toString());
            return;
        }
        // Invariant:
        // At every point in the recursion, close <= open
        // This means every ')' has a matching '(' before it,
        // so the current string is always a valid prefix
        // We never generate invalid strings and therefore prune
        // large parts of the decision tree

        // Choice 1: Add an opening parenthesis if we still have some left
        if (open < n) {
            cur.append('(');
            dfs(open + 1, close, cur, res, n);
            cur.deleteCharAt(cur.length() - 1); // backtracking operation
        }
        // Choice 2: Add a closing parenthesis only if there is
        // an unmatched opening parenthesis available to close
        if (close < open) {
            cur.append(')');
            dfs(open, close + 1, cur, res, n);
            cur.deleteCharAt(cur.length() - 1); // backtracking operation
        }
    }
}