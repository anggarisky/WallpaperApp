package com.pandame.anggarisky.themeto;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class CamActivity extends AppCompatActivity {

    Button save_btn, back_btn, download_btn;
    ImageView place_pic;
    Bitmap camBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        save_btn = (Button) findViewById(R.id.save_btn);
        back_btn = (Button) findViewById(R.id.back_btn);
        download_btn = (Button) findViewById(R.id.download_btn);

        place_pic = (ImageView) findViewById(R.id.place_pic);

        final Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cam, 0);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WallpaperManager wlp = WallpaperManager.getInstance(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Success from Camera", Toast.LENGTH_SHORT).show();
                try {
                    wlp.setBitmap(camBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pending function
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        camBitmap = (Bitmap)data.getExtras().get("data");
        place_pic.setImageBitmap(camBitmap);
    }
}
