package com.example.torchapp;



import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;


import com.example.torchapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
   ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (binding.button.getText().toString().equals("on")){
                binding.button.setText("off");
                binding.flashImage.setImageResource(R.drawable.flashlighton);
                changeLightState(true);
                }else{
                    binding.button.setText("on");
                    binding.flashImage.setImageResource(R.drawable.flashlightoff);
                    changeLightState(false);
                }

            }
        });

    }

    private void changeLightState(boolean State)  {
        CameraManager cameraManager= (CameraManager) getSystemService(CAMERA_SERVICE);
        String CamId=null;
        try {
            CamId=cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(CamId,State);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText("on");
    }


}