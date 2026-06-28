class Solution {
    public List<String> letterCombinations(String digits) {
        // Stores all possible letter combinations
        List<String> res = new ArrayList<>();

        // Edge case: no digits means no combinations
        if (digits.length() == 0) {
            return res;
        }
        
        // Maps each digit to its corresponding letters
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        // Build the current combination one letter at a time
        dfs(digits, 0, new StringBuilder(), map, res);
        return res;
    }

    private void dfs(String digits, int i,
                     StringBuilder cur,
                     Map<Character, String> map,
                     List<String> res) {
        // Invariant:
        // We've already chosen letters for digits[0...i-1], so cur has length i.
        // This call is responsible for choosing ONE letter for digits[i].
        //
        // Example for digits = "23":
        //
        //          ""
        //        /  |  \
        //       a   b   c      <- choose a letter for '2'
        //      /|\ /|\ /|\
        //    ad ae af ...      <- choose a letter for '3'
        //
        // Every level of the recursion tree chooses the letter for one digit.

        // Base case: we've chosen a letter for every digit
        if (i == digits.length()) {
            res.add(cur.toString());
            return;
        }

        // Get all possible letters for the current digit
        String letters = map.get(digits.charAt(i));
        // Try each possible letter
        for (int j = 0; j < letters.length(); j++) {
            // Choose the current letter
            cur.append(letters.charAt(j));
            // Choose a letter for the next digit
            dfs(digits, i + 1, cur, map, res);
            // Backtrack and try the next possible letter
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}