package com.example.turtleneck;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class GetPhotoActivity extends MainActivity {
    ImageView imageView;
    Button Getpht;

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
                        startActivityForResult(intent1, 1);
                    }
                };

                // 갤러리로
                DialogInterface.OnClickListener gogal = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent2 = new Intent();
                        intent2.setType("image/*");
                        intent2.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent2, 2);
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

    protected void onActtivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == 1) {
                try {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    Log.i("카메라 사진","img" );
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(requestCode == 2) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    Log.i("갤러리 사진","img" );
                    imageView.setImageBitmap(img);

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}