package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 회원가입 화면
public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    private Button SignBtn;
    // private EditText eID;
    private EditText ePW1;
    private EditText ePW2;
    private EditText eName;
    private EditText eEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        // 버튼에 이벤트 리스너 연결
        SignBtn = findViewById(R.id.SignBtn);
        SignBtn.setOnClickListener(this);
    }

    // 이메일 형식 확인
    private boolean isValidEmail(String mail) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    // 패스워드 확인
    private boolean isValidPassword(String pass1, String pass2) {
        if(pass1.equals(pass2)) {
            if(pass1.length()>=8) {
                return true;
            } else {
                // 비밀번호가 짧은 경우
                Toast.makeText(getApplicationContext(), "비밀번호가 너무 짧습니다!", Toast.LENGTH_SHORT).show();
            }
        } else {
            // 비밀번호가 다른 경우
            Toast.makeText(getApplicationContext(), "비밀번호를 확인해주세요!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    // 회원가입 버튼 클릭시 로그인 화면으로 이동
    // 나중에 회원가입 되었다고 팝업 추가 고려중
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.SignBtn: {
                // 회원가입 정보 가져오기
                // eID = (EditText) findViewById(R.id.ID);
                ePW1 = (EditText) findViewById(R.id.PW1);
                ePW2 = (EditText) findViewById(R.id.PW2);
                eName = (EditText) findViewById(R.id.Name);
                eEmail = (EditText) findViewById(R.id.Email);

                // 회원가입 정보 String으로 가져오기
                // String ID = eID.getText().toString().trim();
                String PW1 = ePW1.getText().toString().trim();
                String PW2 = ePW2.getText().toString().trim();
                String Name = eName.getText().toString().trim();
                String Email = eEmail.getText().toString().trim();

                if (isValidEmail(Email)) {
                    if (isValidPassword(PW1, PW2)) {
                        if (!Email.isEmpty() && !PW1.isEmpty() && !Name.isEmpty() && !PW2.isEmpty()) {
                            // 디비로 회원가입 정보 전송
                            // @@@@@@@@@@@@@@@여기 수정중!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//                    // 네트워크 빌드 (위치 변경해서 해보기)
//                    ApplicationController application = new ApplicationController();
//                    application.getInstance();
//                    application.buildNetworkService();
//                    networkService = application.getNetworkService();

                            // 네트워크 빌드
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(DjangoApi.DJANGO_SITE)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            DjangoApi postApi = retrofit.create(DjangoApi.class);

                            // 값 연결
                            Signup signup = new Signup();
                            // signup.setS_ID(ID);
                            signup.setPW1(PW1);
                            signup.setPW2(PW2);
                            signup.setName(Name);
                            signup.setEmail(Email);

                            // POST 함수
                            Call<Signup> postCall = postApi.post_signup(signup);

                            postCall.enqueue(new Callback<Signup>() {
                                @Override
                                public void onResponse(Call<Signup> call, Response<Signup> response) {
                                    if (response.isSuccessful()) {
                                    } else {
                                        int StatusCode = response.code();
                                        try {
                                            Log.i("GGG", "Status Code : " + StatusCode + " Error Message : " + response.errorBody().string());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<Signup> call, Throwable t) {
                                    Log.i("GGG", "실패 메시지 : " + t.getMessage());
                                    Log.i("GGG", "요청 메시지 : " + call.request());
                                }
                            });

                            Intent intent1 = new Intent(this, LoginActivity.class);
                            startActivity(intent1);
                            finish();
                            break;
                        } else {
                            Toast.makeText(getApplicationContext(), "필수 사항을 입력해주세요!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }
}