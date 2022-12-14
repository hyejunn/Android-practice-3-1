package com.example.fashionclassifier.image;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Pair;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fashionclassifier.R;
import com.example.fashionclassifier.tflite.Classifier;

import java.io.IOException;
import java.util.Locale;

public class ImageActivity extends AppCompatActivity {
    public static final String TAG = "[IC]GalleryActivity";
    public static final int GALLERY_IMAGE_REQUEST_CODE = 1;

    Classifier cls;
    Bitmap bitmap = null;
    private ImageView imageView;
    private TextView textView;
    String classNames[] = {"T-shirt/top", "Trouser", "Pullover", "Dress", "Coat", "sandal", "shirt", "Sneaker", "Bag", "Ankle boot"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Button selectBtn = findViewById(R.id.gallerySelectBtn);
        selectBtn.setOnClickListener(v -> getImageFromGallery());

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        Button classifyBtn = findViewById(R.id.galleryClassifyBtn);
        classifyBtn.setOnClickListener(v -> {
            if (bitmap != null) {
                Pair<Integer, Float> res = cls.classify(bitmap);
                String outStr = String.format(
                        Locale.ENGLISH,
                        "%s, %.0f%%",
                        classNames[res.first],
                        res.second * 100.0f);
                textView.setText(outStr);
            }
        });

        cls = new Classifier(this);
        try {
            cls.init();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void getImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT).setType("image/*");
        startActivityForResult(intent, GALLERY_IMAGE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK &&
                requestCode == GALLERY_IMAGE_REQUEST_CODE) {
            if (data == null) {
                return;
            }

            Uri selectedImage = data.getData();

            try {
                if(Build.VERSION.SDK_INT >= 29) {
                    ImageDecoder.Source src =
                            ImageDecoder.createSource(getContentResolver(), selectedImage);
                    bitmap = ImageDecoder.decodeBitmap(src).copy(Bitmap.Config.ARGB_8888, true);
                } else {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                }
            } catch (IOException ioe) {
                Log.e(TAG, "Failed to read Image", ioe);
            }

            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected void onDestroy() {
        cls.finish();
        super.onDestroy();
    }
}
