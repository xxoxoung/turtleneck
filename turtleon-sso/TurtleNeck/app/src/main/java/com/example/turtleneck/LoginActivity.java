package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 로그인 화면
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button LoginBtn;
    private Button GoSignBtn;
    private EditText eName;
    private EditText ePW;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 버튼에 이벤트 리스너 연결
        LoginBtn = findViewById(R.id.LoginBtn);
        GoSignBtn= findViewById(R.id.GoSignBtn);

        LoginBtn.setOnClickListener(this);
        GoSignBtn.setOnClickListener(this);

        // 로그인 정보 가져오기
        eName = (EditText) findViewById(R.id.Name);
        ePW = (EditText) findViewById(R.id.PW);
    }

    // 클릭시 이벤트 설정
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.GoSignBtn: {
                // 회원가입 필요 > 회원가입 화면으로 이동
                Intent intent2 = new Intent(this, SignActivity.class);
                startActivity(intent2);
                break;
            }

            case R.id.LoginBtn: {
                final String username = eName.getText().toString().trim();
                final String password = ePW.getText().toString().trim();

                if(!username.isEmpty()) {
                    if(!username.isEmpty()) {

                        // 내장디비를 이용한 로그인 기능 구현
                        DBHelper dbHelper = new DBHelper(getApplicationContext(), "AAA.db", null, 1);

                        int var;
                        var = dbHelper.GetLogin(username, password);

                        // 로그인 성공한 경우
                        if (var == 0) {
//                            // 세션 테이블에 유저 저장
//                            dbHelper.MakeSession(username);

                            // 메인 화면으로 넘어가기
                            Intent intent1 = new Intent(this, MainActivity.class);
                            startActivity(intent1);
                            finish();
                            break;
                        }
                        // 로그인 실패한 경우
                        else {
                            Toast.makeText(getApplicationContext(),"아이디와 비밀 번호를 확인해주세요!", Toast.LENGTH_LONG).show();
                        }



//                        // 네트워크 빌드
//                        Retrofit retrofit = new Retrofit.Builder()
//                                .baseUrl(DjangoApi.DJANGO_SITE)
//                                .addConverterFactory(GsonConverterFactory.create())
//                                .build();
//
//                        final DjangoApi postApi = retrofit.create(DjangoApi.class);
//
//                        // multipart 값 정리
//                        RequestBody requestUsername = RequestBody.create(MediaType.parse("multipart/data"), username);
//                        RequestBody requestPassword = RequestBody.create(MediaType.parse("multipart/data"), password);
//                        //RequestBody requestEmail = RequestBody.create(MediaType.parse("multipart/data"), emailadress);
//
//                        //Call<ResponseBody> post_logincall = postApi.post_login(requestUsername,requestPassword,requestEmail);
//
//                        // 로그인 정보 전송 > 로그인 요청
//                        Call<ResponseBody> post_logincall = postApi.post_login(requestUsername,requestPassword);
//                        post_logincall.enqueue(new Callback<ResponseBody>() {
//                            @Override
//                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                Log.d("로그인 정보 전송 성공",""+username+"/"+password);
//
//                                String confirm_username = "baba";
//                                //로그인 여부 확인하는 코드 추가 필요
//                                //GET?
//                                Call<ResponseBody> get_logincall = postApi.get_login(confirm_username);
//                                get_logincall.enqueue(new Callback<ResponseBody>() {
//                                    @Override
//                                    public void onResponse(@NonNull Call<ResponseBody> call,@NonNull Response<ResponseBody> response) {
//                                        if(response.isSuccessful()) {
//                                            if(response.body() != null) {
//                                                Log.d("성공~~~~~~~~~~",""+response.body().toString());
//                                            }
//                                        }
//                                        Log.d("NULL값 가져옴~~~~~~~~~~","");
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                        Log.d("실~~~~~~~~패!!!!!","");
//                                    }
//                                });
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                Log.d("로그인 정보 전송 실패",""+username+"/"+password);
//                                Toast.makeText(getApplicationContext(),"아이디와 비밀 번호를 확인해주세요!", Toast.LENGTH_LONG).show();
//                            }
//                        });

//                        String confirm_username = "baba";
//                        //로그인 여부 확인하는 코드 추가 필요
//                        //GET?
//                        Call<ResponseBody> get_logincall = postApi.get_login(confirm_username);
//                        get_logincall.enqueue(new Callback<ResponseBody>() {
//                            @Override
//                            public void onResponse(@NonNull Call<ResponseBody> call,@NonNull Response<ResponseBody> response) {
//                                if(response.isSuccessful()) {
//                                    if(response.body() != null) {
//                                        Log.d("성공~~~~~~~~~~",""+response.body().toString());
//                                    }
//                                }
//                                Log.d("NULL값 가져옴~~~~~~~~~~","");
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                Log.d("실~~~~~~~~패!!!!!","");
//                            }
//                        });

                        // 토큰 확인 POST
//                        Call<ResponseBody> post_confirmcall = postApi.post_confirm();
//                        post_confirmcall.enqueue(new Callback<ResponseBody>() {
//                            @Override
//                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            }
//                        });

//                        // 메인 화면으로 이동
//                        Intent intent1 = new Intent(this, MainActivity.class);
//                        startActivity(intent1);
//                        finish();
//                        break;
                    } else {
                        Toast.makeText(getApplicationContext(),"비밀 번호를 입력해주세요!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"아이디를 입력해주세요!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}