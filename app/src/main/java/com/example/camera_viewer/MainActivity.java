package com.example.camera_viewer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @noinspection ALL
 */
public class MainActivity extends AppCompatActivity {
    private CameraPreview cameraPreview;
    private int cameraId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraPreview = new CameraPreview(this, cameraId);
        FrameLayout preview = findViewById(R.id.camera_preview);
        preview.addView(cameraPreview);


        Button switchButton = findViewById(R.id.btn_switch_camera);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraId = (cameraId == 0) ? 1 : 0;
                cameraPreview.switchCamera(cameraId);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}