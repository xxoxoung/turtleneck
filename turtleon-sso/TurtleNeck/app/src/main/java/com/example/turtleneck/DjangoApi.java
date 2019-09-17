package com.example.turtleneck;
//업로드
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DjangoApi {

    String DJANGO_SITE = "http://e49150c1.ngrok.io/";
    //내 authtoken 주소/ngrok 끄면 다시 켜서 주소 바꿔줘야함..

    // GetPhotoActivity uploadImage()
    @Multipart
    @POST("image/upload/")
    Call<RequestBody> uploadFile(
            @Part MultipartBody.Part file,
            @Part("point_x") RequestBody point_x,
            @Part("point_y") RequestBody point_y);

//    // GetPhotoActivity 좌표전송 Point
//    @POST("image/upload/")
//    Call<Point> post_point(@Body Point point);

    // SignActivity 회원가입 Signup
    @POST("rest-auth/registration/")
    Call<Signup> post_signup(@Body Signup signup);
}