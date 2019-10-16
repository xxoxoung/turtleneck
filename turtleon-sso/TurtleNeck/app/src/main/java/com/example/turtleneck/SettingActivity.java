package com.example.turtleneck;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {
    Button mdButton;
    Button osButton;

    public String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        mdButton = (Button) findViewById(R.id.ModiSignBtn);
        osButton = (Button) findViewById(R.id.OutBtn);
    }

    // ModifySignActivity 로 이동
    public void ModiSign (View view) {
        Intent intent1 = new Intent(this, ModifySignActivity.class);
        intent1.putExtra("username",username);
        startActivity(intent1);
    }

    // 회원 탈퇴 진행
    public void OutSite (View view) {
        // 회원 탈퇴 여부 묻는 팝업 띄우기
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 팝업창 내용 설정
        builder.setTitle("정말 탈퇴하시겠습니까?")
                .setMessage("탈퇴하기");

        // 버튼 설정
        // 예 버튼 누른 경우
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper dbHelper = new DBHelper(getApplicationContext(), "A.db", null,1);
                dbHelper.SignoutUser(username);

                Intent intent2 = new Intent(SettingActivity.this, StartActivity.class);
                startActivity(intent2);
                finish();
            }
        });
        // 아니오 버튼 누른 경우
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        // 팝업창 빌드
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}