package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 회원가입 화면
public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ePW1;
    private EditText ePW2;
    private EditText eName;
    private EditText eEmail;

    private ImageView loginImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        loginImage = (ImageView) findViewById(R.id.SignBtn);
        loginImage.setOnClickListener(this);
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
                ePW1 = (EditText) findViewById(R.id.PW1);
                ePW2 = (EditText) findViewById(R.id.PW2);
                eName = (EditText) findViewById(R.id.Name);
                eEmail = (EditText) findViewById(R.id.Email);

                // 회원가입 정보 String으로 가져오기
                final String username = eName.getText().toString().trim();
                final String password1 = ePW1.getText().toString().trim();
                final String password2 = ePW2.getText().toString().trim();
                final String emailadress = eEmail.getText().toString().trim();

                // 디비로 회원가입 정보 전송
                if (isValidEmail(emailadress)) {
                    if (isValidPassword(password1, password2)) {
                        if (!emailadress.isEmpty() && !password1.isEmpty() && !username.isEmpty() && !password2.isEmpty()) {

                            // 내장 디비 이용한 회원가입 구현
                            // 내장 디비 연결
                            DBHelper dbHelper = new DBHelper(getApplicationContext(), "A.db", null, 1);
                            int var;
                            var = dbHelper.InsertSign(username, password1, emailadress);

                            // 로그인 성공한 경우
                            if (var == 0) {
                                // 로그인 화면으로 넘어가기
                                Intent intent1 = new Intent(this, LoginActivity.class);
                                startActivity(intent1);
                                finish();
                            }
                            // 로그인 실패한 경우
                            // 1009 기준 안뜨는중
                            else {
                                Toast.makeText(getApplicationContext(),"중복된 아이디입니다!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "필수 사항을 입력해주세요!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }
}

//                            // 네트워크 빌드
//                            Retrofit retrofit = new Retrofit.Builder()
//                                    .baseUrl(DjangoApi.DJANGO_SITE)
//                                    .addConverterFactory(GsonConverterFactory.create())
//                                    .build();
//
//                            DjangoApi postApi = retrofit.create(DjangoApi.class);
//
//                            // 값 정리
//                            RequestBody requestUsername = RequestBody.create(MediaType.parse("multipart/data"), username);
//                            RequestBody requestEmail = RequestBody.create(MediaType.parse("multipart/data"), emailadress);
//                            RequestBody requestPassword1 = RequestBody.create(MediaType.parse("multipart/data"), password1);
//                            RequestBody requestPassword2 = RequestBody.create(MediaType.parse("multipart/data"), password2);
//
//                            Call<ResponseBody> call = postApi.post_signup(requestUsername,requestEmail,requestPassword1,requestPassword2);
//
//                            call.enqueue(new Callback<ResponseBody>() {
//                                @Override
//                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                    Log.d("회원가입 정보 전송 성공",""+username+"/"+password1+"/"+password2+"/"+emailadress);
//                                }
//
//                                @Override
//                                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                    Log.i("GGG", "실패 메시지 : " + t.getMessage());
//                                    Log.i("GGG", "요청 메시지 : " + call.request());
//                                }
//                            });