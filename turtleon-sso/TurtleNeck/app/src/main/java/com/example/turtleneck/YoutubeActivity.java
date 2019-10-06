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

    /**
     * 플레이어가 초기화될 때 호출됩니다.
     * 매개변수
     *
     * provider YouTubePlayer를 초기화화는 데 사용된 제공자입니다.
     * player 제공자에서 동영상 재생을 제어하는 데 사용할 수 있는 YouTubePlayer입니다
     * wasRestored
     *    YouTubePlayerView 또는 YouTubePlayerFragment가 상태를 복원하는 과정의 일부로서
     *    플레이어가 이전에 저장된 상태에서 복원되었는지 여부입니다.
     *    true는 일반적으로 사용자가 재생이 다시 시작될 거라고 예상하는 지점에서 재생을 다시 시작하고
     *    새 동영상이 로드되지 않아야 함을 나타냅니다.
     */

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

            //cueVideo(String videoId)
            //지정한 동영상의 미리보기 이미지를 로드하고 플레이어가 동영상을 재생하도록 준비하지만
            //play()를 호출하기 전에는 동영상 스트림을 다운로드하지 않습니다.
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
