package com.epicodus.parallelmusic.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.epicodus.parallelmusic.R;
import com.epicodus.parallelmusic.util.OnStartDragListener;


public class SavedTrackListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_track_list);
    }
}
