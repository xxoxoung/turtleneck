package com.example.turtleneck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 회원 가입 정보 수정 페이지
public class ModifySignActivity extends AppCompatActivity {

    private EditText ePW;
    private EditText eEmail;

    public String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifysign);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }

    // 회원정보 수정하기 버튼 누르면
    // 수정될 정보를 서버로 전송후
    // 메인으로 다시 이동
    public void Modify(View v) {
        // 정보 전송
        ePW = (EditText) findViewById(R.id.modifyPW);
        eEmail = (EditText) findViewById(R.id.modifyEmail);

        final String password = ePW.getText().toString().trim();
        final String emailadress = eEmail.getText().toString().trim();

        // 디비로 회원가입 정보 전송
        if (isValidEmail(emailadress)) {
            if (isValidPassword(password)) {
                if (!emailadress.isEmpty() && !password.isEmpty()) {

                    DBHelper dbHelper = new DBHelper(getApplicationContext(), "A.db", null, 1);
                    dbHelper.ModifyUser(username, password,emailadress);

//                    // 네트워크 빌드
//                    Retrofit retrofit = new Retrofit.Builder()
//                            .baseUrl(DjangoApi.DJANGO_SITE)
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .build();
//
//                    DjangoApi postApi = retrofit.create(DjangoApi.class);
//
//                    // 값 정리
//                    RequestBody requestPassword = RequestBody.create(MediaType.parse("multipart/data"), password);
//                    RequestBody requestEmail = RequestBody.create(MediaType.parse("multipart/data"), emailadress);
//                    //RequestBody requestUsername = RequestBody.create(MediaType.parse("multipart/data"), username);
//
//                    Call<ResponseBody> call = postApi.post_modifysign(requestPassword, requestEmail);
//
//                    call.enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            Log.d("회원가입 정보 전송 성공", "" + password + "/" + emailadress);
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            Log.i("GGG", "실패 메시지 : " + t.getMessage());
//                            Log.i("GGG", "요청 메시지 : " + call.request());
//                        }
//                    });
                }

                // 메인 페이지로 이동
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        }
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
    private boolean isValidPassword(String pass1) {
        if (pass1.length() >= 8) {
            return true;
        } else {
            // 비밀번호가 짧은 경우
            Toast.makeText(getApplicationContext(), "비밀번호가 너무 짧습니다!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
