package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;

public class SearchActivity extends AppCompatActivity {

    private static String api_url;
    private static AsyncHttpClient client = new AsyncHttpClient();

    private Button goButton;

    private String name="";
    private String dateStart ="";
    private String dateEnd="";
    private String hpb="false";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        goButton=findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //generateBeerList(v,1);
                //launchNextActivity(v);
                validateParams(v);
            }
        });

    }

    public void helpWords(){
        //name="";
        //dateStart ="";
        //dateEnd="";
        //hpb="false";
    }

    public void validateParams(View view) {
        EditText helper = findViewById(R.id.beerName);
        name=helper.getText().toString().trim().replace(' ','_');

        helper = findViewById(R.id.endDate);
        dateEnd=helper.getText().toString().trim();

        helper = findViewById(R.id.startDate);
        dateStart=helper.getText().toString().trim();

        Switch helper2 = findViewById(R.id.hpb);
        hpb=String.valueOf(helper2.isChecked());


        Boolean[] good = {false, false};
        float[] timeArray={01,0001,12,9999};
        float startF=1;
        float endF=9999;
        String nums = "1234567890";
        String sep = "-/.,'\"*_ |:;\\";


        Intent intent = new Intent(this, ResultsActivity.class);
        helpWords();

        if (dateStart.length()==7 &&
                sep.contains(Character.toString(dateStart.charAt(2))) &&
                nums.contains(Character.toString(dateStart.charAt(0))) &&
                nums.contains(Character.toString(dateStart.charAt(1))) &&
                nums.contains(Character.toString(dateStart.charAt(3))) &&
                nums.contains(Character.toString(dateStart.charAt(4))) &&
                nums.contains(Character.toString(dateStart.charAt(5))) &&
                nums.contains(Character.toString(dateStart.charAt(6)))) {
            timeArray[0]=Float.parseFloat(dateStart.substring(0,2));
            timeArray[1]=Float.parseFloat(dateStart.substring(3));
            dateStart=dateStart.substring(0,2)+"-"+dateStart.substring(3);
            startF=((timeArray[0]-1)/12)+timeArray[1];
            Log.d("help", ""+startF);
            good[0]=true;
        }
        else if (dateStart.length()==0){
            dateStart="01-0001";
            good[0]=true;
        }

        if (dateEnd.length()==7 &&
                sep.contains(Character.toString(dateStart.charAt(2))) &&
                nums.contains(Character.toString(dateEnd.charAt(0))) &&
                nums.contains(Character.toString(dateEnd.charAt(1))) &&
                nums.contains(Character.toString(dateEnd.charAt(3))) &&
                nums.contains(Character.toString(dateEnd.charAt(4))) &&
                nums.contains(Character.toString(dateEnd.charAt(5))) &&
                nums.contains(Character.toString(dateEnd.charAt(6)))) {
            timeArray[2]=Float.parseFloat(dateEnd.substring(0,2));
            timeArray[3]=Float.parseFloat(dateEnd.substring(3));
            endF=((timeArray[2]-1)/12)+timeArray[3];;
            dateEnd=dateEnd.substring(0,2)+"-"+dateEnd.substring(3);
            Log.d("help", ""+endF);
            good[1]=true;
        }
        else if (dateEnd.length()==0){
            dateEnd="12-9999";
            good[1]=true;
        }

        if (!good[0] && !good[1]) errorToast("Invalid dates");
        else if(timeArray[0]==0 || timeArray[1]==0 || timeArray[2]==0 || timeArray[3]==0) errorToast("Dates cannot contain a 0");
        else if (startF>endF) errorToast("Starting date must be before end");
        else if (!good[0] || timeArray[0]>12) errorToast("Invalid start date");
        else if (!good[1] || timeArray[2]>12) errorToast("Invalid end date");
        else{
            launchNextActivity(view,intent);
        }
    }

    public void errorToast(String error){
        Toast toastTest = Toast.makeText(this, error, Toast.LENGTH_SHORT);
        toastTest.show();
    }

    public void launchNextActivity(View view, Intent intent){

        if (dateStart.equals("01-0001")) dateStart="";
        if (dateEnd.equals("12-9999")) dateEnd="";
        String[] params= {name, dateStart, dateEnd,hpb};
        intent.putExtra("params", params);
        startActivity(intent);
    }
}
