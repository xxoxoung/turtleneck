package com.example.turtleneck;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

// 진단 결과 그래프로 보여주는 화면
public class GraphActivity extends AppCompatActivity implements View.OnClickListener {

    // 막대그래프 그리기
    private GraphicalView mChartView;

    private String[] mLable = new String[]{
            "",
            "당신의 \n진단값",
            "진단 \n기준값"
    };

    // 진단 기준값에서 뵤여주고 싶은 레이블,,,
    private String[] standard = new String[]{
            "",
            "Bad",
            "Fair",
            "Good"
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        findViewById(R.id.GotoMainBtn).setOnClickListener(this);

        drawChart();
    }

    // 메인으로 버튼 클릭 이벤트
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // 막대 그래프 그리기
    private void drawChart() {

        // X축 항목 갯수
        // 0 > 쓰레기값 (진단값 잘리는 것 해결하기 위해 만듬
        // 1 > 사용자의 진단값
        // 2 > 진단 기준값
        int[] x = {0,1,2};

        // 사용자의 진단값
        // 서버에서 온 값으로 변경
        int diag = 10000;
        int[] tester1 = {6000,diag,6000};

        // 진단 기준값
        // Good 1.1106 <= 진단값
        // Fair 0.9325 <= 진단값 < 1.1106
        // Bad 진단값 < 0.9325
        // 현재 10000 곱한 값으로 그래프 그리는중
        // 나중에 값 처리 더 필요함
        int Bad = 7500;         // 임시 기준값 그래프를 어느 정도로 보여줄지 생각 필요
        int Fair = 9325;
        int Good = 11106;
        int[] tester2 = {6000,6000,Bad};
        int[] tester3 = {6000,6000,Fair};
        int[] tester4 = {6000,6000,Good};

        // 진단 결과를 보여줄 XYSeries 생성
        // 안의 텍스트는 항목 보여주는 범례 같은거 추가되는거
        // 왼쪽 하단의 작은 네모 > legend
        XYSeries testers1 = new XYSeries("");
        XYSeries testers2 = new XYSeries("");
        XYSeries testers3 = new XYSeries("");
        XYSeries testers4 = new XYSeries("");

        // 데이터와 연결
        // 테스터에 데이터 추가
        for (int i = 0; i < x.length; i++) {
            testers1.add(i, tester1[i]);
            testers2.add(i, tester2[i]);
            testers3.add(i, tester3[i]);
            testers4.add(i, tester4[i]);
        }

        // 데이터 셋 생성
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        // 데이터 셋에 테스터 추가
        dataset.addSeries(testers1);
        dataset.addSeries(testers2);
        dataset.addSeries(testers3);
        dataset.addSeries(testers4);

        //커스터마이즈 위한 데이터 연결
        // 테스터 커스터마이즈 위한 XYSeriesRenderer 생성
        // 사용자의 진단값
        XYSeriesRenderer testerRenderer1 = new XYSeriesRenderer();
        testerRenderer1.setColor(Color.BLACK);
        testerRenderer1.setFillPoints(true);
        testerRenderer1.setLineWidth(10);
        testerRenderer1.setDisplayChartValues(false);
        testerRenderer1.setDisplayChartValuesDistance(100);

        // Bad 기준값
        XYSeriesRenderer testerRenderer2 = new XYSeriesRenderer();
        testerRenderer2.setColor(Color.RED);
        testerRenderer2.setFillPoints(true);
        testerRenderer2.setLineWidth(10);
        testerRenderer2.setDisplayChartValues(false);
        testerRenderer2.setDisplayChartValuesDistance(100);

        // Fair 기준값
        XYSeriesRenderer testerRenderer3 = new XYSeriesRenderer();
        testerRenderer3.setColor(Color.GREEN);
        testerRenderer3.setFillPoints(true);
        testerRenderer3.setLineWidth(10);
        testerRenderer3.setDisplayChartValues(false);
        testerRenderer3.setDisplayChartValuesDistance(100);

        // Good 기준값
        XYSeriesRenderer testerRenderer4 = new XYSeriesRenderer();
        testerRenderer4.setColor(Color.BLUE);
        testerRenderer4.setFillPoints(true);
        testerRenderer4.setLineWidth(10);
        testerRenderer4.setDisplayChartValues(false);
        testerRenderer4.setDisplayChartValuesDistance(100);

        // 전체 차트 커스터마이즈 위한
        // 그래픽 속성 지정 객체 XYMultipleSeriesRenderer 생성
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.VERTICAL);
        multiRenderer.setXLabels(0);
        //multiRenderer.setChartTitle("\n \n \n \n \t<당신의 진단 결과>");
        //multiRenderer.setChartTitleTextSize(50);

