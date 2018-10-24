/**
 * Created by Jhonarendra on 10/20/2018.
 */

package com.example.jhonarendra.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.Objects;

import static org.opencv.core.Core.circle;
import static org.opencv.core.Core.line;

public class TesHough extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{

    private static String TAG = "MainACtivity";
    JavaCameraView javaCameraView;
    Mat mRgba, mRgbat, imgGray, imgCanny;

//    String namaFilter = "NoFilter";

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this){
        @Override
        public void onManagerConnected(int status) {
            super.onManagerConnected(status);

            switch (status){
                case LoaderCallbackInterface.SUCCESS:
                {
                    javaCameraView.enableView();

                }
                break;
                default:
                {
                    super.onManagerConnected(status);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hough);

        javaCameraView = (JavaCameraView)findViewById(R.id.java_camera_view4);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(javaCameraView!=null){
            javaCameraView.disableView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(javaCameraView!=null) {
            javaCameraView.disableView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(OpenCVLoader.initDebug()){
            Log.i(TAG, "MAU");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else {
            Log.i(TAG, "GAK MAU");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_2, this, mLoaderCallback);
        }
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        imgGray = new Mat(height, width, CvType.CV_8UC1);
        imgCanny = new Mat(height, width, CvType.CV_8UC1);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        mRgbat = mRgba.t();
        Core.flip(mRgba.t(), mRgbat, 1);
        Imgproc.resize(mRgbat, mRgbat, mRgba.size());
        Imgproc.cvtColor(mRgbat, imgGray, Imgproc.COLOR_RGB2GRAY);

        Mat input = inputFrame.gray();
        Mat circles = new Mat();
        Imgproc.blur(input, input, new Size(7, 7), new Point(2,2));
        Imgproc.HoughCircles(input, circles, Imgproc.CV_HOUGH_GRADIENT, 2, 100, 100, 90, 0, 1000);


        Mat lines = new Mat();
        Imgproc.Canny(input, imgCanny, 50, 50);
        Imgproc.HoughLinesP(imgCanny, lines, 1, Math.PI/180, 50, 20, 20);

        for(int x = 0; x <lines.cols(); x++){
            double[] vec = lines.get(0, x);
            double x1 = vec[0], y1 = vec[1], x2 = vec[2], y2 = vec[3];
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            Core.line(input, start, end, new Scalar(255,0,0), 3);
        }

        if(circles.cols()>0){
            for(int x=0; x<Math.min(circles.cols(), 5); x++){
                double circleVect[] = circles.get(0, x);
                if(circleVect == null){
                    break;
                }
                Point center = new Point((int)circleVect[0], (int) circleVect[1]);
                int radius = (int) circleVect[2];

//                circle(input, center, 3, new Scalar(0, 255, 0), -1, 8, 0);
                circle(input, center, radius, new Scalar(255,0,0),3, 8,0);
            }
        }
        circles.release();
        imgGray.release();
        return inputFrame.rgba();
    }

}
