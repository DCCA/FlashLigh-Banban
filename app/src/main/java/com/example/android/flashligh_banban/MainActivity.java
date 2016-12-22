package com.example.android.flashligh_banban;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

@RequiresApi(api = Build.VERSION_CODES.M)

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.gif_window);
        Glide.with(this).load(R.drawable.waiting).into(new GlideDrawableImageViewTarget(imageView));
    }

    public void state(View view){
        ToggleButton getState = (ToggleButton) findViewById(R.id.switch1);
        Boolean isChecked = getState.isChecked();
        Log.i("State","Is " + isChecked);
        on(isChecked);
        changeGif(isChecked);
    }

    public void changeGif(Boolean isChecked) {
        if (isChecked) {
            ImageView imageView = (ImageView) findViewById(R.id.gif_window);
            Glide.with(this).load(R.drawable.cheering).into(new GlideDrawableImageViewTarget(imageView));
        } else{
            ImageView imageView = (ImageView) findViewById(R.id.gif_window);
            Glide.with(this).load(R.drawable.waiting).into(new GlideDrawableImageViewTarget(imageView));
        }
    }

    private void on(Boolean isChecked){
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        if (isChecked){
            try {
                cameraManager.setTorchMode(getRearCamera2(),true);
            } catch (CameraAccessException e){
            }
        } else {
            try {
                cameraManager.setTorchMode(getRearCamera2(),false);
            } catch (CameraAccessException e){

            }
        }
    }

    private String getRearCamera2(){
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String[] idCameraList;
        try {
            idCameraList = cameraManager.getCameraIdList();
        } catch (CameraAccessException e) {
            return null;
        }
        for (String cameraId : idCameraList) {
            CameraCharacteristics cameraCharacteristics = null;
            try {
                cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
            } catch (CameraAccessException e){
                return null;
            }
            Integer lensFacing = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
            if (lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK){
                return  cameraId;
            }
        }
        return null;
    }

}
