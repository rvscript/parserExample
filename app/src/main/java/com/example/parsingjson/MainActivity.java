package com.example.parsingjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String json =
            "{   \"history\": [ {\"mem\": \"Please Approve OT\",\"pid\": \"NICEQ0002\" },{ " +
                    "\"mem\":" +
                    " \"give OT for this job\", \"pid\": \"NICEQ0019\" } ] }";

    private List<String> approvers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        approvers.add("NICEQ0002");
        approvers.add("NICEQ0019");
        approvers.add("NICEQ0004");
    }

    public void onParse(View view) {

        String dataString = "";
        ArrayList<HashMap<String, String>> khmList = new ArrayList<>();
        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
        ListIterator<HashMap<String, String>> listItr;

       try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("history");

            for (int i = 0; i < jsonArray.length(); i++) {
                HashMap<String, String> khm = new HashMap<>();
                JSONObject obj = jsonArray.getJSONObject(i);
                khm.put("mem", obj.getString("mem"));
                khm.put("pid", obj.getString("pid"));
                khmList.add(khm);
            }

            listItr = khmList.listIterator();
           for (int i = 0; i < approvers.size(); i++) {
               HashMap<String, String> dataMap = new HashMap<>();
               if(khmList.size() > i && listItr.hasNext()){
                   dataMap.put("title", approvers.get(i));
                   dataMap.put("pid",khmList.get(i).get("pid"));
                   dataMap.put("mem", khmList.get(i).get("mem"));
               } else {
                   dataMap.put("title", approvers.get(i));
                   dataMap.put("pid","MEMO ");
                   dataMap.put("mem", "n/a");
               }
                 dataList.add(dataMap);
           }
//            String output
            for (int i = 0; i < dataList.size(); i++) {
                dataString += dataList.get(i).get("title") +"\n"+
                        dataList.get(i).get("pid") + ": "
                        + dataList.get(i).get("mem") + "\n";
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        textView.setText(dataString);
    }

}
