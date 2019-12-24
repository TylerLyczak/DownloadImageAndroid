package com.tylerlyczak.downloadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    public void downloadImage (View view)   {
        ImageDownloader task = new ImageDownloader();
        try {
            Bitmap bitmap = task.execute("https://en.wikipedia.org/wiki/Bart_Simpson#/media/File:Bart_Simpson_200px.png").get();

            imageView.setImageBitmap(bitmap);
        }
        catch   (Exception e)   {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>    {

        @Override
        protected Bitmap doInBackground (String... urls)    {
            try {
                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream in = connection.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(in);

                return bitmap;
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
