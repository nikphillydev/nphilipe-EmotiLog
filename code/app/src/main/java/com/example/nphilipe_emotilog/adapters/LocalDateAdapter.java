package com.example.nphilipe_emotilog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nphilipe_emotilog.R;
import com.example.nphilipe_emotilog.models.Emoticon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LocalDateAdapter extends ArrayAdapter<LocalDate> {
    public LocalDateAdapter(Context context, ArrayList<LocalDate> dates) {
        super(context, 0, dates);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View localDateView;
        if (convertView == null) {
            localDateView = LayoutInflater.from(getContext()).inflate(R.layout.local_date_view, parent, false);
        }
        else {
            localDateView = convertView;
        }
        LocalDate date = getItem(position);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        String formattedDate = date.format(formatter);
        TextView dateTextView = localDateView.findViewById(R.id.date_textview);
        dateTextView.setText(formattedDate);
        return localDateView;
    }

    @NonNull
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
