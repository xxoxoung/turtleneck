package com.example.turtleneck;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "A.db", null, 1);

        // 어댑터 할당
        listview.setAdapter(adapter);
        // itemClickListener 설정
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long id) {
                // 게시글 삭제 여부 묻는 팝업 띄우기
                AlertDialog.Builder builder = new AlertDialog.Builder(BoardManageActivity.this);

                // 팝업창 내용 설정
                builder.setTitle("정말 게시글을 삭제하시겠습니까?")
                        .setMessage("게시글 삭제하기");

                // 버튼 설정
                // 예 버튼 누른 경우
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 데이터베이스에서 게시글 삭제하기
                        dbHelper.DeleteBoard(adapter.listVO.get(pos).getNumber());

                        // 리스트뷰 객체에서도 지우고
                        // 리스트뷰 전부 지운 다음 다시 로딩하기
                        if(pos != listview.INVALID_POSITION) {
                            adapter.listVO.remove(pos);
                            listview.clearChoices();
                            adapter.notifyDataSetChanged();
                        }
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
        });

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        // 데이터베이스에서 게시글 찾아서 선언된 배열에 할당
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
