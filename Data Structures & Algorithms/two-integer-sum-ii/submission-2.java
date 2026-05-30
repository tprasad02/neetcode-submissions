class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int i = 0;
        int j = numbers.length-1;
        int sum = 0;
        for (int idx = 0; (i < j) && (i < numbers.length) && (j > 0); idx++){ // pointers should not cross
            sum = numbers[i] + numbers[j];
            if (sum < target) {
                i++;
            }
            else if (sum > target){
                j--;
            }
            else { // sum = target
                res[0] = i+1; // 1-based indexing
                res[1] = j+1;
                return res;
            }
        }
        return res;
    }
}
