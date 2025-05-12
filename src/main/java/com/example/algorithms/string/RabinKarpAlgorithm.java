package com.example.algorithms.string;

import java.util.ArrayList;

public class RabinKarpAlgorithm {
    private final int primeNumber = 101;

    public long computeHash(final String str) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = hash + str.charAt(i) * (long) Math.pow(primeNumber, i);
        }
        return hash;
    }

    public long rollingHash(final long currentHash, char oldChar, char newChar, int windowSize) {
        return (currentHash - oldChar) / primeNumber + newChar * (long) Math.pow(primeNumber, windowSize-1);
    }

    public ArrayList<Integer> findPattern(final String a, final String b) {
        int windowSize = b.length();
        ArrayList<Integer> locations = new ArrayList<Integer>();
        long windowHashOfA = computeHash(a.substring(0, windowSize));
        long windowHashOfB = computeHash(b);
        for (int i = 0; i < a.length() - windowSize; i++) {
            if (windowHashOfA == windowHashOfB) {
                locations.add(i);
            }
            if (i < a.length() - windowSize) {
                windowHashOfA = rollingHash(windowHashOfA, a.charAt(i), a.charAt(i + windowSize ), windowSize);
            }
        }
        return locations;
    }

    public static void main(String[] args) {
        RabinKarpAlgorithm algorithm = new RabinKarpAlgorithm();
        var positions = algorithm.findPattern("Siddharth Gawas", "id");
        System.out.println(positions);
    }
}
