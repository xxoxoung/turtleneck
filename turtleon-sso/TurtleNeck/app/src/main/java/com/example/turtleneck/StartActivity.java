package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

// 어플 시작 화면
public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // 진단 시작 버튼에 클릭 이벤트 연결
        findViewById(R.id.StartBtn).setOnClickListener(this);
    }

    // 진단 시작 버튼 클릭 이벤트
    // GetPhotoActivity 호출
    public void onClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}