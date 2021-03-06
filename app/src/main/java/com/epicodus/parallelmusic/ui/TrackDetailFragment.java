package com.epicodus.parallelmusic.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.parallelmusic.R;
import com.epicodus.parallelmusic.models.Track;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrackDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 300;
    private static final int MAX_HEIGHT = 300;

    private Track mTrack;
    private ArrayList<Track> mTracks;
    private int mPosition;

    @BindView(R.id.trackDetailImageView) ImageView mTrackImage;
    @BindView(R.id.trackNameDetailTextView) TextView mTrackName;
    @BindView(R.id.trackArtistDetailTextView) TextView mTrackArtist;
    @BindView(R.id.listenerTextView) TextView mTrackListener;
    @BindView(R.id.webTextView) TextView mWebsite;
    @BindView(R.id.saveTrackButton) ImageButton mSaveTrackButton;

    public static TrackDetailFragment newInstance(ArrayList<Track> tracks, Integer position) {
        TrackDetailFragment trackDetailFragment = new TrackDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_KEY_TRACKS, Parcels.wrap(tracks));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        trackDetailFragment.setArguments(args);
        return trackDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTracks = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_TRACKS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mTrack = mTracks.get(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mTrack.getImageUrl()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(mTrackImage);

        mTrackName.setText(mTrack.getName());
        mTrackArtist.setText(mTrack.getArtist());
        mTrackListener.setText(String.valueOf(mTrack.getListeners()));
        mWebsite.setOnClickListener(this);
        mSaveTrackButton.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v){
        if(v == mWebsite){
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mTrack.getWebsite()));
            startActivity(webIntent);
        }
        if(v == mSaveTrackButton){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference trackRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_TRACKS)
                    .child(uid);
            DatabaseReference pushRef = trackRef.push();
            String pushId = pushRef.getKey();
            mTrack.setPushId(pushId);
            pushRef.setValue(mTrack);

            Toast.makeText(getContext(),"Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
