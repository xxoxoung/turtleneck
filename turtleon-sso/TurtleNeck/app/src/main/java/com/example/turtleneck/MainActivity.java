package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button youtubeButton;
    Button newsButton;

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

        youtubeButton = (Button) findViewById(R.id.youtubeButton);
        newsButton = (Button) findViewById(R.id.newsButton);
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
            startActivity(intent1);
        } else if (id == R.id.nav_ConfirmDia) {
            // 진단 확인 화면으로 이동
            Intent intent2 = new Intent(this, ConfirmActivity.class);
            startActivity(intent2);
        } else if (id == R.id.nav_Board) {
            // 게시글 관리 화면으로 이동
            Intent intent4 = new Intent(this, BoardActivity.class);
            startActivity(intent4);
        } else if (id == R.id.nav_Setting) {
            // 설정 화면으로 이동
            Intent intent5 = new Intent(this, SettingActivity.class);
            startActivity(intent5);
        } else if (id == R.id.nav_FAQ) {
            // 고객센터 화면으로 이동
            Intent intent6 = new Intent(this, FAQActivity.class);
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
        Intent intent2 = new Intent(this, NewsActivity.class);
        startActivity(intent2);
    }
}
