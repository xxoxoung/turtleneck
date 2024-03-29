package com.example.turtleneck;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.lang.String;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 사진 가져오는 화면
public class GetPhotoActivity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = getClass().getSimpleName();
    ImageView imageView;
    ImageView HelpImg;
    ImageView GoCam;
    ImageView GoServer;
    final static int TAKE_PHOTO = 1;

    // 사진 이름을 위한 변수
    double cnt = 0;

    // 서버에서 알고리즘을 돌리기 위한 변수
    String confirm = "-1";

    public double height;                             // 사진 세로 크기
    public double width;                              // 사진 가로 크기

    String mCurrentPhotoPath;

    static final int REQUEST_TAKE_PHOTO = 1;

    public String tall;
    public String gender;
    public String username;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getphoto);

        // DiagActivity 에서 tall, gender 받아오기
        Intent intent = getIntent();
        tall = intent.getStringExtra("tall");
        gender = intent.getStringExtra("gender");
        username = intent.getStringExtra("username");

        // 레이아웃 연결
        imageView = findViewById(R.id.DiaPht);  // 이미지뷰
        GoCam = findViewById(R.id.GoCam);       // 카메라
        GoServer = findViewById(R.id.GoServer); // 서버로 전송
        HelpImg = findViewById(R.id.HelpImg);   // 도움말

        // 클릭 리스너 연결
        GoServer.setOnClickListener(this);      // 서버로
        GoCam.setOnClickListener(this);         // 카메라로
        HelpImg.setOnClickListener(this);       // 도움말 아이콘

        // 6.0 마쉬멜로우 이상일 경우에는 권한 체크 후 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
        }
    }

    // 클릭 이벤트리스너 처리
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.GoCam: {
                // 카메라 앱을 여는 소스
                dispatchTakePictureIntent();
                break;
            }

            case R.id.GoServer: {
                //서버로 보내는 함수 호출
                uploadImage();

                // 이미지 전송 >> 로딩 화면 >> 서버 알고리즘 실행 후 >> 그래프 화면 (진단확인 화면)
                Intent intent1 = new Intent(this, DelayActivity.class);
                intent1.putExtra("username",username);
                startActivity(intent1);
                finish();
                break;
            }

            // 도움말 페이지 열기
            case R.id.HelpImg: {
                Intent intent2 = new Intent(this, HelpPhotoActivity.class);
                intent2.putExtra("tall",tall);
                intent2.putExtra("gender",gender);
                intent2.putExtra("username",username);
                startActivity(intent2);
                break;
            }
        }
    }

    // 카메라 실행
    private void dispatchTakePictureIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 인텐트 확인
        if (intent.resolveActivity(getPackageManager()) != null) {

            // 파일 만들고 초기화
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // 에러난 경우
            }

            // 파일이 정상적으로 만들어지면 계속
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.turtleneck.fileprovider",
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                intent.putExtra("username",username);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    // 카메라로 촬영한 이미지를 파일로 저장
    private File createImageFile() throws IOException {

        // 파일 이름 만들기
        // 이름 변경 > 유저이름 cnt++
        cnt++;

        String imageFileName = username + cnt;

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // 파일 저장
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // 카메라로 촬영한 영상을 가져오는 부분
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        try {
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO: {
                    if (resultCode == RESULT_OK) {
                        File file = new File(mCurrentPhotoPath);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));

                        if (bitmap != null) {
                            ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

                            // 사진 회전하기
                            Bitmap rotatedBitmap = null;
                            switch (orientation) {
                                case ExifInterface.ORIENTATION_ROTATE_90: {
                                    rotatedBitmap = rotateImage(bitmap, 90);
                                    break;
                                }
                                case ExifInterface.ORIENTATION_ROTATE_180: {
                                    rotatedBitmap = rotateImage(bitmap, 180);
                                    break;
                                }
                                case ExifInterface.ORIENTATION_ROTATE_270: {
                                    rotatedBitmap = rotateImage(bitmap, 270);
                                    break;
                                }
                                case ExifInterface.ORIENTATION_NORMAL:
                                default:
                                    rotatedBitmap = bitmap;
                            }

                            // 이미지뷰에 사진 띄우기
                            imageView.setImageBitmap(rotatedBitmap);

                            // 진단에 사용할 좌표값
                            height = rotatedBitmap.getHeight();
                            width = rotatedBitmap.getWidth();
                            height = ((height * 2) / 3) / (4.2);
                            width = ((width * 2) / 3) / (4.2);
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 사진 돌려주는 함수
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    //이미지 서버로 전송하는 함수
    public void uploadImage() {
        File imageFile = new File(mCurrentPhotoPath);

        // double을 String으로 변환
        String point_x = Double.toString(width);
        String point_y = Double.toString(height);
        String count = Double.toString(cnt);

        // 네트워크 빌드
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DjangoApi.DJANGO_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DjangoApi postApi = retrofit.create(DjangoApi.class);

        // 사용자의 진단 사진 정보
        RequestBody requestImage = RequestBody.create(MediaType.parse("multipart/data"), imageFile);
        RequestBody requestPointX = RequestBody.create(MediaType.parse("multipart/data"), point_x);
        RequestBody requestPointY = RequestBody.create(MediaType.parse("multipart/data"), point_y);

        // 사용자의 진단 정보
        RequestBody requestTall = RequestBody.create(MediaType.parse("multipart/data"), tall);
        RequestBody requestGender = RequestBody.create(MediaType.parse("multipart/data"), gender);

        // 알고리즘을 위한 변수 (사진 이름 인식, 알고리즘 실행 변수)
        RequestBody requestCount = RequestBody.create(MediaType.parse("multipart/data"), count);
        RequestBody requestConfirm = RequestBody.create(MediaType.parse("multipart/data"), confirm);

        // username도 같이 보내기 (진단 확인에 쓰일 예정)
        RequestBody requestUsername = RequestBody.create(MediaType.parse("multipart/data"), username);

        MultipartBody.Part multiPartBody = MultipartBody.Part.createFormData("model_pic", imageFile.getName(), requestImage);

        // 정보를 하나로 묶어서 서버로 전송
        Call<ResponseBody> postcall = postApi.uploadFile(multiPartBody, requestPointX, requestPointY, requestTall, requestGender, requestCount, requestConfirm, requestUsername);
        postcall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
}
