package com.example.jhonarendra.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.opencv.core.Core.circle;

/**
 * Created by Jhonarendra on 10/20/2018.
 */

public class TesHoughInput extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 1;
    Bitmap bitmap;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hough_input);

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
        if (resultCode == RESULT_OK) {
            try {
                final Uri imgUri = data.getData();
                final InputStream imgStream;
                imgStream = getContentResolver().openInputStream(imgUri);

                final Bitmap bitmap = BitmapFactory.decodeStream(imgStream);

                final ImageView imgSrc = (ImageView) findViewById(R.id.image_src);
                final ImageView imgOut = (ImageView) findViewById(R.id.image_out);

                imgSrc.setImageBitmap(bitmap);
//                bitmap = ((BitmapDrawable)imgSrc.getDrawable()).getBitmap();

//		Bitmap bmp = Bitmap.createBitmap(greyImg.cols(), greyImg.rows(), Bitmap.Config.ARGB_8888);

                Button btnHough = (Button) findViewById(R.id.btn_hough);

                btnHough.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Mat input = new Mat();
                        Mat mRGB = new Mat();

                        Utils.bitmapToMat(bitmap, mRGB);

                        Imgproc.cvtColor(mRGB, input, Imgproc.COLOR_RGB2GRAY);

                        Mat circles = new Mat();
                        Imgproc.blur(input, input, new Size(7, 7), new Point(2,2));
                        Imgproc.HoughCircles(input, circles, Imgproc.CV_HOUGH_GRADIENT, 2, 100, 100, 90, 0, 1000);

                        Mat lines = new Mat();
                        Imgproc.Canny(input, input, 50, 50);
                        Bitmap bmp = Bitmap.createBitmap(input.cols(), input.rows(), Bitmap.Config.ARGB_8888);
                        Imgproc.HoughLinesP(input, lines, 1, Math.PI/180, 50, 20, 20);

                        for(int x = 0; x <lines.cols(); x++){
                            double[] vec = lines.get(0, x);
                            double x1 = vec[0], y1 = vec[1], x2 = vec[2], y2 = vec[3];
                            Point start = new Point(x1, y1);
                            Point end = new Point(x2, y2);
                            Core.line(mRGB, start, end, new Scalar(0,0,255,255), 3);
                        }

                        if(circles.cols()>0){
                            for(int x=0; x<Math.min(circles.cols(), 5); x++){
                                double circleVect[] = circles.get(0, x);
                                if(circleVect == null){
                                    break;
                                }
                                Point center = new Point((int)circleVect[0], (int) circleVect[1]);
                                int radius = (int) circleVect[2];

                                circle(mRGB, center, radius, new Scalar(255,0,0,255),3, 8,0);
                            }
                        }
                        Utils.matToBitmap(mRGB, bmp);
                        imgOut.setImageBitmap(bmp);
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
