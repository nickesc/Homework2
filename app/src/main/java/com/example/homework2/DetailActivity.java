package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class DetailActivity extends AppCompatActivity {

    private Beer beer;

    private TextView nameTV;
    private TextView abvTV;
    private TextView firstBrewedTV;
    private ImageView image;
    private TextView descTV;
    private TextView pairTV;
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
        setViews();



    }

    public void setViews(){
        nameTV = findViewById(R.id.nameTV);
        nameTV.setText(beer.getName());
        image=findViewById(R.id.image1);
        Picasso.get().load(beer.getImageURL()).into(image);
        abvTV = findViewById(R.id.abvTV);
        abvTV.setText("AVB: "+beer.getAbv());
        firstBrewedTV = findViewById(R.id.firstBrewedTV);
        firstBrewedTV.setText("First Brewed: "+beer.getFirstBrewed());
        descTV = findViewById(R.id.descTV);
        descTV.setText(beer.getDesc());
        tipsTV = findViewById(R.id.tipsTV);
        tipsTV.setText(beer.getTips());

        String pairString="";
        for(int i=0; i<beer.getPairings().length;i++){
            pairString=pairString+"- "+beer.getPairings()[i]+"\n";
        }
        pairTV = findViewById(R.id.pairTV);
        pairTV.setText(pairString);

    }

}
