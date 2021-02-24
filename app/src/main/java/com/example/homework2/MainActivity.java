package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView mainImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainImage=findViewById(R.id.mainImage);
        try {
            InputStream ims = getAssets().open("images/beerBud.png");
            Bitmap bitmap = BitmapFactory.decodeStream(ims);
            mainImage.setImageBitmap(bitmap);
        }
        catch(IOException ex) {
            return;
        }

    }

    public void launchNextActivity(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }



}