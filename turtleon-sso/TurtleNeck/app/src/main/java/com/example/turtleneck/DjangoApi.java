package com.example.turtleneck;
//업로드
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DjangoApi {

    String DJANGO_SITE = "http://6ce9cd5c.ngrok.io/";
    //내 authtoken 주소/ngrok 끄면 다시 켜서 주소 바꿔줘야함..

    // SignActivity 회원가입
    @Multipart
    @POST("rest-auth/registration/")
    Call<ResponseBody> post_signup(
            @Part("username") RequestBody username,
            @Part("emailadress") RequestBody emailadress,
            @Part("password1") RequestBody password1,
            @Part("password2") RequestBody password2);

    // LoginActivity 로그인 정보 전송
    @Multipart
    @POST("rest-auth/login/")
    Call<ResponseBody> post_login(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password,
            @Part("email") RequestBody emailadress);

    // GetPhotoActivity uploadImage()
    // 사진 + 좌표 전송
    // 키, 성별 추가
    @Multipart
    @POST("image/upload/")
    Call<ResponseBody> uploadFile(
            @Part MultipartBody.Part file,
            @Part("point_x") RequestBody point_x,
            @Part("point_y") RequestBody point_y,
            @Part("tall") RequestBody tall,
            @Part("gender") RequestBody gender);

//    // DiagActivity 진단 관련 정보 전송
//    @Multipart
//    @POST("image/upload/")
//    Call<ResponseBody> post_diag(
//        @Part("tall") RequestBody tall,
//        @Part("gender") RequestBody gender);
}