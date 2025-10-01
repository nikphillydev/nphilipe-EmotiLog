package com.example.nphilipe_emotilog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nphilipe_emotilog.models.Emoticon;
import com.example.nphilipe_emotilog.R;

import java.util.ArrayList;

public class EmoticonArrayAdapter extends ArrayAdapter<Emoticon> {
    public EmoticonArrayAdapter(Context context, ArrayList<Emoticon> emoticons) {
        super(context, 0, emoticons);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View addEmoticonView;
        if (convertView == null) {
            addEmoticonView = LayoutInflater.from(getContext()).inflate(R.layout.add_emoticon_view, parent, false);
        }
        else {
            addEmoticonView = convertView;
        }
        Emoticon emoticon = getItem(position);
        ImageView addEmoticonImageView = addEmoticonView.findViewById(R.id.add_emoticon_imageview);
        addEmoticonImageView.setImageDrawable(emoticon.getImage());
        return addEmoticonView;
    }
}
