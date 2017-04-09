package com.example.zhongsifen.view58;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Fs _fs;

    public int width;
    public int height;
    public int[] pixels;

    private ImageView _imageView;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        _fs = new Fs();
        _imageView = (ImageView)findViewById(R.id.imageView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri imageUri = data.getData();

            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                _imageView.setImageBitmap(bitmap);
                _fs.setup(bitmap, 135);
//                width  = bitmap.getWidth();
//                height = bitmap.getHeight();
//                pixels = new int[width*height];
//                bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

//                _fs.setup(bitmap, 135);
//                _imageView.setImageBitmap(Bitmap.createBitmap(pixels, width, height, Bitmap.Config.ARGB_8888));
            } catch (Exception e) {}
        }
    }

    public void onImageViewClick(View view) {
        _fs.setupPov(Fs.show_pov[_fs.pov_index]);
        _fs.run();
        _imageView.setImageBitmap(_fs.imageH.getImage());

        _fs.pov_index++; _fs.pov_index %= _fs.pov_count;
    }

//    public boolean dispatchTouchEvent(MotionEvent event) {
//        int eventaction=event.getAction();
//
//        switch(eventaction) {
//            case MotionEvent.ACTION_DOWN:
//                float x = event.getX();
//                float y = event.getY();
//                int w = _imageView.getWidth();
//                _fs.run();
//                _imageView.setImageBitmap(_fs.imageH.getImage());
//                if (x < w/4) {
//
//                }
//                System.out.println(x);
//                break;
//            default:
//                break;
//        }
//
//        return super.dispatchTouchEvent(event);
//    }

}
