package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// 로그인 화면
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button LoginBtn;
    private Button GoSignBtn;
    private EditText eID;
    private EditText ePW;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 버튼에 이벤트 리스너 연결
        LoginBtn = findViewById(R.id.LoginBtn);
        GoSignBtn= findViewById(R.id.GoSignBtn);

        LoginBtn.setOnClickListener(this);
        GoSignBtn.setOnClickListener(this);

        // 로그인 정보 가져오기
        eID = (EditText) findViewById(R.id.ID);
        ePW = (EditText) findViewById(R.id.PW);
    }

    // 클릭시 이벤트 설정
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.GoSignBtn: {
                // 회원가입 필요
                // 회원가입 화면으로 이동
                Intent intent2 = new Intent(this, SignActivity.class);
                startActivity(intent2);
                break;
            }

            case R.id.LoginBtn: {
                String ID = eID.getText().toString().trim();
                String PW = ePW.getText().toString().trim();

                if(!ID.isEmpty()) {
                    if(!PW.isEmpty()) {
                        // 로그인 완료
                        // 메인 화면으로 이동
                        Intent intent1 = new Intent(this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    } else {
                        Toast.makeText(getApplicationContext(),"비밀 번호를 입력해주세요!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"아이디를 입력해주세요!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
