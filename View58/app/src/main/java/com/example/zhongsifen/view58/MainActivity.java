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

public class MainActivity extends AppCompatActivity {

    private int _status;
    private Fs _fs;
    private Bitmap _bitmap;
    private ImageView _imageView;
    private static int RESULT_LOAD_IMAGE = 1;

    private int status_run = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _status = 0;
        _fs = new Fs();
        _imageView = (ImageView) findViewById(R.id.imageView);

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
                switch (status_run) {
                    case 0: {
                        _imageView.setImageBitmap(null);
                        status_run = 1;
                    }
                    break;
                    case 1: {
                        for (int t=0; t<25; t++) {
                            _fs.run_s(_fs.map);
                        }
                        _imageView.setImageBitmap(_fs.imageH.getImage());
                        status_run = 0;
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
                _fs.setup(_bitmap, Fs.cap_fov);
            } catch (Exception e) {}
        }
    }

}
