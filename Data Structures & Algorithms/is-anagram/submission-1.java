class Solution {
    public boolean isAnagram(String s, String t) {
        // Set<String> notCommon = new HashSet();
        // // If not same length, immediately return false
        // if (!(s.length() == t.length())){
        //     return false;
        // }
        // // Create a HashSet containing all characters in the first string
        // for (int i = 0; i < s.length(); i++){
        //     String curr = s.substring(i, i+1);
        //     notCommon.add(curr);
        // }
        // // Check one by one if each character in second string is in the HashSet
        // // i.e. first string contains it
        // // If so, remove it, leaving only chars that have not been checked yet
        // // If all are same, HashSet should have length 0 at the end, return true
        // for (int j = 0; j < t.length(); j++){
        //     String curr = t.substring(j, j+1);
        //     if (notCommon.contains(curr)){
        //         notCommon.remove(curr);
        //     }
        // }
        // if (notCommon.size() == 0){
        //     return true;
        // }
        // return false;

        String[] sArr = s.split("");
        String[] tArr = t.split("");
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return Arrays.equals(sArr, tArr);
    }
}
