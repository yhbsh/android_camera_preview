package com.example.camera_viewer;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

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

    private int cameraId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SurfaceView surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        final Button button = findViewById(R.id.btn_switch_camera);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cameraId == 0) {
                    cameraId = 1;
                } else {
                    cameraId = 0;
                }

                camera = Camera.open(cameraId);

                camera.setDisplayOrientation(90);

                try {
                    camera.setPreviewDisplay(surfaceHolder);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                camera.startPreview();
            }
        });
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
        camera = Camera.open(cameraId);

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