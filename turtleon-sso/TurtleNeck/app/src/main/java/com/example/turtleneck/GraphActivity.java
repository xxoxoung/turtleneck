package com.example.turtleneck;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GraphActivity extends AppCompatActivity implements View.OnClickListener {

    public String username;
    public Float diag;

    TextView DiagConfirm;
    TextView DiagLevel;
    ImageView DiagImg;
    TextView Standard;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        findViewById(R.id.GotoMainimg).setOnClickListener(this);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        diag = intent.getFloatExtra("diagconfirm",0f);

        DiagConfirm = (TextView) findViewById(R.id.DiagConfirm);
        DiagLevel = (TextView) findViewById(R.id.DiagLevel);
        DiagImg = (ImageView) findViewById(R.id.DiagImg);
        Standard = (TextView) findViewById(R.id.Standard);

        DiagConfirm.setText("당신의 진단값은 \n" + diag.toString() +" 입니다.");
        Standard.setText("\n 진단 기준 \nGOOD : 1.1106 <= 진단값\nFair : 0.9325 <= 진단값 <1.1106\nBad : 진단값 < 0.9325");

        // Good 1.1106 <= 진단값
        // Fair 0.9325 <= 진단값 < 1.1106
        // Bad 진단값 < 0.9325
        if(1.1106 <= diag) {
            DiagImg.setImageResource(R.drawable.diaggood);
            DiagLevel.setText("당신의 거북목 상태는\n Good 입니다.");
        } else if (0.9325 <= diag && diag < 1.1106) {
            DiagImg.setImageResource(R.drawable.diagfair);
            DiagLevel.setText("당신의 거북목 상태는\n Fair 입니다.");
        } else {
            DiagImg.setImageResource(R.drawable.diagbad);
            DiagLevel.setText("당신의 거북목 상태는\n Bad 입니다.");
        }
    }

    // 메인으로 버튼 클릭 이벤트
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }
}