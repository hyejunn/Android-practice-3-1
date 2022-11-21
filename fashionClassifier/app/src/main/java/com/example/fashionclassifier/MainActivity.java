package com.example.fashionclassifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.fashionclassifier.image.ImageActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button drawBtn = findViewById(R.id.imageBtn);
        drawBtn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, ImageActivity.class);
            startActivity(i);
        });
    }
}