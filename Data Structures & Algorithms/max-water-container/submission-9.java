class Solution {
    public int maxArea(int[] heights) {
        // Height of the container = min (bar1, bar2) x index of bar2 - index of bar1
        // O(n) time, O(1) space
        int i = 0;
        int j = heights.length-1;
        int maxHeight = 0;
        int currHeight;
        for (int idx = 0; (i < j) && (idx < heights.length); idx++){
            currHeight = ((Math.min(heights[i], heights[j])) * (j-i));
            if (currHeight > maxHeight){
                maxHeight = currHeight;
            }
            if (heights[i] <= heights[j]){
                i++;
            }
            else {
                j--;
            }
        }
        return maxHeight;    
    }
}
