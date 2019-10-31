package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FAQActivity extends AppCompatActivity implements View.OnClickListener {

    public String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        findViewById(R.id.goMainBtn).setOnClickListener(this);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }

    // 메인으로 버튼 클릭 이벤트
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }
}
