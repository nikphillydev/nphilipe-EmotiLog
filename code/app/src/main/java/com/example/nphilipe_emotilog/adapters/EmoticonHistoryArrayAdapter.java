package com.example.nphilipe_emotilog.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nphilipe_emotilog.models.Emoticon;
import com.example.nphilipe_emotilog.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmoticonHistoryArrayAdapter extends ArrayAdapter<Pair<Emoticon, LocalDateTime>> {
    public EmoticonHistoryArrayAdapter(Context context, ArrayList<Pair<Emoticon, LocalDateTime>> history) {
        super(context, 0, history);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listEmoticonView;
        if (convertView == null) {
            listEmoticonView = LayoutInflater.from(getContext()).inflate(R.layout.list_emoticon_view, parent, false);
        }
        else {
            listEmoticonView = convertView;
        }
        Pair<Emoticon, LocalDateTime> pair = getItem(position);
        Emoticon emoticon = pair.first;
        LocalDateTime dateTime = pair.second;
        ImageView listEmoticonImageView = listEmoticonView.findViewById(R.id.list_emoticon_imageview);
        listEmoticonImageView.setImageDrawable(emoticon.getImage());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String readableDateTime = dateTime.format(formatter);
        TextView listEmoticonTextView = listEmoticonView.findViewById(R.id.list_emoticon_textview);
        listEmoticonTextView.setText(readableDateTime);
        return listEmoticonView;
    }
}
