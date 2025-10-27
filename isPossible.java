class Solution {

    public boolean isPossible(int[] arr, int k) {
        // Code here
         Map<Integer, Integer> count = new HashMap<>();
        // Map to track subsequences ending at a certain number
        Map<Integer, Integer> end = new HashMap<>();

        // Build frequency map
        for (int num : arr) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // Iterate through array
        for (int num : arr) {
            if (count.get(num) == 0) continue; // already used in a subsequence

            // Use one occurrence of this number
            count.put(num, count.get(num) - 1);

            // If there’s a subsequence ending with num-1, extend it
            if (end.getOrDefault(num - 1, 0) > 0) {
                end.put(num - 1, end.get(num - 1) - 1);
                end.put(num, end.getOrDefault(num, 0) + 1);
            }
            // Else, try to create a new subsequence starting from num
            else {
                // Check if we can form a subsequence of length at least k
                boolean canForm = true;
                for (int i = 1; i < k; i++) {
                    if (count.getOrDefault(num + i, 0) <= 0) {
                        canForm = false;
                        break;
                    }
                }

                if (!canForm) return false;

                // Deduct counts for the next (k-1) numbers
                for (int i = 1; i < k; i++) {
                    count.put(num + i, count.get(num + i) - 1);
                }

                // Mark that a subsequence now ends at num + k - 1
                end.put(num + k - 1, end.getOrDefault(num + k - 1, 0) + 1);
            }
        }

        return true;
    }
}
