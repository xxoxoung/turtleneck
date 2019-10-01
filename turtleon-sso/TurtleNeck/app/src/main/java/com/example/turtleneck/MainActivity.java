package com.example.turtleneck;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.youtube.player.YouTubePlayerFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // 유튜브 플레이어
        // API_KEY = "AIzaSyBApq9HjukG3rAcuQd6kEC-IhQ_HKuobIg"
        // url = "8y8SjUIib28"
        YoutubeFragment youF = new YoutubeFragment();
        YoutubeFragment.newInstance("8y8SjUIib28");
        Log.d("유튜브 플레이어!!!!","인스턴스 생성 성공");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Log.d("FragmentManager 웅앵","!!!!!!!!!!!!!!!!!!!!!!");
        transaction.add(R.id.youtube_fragment,youF).commit();
        Log.d("FragmentManager ㅇㅇㅇㅇㅇㅇㅇ","!!!!!!!!!!!!!!!!!!!!!!");
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_StartDia) {
            // 진단 시작 화면으로 이동
            Intent intent1 = new Intent(this, DiagActivity.class);
            startActivity(intent1);
        } else if (id == R.id.nav_ConfirmDia) {
            // 진단 확인 화면으로 이동
            Intent intent2 = new Intent(this, ConfirmActivity.class);
            startActivity(intent2);
        } else if (id == R.id.nav_ModifySign) {
            // 회원 정보 수정 화면으로 이동
            Intent intent4 = new Intent(this, ModifySignActivity.class);
            startActivity(intent4);
        } else if (id == R.id.nav_Board) {
            // 게시글 관리 화면으로 이동

        } else if (id == R.id.nav_Setting) {
            // 설정 화면으로 이동

        } else if (id == R.id.nav_Send) {
            // 오류보내기 화면으로 이동

        } else if (id == R.id.nav_FAQ) {
            // 고객센터 화면으로 이동

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
