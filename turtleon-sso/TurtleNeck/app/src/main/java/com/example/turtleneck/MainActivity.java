package com.example.turtleneck;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // 아이콘을 보여주기 위한 이미지뷰
    ImageView youtubeButton;
    ImageView newsButton;

    // 게시글 출력을 위한 것들
    private ListView listview1;
    private ListViewAdapter adapter2;

    private String[] Number = new String[100];
    private String[] Date = new String[100];
    private String[] Username = new String[100];
    private String[] Title = new String[100];
    private String[] Content = new String[100];

    // 유저
    public String username;

    // 헤더에 띄워주기 위한 텍스트뷰
    public TextView tUsername;
    public TextView tEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header= navigationView.getHeaderView(0);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        youtubeButton = (ImageView) findViewById(R.id.youtubeButton);
        newsButton = (ImageView) findViewById(R.id.newsButton);
        tUsername = (TextView) header.findViewById(R.id.textUsername) ;
        tEmail = (TextView) header.findViewById(R.id.textEmailaddress);

        // 변수 초기화
        adapter2 = new ListViewAdapter();
        listview1 = (ListView) findViewById(R.id.List_view1);

        // 어댑터 할당
        listview1.setAdapter(adapter2);

        // 내장 디비 연결
        DBHelper dbHelper = new DBHelper(getApplicationContext(), "A.db", null, 1);
        int a = dbHelper.LoadBoard(Number, Username, Date, Title, Content);

        // 어댑터를 통한 값 전달
        for(int i=0; i < a; i++) {
            adapter2.MainaddVO(Number[i], Username[i], Date[i], Title[i],Content[i]);
        }

        // LoginActivity로부터 유저이름 받아오기
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        // 유저 이름, 이메일 가져와서 헤더에 띄우기
        tUsername.setText(username);
        tUsername.setTextColor(Color.BLACK);
        tEmail.setText(dbHelper.GetHeader(username));
        tEmail.setTextColor(Color.BLACK);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 네비게이터바에서 메뉴 선택
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_StartDia) {
            // 진단 시작 화면으로 이동
            Intent intent1 = new Intent(this, DiagActivity.class);
            intent1.putExtra("username",username);
            startActivity(intent1);
        } else if (id == R.id.nav_ConfirmDia) {
            // 진단 확인 화면으로 이동
            Intent intent2 = new Intent(this, ConfirmActivity.class);
            intent2.putExtra("username",username);
            startActivity(intent2);
        } else if (id == R.id.nav_Board) {
            // 게시글 관리 화면으로 이동
            Intent intent4 = new Intent(this, BoardManageActivity.class);
            intent4.putExtra("username",username);
            startActivity(intent4);
        } else if (id == R.id.nav_Setting) {
            // 설정 화면으로 이동
            Intent intent5 = new Intent(this, SettingActivity.class);
            intent5.putExtra("username",username);
            startActivity(intent5);
        } else if (id == R.id.nav_FAQ) {
            // 고객센터 화면으로 이동
            Intent intent6 = new Intent(this, FAQActivity.class);
            intent6.putExtra("username",username);
            startActivity(intent6);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // YoutubeAcitivity 로 이동
    public void GoYoutube (View view) {
        Intent intent1 = new Intent(this, YoutubeActivity.class);
        startActivity(intent1);
    }

    // NewsAcitivity 로 이동
    public void GoNews (View view) {

        // 뉴스기사 URL 추가되면 추가하기!!!
        // 2019.10.09 기준 4개
        String newsString[] = {"https://www.insight.co.kr/news/236917",
                "http://www.polinews.co.kr/news/article.html?no=423980",
                "http://www.polinews.co.kr/news/article.html?no=423741#0B4G",
                "http://www.munhwa.com/news/view.html?no=2019091001032842000003"};

        // 랜덤값을 위한 변수
        // (최대값-최소값+1)+최소값
        int i = (int)(Math.random() * 4)+0;

        // 랜덤으로 뉴스 기사 페이지로 이동해서 보여주기
        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(newsString[i]));
        startActivity(intent2);

//        Intent intent2 = new Intent(this, NewsActivity.class);
//        startActivity(intent2);
    }

    // BoardAcitivity 로 이동
    public void GoBoard (View view) {
        Intent intent1 = new Intent(this, BoardActivity.class);
        intent1.putExtra("username",username);
        startActivity(intent1);
    }
}
