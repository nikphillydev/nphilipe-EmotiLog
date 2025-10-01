package com.example.nphilipe_emotilog.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

/**
 * This class tracks daily {@link EmoticonDailyLogger} records, organizing emoticon usage by date.
 */
public class EmoticonLogger implements Serializable {
    private HashMap<LocalDate, EmoticonDailyLogger> logger;
    public EmoticonLogger() {
        logger = new HashMap<>();
    }
    public void recordEmoticon(Emoticon emoticon) {
        LocalDate today = LocalDate.now();
        if (logger.containsKey(today)) {
            EmoticonDailyLogger dailyLogger = logger.get(today);
            dailyLogger.recordEmoticon(emoticon);
        }
        else {
            EmoticonDailyLogger dailyLogger = new EmoticonDailyLogger();
            dailyLogger.recordEmoticon(emoticon);
            logger.put(today, dailyLogger);
        }
    }
    public void recordEmoticon(Emoticon emoticon, LocalDate date) {
        if (logger.containsKey(date)) {
            logger.get(date).recordEmoticon(emoticon);
        }
        else {
            EmoticonDailyLogger dailyLogger = new EmoticonDailyLogger();
            dailyLogger.recordEmoticon(emoticon);
            logger.put(date, dailyLogger);
        }
    }
    public ArrayList<LocalDate> getDatesAsArray() {
        ArrayList<LocalDate> dates = new ArrayList<>(logger.keySet());
        dates.sort(Comparator.naturalOrder());
        return dates;
    }
    public EmoticonDailyLogger getDailyLogger(LocalDate date) {
        EmoticonDailyLogger dailyLogger;
        if (logger.containsKey(date)) {
            dailyLogger = logger.get(date);
        }
        else {
            dailyLogger = new EmoticonDailyLogger();
        }
        return dailyLogger;
    }
}
