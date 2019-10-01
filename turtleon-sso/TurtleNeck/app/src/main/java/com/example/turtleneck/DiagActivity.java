package com.example.turtleneck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 진단 정보 입력 화면
public class DiagActivity extends AppCompatActivity implements View.OnClickListener {

    public Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    public EditText eTall;
    public String Gender;

    public String tall;
    public String gender;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diag);

        // 진단 시작 버튼에 클릭 이벤트 연결
        findViewById(R.id.StDgBtn).setOnClickListener(this);

        eTall = (EditText) findViewById(R.id.Tall);

        // Spinner를 위한 arrayList 정의
        // 성별
        arrayList = new ArrayList<>();
        arrayList.add("여자");
        arrayList.add("남자");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, arrayList);

        spinner = (Spinner)findViewById(R.id.spinnerGender);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Gender = arrayList.get(position);
                Toast.makeText(getApplicationContext(), arrayList.get(position) + "가 선택되었습니다.",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // 진단 시작 버튼 클릭 이벤트
    // GetPhotoActivity 호출
    public void onClick(View view) {
        tall = eTall.getText().toString().trim();
        gender = Gender;

        if(!tall.isEmpty()) {
            Intent intent = new Intent(this, GetPhotoActivity.class);

            // tall,gender정보를 GetPhotoActivity로 전송
            intent.putExtra("tall",tall);
            intent.putExtra("gender",gender);

            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"키를 입력해주세요!", Toast.LENGTH_LONG).show();
        }
    }
}
