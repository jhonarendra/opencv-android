package com.example.jhonarendra.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jhonarendra on 10/4/2018.
 */

public class TesGetPixel extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pixel);

        Button btnUpload = (Button)findViewById(R.id.btn_pilih_gambar);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, RESULT_LOAD_IMG);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Bitmap bitmap = Bitmap.createBitmap(8, 8, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);

        if (resultCode == RESULT_OK){
            try {
                final Uri imgUri = data.getData();
                final InputStream imgStream;
                imgStream = getContentResolver().openInputStream(imgUri);
                final Bitmap selectedImg = BitmapFactory.decodeStream(imgStream);

                final ImageView imgSrc = (ImageView)findViewById(R.id.image_src);
                imgSrc.setImageBitmap(selectedImg);

                final TextView textPixel = (TextView)findViewById(R.id.text_pixel);
                Button btnGetPixel = (Button)findViewById(R.id.btn_get_pixel);
                Button btnFlip = (Button)findViewById(R.id.btn_flip);
                Button btnFlipH = (Button)findViewById(R.id.btn_flip_h);
                Button btnBrightness = (Button)findViewById(R.id.btn_brightness);

                final Bitmap bitmap = ((BitmapDrawable)imgSrc.getDrawable()).getBitmap();

                btnGetPixel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        textPixel.setText("Resolusi gambar\npanjang :"+bitmap.getWidth()+"\nlebar :"+bitmap.getHeight()+"\n\n");
                        Bitmap resultBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true);
                        for (int i=0; i<bitmap.getHeight(); i++){
                            for (int j=0; j<bitmap.getWidth(); j++){
                                int pixel = bitmap.getPixel(j,i);
                                int r = Color.red(pixel);
                                int g = Color.green(pixel);
                                int b = Color.blue(pixel);

                                int gr = (r+g+b)/3;

//                                textPixel.append("("+r+","+g+","+b+") ");
                                textPixel.append(""+gr+"\t\t");
                                setTxt("("+r+","+g+","+b+") ");
                            }
                            textPixel.append("\n");
                            setTxt("\n");
                        }
                        ImageView imgOut = (ImageView)findViewById(R.id.image_out);
                        imgOut.setImageBitmap(resultBitmap);
                    }
                });

                btnFlip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        textPixel.setText("Resolusi gambar\npanjang :"+bitmap.getWidth()+"\nlebar :"+bitmap.getHeight()+"\n\n");
                        Bitmap resultBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true);
                        for (int i=0; i<bitmap.getHeight(); i++){
                            for (int j=0; j<bitmap.getWidth(); j++){


                                int pixel = bitmap.getPixel(bitmap.getHeight()-i-1,j);
                                int r = Color.red(pixel);
                                int g = Color.green(pixel);
                                int b = Color.blue(pixel);

                                int gr = (r+g+b)/3;

//                                textPixel.append("("+r+","+g+","+b+") ");

                                textPixel.append(""+gr+"\t\t");
                                setTxt("("+r+","+g+","+b+") ");

                                pixel = Color.rgb(r,g,b);

                                resultBitmap.setPixel(j,i,pixel);
                            }
                            textPixel.append("\n");
                            setTxt("\n");
                        }
                        ImageView imgOut = (ImageView)findViewById(R.id.image_out);
                        imgOut.setImageBitmap(resultBitmap);
                    }
                });

                btnFlipH.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        textPixel.setText("Resolusi gambar\npanjang :"+bitmap.getWidth()+"\nlebar :"+bitmap.getHeight()+"\n\n");
                        Bitmap resultBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true);
                        for (int i=0; i<bitmap.getHeight(); i++){
                            for (int j=0; j<bitmap.getWidth(); j++){


                                int pixel = bitmap.getPixel(i,bitmap.getWidth()-j-1);
                                int r = Color.red(pixel);
                                int g = Color.green(pixel);
                                int b = Color.blue(pixel);

                                int gr = (r+g+b)/3;

//                                textPixel.append("("+r+","+g+","+b+") ");

                                textPixel.append(""+gr+"\t\t");
                                setTxt("("+r+","+g+","+b+") ");

                                pixel = Color.rgb(r,g,b);

                                resultBitmap.setPixel(j,i,pixel);
                            }
                            textPixel.append("\n");
                            setTxt("\n");
                        }
                        ImageView imgOut = (ImageView)findViewById(R.id.image_out);
                        imgOut.setImageBitmap(resultBitmap);
                    }
                });

                btnBrightness.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        textPixel.setText("Resolusi gambar\npanjang :"+bitmap.getWidth()+"\nlebar :"+bitmap.getHeight()+"\n\n");
                        Bitmap resultBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true);
                        for (int i=0; i<bitmap.getHeight(); i++){
                            for (int j=0; j<bitmap.getWidth(); j++){
                                int pixel = bitmap.getPixel(j,i);
                                int r = Color.red(pixel)+50;
                                int g = Color.green(pixel)+50;
                                int b = Color.blue(pixel)+50;

                                if(r>255||g>255||b>255){
                                    if (r>255){
                                        r=255;
                                    }
                                    if (g>255){
                                        g=255;
                                    }
                                    if (b>255){
                                        b=255;
                                    }
                                }

                                int gr = (r+g+b)/3;

//                                textPixel.append("("+r+","+g+","+b+") ");
                                textPixel.append(""+gr+"\t");
                                setTxt("("+r+","+g+","+b+") ");

                                pixel = Color.rgb(r,g,b);

                                resultBitmap.setPixel(j,i,pixel);
                            }
                            textPixel.append("\n");
                            setTxt("\n");
                        }
                        ImageView imgOut = (ImageView)findViewById(R.id.image_out);
                        imgOut.setImageBitmap(resultBitmap);
                    }
                });



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void setTxt(String text){
        File logFile = new File("sdcard/log.txt");
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
