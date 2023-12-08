package com.example.camera_viewer;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

/**
 * @noinspection ALL
 */
public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    static final Integer CAMERA_PERMISSION_CODE = 200;
    private Camera camera;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final SurfaceView surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null) {
            camera.stopPreview();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (camera != null) {
            camera.startPreview();
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        camera = Camera.open();

        camera.setDisplayOrientation(90);

        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        camera.startPreview();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if (camera != null) {
            camera.toString();
            camera.release();
            camera = null;
        }
    }
}