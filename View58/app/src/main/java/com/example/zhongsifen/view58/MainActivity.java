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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public int width;
    public int height;
    public int[] pixels;

    private int _status;
    private Fs _fs;
    private Bitmap _bitmap;
    private ImageView _imageView;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        _status = 0;
        _fs = new Fs();
        _imageView = (ImageView) findViewById(R.id.imageView);
        _imageView.setOnTouchListener(new OnImageViewTouchListener());

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

            try {
                _bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                _fs.setup(_bitmap, 180);
                _imageView.setImageBitmap(_bitmap);
                _status = 1;
            } catch (Exception e) {}
        }
    }

    private class OnImageViewTouchListener implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event){
            switch (_status) {
                case 0: {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, RESULT_LOAD_IMAGE);
                    _status = 1;
                } break;
                case 1: {
                    float w = v.getWidth();
                    float x = event.getX();
                    if (x < w/5) {
                        _fs.povIx--;        if (_fs.povIx < 0) _fs.povIx += _fs.pov_a_count;
                    }
                    else
                    if (x > w*4/5) {
                        _fs.povIx++;        if (_fs.povIx == _fs.pov_a_count) _fs.povIx = 0;
                    }
                    else {
                        _fs.povIx = 0;
                    }
                    _fs.setupPov(_fs.pov_a[_fs.povIx]);
                    _fs.run();
                    _imageView.setImageBitmap(_fs.imageH.getImage());
                } break;
                default: {

                }
            }

            return true;
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction() & MotionEvent.ACTION_MASK;
//        if (action == MotionEvent.ACTION_DOWN) {
//            switch (_status) {
//                case 0: {
//                    Intent intent = new Intent(
//                            Intent.ACTION_PICK,
//                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent, RESULT_LOAD_IMAGE);
//                    _imageView.setImageBitmap(_bitmap);
//                    _status = 1;
//                } break;
//                case 1: {
//                    _fs.setupPov(Fs.show_pov[_fs.pov_index]);
//                    _fs.run();
//                    _imageView.setImageBitmap(_fs.imageH.getImage());
//
//                    _fs.pov_index++; _fs.pov_index %= _fs.pov_count;
//                } break;
//                default: {
//
//                }
//            }
//        }
//
//        return true;
//    }

//    public void onImageViewClick(View view) {
//        _fs.setupPov(Fs.show_pov[_fs.pov_index]);
//        _fs.run();
//        _imageView.setImageBitmap(_fs.imageH.getImage());
//
//        _fs.pov_index++; _fs.pov_index %= _fs.pov_count;
//    }

}
