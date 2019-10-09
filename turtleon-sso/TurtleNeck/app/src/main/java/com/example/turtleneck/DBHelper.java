package com.example.turtleneck;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.support.v4.content.ContextCompat.startActivity;

public class DBHelper extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성

        // 테이블 이름 USER
        // 기본키 : 유저 번호 _id (자동으로 숫자 증가)
        // 유저이름 username    비밀번호 password   이메일주소 email
        db.execSQL("CREATE TABLE USER (_id_u INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT);");

        // 로그인 유지를 위한 세션 테이블
        // 기본키 : 자동 증가하는 숫자
        // 유저이름만 저장
        // 대충 만든거라 수정 필요
        db.execSQL("CREATE TABLE SESSION (_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT);");

        // 테이블 이름 BOARD
        // 기본키 : 게시글 번호 _id (자동으로 숫자 증가)
        // 게시글 작성 날짜 date
        // 게시글 제목 title     게시글 내용 content
        db.execSQL("CREATE TABLE BOARD (_id_b INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, title TEXT, content TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // SignActivity
    // 회원가입 기능
    public int InsertSign(String username, String password, String email) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        String u = "";

        // 중복 회원 가입 방지
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while (cursor.moveToNext()) {
            u = cursor.getString(1);
            if (u.equals(username)) {
                db.close();
                return 1;
            }
        }

        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO USER VALUES(null,'" + username + "','" + password + "','" + email + "');");
        db.close();
        return 0;
    }

    // LoginActivity
    // 로그인 기능 > 회원가입 된 정보와 비교하여 로그인 승인
    public int GetLogin(String u, String p) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String username = "";
        String password = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while (cursor.moveToNext()) {
            username = cursor.getString(1);
            password = cursor.getString(2);

            if (password.equals(p)&&username.equals(u)) {
                db.close();
                return 0;
            }
        }
        db.close();
        return 1;
    }

//    // LoginActivity
//    // 세션 유지하기
//    public void MakeSession(String u) {
//        SQLiteDatabase db = getWritableDatabase();
//
//        // 세션 테이블에 유저이름 저장
//        db.execSQL("INSERT INTO SESSION VALUES(null,'" + u + "');");
//        db.close();
//    }

    // MainActivity
    // 게시판 글 불러오기 > 게시판 기능
    public String GetResultBoard() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM BOARD", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + "번 게시글" + "\n"
                    + "작성 날짜 : " + cursor.getString(1) + "\n"
                    + "제목 : " + cursor.getString(2) +"\n"
                    + "내용 : " + cursor.getString(3) + "\n";
        }
        db.close();
        return result;
    }

    // MainActivity
    // 헤더에 유저이름, 유저이메일 띄우기
    public String GetHeader(String u) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        String username = "";

        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while (cursor.moveToNext()) {
            username = cursor.getString(1);
            if (username.equals(u)) {
                result += cursor.getString(3);
                db.close();
            }
        }
        return result;
    }

    // BoardActivity
    // 게시판 글쓰기
    public void InsertBoard(String date, String title, String content) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO BOARD VALUES(null,'" + date + "','" + title + "','" + content + "');");
        db.close();
    }

//    public void update(String item, int price) {
//        SQLiteDatabase db = getWritableDatabase();
//        // 입력한 항목과 일치하는 행의 가격 정보 수정
//        db.execSQL("UPDATE MONEYBOOK SET price=" + price + " WHERE item='" + item + "';");
//        db.close();
//    }

//    public void delete(String item) {
//        SQLiteDatabase db = getWritableDatabase();
//        // 입력한 항목과 일치하는 행 삭제
//        db.execSQL("DELETE FROM MONEYBOOK WHERE item='" + item + "';");
//        db.close();
//    }

}
