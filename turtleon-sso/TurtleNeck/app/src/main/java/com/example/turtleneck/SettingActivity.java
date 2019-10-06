package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {
    Button mdButton;
    Button osButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mdButton = (Button) findViewById(R.id.ModiSignBtn);
        osButton = (Button) findViewById(R.id.OutBtn);
    }

    // ModifySignActivity 로 이동
    public void ModiSign (View view) {
        Intent intent1 = new Intent(this, ModifySignActivity.class);
        startActivity(intent1);
    }

    // 회원 탈퇴 진행
    public void OutSite (View view) {
        // 나중에 채워넣기
    }
}