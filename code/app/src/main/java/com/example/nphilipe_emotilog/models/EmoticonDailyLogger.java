package com.example.nphilipe_emotilog.models;

import android.util.Pair;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class tracks {@link Emoticon} usage using {@link EmoticonCounter} and {@link EmoticonHistory} to record counts and timestamps.
 */
public class EmoticonDailyLogger {
    private EmoticonCounter counter;
    private EmoticonHistory history;
    public EmoticonDailyLogger() {
        this.counter = new EmoticonCounter();
        this.history = new EmoticonHistory();
    }
    public void recordEmoticon(Emoticon emoticon) {
        counter.addCount(emoticon);
        history.record(emoticon);
    }
    public Integer getCount(Emoticon emoticon) {
        return counter.getCount(emoticon);
    }
    public Integer getTotalCount() {
        return counter.getTotalCount();
    }
    public ArrayList<Map.Entry<Emoticon, Integer>> getCountAsArray() {
        return counter.getCountAsArray();
    }
    public ArrayList<Pair<Emoticon, LocalDateTime>> getHistoryAsArray() {
        return history.getHistoryAsArray();
    }
}
