package com.epicodus.parallelmusic.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.parallelmusic.R;
import com.epicodus.parallelmusic.adapters.TrackPagerAdapter;
import com.epicodus.parallelmusic.models.Track;


import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackDetailActivity extends AppCompatActivity {
    private TrackPagerAdapter adapterViewPager;
    @BindView(R.id.viewPager) ViewPager mViewPager;

    ArrayList<Track> mTracks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_detail);
        ButterKnife.bind(this);

        mTracks = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_TRACKS));
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);

        adapterViewPager = new TrackPagerAdapter(getSupportFragmentManager(), mTracks);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
