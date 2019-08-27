package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HelpPhotoActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView example;
    Button NextBtn;
    Button ShutBtn;

    int next = 1;           // 다음 버튼위한 변수

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpphoto);

        example = (ImageView) findViewById(R.id.example);       // 예시 이미지
        NextBtn = (Button) findViewById(R.id.NextBtn);       // 다음 버튼
        ShutBtn = (Button) findViewById(R.id.ShutBtn);       // 닫기 버튼

        example.setOnClickListener(this);
        NextBtn.setOnClickListener(this);
        ShutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            // 다음 버튼 눌렀을 때
            case R.id.NextBtn : {
                next++;

                if(next == 2) {
                    example.setImageResource(R.drawable.example2);
                    break;
                } else if(next == 3){
                    example.setImageResource(R.drawable.example3);
                    break;
                } else {
                    break;
                }
            }

            // 닫기 버튼 눌렀을 때
            case R.id.ShutBtn : {
                Intent intent1 = new Intent(this, GetPhotoActivity.class);
                startActivity(intent1);
                finish();
                break;
            }

        }
    }
//    public void NextClick(View view) {
//        next++;
//
//
//
//        if(next == 2) {
//            exImg.setImageResource(R.drawable.example2);
//        } else {
//            exImg.setImageResource(R.drawable.example3);
//        }
//    }
//
//    // 닫기 버튼 눌렀을 때
//    public void ShutClick(View view) {
//        finish();
//    }
}
