package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "AAA.db", null, 1);

        final EditText etDate = (EditText) findViewById(R.id.dateText);
        final EditText etTitle = (EditText) findViewById(R.id.titleText);
        final EditText etContent = (EditText) findViewById(R.id.contentText);

        // 날짜는 현재 날짜로 고정
        // 현재 시간 구하기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 출력될 포맷 설정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        etDate.setText(simpleDateFormat.format(date));

        // DB에 데이터 추가
        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = etDate.getText().toString();
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();

                dbHelper.InsertBoard(date, title, content);

                Intent intent1 = new Intent(BoardActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

//        // DB에 있는 데이터 수정
//        Button update = (Button) findViewById(R.id.update);
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String item = etItem.getText().toString();
//                int price = Integer.parseInt(etPrice.getText().toString());
//
//                dbHelper.update(item, price);
//                result.setText(dbHelper.getResult());
//            }
//        });

//        // DB에 있는 데이터 삭제
//        Button delete = (Button) findViewById(R.id.delete);
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String item = etItem.getText().toString();
//
//                dbHelper.delete(item);
//                result.setText(dbHelper.getResult());
//            }
//        });

//        // DB에 있는 데이터 조회
//        Button select = (Button) findViewById(R.id.select);
//        select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                result.setText(dbHelper.getResult());
//            }
//        });
    }
}
