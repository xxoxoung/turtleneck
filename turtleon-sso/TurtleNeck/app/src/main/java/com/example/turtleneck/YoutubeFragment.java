package com.example.turtleneck;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class YoutubeFragment extends YouTubePlayerFragment {

    public YoutubeFragment() {}

    public static YoutubeFragment newInstance(String url) {
        Log.d("프래그먼트 들어옴~!","");
        YoutubeFragment youF = new YoutubeFragment();
        Bundle b = new Bundle();
        b.putString("url",url);
        youF.setArguments(b);
        Log.d("유튜브 프래그먼트~!","");
        youF.init();

        return youF;
    }

    private void init() {
        Log.d("유튜브 초기화 한다~!","");
        initialize("AIzaSyBApq9HjukG3rAcuQd6kEC-IhQ_HKuobIg", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b) {
                    youTubePlayer.cueVideo(getArguments().getString("url"));
                    Log.d("유튜브 영상 재생 한다~!","");
                }
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }


}
