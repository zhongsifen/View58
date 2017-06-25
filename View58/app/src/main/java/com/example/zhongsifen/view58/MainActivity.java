package com.example.zhongsifen.view58;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//import android.support.annotation.RequiresApi;

public class MainActivity extends AppCompatActivity {

    private int _status;
    private Fs _fs;
    private Bitmap _bitmap;
    private ImageView _imageView;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _status = 0;
        _fs = new Fs();
        _imageView = (ImageView) findViewById(R.id.imageView);
//        _imageView.setOnTouchListener(new OnImageViewTouchListener());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (_status) {
//                    case 0: {
//                        Intent intent = new Intent(
//                                Intent.ACTION_PICK,
//                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        startActivityForResult(intent, RESULT_LOAD_IMAGE);
//                        _status = 1;
//                    } break;
//                    case 1: {
//                        _fs.setup(_bitmap);
//                        _fs.pov_a_index = 0;
//                        _fs.run();
//                        _imageView.setImageBitmap(_fs.imageH.getImage());
//                        _status = 2;
//                    } break;
//                    case 2: {
//                        _fs.pov_a_index++;        if (_fs.pov_a_index == _fs.pov_a_count) _fs.pov_a_index = 0;
//                        _fs.run();
//                        _imageView.setImageBitmap(_fs.imageH.getImage());
//                    } break;
//                    default: {
//                    }
//                }
//            }
//        });

        Button btn_setup = (Button) findViewById(R.id.button_setup);
        btn_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (_status) {
                    case 0: {
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, RESULT_LOAD_IMAGE);
                        _status = 1;
                    }
                    break;
                    case 1: {
                        _fs.pov_a_index = 0;
                        _fs.setupMap(_fs.pov_a[_fs.pov_a_index], _fs.map);
                        _fs.run(_fs.map);
                        _imageView.setImageBitmap(_fs.imageH.getImage());
                        _status = 2;
                    }
                    break;
                    case 2: {
                        _fs.pov_a_index++;  if (_fs.pov_a_index == _fs.pov_a_count) _fs.pov_a_index = 0;
                        _fs.setupMap(_fs.pov_a[_fs.pov_a_index], _fs.map);
                        _fs.run(_fs.map);
                        _imageView.setImageBitmap(_fs.imageH.getImage());
                        _status = 2;
                    }
                    break;
                    default: {
                    }
                }
            }
        });

        Button btn_run = (Button) findViewById(R.id.button_run);
        btn_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (_status) {
                    case 0: {
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, RESULT_LOAD_IMAGE);
                        _status = 1;
                    }
                    break;
                    case 1: {
                        _fs.pov_a_index = 0;
                        _fs.setupMap(_fs.pov_a[_fs.pov_a_index], _fs.map);
                        _fs.run(_fs.map);
                        _imageView.setImageBitmap(_fs.imageH.getImage());
                        _status = 2;
                    }
                    break;
                    case 2: {
                        _fs.pov_a_index++;  if (_fs.pov_a_index == _fs.pov_a_count) _fs.pov_a_index = 0;
                        _fs.setupMap(_fs.pov_a[_fs.pov_a_index], _fs.map);
                        _fs.run(_fs.map);
                        _imageView.setImageBitmap(_fs.imageH.getImage());
                        _status = 2;
                    }
                    break;
                    default: {
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri imageUri = data.getData();

            try {
                _bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                _imageView.setImageBitmap(_bitmap);
                _fs.setup(_bitmap);
            } catch (Exception e) {}
        }
    }

//    private class OnImageViewTouchListener implements OnTouchListener {
//        @Override
//        public boolean onTouch(View v, MotionEvent event){
//            switch (_status) {
//                case 0: {
//                    Intent intent = new Intent(
//                            Intent.ACTION_PICK,
//                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent, RESULT_LOAD_IMAGE);
//                    _status = 1;
//                } break;
//                case 1: {
//                    _fs.povIx = 0;
//                    _fs.setupPov(_fs.pov_a[_fs.povIx]);
//                    _fs.run();
//                    _imageView.setImageBitmap(_fs.imageH.getImage());
//                } break;
//                case 2: {
//                    float w = v.getWidth();
//                    float x = event.getX();
//                    if (x < w/5) {
//                        _fs.povIx--;        if (_fs.povIx < 0) _fs.povIx += _fs.pov_a_count;
//                    }
//                    else
//                    if (x > w*4/5) {
//                        _fs.povIx++;        if (_fs.povIx == _fs.pov_a_count) _fs.povIx = 0;
//                    }
//                    else {
//                        _fs.povIx = 0;
//                    }
//                    _fs.setupPov(_fs.pov_a[_fs.povIx]);
//                    _fs.run();
//                    _imageView.setImageBitmap(_fs.imageH.getImage());
//                } break;
//                default: {
//
//                }
//            }
//
//            return true;
//        }
//    }

}
