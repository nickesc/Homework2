package com.example.homework2;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    List<Beer> beers;
    Context context;

    public BeerAdapter(List<Beer> beers){
        this.beers=beers;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View beerView = inflater.inflate(R.layout.item_beer, parent, false);
        ViewHolder viewHolder = new ViewHolder(beerView);



        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Beer beer = beers.get(position);

        holder.nameRV.setText(beer.getName());
        holder.descRV.setText(beer.getDesc());
        Picasso.get().load(beer.getImageURL()).into(holder.imageIV);

        String path;
        if (beer.getFav()) path="images/unfavorite.png";
        else path="images/favorite.png";

        try {
            InputStream ims = context.getAssets().open(path);
            Bitmap bitmap = BitmapFactory.decodeStream(ims);
            holder.favoriteIV.setImageBitmap(bitmap);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        holder.imageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    launchNextActivity(v,beer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.favoriteIV.setOnClickListener(v -> {
            beer.setFav(!beer.getFav());
            this.notifyItemChanged(position);
        });


    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    class ViewHolder  extends RecyclerView.ViewHolder{
        TextView nameRV;
        TextView descRV;
        ImageView imageIV;
        ImageView favoriteIV;

        public ViewHolder(View itemView){
            super(itemView);
            nameRV = itemView.findViewById(R.id.nameRV);
            descRV = itemView.findViewById(R.id.descRV);
            imageIV = itemView.findViewById(R.id.imageIV);
            favoriteIV = itemView.findViewById(R.id.favoriteIV);


        }
    }
    public void launchNextActivity(View view, Beer beer) throws JSONException {
        String jsonExtra = beer.toJSON().toString();

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("beer",jsonExtra);

        context.startActivity(intent);
    }
}
