import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 763. Partition Labels
 * Link: https://leetcode.com/problems/partition-labels/description/
 */
//------------------------------------ Solution 1 -----------------------------------
public class PartitionLabels {
    /**
     * Greedy Solution - To maximize number of partitions, we need it to make each one as small as possible while
     * accommodating all chars within the same partition. To achieve this, we need to know the lastIndex of each character
     * in the string, we start with a potential partition with a start and end index, within that range if any other
     * char has its last index beyond the current end we extend our end to that bigger index, if our iteration (i) reaches
     * the end we successfully created a smallest possible partition with given constraints and store the length in result
     * and we process to find another potential partition.
     *
     * TC : O(n)
     * SC : O(1) -> only 26 lower case alphabets
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.isEmpty()) {
            return result;
        }

        int[] partitionMap = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            partitionMap[c - 'a'] = i;
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            end = Math.max(end, partitionMap[c - 'a']);

            if (i == end) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }

        return result;
    }
}

//------------------------------------ Solution 2 -----------------------------------
class PartitionLabels2 {
    /**
     * Slight improvement on above solution by existing early if during processing our end points to the last index of the
     * string as no other unprocessed characters can have its last index beyond it.
     *
     * TC: O(n)
     * SC: O(1) -> only 26 lower case alphabets
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.isEmpty()) {
            return result;
        }

        int[] partitionMap = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            partitionMap[c - 'a'] = i;
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            end = Math.max(end, partitionMap[c - 'a']);

            if (i == end || end == s.length() - 1) {
                result.add(end - start + 1);
                start = end + 1;
            }

            //if at any point end reaches last idx in string, no need to process further
            if (end == s.length() - 1) {
                return result;
            }
        }

        return result;
    }
}