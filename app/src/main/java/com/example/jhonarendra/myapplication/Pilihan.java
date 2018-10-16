package com.example.jhonarendra.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Jhonarendra on 9/27/2018.
 */

public class Pilihan extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public void setFilterNormal(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void setFilterGrayscale(View view){
        Intent intent = new Intent(getApplicationContext(), GrayscaleFilter.class);
        startActivity(intent);
    }
    public void setFilterCanny(View view){
        Intent intent = new Intent(getApplicationContext(), CannyFilter.class);
        startActivity(intent);
    }
    public void setFilterGaussian(View view){
        Intent intent = new Intent(getApplicationContext(), GaussianFilter.class);
        startActivity(intent);
    }
    public void tesGetPixel(View view){
        Intent intent = new Intent(getApplicationContext(), TesGetPixel.class);
        startActivity(intent);
    }
}
