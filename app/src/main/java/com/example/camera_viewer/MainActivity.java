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
    private CameraPreview mPreview;
    private int cameraId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mPreview = new CameraPreview(this, cameraId);

        FrameLayout preview = findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        Button switchButton = findViewById(R.id.btn_switch_camera);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraId = (cameraId == 0) ? 1 : 0;
                mPreview.switchCamera(cameraId);
            }
        });
    }
}