package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Random;

import cz.msebera.android.httpclient.Header;

public class ResultsActivity extends AppCompatActivity {

    private JSONArray list=new JSONArray();
    private String[] paramsArray;

    private static String api_url;
    private static AsyncHttpClient client = new AsyncHttpClient();

    private TextView resultNumber;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Log.d("api-help", "here");

        Intent intent = getIntent();
        paramsArray = intent.getStringArrayExtra("params");
        Log.d("help", ""+ Arrays.toString(paramsArray));

        generateParams();
        generateBeerList(1);



    }

    public void generateParams(){
        api_url="https://api.punkapi.com/v2/beers?per_page=80";
        if(!paramsArray[0].equals("")) api_url=api_url+insertAnd()+"beer_name="+paramsArray[0];
        if(!paramsArray[1].equals("")) api_url=api_url+insertAnd()+"brewed_after="+paramsArray[1].substring(0,2)+"-"+paramsArray[1].substring(3);
        if(!paramsArray[2].equals("")) api_url=api_url+insertAnd()+"brewed_before="+paramsArray[2].substring(0,2)+"-"+paramsArray[2].substring(3);
        if(paramsArray[3].equals("true")) api_url=api_url+insertAnd()+"abv_gt=3.9";
        Log.d("help", api_url);
        //api_url="?beer_name="+paramsArray[0]+"?brewed"
    }

    public String insertAnd(){
        if(api_url.substring(api_url.length())!="?"){
            return "&";
        }
        else{
            return "";
        }
    }


    public void generateBeerList(int page){
        String url=api_url+"&page="+page;
        page++;

        int finalPage = page;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //Log.d("api response", new String(responseBody));
                try{
                    JSONArray json = new JSONArray(new String(responseBody));
                    if(json.length()!=0){
                        for (int i=0; i<json.length(); i++){
                            list.put(json.get(i));
                        }
                        Log.d("help", json.toString());
                        generateBeerList(finalPage);
                    }
                    else{
                        String response = list.toString();
                        Log.d("help", ""+list.length());
                        resultNumber=findViewById(R.id.resultNumber);
                        resultNumber.setText("Found "+list.length()+" results:");
                        //launchNextActivity(view, response);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String((responseBody)));
            }
        });
    }
    public void launchNextActivity(View view) throws JSONException {
        Random rand = new Random();
        String extra = list.get(rand.nextInt(list.length())).toString();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("beer",extra);

        startActivity(intent);
    }

}
