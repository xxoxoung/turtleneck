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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 회원가입 화면
public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    private NetworkService networkService;

    private Button SignBtn;
    private EditText eID;
    private EditText ePW;
   // private EditText ePW2;
    private EditText eName;
    private EditText eEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        // 버튼에 이벤트 리스너 연결
        SignBtn = findViewById(R.id.SignBtn);
        SignBtn.setOnClickListener(this);

        // 네트워크 빌드
        ApplicationController application = new ApplicationController();
        application.getInstance();
        application.buildNetworkService("517b529f.ngrok.io",3306);
        networkService = application.getNetworkService();
    }

    // 회원가입 버튼 클릭시 로그인 화면으로 이동
    // 나중에 회원가입 되었다고 팝업 추가 고려중
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.SignBtn: {
                // 회원가입 정보 가져오기
                eID = (EditText) findViewById(R.id.ID);
                ePW = (EditText) findViewById(R.id.PW);
                // ePW2 = (EditText) findViewById(R.id.PW2);
                eName = (EditText) findViewById(R.id.Name);
                eEmail = (EditText) findViewById(R.id.Email);

                // 회원가입 정보 String으로 가져오기
                String ID = eID.getText().toString().trim();
                String PW = ePW.getText().toString().trim();
                String Name = eName.getText().toString().trim();
                String Email = eEmail.getText().toString().trim();

                if(!ID.isEmpty()&&!PW.isEmpty()&&!Name.isEmpty()&&!Email.isEmpty()) {
                    // 디비로 회원가입 정보 전송
                    // @@@@@@@@@@@@@@@여기 수정중!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                    // 값 연결
                    Signup sss = new Signup();
                    sss.setS_ID(ID);
                    sss.setPW(PW);
                    sss.setName(Name);
                    sss.setEmail(Email);

                    Toast.makeText(getApplicationContext(),"함수 호출중!", Toast.LENGTH_SHORT).show();

                    // POST 함수
                    Call<Signup> postCall = networkService.post_signup(sss);

                    postCall.enqueue(new Callback<Signup>() {
                        @Override
                        public void onResponse(Call<Signup> call, Response<Signup> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),"POST 성공!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(),"POST 실패!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Signup> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"함수 호출 실패!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // 로그인 화면으로 이동
                    //Intent intent1 = new Intent(this, LoginActivity.class);
                    //startActivity(intent1);
                    //finish();
                    //break;
                } else {
                    Toast.makeText(getApplicationContext(),"필수 사항을 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}