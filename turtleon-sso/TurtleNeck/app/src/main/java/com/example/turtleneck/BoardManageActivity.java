package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

public class BoardManageActivity extends AppCompatActivity {

    private ListView listview;
    private BoardListViewAdapter adapter;

    private String[] Number = new String[100];
    private String[] Date = new String[100];
    private String[] Title = new String[100];
    private String[] Content = new String[100];

    public String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardmanage);

        // 변수 초기화
        adapter = new BoardListViewAdapter();
        listview = (ListView) findViewById(R.id.List_view);

        // 어댑터 할당
        listview.setAdapter(adapter);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        // 데이터베이스에서 게시글 찾아서 선언된 배열에 할당
        DBHelper dbHelper = new DBHelper(getApplicationContext(), "A.db", null, 1);
        int a = dbHelper.ManageBoard(Number, username, Date, Title, Content);

        // 어댑터를 통한 값 전달
        for(int i = 0; i < a; i++) {
            adapter.addVO(Number[i], username, Date[i], Title[i],Content[i]);
        }
    }

    // MainAcitivity 로 이동
    public void GoMain (View view) {
        Intent intent1 = new Intent(this, MainActivity.class);
        intent1.putExtra("username",username);
        startActivity(intent1);
        finish();
    }
}
