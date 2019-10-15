package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 어플 시작 화면
public class DelayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        // 네트워크 빌드
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(DjangoApi.DJANGO_SITE)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        DjangoApi postApi = retrofit.create(DjangoApi.class);
//
//        // 서버 함수를 돌리기 위한 호출 실행
//        Call<ResponseBody> getcall = postApi.callFunction();
//        getcall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//            }
//        });


        // 5초뒤 화면전환
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent intent = new Intent(DelayActivity.this, GraphActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0,1000);
    }
}