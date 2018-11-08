<h1 align="center">Opencv Android</h1>
<p align="center">Aplikasi Computer Vision Android menggunakan OpenCV</p>

## Intro
<p>Aplikasi android ini berisi kumpulan latihan-latihan menggunakan open cv, dari membuka kamera, memberi filter pada kamera, menampilkan pixel dari gambar, dan mendeteksi garis dan lingkaran.</p>

### Kamera OpenCV
Latihan membuka kamera dan filterisasi pada opencv terdapat pada activity `MainActivity`, `GrayscaleFilter`, `CannyFilter`, dan `GaussianFilter`.

### Menampilkan Pixel Gambar
Menampilkan pixel gambar bisa dicoba dengan mengklik tombol `GET PIXEL` pada halaman utama. Resolusi gambar yang digunakan jangan terlalu besar karena aplikasi akan crash. Pakai saja gambar 10 x 10 (bisa dibuat di paint).
Activity untuk modul get pixel ada pada `TesGetPixel`. Cara kerjanya, pilih gambar > klik tombol pixel
- `GET PIXEL`, untuk menampilkan pixel asli.
- `FLIP VERTICAL`, flip gambar thd sumbu x.
- `FLIP HORIZONTAL`, flip gambar thd sumbu y.
- `BRIGHTNESS +20`, menambah value pixel +20.

### Deteksi Garis dan Lingkaran
Untuk mendeteksi garis dan lingkaran, digunakan fungsi Hough Transform yang sudah ada di OpenCV. Tombol `HOUGH CAMERA` akan membuka kamera dan mendeksi garis dan lingkaran secara live. Sedangkan `HOUGH INPUT` mendeteksi dari gambar yang di import.
<p>Ini contoh kode untuk deteksi garis dan lingkaran</p>

```java
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
```

## Screenshot

![](https://raw.githubusercontent.com/jhonarendra/opencv-android/master/screenshot/img1.png)
![](https://raw.githubusercontent.com/jhonarendra/opencv-android/master/screenshot/img2.png)
![](https://raw.githubusercontent.com/jhonarendra/opencv-android/master/screenshot/img3.png)
![](https://raw.githubusercontent.com/jhonarendra/opencv-android/master/screenshot/img4.png)
![](https://raw.githubusercontent.com/jhonarendra/opencv-android/master/screenshot/img5.png)
![](https://raw.githubusercontent.com/jhonarendra/opencv-android/master/screenshot/img6.png)