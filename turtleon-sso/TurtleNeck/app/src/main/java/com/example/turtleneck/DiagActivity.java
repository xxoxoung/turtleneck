package com.example.turtleneck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

// 진단 정보 입력 화면
public class DiagActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diag);

        // 진단 시작 버튼에 클릭 이벤트 연결
        findViewById(R.id.StDgBtn).setOnClickListener(this);

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
                // 나중에 키 정보와 합쳐서 디비로 전송 추가 고려
                // 진단 시작할 때 정보 전송해야 함!
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
        Intent intent = new Intent(this, GetPhotoActivity.class);
        startActivity(intent);
        finish();
    }
}
