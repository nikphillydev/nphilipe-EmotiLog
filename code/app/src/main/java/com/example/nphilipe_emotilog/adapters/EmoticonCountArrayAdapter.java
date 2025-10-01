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

import com.example.nphilipe_emotilog.models.Emoticon;
import com.example.nphilipe_emotilog.R;

import java.util.ArrayList;
import java.util.Map;

public class EmoticonCountArrayAdapter extends ArrayAdapter<Map.Entry<Emoticon, Integer>> {
    public EmoticonCountArrayAdapter(Context context, ArrayList<Map.Entry<Emoticon, Integer>> frequencyMap) {
        super(context, 0, frequencyMap);
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
        Map.Entry<Emoticon, Integer> entry = getItem(position);
        Emoticon emoticon = entry.getKey();
        Integer frequency = entry.getValue();
        ImageView listEmoticonImageView = listEmoticonView.findViewById(R.id.list_emoticon_imageview);
        listEmoticonImageView.setImageDrawable(emoticon.getImage());
        TextView listEmoticonTextView = listEmoticonView.findViewById(R.id.list_emoticon_textview);
        listEmoticonTextView.setText(frequency.toString());
        return listEmoticonView;
    }
}
