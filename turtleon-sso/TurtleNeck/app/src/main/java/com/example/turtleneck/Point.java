package com.example.turtleneck;

// 사진 좌표 전송을 위한 클래스
public class Point {

    // 실제 데이터베이스와 이름이 같아야 함
    //public String username;
    public String point_x;
    public String point_y;

    //public void setName(String Name) { this.username = Name;}
    public void setX(String x) { this.point_x = x;}
    public void setY(String y) { this.point_y = y;}
}
