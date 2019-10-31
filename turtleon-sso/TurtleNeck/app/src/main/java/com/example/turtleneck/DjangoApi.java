package com.example.turtleneck;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface DjangoApi {

    String DJANGO_SITE = "http://turtleneck.ngrok.io/";

    // SignActivity
    // 회원가입
    @Multipart
    @POST("rest-auth/registration/")
    Call<ResponseBody> post_signup(
            @Part("username") RequestBody username,
            @Part("emailadress") RequestBody emailadress,
            @Part("password1") RequestBody password1,
            @Part("password2") RequestBody password2);

    // LoginActivity
    // 로그인 정보 전송
    @Multipart
    @POST("rest-auth/login/")
    Call<ResponseBody> post_login(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password);

    // GetPhotoActivity uploadImage()
    // 사진, 좌표, 키, 성별, 진단번호 변수(count), 알고리즘 변수(confirm), username
    @Multipart
    @POST("image/upload/")
    Call<ResponseBody> uploadFile(
            @Part MultipartBody.Part file,
            @Part("point_x") RequestBody point_x,
            @Part("point_y") RequestBody point_y,
            @Part("tall") RequestBody tall,
            @Part("gender") RequestBody gender,
            @Part("count") RequestBody count,
            @Part("confirm") RequestBody confirm,
            @Part("username") RequestBody username);

    // GraphActivity
    // 진단값 가져오기
    // 서버 연결 후 수정하기
    @GET("image/diag/")
    Call<String> GetDiagValue();

    // ModifySignActivity
    // 회원가입 정보 수정
    // 로그인 구현 후 username 추가 필요
    @Multipart
    @POST("rest-auth/registration/")
    Call<ResponseBody> post_modifysign(
            @Part("password") RequestBody password,
            @Part("emailadress") RequestBody emailadress);
}