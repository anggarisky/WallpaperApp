package com.pandame.anggarisky.themeto;

import android.Manifest;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.R.attr.bitmap;

public class ThemeActivity extends AppCompatActivity {

    ConstraintLayout theme_preview;
    Button save_btn, back_btn, download_btn;
    String newString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        ActivityCompat.requestPermissions(ThemeActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        theme_preview = (ConstraintLayout) findViewById(R.id.theme_preview);
        save_btn = (Button) findViewById(R.id.save_btn);
        back_btn = (Button) findViewById(R.id.back_btn);
        download_btn = (Button) findViewById(R.id.download_btn);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString = null;
            } else {
                newString = extras.getString("PATH_PICTURE");

                if(newString.equals("bg_item1")){
                    theme_preview.setBackgroundResource(R.drawable.bg_item1);

                    save_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            WallpaperManager wlp = WallpaperManager.getInstance(getApplicationContext());
                            Toast.makeText(getApplicationContext(), "Wallpaper Success Sunny", Toast.LENGTH_SHORT).show();
                            try {
                                wlp.setResource(+ R.drawable.bg_item1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    download_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            // save image to Gallery

                            Drawable drawable = getDrawable(R.drawable.bg_item1);
                            Bitmap mybitmap = ((BitmapDrawable)drawable).getBitmap();

                            //phase to save the image
                            String saveImageURL = MediaStore.Images.Media.insertImage(

                                    getContentResolver(),
                                    mybitmap,
                                    "Title Picture",
                                    "Image of pictures"

                            );

                            Uri saveImageURI = Uri.parse(saveImageURL);
                            Toast.makeText(getApplicationContext(), "Sunny to Gallery", Toast.LENGTH_SHORT).show();



                            // save image to sd card
                            Bitmap bitmap;
                            OutputStream output;

                            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_item1);

                            File filepath = Environment.getExternalStorageDirectory();
                            File dir = new File(filepath+"/MyWallp/");
                            dir.mkdirs();

                            File file = new File(dir, "mybackground_1.png");

                            try {
                                output = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                                output.flush();
                                output.close();

                                Toast.makeText(getApplicationContext(), "Sunny to SD Card", Toast.LENGTH_SHORT).show();
                            } catch(Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });

                } else if(newString.equals("bg_item2")) {
                    theme_preview.setBackgroundResource(R.drawable.bg_item2);

                    save_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            WallpaperManager wlp = WallpaperManager.getInstance(getApplicationContext());
                            Toast.makeText(getApplicationContext(), "Success Cloud", Toast.LENGTH_SHORT).show();
                            try {
                                wlp.setResource(+ R.drawable.bg_item2);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    download_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            // save image to Gallery

                            Drawable drawable = getDrawable(R.drawable.bg_item2);
                            Bitmap mybitmap = ((BitmapDrawable)drawable).getBitmap();

                            //phase to save the image
                            String saveImageURL = MediaStore.Images.Media.insertImage(

                                    getContentResolver(),
                                    mybitmap,
                                    "Title Picture",
                                    "Image of pictures"

                            );

                            Uri saveImageURI = Uri.parse(saveImageURL);
                            Toast.makeText(getApplicationContext(), "Cloud to Gallery", Toast.LENGTH_SHORT).show();

                            // save image to sd card
                            Bitmap bitmap;
                            OutputStream output;

                            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_item2);

                            File filepath = Environment.getExternalStorageDirectory();
                            File dir = new File(filepath+"/MyWallp/");
                            dir.mkdirs();

                            File file = new File(dir, "mybackground_2.png");

                            try {
                                output = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                                output.flush();
                                output.close();

                                Toast.makeText(getApplicationContext(), "Cloud to SD Card", Toast.LENGTH_SHORT).show();
                            } catch(Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });

                } else {
                    // write the statement.
                }

            }
        }

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}
