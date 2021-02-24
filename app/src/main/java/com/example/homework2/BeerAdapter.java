package com.example.homework2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    List<Beer> beers;

    public BeerAdapter(List<Beer> beers){
        this.beers=beers;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View beerView = inflater.inflate(R.layout.item_beer, parent, false);
        ViewHolder viewHolder = new ViewHolder(beerView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Beer beer = beers.get(position);

        holder.nameRV.setText(beer.getName());
        if(beer.getDesc().length()<=120) holder.descRV.setText(beer.getDesc());
        else holder.descRV.setText(beer.getDesc().substring(0,117)+"...");

        Picasso.get().load(beer.getImageURL()).into(holder.imageIV);

        holder.imageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("help", beer.toString());
            }
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

        public ViewHolder(View itemView){
            super(itemView);
            nameRV = itemView.findViewById(R.id.nameRV);
            descRV = itemView.findViewById(R.id.descRV);
            imageIV = itemView.findViewById(R.id.imageIV);


        }
    }
}
