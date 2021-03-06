package com.epicodus.parallelmusic.ui;

import com.epicodus.parallelmusic.BuildConfig;

/**
 * Created by Guest on 3/23/18.
 */

public class Constants {
    public static final String LASTFM_TOKEN  = BuildConfig.LASTFM_TOKEN;
    public static final String LASTFM_BASE_TRACKSEARCH_URL = "https://ws.audioscrobbler.com/2.0/?method=track.search";
    public static final String LASTFM_SONG_QUERY_PARAMETER = "track";
    public static final String LASTFM_FORMAT_PARAMETER = "json";
    public static final String FIREBASE_CHILD_SEARCHED_SONG = "searchedSong";
    public static final String FIREBASE_CHILD_TRACKS = "tracks";
    public static final String FIREBASE_QUERY_INDEX = "index";
    public static final String PREFERENCES_SONG_KEY = "song";
    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_TRACKS = "tracks";
}