        // VERTICAL 이라서 X축, Y축이 뒤바뀜
        //multiRenderer.setYTitle("목 기울기 변환값");           // 가로축
        //multiRenderer.setXTitle("항목");                       // 세로축

        // 그래프 커스터마이징
        // 폰트 설정
        multiRenderer.setChartTitleTextSize(50);                                        // 차트 제목 크기
        multiRenderer.setAxisTitleTextSize(50);
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);    // 텍스트 스타일

        // 확대, 클릭
        multiRenderer.setZoomButtonsVisible(false);                                     // 확대 버튼 보이게 할 것인지
        multiRenderer.setPanEnabled(false, false);                 // setting pan enablity which uses graph to move on both axis
        multiRenderer.setClickEnabled(false);                                           // 차트에 대해 클릭 비활성화
        multiRenderer.setZoomEnabled(false, false);                // X축, Y축 확대 비활성화
        multiRenderer.setZoomEnabled(false);                                            // 차트에 대한 확대 비활성화
        multiRenderer.setExternalZoomEnabled(false);                                    // 차트 외부에 대한 확대 비활성화
        multiRenderer.setInScroll(false);                                               // 스크롤 허용

        // 그리드
        multiRenderer.setShowGridY(false);                                              // Y Grid
        multiRenderer.setShowGridX(false);                                              // X Grid
        multiRenderer.setGridColor(Color.BLACK);                                        // 그리드 색
        multiRenderer.setShowGrid(false);                                               // 그리드 보여주기

        // 그래프 색 설정
        multiRenderer.setBackgroundColor(Color.WHITE);                                  // 그래프의 배경색 설정
        multiRenderer.setMarginsColor(Color.WHITE);                                     // 그래프의 margin 색 설정
        multiRenderer.setApplyBackgroundColor(true);                                    // 그래프의 배경색 설정 (false = 투명)

        multiRenderer.setFitLegend(false);                                              // 스크린 사이즈에 맞게 (false = legend 안보임)
        //multiRenderer.isShowLegend(false);                                            // 왜 오류 뜨는지 모르는중,,,
        multiRenderer.setAntialiasing(true);                                            // 앨리어싱

        // 높이, 정렬
        multiRenderer.setLegendHeight(10);                                              // 높이 설정
        multiRenderer.setXLabelsAlign(Paint.Align.RIGHT);                               // X축 정렬
        multiRenderer.setYLabelsAlign(Paint.Align.CENTER);                              // Y축 정렬

        // 축 설정
        multiRenderer.setYLabels(0);                                                    // Y축 레이블 갯수 설정
        multiRenderer.setYAxisMax(11200);                                               // Y 최대값
        multiRenderer.setXAxisMax(x.length +1);                                         // X 최대값
        multiRenderer.setXAxisMin(0);                                                   // setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setLabelsTextSize(30);                                            // X축 레이블 크기
        multiRenderer.setAxesColor(Color.WHITE);                                        // X축, Y 축 색상
        multiRenderer.setBarSpacing(0);                                                 // 두 항목 (bar) 사이의 거리 >> 진단값과 진단 기준 사이의 거리
        multiRenderer.setMargins(new int[]{200,400,200,400});                           // 그래프의 margin 설정 (위, 왼쪽, 아래, 오른쪽) > VERTICAL 이라 (오른쪽, 아래, 왼쪽, 위)

        // X축(세로)에 텍스트 레이블 추가
        for (int i = 0; i < x.length; i++) {
            multiRenderer.addXTextLabel(i, mLable[i]);
        }

        // multiRenderer에 testRenderer 추가
        // 데이터셋에 데이터시리즈를 추가한 순서와
        // 멀티렌더러에 렌더러들을 추가한 순서가 같아야 함
        multiRenderer.addSeriesRenderer(testerRenderer1);
        multiRenderer.addSeriesRenderer(testerRenderer2);
        multiRenderer.addSeriesRenderer(testerRenderer3);
        multiRenderer.addSeriesRenderer(testerRenderer4);

        // 그래프를 레이아웃에 그리기
        LinearLayout layout = (LinearLayout) findViewById(R.id.chart_bar);

        // 그래프 그리기전에 있는 모든 뷰 제거
        layout.removeAllViews();

        // 막대 그래프 그리기
        mChartView = ChartFactory.getBarChartView(this, dataset, multiRenderer, BarChart.Type.DEFAULT);
        layout.addView(mChartView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT));
    }
}
