class Solution {
    public int evalRPN(String[] tokens) {
        Set<String> ops = new HashSet<>(4);
        int op1 = 0;
        int op2 = 0;
        ops.add("+");
        ops.add("-");
        ops.add("*");
        ops.add("/");
        int total = 0;
        Stack<Integer> nums = new Stack<>();
        for (String token : tokens){
            if (!(ops.contains(token))){
                nums.push(Integer.parseInt(token));
                total = Integer.parseInt(token);
            }
            else {
                if (token.equals("+")){
                    op2 = nums.pop();
                    op1 = nums.pop();
                    total = (op1 + op2);
                    nums.push(total);
                }
                else if (token.equals("-")){
                    op2 = nums.pop();
                    op1 = nums.pop();
                    total = (op1-op2);
                    nums.push(total);
                }
                else if (token.equals("*")){
                    op2 = nums.pop();
                    op1 = nums.pop();
                    total = (op1*op2);
                    nums.push(total);
                }
                else if (token.equals("/")){
                    op2 = nums.pop();
                    op1 = nums.pop();
                    total = (op1/op2);
                    nums.push(total);
                }
            }
        }
        return total;
    }
}
