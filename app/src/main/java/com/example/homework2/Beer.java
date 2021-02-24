package com.example.homework2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Beer{
    private String name;
    private String abv;
    private String firstBrewed;
    private String imageURL;
    private String desc;
    private String tips;
    private String[] pairings;

    public Beer(JSONObject beerInfo) throws JSONException {
        this.name=beerInfo.getString("name");
        this.abv=""+beerInfo.getDouble("abv");
        this.firstBrewed=beerInfo.getString("first_brewed");
        this.imageURL=beerInfo.getString("image_url");
        this.desc=beerInfo.getString("description");
        this.tips=beerInfo.getString("brewers_tips");

        JSONArray temp=beerInfo.getJSONArray("food_pairing");
        this.pairings=new String[temp.length()];
        for (int i=0; i<temp.length(); i++){
            pairings[i]=temp.getString(i);
        }

    }

    public String toString(){
        Log.d("help","got here");

        String string=this.name+"~"+this.abv+"~"+this.firstBrewed+"~"+this.imageURL+"~"+this.desc+"~"+this.tips;
        for(int i=0;i<pairings.length;i++){
            string=string+"~"+pairings[i];
        }
        return string;
    }

    public String getAbv() {
        return abv;
    }

    public String getDesc() {
        return desc;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getTips() {
        return tips;
    }

    public String[] getPairings() {
        return pairings;
    }

}