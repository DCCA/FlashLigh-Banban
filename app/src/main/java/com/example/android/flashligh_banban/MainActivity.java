package com.example.android.flashligh_banban;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

@RequiresApi(api = Build.VERSION_CODES.M)

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void state(View view){
        Switch getState = (Switch) findViewById(R.id.switch1);
        Boolean isChecked = getState.isChecked();
        Log.i("State","Is " + isChecked);
        CameraManager camera = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        on();
//        onOff(isChecked, camera);
    }

    public void on(){
        CameraManager camera = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            try {
                for (String idCamera : camera.getCameraIdList()){
//                    camera.setTorchMode(idCamera,true);
                    continue;
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
    }

//    public void onOff(Boolean isChecked, CameraManager camera){
//        //getting the camera id
//        if (isChecked){
//            try {
//                for (String idCamera : camera.getCameraIdList()){
//                    camera.setTorchMode(idCamera,true);
//                    continue;
//                }
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

}
