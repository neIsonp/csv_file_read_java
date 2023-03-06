package com.example.csv_file_learn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MonthCustSample> custSample = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readMyData();

    }

    private void readMyData() {

        InputStream is = getResources().openRawResource(R.raw.dados);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );

        String line = "";



        try{
            reader.readLine();

            while((line = reader.readLine()) != null){

                System.out.println(line);

                //split by ","
                String[] tokens = line.split(",");

                // read the data

                MonthCustSample data = new MonthCustSample();
                data.setMonth(tokens[0]);
                if(tokens[1].length() > 0 && tokens[2].length() > 0){
                    data.setPriceForMe(Integer.parseInt(tokens[1]));
                    data.setPriceForMyGirl(Integer.parseInt(tokens[2]));
                }else{
                    data.setPriceForMe(0);
                    data.setPriceForMyGirl(0);
                }

                custSample.add(data);

                Log.d("MyActivity", "just created");

            }

        }catch (IOException error){
            Log.wtf("MyActivity", "error reading a csv file" + line, error);
            error.printStackTrace();
        }
    }
}