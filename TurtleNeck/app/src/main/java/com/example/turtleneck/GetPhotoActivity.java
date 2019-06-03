package com.example.turtleneck;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetPhotoActivity extends MainActivity {
    ImageView imageView;
    Button Getpht;

    static final int GO_CAMERA = 1;
    static final int GO_GALLERY = 2;
    static final int TAKE_PHOTO = 3;

    // 카메라로 촬영한 이미지 저장 경로
    String mCurrentPhotoPath;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getphoto);

        imageView = findViewById(R.id.DiaPht);
        Getpht = findViewById(R.id.GetPht);

        Getpht.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 카메라로
                DialogInterface.OnClickListener gocam = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent1, GO_CAMERA);
                    }
                };

                // 갤러리로
                DialogInterface.OnClickListener gogal = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent2 = new Intent();
                        intent2.setType("image/*");
                        intent2.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent2, GO_GALLERY);
                    }
                };

                //취소
                DialogInterface.OnClickListener can = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                new AlertDialog.Builder(GetPhotoActivity.this)
                        .setTitle("사진을 골라주세요!")
                        .setPositiveButton("카메라로", gocam)
                        .setNeutralButton("취소", can)
                        .setNegativeButton("갤러리로", gogal)
                        .show();
            }
        });
    }

    private File createImageFile() throws IOException {
        // 이미지 파일 이름 생성
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        // 파일 저장
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // 카메라 인텐트 실행 함수
    private void TakeCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch(IOException e) {
                e.printStackTrace();
            }

            if(photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.turtleneck.fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        }
    }

    protected void onActtivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            // 카메라로 성공시 처리
            if(requestCode == GO_CAMERA) {
                /**
                try {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    if(bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                    //imageView.setImageURI(data.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                } **/
                TakeCameraIntent();
            }
            // 갤러리로 성공시 처리
            else if(requestCode == GO_GALLERY) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    imageView.setImageBitmap(img);
                    in.close();

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            // 사진을 저장하는 부분
            else if(requestCode == TAKE_PHOTO) {
                try {
                    File file = new File(mCurrentPhotoPath);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
                    if(bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}