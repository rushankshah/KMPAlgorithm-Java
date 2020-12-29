public class KMPAlgorithm {
    public static void main(String[] args) {
        String text = "AAAABBAAABBABAAAA";
        String pattern = "AAAA";

        int textLength = text.length();
        int patternLength = pattern.length();
        int[] longestProperPrefixSuffix = new int[patternLength];
        longestProperPrefixSuffix[0] = 0;
        int lengthOfPreviousLongestPrefixSuffix = 0;

        // Calculating the LPS Array

        for (int i = 1; i < patternLength; i++) {
            if (pattern.charAt(i) == pattern.charAt(lengthOfPreviousLongestPrefixSuffix)) {
                lengthOfPreviousLongestPrefixSuffix += 1;
                longestProperPrefixSuffix[i] = lengthOfPreviousLongestPrefixSuffix;
            } else {
                if (lengthOfPreviousLongestPrefixSuffix == 0) {
                    longestProperPrefixSuffix[i] = 0;
                } else {
                    // This is tricky. Consider the example "ababe......ababc", i is index of 'c',
                    // len==4. The longest prefix suffix is "abab",
                    // when pat[i]!=pat[len], we get new prefix "ababe" and suffix "ababc", which
                    // are not equal.
                    // This means we can't increment length of lps based on current lps "abab" with
                    // len==4. We may want to increment it based on
                    // the longest prefix suffix with length < len==4, which by definition is lps of
                    // "abab". So we set len to lps[len-1],
                    // which is 2, now the lps is "ab". Then check pat[i]==pat[len] again due to the
                    // while loop, which is also the reason
                    // why we do not increment i here. The iteration of i terminate until len==0
                    // (didn't find lps ends with pat[i]) or found
                    // a lps ends with pat[i].
                    lengthOfPreviousLongestPrefixSuffix = longestProperPrefixSuffix[lengthOfPreviousLongestPrefixSuffix
                            - 1];
                    i -= 1;
                }
            }
        }

        for (int i : longestProperPrefixSuffix) {
            System.out.println(i);
        }

        // Pattern Matching
        int i = 0, j = 0;
        while (i < textLength) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i += 1;
                j += 1;
            }

            if (j == patternLength) {
                System.out.println("Pattern is found at " + (i - j));
                j = longestProperPrefixSuffix[j - 1];
            } else if (i < textLength && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = longestProperPrefixSuffix[j - 1];
                } else {
                    i += 1;
                }
            }
        }
    }
}
