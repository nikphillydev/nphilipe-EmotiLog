package com.example.nphilipe_emotilog.models;

import android.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class tracks a timestamped history of {@link Emoticon} records.
 */
public class EmoticonHistory {
    private ArrayList<Pair<Emoticon, LocalDateTime>> history;
    public EmoticonHistory() {
        history = new ArrayList<>();
    }
    public void record(Emoticon emoticon) {
        history.add(new Pair<>(emoticon, LocalDateTime.now()));
    }
    public ArrayList<Pair<Emoticon, LocalDateTime>> getHistoryAsArray() {
        return history;
    }
}
