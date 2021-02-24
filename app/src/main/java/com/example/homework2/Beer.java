package com.example.homework2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Beer{
    private String name;
    private String abv;
    private String firstBrewed;
    private String imageURL;
    private String desc;
    private String[] pairings;
    private String tips;
    private Boolean fav;

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

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        JSONArray paired = new JSONArray();
        for (int i=0; i<getPairings().length;i++){
            paired.put(getPairings()[i]);
        }
        json.put("name",getName());
        json.put("abv",getAbv());
        json.put("first_brewed",getFirstBrewed());
        json.put("image_url", getImageURL());
        json.put("description",getDesc());
        json.put("brewers_tips",getTips());
        json.put("food_pairing",paired);
        return json;
    }

    public Boolean getFav() {
        return fav;
    }

    public void setFav(Boolean fav) {
        this.fav = fav;
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