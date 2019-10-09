package com.example.turtleneck;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.os.Build.VERSION.SDK_INT;

public class NewsActivity extends AppCompatActivity {

    private WebView mWebView1;
    private WebView mWebView2;
    private WebView mWebView3;
    private WebView mWebView4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // 뉴스기사 URL 추가되면 추가하기!!!
        // 2019.10.09 기준 4개
        final String newsString[] = {"https://www.insight.co.kr/news/236917",
                "http://www.polinews.co.kr/news/article.html?no=423980",
                "http://www.polinews.co.kr/news/article.html?no=423741#0B4G",
                "http://www.munhwa.com/news/view.html?no=2019091001032842000003"};

        // WebView 연결
        mWebView1 = (WebView) findViewById(R.id.webView1);
//        mWebView2 = (WebView) findViewById(R.id.webView2);
//        mWebView3 = (WebView) findViewById(R.id.webView3);
//        mWebView4 = (WebView) findViewById(R.id.webView4);

        // 에러 해결
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mWebView1.getSettings().setSafeBrowsingEnabled(false);
//            mWebView2.getSettings().setSafeBrowsingEnabled(false);
//            mWebView3.getSettings().setSafeBrowsingEnabled(false);
//            mWebView4.getSettings().setSafeBrowsingEnabled(false);
        }

        // 자바스크립트 허용
        mWebView1.getSettings().setJavaScriptEnabled(true);

        // 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 자동 조정
        mWebView1.getSettings().setLoadWithOverviewMode(true);

//        // 터치 이벤트 구현
//        mWebView1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.d("터치했습니다~!!!!",newsString[0]);
//                return false;
//            }
//        });

        // 새 창이 뜨는 것 방지
        mWebView1.setWebViewClient(new WebViewClient() {
            public boolean shoulOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        // 웹뷰에서 보여줄 URL 추가
        Log.d("웹뷰 띄운다~!!!!",newsString[0]);
        mWebView1.loadUrl(newsString[0]);
        Log.d("웹뷰 성공ㅇㅇㅇㅇㅇㅇㅇ~!!!!",newsString[0]);

//        // 웹뷰에서 크롬이 실행 가능하도록
//        mWebView1.setWebChromeClient(new WebChromeClient());
//        mWebView2.setWebChromeClient(new WebChromeClient());
//        mWebView3.setWebChromeClient(new WebChromeClient());
//        mWebView4.setWebChromeClient(new WebChromeClient());

//        mWebView1.loadUrl(newsString[0]);
//        mWebView2.loadUrl(newsString[1]);
//        mWebView3.loadUrl(newsString[2]);
//        mWebView4.loadUrl(newsString[3]);
    }

    // 뒤로가기 기능 추가
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(mWebView1.canGoBack()) {
                mWebView1.goBack();
                return false;
            }
        }
        return super.onKeyDown(keyCode,event);
    }
}
