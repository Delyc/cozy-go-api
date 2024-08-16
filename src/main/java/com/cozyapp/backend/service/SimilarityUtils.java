package com.cozyapp.backend.service;

import org.apache.commons.text.similarity.CosineSimilarity;

import java.util.HashMap;
import java.util.Map;

public class SimilarityUtils {

    public static double computeCosineSimilarity(String text1, String text2) {
        CosineSimilarity cosineSimilarity = new CosineSimilarity();

        Map<CharSequence, Integer> vectorA = getVector(text1);
        Map<CharSequence, Integer> vectorB = getVector(text2);

        return cosineSimilarity.cosineSimilarity(vectorA, vectorB);
    }

    private static Map<CharSequence, Integer> getVector(String text) {
        Map<CharSequence, Integer> vector = new HashMap<>();
        for (String word : text.toLowerCase().split(" ")) {
            vector.put(word, vector.getOrDefault(word, 0) + 1);
        }
        return vector;
    }
}