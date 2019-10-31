package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 어플 시작 화면
public class DelayActivity extends AppCompatActivity {

    public String username;

    public float confirm = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay);


        // LoginActivity로부터 유저이름 받아오기
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        // 네트워크 빌드
           Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DjangoApi.DJANGO_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DjangoApi postApi = retrofit.create(DjangoApi.class);

        // 20초에 한번씩 실행
        final Timer timer = new Timer();

        TimerTask TT = new TimerTask() {
            // 반복 실행할 구문
            @Override
            public void run() {
                // comfirm 값을 가져와서
                // -1이면 반복 실행
                // 다른 값이면 화면 넘어가기
                Call<String> getcall = postApi.GetDiagValue();
                getcall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String body = response.body();
                            float diagconfirm = Float.parseFloat(body);
                            Toast.makeText(getApplicationContext(), "가져온 값 : " +diagconfirm, Toast.LENGTH_LONG).show();
                            if (body != null) {
                                if( diagconfirm == confirm) {
                                    // 값이 변한게 아니므로 계속 되도록
                                }
                                else {
                                    // 값이 변한 것이므로 화면 넘어가고 타이머 중지
                                    timer.cancel();
                                    Intent intent1 = new Intent(DelayActivity.this, GraphActivity.class);
                                    intent1.putExtra("username", username);
                                    intent1.putExtra("diagconfirm", diagconfirm);
                                    startActivity(intent1);
                                    finish();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"실패!!!!!!" +t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        };

        // TimerTask, 처음 대기시간, 반복시간
        timer.schedule(TT, 50000, 20000);
    }
}