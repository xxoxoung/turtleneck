package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

// API_KEY = "AIzaSyBApq9HjukG3rAcuQd6kEC-IhQ_HKuobIg"

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        //API_KEY 값을 이용한 초기화
        getYouTubePlayerProvider().initialize("AIzaSyBApq9HjukG3rAcuQd6kEC-IhQ_HKuobIg",this);
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtubeView1);
    }

    // 비디오 URL 추가되면 추가하기!!!
    // 2019.10.06 기준 요소 11개
    String videoString[] = {"YPYPvcRE4m8",
                            "dJXZRZvqbYg",
                            "2u97jwzp0Jw",
                            "3aTPapvWpKs",
                            "knxMmwLmuk0",
                            "T9jMXWFauhs",
                            "jD5EwJncYLw",
                            "3sbxablQsKk",
                            "4UhHQ2OxvFI",
                            "MRF_6Dg61N0",
                            "XWDJ-14zvzk"};

    // 랜덤값을 위한 변수
    // (최대값-최소값+1)+최소값
    int i = (int)(Math.random() * 12)+0;

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(videoString[i]);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // 재시도한 경우 API_KEY 값을 위한 초기화 다시 진행
            getYouTubePlayerProvider().initialize("AIzaSyBApq9HjukG3rAcuQd6kEC-IhQ_HKuobIg", this);
        }
    }
}
