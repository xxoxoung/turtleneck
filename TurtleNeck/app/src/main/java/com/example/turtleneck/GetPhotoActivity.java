package com.example.turtleneck;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GetPhotoActivity extends MainActivity implements View.OnClickListener {
    ImageView imageView;
    Button Getpht;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getphoto);

        imageView = findViewById(R.id.DiaPht);
        Getpht = findViewById(R.id.GetPht);

        Getpht.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
    }

    protected void onActivityReslut(int requestcode, int resultcode, Intent data) {
        if(requestcode == 1) {
            imageView.setImageURI(data.getData());
        }
    }
}