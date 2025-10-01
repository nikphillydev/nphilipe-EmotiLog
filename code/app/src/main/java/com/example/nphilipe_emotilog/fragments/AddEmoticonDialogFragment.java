package com.example.nphilipe_emotilog.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.nphilipe_emotilog.models.Emoticon;
import com.example.nphilipe_emotilog.adapters.EmoticonArrayAdapter;
import com.example.nphilipe_emotilog.R;

import java.util.ArrayList;

/**
 * DialogFragment that displays available emoticons in a grid and returns the selected emoticon to a listener.
 */
public class AddEmoticonDialogFragment extends DialogFragment {
    public interface AddEmoticonDialogListener {
        void addEmoticon(Emoticon emoticon);
    }
    private AddEmoticonDialogListener listener;
    private ArrayList<Emoticon> emoticonArrayList;
    private EmoticonArrayAdapter emoticonArrayAdapter;
    private GridView addEmoticonGridView;

    public static AddEmoticonDialogFragment newInstance(ArrayList<Emoticon> availableEmoticons) {
        AddEmoticonDialogFragment fragment = new AddEmoticonDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("availableEmoticons", availableEmoticons);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddEmoticonDialogListener) {
            listener = (AddEmoticonDialogListener) context;
        }
        else {
            throw new RuntimeException(context + " must implement AddEmoticonDialogListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_emoticon_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            emoticonArrayList = (ArrayList<Emoticon>) args.getSerializable("availableEmoticons");
        }
        else {
            emoticonArrayList = new ArrayList<Emoticon>();
        }

        addEmoticonGridView = view.findViewById(R.id.add_emoticon_gridview);

        emoticonArrayAdapter = new EmoticonArrayAdapter(getContext(), emoticonArrayList);
        addEmoticonGridView.setAdapter(emoticonArrayAdapter);
        addEmoticonGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Emoticon clicked = (Emoticon) parent.getItemAtPosition(position);
                listener.addEmoticon(clicked);
                dismiss();
            }
        });
    }
}