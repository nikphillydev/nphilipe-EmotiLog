package com.example.nphilipe_emotilog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nphilipe_emotilog.fragments.AddEmoticonDialogFragment;
import com.example.nphilipe_emotilog.fragments.DailySummaryFragment;
import com.example.nphilipe_emotilog.fragments.HistoryFragment;
import com.example.nphilipe_emotilog.models.Emoticon;
import com.example.nphilipe_emotilog.models.EmoticonLogger;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddEmoticonDialogFragment.AddEmoticonDialogListener {
    private int numGridRows = 0;
    private int numGridCol = 0;
    private int selectedRow = 0;
    private int selectedCol = 0;
    private Emoticon[][] emoticonGridArray;
    private GridLayout emoticonGridlayout;
    private Button editButton;
    private Button confirmButton;
    private Button dailySummaryButton;
    private Button historyButton;
    private TextView mainMessageTextview;
    private boolean editMode = false;
    private ArrayList<Emoticon> availableEmoticonArrayList;
    private EmoticonLogger logger;

    /**
     * Initializes the activity, sets up UI components, and configures listeners.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find views
        emoticonGridlayout = findViewById(R.id.emoticon_gridlayout);
        editButton = findViewById(R.id.edit_button);
        confirmButton = findViewById(R.id.confirm_button);
        dailySummaryButton = findViewById(R.id.daily_summary_button);
        historyButton = findViewById(R.id.history_button);
        mainMessageTextview = findViewById(R.id.main_message_textview);

        // Initialize emoticon 2D array
        numGridRows = emoticonGridlayout.getRowCount();
        numGridCol = emoticonGridlayout.getColumnCount();
        emoticonGridArray = new Emoticon[numGridRows][numGridCol];
        for (int i = 0; i < numGridRows; i++) {
            for (int j = 0; j < numGridCol; j++) {
                emoticonGridArray[i][j] = null;
            }
        }

        // Initialize logger
        logger = new EmoticonLogger();

        // Initialize available emoticon array list with all possible feelings
        Emoticon angryFace = new Emoticon("Angry", ResourcesCompat.getDrawable(getResources(), R.drawable.angry_face, null));
        Emoticon coolFace = new Emoticon("Cool", ResourcesCompat.getDrawable(getResources(), R.drawable.cool_face, null));
        Emoticon crazyFace = new Emoticon("Crazy", ResourcesCompat.getDrawable(getResources(), R.drawable.crazy_face, null));
        Emoticon cryingFace = new Emoticon("Crying", ResourcesCompat.getDrawable(getResources(), R.drawable.crying_face, null));
        Emoticon excitedFace = new Emoticon("Excited", ResourcesCompat.getDrawable(getResources(), R.drawable.excited_face, null));
        Emoticon eyeRollFace = new Emoticon("EyeRoll", ResourcesCompat.getDrawable(getResources(), R.drawable.eye_roll_face, null));
        Emoticon huggingFace = new Emoticon("Hugging", ResourcesCompat.getDrawable(getResources(), R.drawable.hugging_face, null));
        Emoticon poopFace = new Emoticon("Poop", ResourcesCompat.getDrawable(getResources(), R.drawable.poop_face, null));
        Emoticon sadFace = new Emoticon("Sad", ResourcesCompat.getDrawable(getResources(), R.drawable.sad_face, null));
        Emoticon sickFace = new Emoticon("Sick", ResourcesCompat.getDrawable(getResources(), R.drawable.sick_face, null));
        Emoticon smilingFace = new Emoticon("Smiling", ResourcesCompat.getDrawable(getResources(), R.drawable.smiling_face, null));
        Emoticon smirkFace = new Emoticon("Smirk", ResourcesCompat.getDrawable(getResources(), R.drawable.smirk_face, null));
        Emoticon tearsOfJoyFace = new Emoticon("TearsOfJoy", ResourcesCompat.getDrawable(getResources(), R.drawable.tears_of_joy_face, null));
        Emoticon wearyFace = new Emoticon("Weary", ResourcesCompat.getDrawable(getResources(), R.drawable.weary_face, null));
        availableEmoticonArrayList = new ArrayList<>(Arrays.asList(
                angryFace,
                coolFace,
                crazyFace,
                cryingFace,
                excitedFace,
                eyeRollFace,
                huggingFace,
                poopFace,
                sadFace,
                sickFace,
                smilingFace,
                smirkFace,
                tearsOfJoyFace,
                wearyFace
        ));

//        // Testing
//        LocalDate date1 = LocalDate.of(2024, 1, 31);
//        logger.recordEmoticon(poopFace, date1);
//        logger.recordEmoticon(poopFace, date1);
//        logger.recordEmoticon(wearyFace, date1);
//        logger.recordEmoticon(smilingFace, date1);
//        LocalDate date2 = LocalDate.of(2024, 5, 20);
//        logger.recordEmoticon(crazyFace, date2);
//        logger.recordEmoticon(cryingFace, date2);
//        logger.recordEmoticon(sadFace, date2);
//        logger.recordEmoticon(sadFace, date2);
//        LocalDate date3 = LocalDate.of(2025, 9, 25);
//        logger.recordEmoticon(eyeRollFace, date3);
//        logger.recordEmoticon(angryFace, date3);
//        logger.recordEmoticon(angryFace, date3);
//        logger.recordEmoticon(sickFace, date3);

        // Add some default feelings
        emoticonGridArray[0][0] = smilingFace;
        emoticonGridArray[0][1] = angryFace;
        emoticonGridArray[1][0] = coolFace;
        availableEmoticonArrayList.remove(smilingFace);
        availableEmoticonArrayList.remove(angryFace);
        availableEmoticonArrayList.remove(coolFace);

        // Configures the main activity's layout for viewing or editing emoticons.
        renderMainActivity();

        // Set On Click Listeners
        editButton.setOnClickListener(v -> {
            editMode = true;
            renderMainActivity();
        });
        confirmButton.setOnClickListener(v -> {
            editMode = false;
            renderMainActivity();
        });
        dailySummaryButton.setOnClickListener(v -> {
            DailySummaryFragment fragment = DailySummaryFragment.newInstance(logger);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.main, fragment, "DailySummaryFragment")
                    .addToBackStack(null)
                    .commit();
        });
        historyButton.setOnClickListener(v -> {
            HistoryFragment fragment = HistoryFragment.newInstance(logger);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.main, fragment, "HistoryFragment")
                    .addToBackStack(null)
                    .commit();
        });
    }

    /**
     * Renders the main activity layout based on edit mode and updates emoticon grid views.
     */
    public void renderMainActivity() {
        // Set button visibility and main text based on if user is editing main screen or not
        if (editMode) {
            editButton.setVisibility(View.INVISIBLE);
            confirmButton.setVisibility(View.VISIBLE);
            dailySummaryButton.setVisibility(View.INVISIBLE);
            historyButton.setVisibility(View.INVISIBLE);
            mainMessageTextview.setText(getResources().getString(R.string.activity_main_message_edit_text));
        }
        else {
            editButton.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.INVISIBLE);
            dailySummaryButton.setVisibility(View.VISIBLE);
            historyButton.setVisibility(View.VISIBLE);
            mainMessageTextview.setText(getResources().getString(R.string.activity_main_message_nonedit_text));
        }

        // Render emoticons based on if user is editing main screen or not
        for (int i = 0; i < numGridRows; i++) {
            for (int j = 0; j < numGridCol; j++) {
                int childIndex = i * numGridCol + j;
                FrameLayout frame = (FrameLayout) emoticonGridlayout.getChildAt(childIndex);
                frame.removeAllViews();

                Emoticon emoticon = emoticonGridArray[i][j];
                View emoticonView = LayoutInflater.from(MainActivity.this).inflate(R.layout.edit_emoticon_view, frame, false);
                ImageButton emoticonImageButton = emoticonView.findViewById(R.id.emoticon_image_button);
                ImageButton emoticonDeleteButton = emoticonView.findViewById(R.id.delete_image_button);

                if (emoticon != null) {
                    // Emoticon exists at this grid location, render based on if user is editing main screen or not
                    emoticonImageButton.setImageDrawable(emoticon.getImage());
                    if (editMode) {
                        // Add delete button
                        emoticonImageButton.setEnabled(false);
                        emoticonDeleteButton.setVisibility(View.VISIBLE);
                        int finalI = i; int finalJ = j;
                        emoticonDeleteButton.setOnClickListener(v -> {
                            deleteEmoticonGridArray(finalI, finalJ);
                        });
                    }
                    else {
                        // Remove delete button, enable emoticon press recording
                        emoticonImageButton.setEnabled(true);
                        emoticonDeleteButton.setVisibility(View.INVISIBLE);
                        int finalI = i; int finalJ = j;
                        emoticonImageButton.setOnClickListener(v -> {
                            recordEmoticonGridArray(finalI, finalJ);
                        });
                    }
                    frame.addView(emoticonView);
                }
                else if (editMode) {
                    // Emoticon does not exist at this grid location, render add option if user is editing main screen or not
                    emoticonImageButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.add_button_icon, null));
                    emoticonDeleteButton.setVisibility(View.INVISIBLE);
                    int finalI = i; int finalJ = j;
                    emoticonImageButton.setOnClickListener(v -> {
                        addEmoticonGridArray(finalI, finalJ);
                    });
                    frame.addView(emoticonView);
                }
            }
        }
    }

    /**
     * Deletes an emoticon from the grid at the specified row and column, returning it to the available list.
     */
    public void deleteEmoticonGridArray(int rowIndex, int colIndex) {
        Log.i("DEBUG", String.format("deleteEmoticonGridArray: [%d, %d]", rowIndex + 1, colIndex + 1));
        Emoticon emoticon = emoticonGridArray[rowIndex][colIndex];
        emoticonGridArray[rowIndex][colIndex] = null;
        availableEmoticonArrayList.add(emoticon);
        renderMainActivity();
    }

    /**
     * Opens the add-emoticon dialog for the specified grid position.
     */
    public void addEmoticonGridArray(int rowIndex, int colIndex) {
        Log.i("DEBUG", String.format("addEmoticonGridArray: [%d, %d]", rowIndex + 1, colIndex + 1));
        selectedRow = rowIndex; selectedCol = colIndex;
        AddEmoticonDialogFragment dialogFragment = AddEmoticonDialogFragment.newInstance(availableEmoticonArrayList);
        dialogFragment.show(getSupportFragmentManager(), "AddEmoticonDialogFragment");
    }

    /**
     * Adds the selected emoticon to the chosen grid position and updates the available list.
     */
    public void addEmoticon(Emoticon emoticon) {
        Log.i("DEBUG", String.format("addEmoticon: [%d, %d]", selectedRow + 1, selectedCol + 1));
        emoticonGridArray[selectedRow][selectedCol] = emoticon;
        availableEmoticonArrayList.remove(emoticon);
        renderMainActivity();
    }

    /**
     * Records the usage of the emoticon at the specified grid position in the logger.
     */
    public void recordEmoticonGridArray(int rowIndex, int colIndex) {
        Log.i("DEBUG", String.format("recordEmoticonGridArray: [%d, %d]", rowIndex + 1, colIndex + 1));
        Emoticon emoticon = emoticonGridArray[rowIndex][colIndex];
        logger.recordEmoticon(emoticon);
    }
}

















