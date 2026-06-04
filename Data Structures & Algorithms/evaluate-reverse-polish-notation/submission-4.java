class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            
            // If the token is a number, push it onto the stack
            if (!token.equals("+") &&
                !token.equals("-") &&
                !token.equals("*") &&
                !token.equals("/")) {
                stack.push(Integer.parseInt(token));
            
            } else {
                // Pop second operand first because
                // it appears later in the expression
                int op2 = stack.pop();
                int op1 = stack.pop();
                
                // Evaluate the operation and push the result
                if (token.equals("+")) {
                    stack.push(op1 + op2);
                } else if (token.equals("-")) {
                    stack.push(op1 - op2);
                } else if (token.equals("*")) {
                    stack.push(op1 * op2);
                } else {
                    stack.push(op1 / op2);
                }
            }
        }
        return stack.pop();
    }
}