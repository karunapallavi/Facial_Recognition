package com.example.karuna.facial_recognition;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.attr.id;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private Camera camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageButton();
    }

    private void initImageButton() {
        Button ib = (Button) findViewById(R.id.button);
        ib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                takePhoto();
            }
        });
    }

    public void takePhoto() {
        View v = null;
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
        //cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        //startActivityForResult(cameraIntent, CAMERA_REQUEST);

        camera = Camera.open(2); //open front camera
        startTimer(v);
    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");

            }
        }
    }*/

    public void startTimer(View v){

        // 5000ms=5s at intervals of 500ms=0.5s so that means it lasts 5 seconds taking 10 images
        new CountDownTimer(5000,500){

            @Override
            public void onFinish() {
                // count finished
                camera.takePicture(null, null, null, jpegCallBack);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                // every time 0.5 second passes
                camera.takePicture(null, null, null, jpegCallBack);

            }

        }.start();
    }

    Camera.PictureCallback jpegCallBack=new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            // set file destination and file name
            File destination=new File(Environment.getExternalStorageDirectory(),"myPicture.jpg");
            try {
                Bitmap userImage = BitmapFactory.decodeByteArray(data, 0, data.length);
                FileOutputStream out = new FileOutputStream(destination);
                // set compress format quality and stream
                userImage.compress(Bitmap.CompressFormat.JPEG, 90, out);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    };




}

