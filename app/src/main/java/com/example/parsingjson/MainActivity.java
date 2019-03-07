package com.example.parsingjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String data = "{ \"history\":                                                                                                  [                                                                                                           {  \"mem\": \"test reject NO ID\",\"pid\": \"376549\" },                                                   {  \"mem\": \"test reject NO ID\",\"pid\": \"376550\" },                                                   {  \"mem\": \"test reject NO ID\",\"pid\": \"376513\" }                                                ]}";

    String jsonStr= "{ \"history\":                                                                                                  [                                                                                                           {\"id\": \"247484\",\"mem\": \"TEST4\",\"pid\": \"376549\"},                                              {\"id\": \"244949\",\"mem\": \"TEST1\",\"pid\": \"376549\"},                                              {\"id\": \"247479\",\"mem\": \"TEST3\",\"pid\": \"376549\"},                                              {\"id\": \"244954\",\"mem\": \"TEST2\",\"pid\": \"376549\"}                                            ]                                                                                                   }";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
    }

    public void onParse(View view) {
        //String key = "Default";
        String dataString = "";
        final ArrayList<HashMap<String, String>> khmList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObject.getJSONArray("history");
            for(int i = 0; i<jsonArray.length(); i++){
                HashMap<String, String> khm = new HashMap<>();
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.has("id")){
                    khm.put("id", obj.getString("id"));
                }
                khm.put("mem", obj.getString("mem"));
                khm.put("pid", obj.getString("pid"));
                khmList.add(khm);
            }

            //sort List
            Collections.sort(khmList, new Comparator<HashMap<String, String>>() {
                @Override
                public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                    return Integer.parseInt(o1.get("id")) - Integer.parseInt(o2.get("id"));
                }
            });

            for(int i = 0; i<khmList.size(); i++){
                dataString += khmList.get(i).get("mem") + ", "
                        +khmList.get(i).get("pid")+"\n";
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        textView.setText(dataString);
    }

    public void onClickIntentService(View view) {
        Intent i = new Intent(this, SampleIntentService.class);
        startService(i);
    }
}
