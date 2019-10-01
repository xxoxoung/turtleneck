package com.example.turtleneck;
//업로드
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface DjangoApi {

    String DJANGO_SITE = "http://89d1965d.ngrok.io/";
    //내 authtoken 주소/ngrok 끄면 다시 켜서 주소 바꿔줘야함..

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
            @Part("password") RequestBody password,
            @Part("email") RequestBody emailadress);

    // LoginActivity
    // 로그인 여부 확인
    @GET("rest-auth/login/{username}")
    Call<ResponseBody> get_login( );

    // GetPhotoActivity uploadImage()
    // 사진, 좌표, 키, 성별, 진단번호 변수(count), 알고리즘 변수(confirm)
    // 유저네임
    @Multipart
    @POST("image/upload/")
    Call<ResponseBody> uploadFile(
            @Part MultipartBody.Part file,
            @Part("point_x") RequestBody point_x,
            @Part("point_y") RequestBody point_y,
            @Part("tall") RequestBody tall,
            @Part("gender") RequestBody gender,
            @Part("count") RequestBody count,
            @Part("confirm") RequestBody confirm);
}