package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

// 회원가입 화면
public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    Button SignBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        // 버튼에 이벤트 리스너 연결
        SignBtn = findViewById(R.id.SignBtn);
        SignBtn.setOnClickListener(this);
    }

    // 회원가입 버튼 클릭시 로그인 화면으로 이동
    // 자동 로그인 고려해야 할 듯
    // 나중에 회원가입 되었다고 팝업 추가 고려중
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.SignBtn: {
                // 회원가입 완료
                // 로그인 화면으로 이동
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            }
        }
    }
}