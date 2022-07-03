package com.example.ejercicio21;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.ejercicio21.Procesos.SQLiteConexion;
import com.example.ejercicio21.Procesos.Transacciones;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 101;
    VideoView videoView;
    Button btnvideo, btnguardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.videoView);
        btnvideo = (Button) findViewById(R.id.btnvideo);
        btnguardar = (Button) findViewById(R.id.btnguardar);

        //declarar
        btnvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrabarVideo();
            }
        });

    }


    private void GrabarVideo() {
        Intent Grabarvideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(Grabarvideo.resolveActivity(getPackageManager())!= null){

            //iniciar el activity
            startActivityForResult(Grabarvideo, REQUEST_VIDEO_CAPTURE);

        }
    }
    //obtener el metodo on result

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //ver si la respuesta es igual al acaptura del video
        if(requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK){
            //obtener la uri
            Uri videoUri = data.getData();
            //cargar el video
            videoView.setVideoURI(videoUri);
        }
    }
}