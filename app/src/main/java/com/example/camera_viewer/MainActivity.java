package com.example.camera_viewer;

import android.os.Bundle;
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
    }
}