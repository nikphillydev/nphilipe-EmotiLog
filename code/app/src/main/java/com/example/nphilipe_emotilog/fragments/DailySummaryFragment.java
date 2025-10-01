package com.example.nphilipe_emotilog.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nphilipe_emotilog.adapters.LocalDateAdapter;
import com.example.nphilipe_emotilog.models.Emoticon;
import com.example.nphilipe_emotilog.adapters.EmoticonCountArrayAdapter;
import com.example.nphilipe_emotilog.models.EmoticonDailyLogger;
import com.example.nphilipe_emotilog.R;
import com.example.nphilipe_emotilog.models.EmoticonLogger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/**
 * Fragment that displays a daily summary of emoticon usage, showing total counts and per-emoticon breakdowns by date.
 */
public class DailySummaryFragment extends Fragment {
    private ImageButton summaryBackButton;
    private Spinner summaryDateSpinner;
    private ListView summaryListView;
    private TextView summaryTotalCountTextView;
    private EmoticonLogger logger;
    private LocalDateAdapter localDateAdapter;
    private EmoticonCountArrayAdapter countArrayAdapter;

    public static DailySummaryFragment newInstance(EmoticonLogger logger) {
        DailySummaryFragment fragment = new DailySummaryFragment();
        Bundle args = new Bundle();
        args.putSerializable("logger", logger);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daily_summary_fragment, container, false);
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

        summaryBackButton = view.findViewById(R.id.summary_back_button);
        summaryDateSpinner = view.findViewById(R.id.summary_date_spinner);
        summaryListView = view.findViewById(R.id.summary_listview);
        summaryTotalCountTextView = view.findViewById(R.id.summary_total_count_textview);

        summaryBackButton.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });

        ArrayList<LocalDate> availableDates = logger.getDatesAsArray();
        localDateAdapter = new LocalDateAdapter(getContext(), availableDates);
        summaryDateSpinner.setAdapter(localDateAdapter);
        summaryDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            summaryDateSpinner.setSelection(availableDates.size() - 1);
        }
    }

    /**
     *  Update the fragment based on the selected date.
     */
    public void updateOnSelectedDate(LocalDate selectedDate) {
        EmoticonDailyLogger dailyLogger = logger.getDailyLogger(selectedDate);

        summaryTotalCountTextView.setText(dailyLogger.getTotalCount().toString());

        ArrayList<Map.Entry<Emoticon, Integer>> countArray = dailyLogger.getCountAsArray();
        countArrayAdapter = new EmoticonCountArrayAdapter(getContext(), countArray);
        summaryListView.setAdapter(countArrayAdapter);
    }
}
