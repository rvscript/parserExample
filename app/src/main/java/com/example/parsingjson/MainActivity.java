package com.example.parsingjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String data =
            "{   \"history\": [      {         \"mem\": \"test reject\",         \"pid\": \"376549\"      }   ]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
    }

    public void onParse(View view) {
        //String key = "Default";
        String dataString = "";
        ArrayList<HashMap<String, String>> khmList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("history");
            for(int i = 0; i<jsonArray.length(); i++){
                HashMap<String, String> khm = new HashMap<>();
                JSONObject obj = jsonArray.getJSONObject(i);
                khm.put("mem", obj.getString("mem"));
                khm.put("pid", obj.getString("pid"));
                khmList.add(khm);
            }

            for(int i = 0; i<khmList.size(); i++){
                dataString += khmList.get(i).get("mem") + ", "
                        +khmList.get(i).get("pid")+"\n";
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        textView.setText(dataString);
    }

}
