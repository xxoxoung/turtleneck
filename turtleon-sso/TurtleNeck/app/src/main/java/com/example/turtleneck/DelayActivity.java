package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 어플 시작 화면
public class DelayActivity extends AppCompatActivity {

    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        float confirm = -1;

        // LoginActivity로부터 유저이름 받아오기
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        while (confirm == -1) {
            // 10초에 한번씩 실행
            Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                }
            };
            handler.sendEmptyMessageDelayed(0, 10000);

            //confirm = GetDiag();
        }

        Intent intent1 = new Intent(DelayActivity.this, GraphActivity.class);
        intent1.putExtra("username",username);
        intent1.putExtra("confirm",confirm);
        startActivity(intent1);
        finish();
    }

    public void GetDiag() {
        // 네트워크 빌드
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DjangoApi.DJANGO_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DjangoApi postApi = retrofit.create(DjangoApi.class);
        Call<ResponseBody> getcall = postApi.GetDiagValue( );

        // 진단값 확인을 위한 함수 호출 실행
        // -1 이면 함수 재실행
        // -1 아니면 진단값 저장된 것 이므로 화면 넘기기
        getcall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {

                    //return Diag;
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}