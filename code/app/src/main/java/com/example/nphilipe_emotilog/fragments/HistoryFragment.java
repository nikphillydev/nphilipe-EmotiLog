package com.example.nphilipe_emotilog.fragments;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nphilipe_emotilog.adapters.EmoticonCountArrayAdapter;
import com.example.nphilipe_emotilog.adapters.EmoticonHistoryArrayAdapter;
import com.example.nphilipe_emotilog.adapters.LocalDateAdapter;
import com.example.nphilipe_emotilog.models.Emoticon;
import com.example.nphilipe_emotilog.models.EmoticonDailyLogger;
import com.example.nphilipe_emotilog.R;
import com.example.nphilipe_emotilog.models.EmoticonLogger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

/**
 * Fragment that displays logged emoticon history by date, allowing users to view timestamped records for each day.
 */
public class HistoryFragment extends Fragment {
    private ImageButton historyBackButton;
    private Spinner historyDateSpinner;
    private ListView historyListView;
    private EmoticonLogger logger;
    private LocalDateAdapter localDateAdapter;
    private EmoticonHistoryArrayAdapter historyArrayAdapter;


    public static HistoryFragment newInstance(EmoticonLogger logger) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putSerializable("logger", logger);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            logger = (EmoticonLogger) args.getSerializable("logger");
        }
        else {
            logger = new EmoticonLogger();
        }

        historyBackButton = view.findViewById(R.id.history_back_button);
        historyDateSpinner = view.findViewById(R.id.history_date_spinner);
        historyListView = view.findViewById(R.id.history_listview);

        historyBackButton.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });

        ArrayList<LocalDate> availableDates = logger.getDatesAsArray();
        localDateAdapter = new LocalDateAdapter(getContext(), availableDates);
        historyDateSpinner.setAdapter(localDateAdapter);
        historyDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LocalDate selectedDate = (LocalDate) parent.getItemAtPosition(position);
                updateOnSelectedDate(selectedDate);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        if (!availableDates.isEmpty()) {
            historyDateSpinner.setSelection(availableDates.size() - 1);
        }
    }

    /**
     *  Update the fragment based on the selected date.
     */
    public void updateOnSelectedDate(LocalDate selectedDate) {
        EmoticonDailyLogger dailyLogger = logger.getDailyLogger(selectedDate);

        ArrayList<Pair<Emoticon, LocalDateTime>> historyArray = dailyLogger.getHistoryAsArray();
        historyArrayAdapter = new EmoticonHistoryArrayAdapter(getContext(), historyArray);
        historyListView.setAdapter(historyArrayAdapter);
    }
}
