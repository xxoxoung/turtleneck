package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
        setContentView(R.layout.activity_start);


        // LoginActivity로부터 유저이름 받아오기
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "A.db", null, 1);
        //dbHelper.SetDiag(username,confirm);

        // 네트워크 빌드
//        Gson gson = new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
//                .setLenient()
//                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DjangoApi.DJANGO_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DjangoApi postApi = retrofit.create(DjangoApi.class);

        // 20초에 한번씩 실행
        Timer timer = new Timer();

        TimerTask TT = new TimerTask() {
            // 반복 실행할 구문
            @Override
            public void run() {
                // comfirm 값을 가져와서
                // -1이면 반복 실행
                // 다른 값이면 화면 넘어가기
                Call<RequestBody> getcall = postApi.GetDiagValue();
                getcall.enqueue(new Callback<RequestBody>() {
                    @Override
                    public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                        Toast.makeText(getApplicationContext(), "GET 함수 요청",Toast.LENGTH_SHORT).show();

                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "응답 성공",Toast.LENGTH_SHORT).show();
                            RequestBody body = response.body();
                            if (body != null) {
                                Toast.makeText(getApplicationContext(), "진단값 : " + body.toString(),Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(), "진단값 : " + body.getDiag(),Toast.LENGTH_SHORT).show();
                                // 가져온 진단값 내부디비에 임시 저장
                                //dbHelper.InsertDiag(username,);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "응답 실패ㅐㅐㅐㅐㅐㅐ",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "" + t.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        // TimerTask, 처음 대기시간, 반복시간
        timer.schedule(TT, 10, 20000);

        if(confirm != -1) {
            timer.cancel();
            Intent intent1 = new Intent(DelayActivity.this, GraphActivity.class);
            intent1.putExtra("username", username);
            intent1.putExtra("confirm", confirm);
            startActivity(intent1);
            finish();
        }
    }
}