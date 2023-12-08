package com.example.camera_viewer;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.IOException;

/**
 * @noinspection ALL
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private Camera camera;

    private SurfaceHolder surfaceHolder;

    private int cameraId;

    public CameraPreview(Context context, int id) {
        super(context);
        this.cameraId = id;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    public void switchCamera(int cameraId) {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        this.cameraId = cameraId;
        openCamera();
    }

    private void openCamera() {
        camera = Camera.open(cameraId);
        camera.setDisplayOrientation(90);
        startCameraPreview();
    }

    private void startCameraPreview() {
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        openCamera();
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
