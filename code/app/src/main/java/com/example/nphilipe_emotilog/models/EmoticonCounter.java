package com.example.nphilipe_emotilog.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class tracks and manages the frequency of unique {@link Emoticon} objects.
 */
public class EmoticonCounter {
    private HashMap<Emoticon, Integer> frequencyMap;
    public EmoticonCounter() {
        this.frequencyMap = new HashMap<>();
    }
    public void addCount(Emoticon emoticon) {
        Integer count = 0;
        if (frequencyMap.containsKey(emoticon)) {
            count = frequencyMap.get(emoticon);
        }
        frequencyMap.put(emoticon, count + 1);
    }
    public Integer getCount(Emoticon emoticon) {
        Integer count = 0;
        if (frequencyMap.containsKey(emoticon)) {
            count = frequencyMap.get(emoticon);
        }
        return count;
    }
    public Integer getTotalCount() {
        int totalCount = 0;
        for (Map.Entry<Emoticon, Integer> entry : frequencyMap.entrySet()) {
            totalCount = totalCount + entry.getValue();
        }
        return totalCount;
    }
    public ArrayList<Map.Entry<Emoticon, Integer>> getCountAsArray() {
        return new ArrayList<>(frequencyMap.entrySet());
    }
}
