package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

// 로그인 화면
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button LoginBtn;
    Button GoSignBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 버튼에 이벤트 리스너 연결
        LoginBtn = findViewById(R.id.LoginBtn);
        GoSignBtn= findViewById(R.id.GoSignBtn);

        LoginBtn.setOnClickListener(this);
        GoSignBtn.setOnClickListener(this);
    }

    // 클릭시 이벤트 설정
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.LoginBtn: {
                // 로그인 완료
                // 메인 화면으로 이동
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            }
            case R.id.GoSignBtn:{
                // 회원가입 필요
                // 회원가입 화면으로 이동
                Intent intent2 = new Intent(this, SignActivity.class);
                startActivity(intent2);
                break;
            }
        }
    }
}
