package com.epicodus.parallelmusic;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.searchButton) Button mSearchButton;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
    @BindView(R.id.songEditText) EditText mSongEditText;
    @BindView(R.id.app_bar) android.support.v7.widget.Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);

        Typeface painterFont = Typeface.createFromAsset(getAssets(), "fonts/painter.ttf");
        mAppNameTextView.setTypeface(painterFont);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String song = mSongEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this,TrackSearchActivity.class);
                intent.putExtra("song",song);
                startActivity(intent);
            }
        });
    }
}