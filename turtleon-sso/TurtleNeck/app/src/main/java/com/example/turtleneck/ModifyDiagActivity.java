package com.example.turtleneck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

// 진단 정보 수정 페이지
public class ModifyDiagActivity extends AppCompatActivity {

    private Spinner spinner2;
    ArrayList<String> arrayList2;
    ArrayAdapter<String> arrayAdapter2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifydiag);

        // 스피너 정의
        arrayList2 = new ArrayList<>();
        arrayList2.add("여자");
        arrayList2.add("남자");

        arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, arrayList2);

        spinner2 = (Spinner)findViewById(R.id.spinnerGender);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // 나중에 키 정보와 합쳐서 디비로 전송 추가 고려
                // 진단 시작할 때 정보 전송해야 함!
                Toast.makeText(getApplicationContext(), arrayList2.get(position) + "가 선택되었습니다.",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}