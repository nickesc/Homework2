package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class DetailActivity extends AppCompatActivity {

    private Beer beer;

    private TextView nameTV;
    private TextView abvTV;
    private TextView firstBrewedTV;
    //private String imageURL;
    private TextView descTV;
    private TextView pairingsTV;
    private TextView tipsTV;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        try {
            beer=new Beer(new JSONObject(getIntent().getStringExtra("beer")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("help", beer.getName() + Arrays.toString(beer.getPairings()));

    }

}
