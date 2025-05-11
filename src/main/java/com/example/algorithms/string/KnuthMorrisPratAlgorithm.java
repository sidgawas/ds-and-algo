package com.example.algorithms.string;

public class KnuthMorrisPratAlgorithm {

    /**
     * Return an array of size str.length() which contains length of last prefix suffix at each position.
     * At each position i in str we find maximum length of prefix which is also a suffix. Prefix shouldn't be the
     * whole string uptil that point.
     */
    public int[] computeLPS(final String str) {
        var lps = new int[str.length()];
        int prevLPS = 0;
        int i = 1;
        while (i < lps.length) {
            if (str.charAt(i) == str.charAt(prevLPS)) {
                lps[i] = prevLPS + 1;
                prevLPS++;
                i++;
            } else if (prevLPS == 0) {
                // current position i does not have matching suffix hence the length of largest prefix which is also
                // suffix is 0
                lps[i] = 0;
                i++;
            } else {
                    prevLPS = lps[prevLPS - 1];
            }
        }
        return  lps;
    }

    /**
     * Finds first position in haystack where needle string occurs. If no match is found then return -1.
     */
    public int subStr(final String haystack, final String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        var lps = computeLPS(needle);
        int i = 0; // pointer for haystack
        int j = 0; // point for needle
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0) {
                    i+=1;
                } else {
                    j = lps[j - 1];
                }
            }

            if (j == needle.length()) {
                return i - needle.length();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final var haystack = "AAAXAAAA";
        final var needle = "AAAA";
        var kmp = new KnuthMorrisPratAlgorithm();
        System.out.println(kmp.subStr(haystack, needle));
    }
}
