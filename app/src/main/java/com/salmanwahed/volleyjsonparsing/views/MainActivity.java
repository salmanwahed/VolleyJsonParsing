package com.salmanwahed.volleyjsonparsing.views;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.salmanwahed.volleyjsonparsing.R;
import com.salmanwahed.volleyjsonparsing.api.Requests;
import com.salmanwahed.volleyjsonparsing.api.ResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity implements ResponseListener {

    // json object response url
    private String urlJsonObj = "http://api.androidhive.info/volley/person_object.json";
    private String urlJsonArry = "http://api.androidhive.info/volley/person_array.json";

    private final static String TAG = MainActivity.class.getSimpleName();
    private Button btnMakeObjectRequest, btnMakeArrayRequest;
    private TextView txtResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMakeObjectRequest = (Button) findViewById(R.id.btnObjRequest);
        btnMakeArrayRequest = (Button) findViewById(R.id.btnArrayRequest);
        txtResponse = (TextView) findViewById(R.id.txtResponse);

        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadJsonObject();
            }
        });

        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadJsonArray();
            }
        });
    }

    private void loadJsonObject() {
        Requests requests = new Requests(Request.Method.GET, urlJsonObj, this);
        Log.i(TAG, "loadJsonObject pressed!");
        requests.makeJsonObjectRequest();
    }

    private void loadJsonArray() {
        Requests requests = new Requests(Request.Method.GET, urlJsonArry, this);
        Log.i(TAG, "loadJsonArray pressed!");
        requests.makeJsonArrayRequest();
    }

    @Override
    public void onReceiveJson(JSONObject jsonObject) {
        String jsonResponse = "";
        try {
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            JSONObject phone = jsonObject.getJSONObject("phone");
            String home = phone.getString("home");
            String mobile = phone.getString("mobile");

            jsonResponse += "Name: " + name + "\n\n";
            jsonResponse += "Email: " + email + "\n\n";
            jsonResponse += "Home: " + home + "\n\n";
            jsonResponse += "Mobile: " + mobile + "\n\n";
        }
        catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        Log.i(TAG, jsonResponse);
        txtResponse.setText(jsonResponse);

    }

    @Override
    public void onError(String message) {
        Log.i(TAG, message);
    }

    @Override
    public void onReceiveJsonArray(JSONArray jsonArray) {
        String jsonResponse;
        try {
            // Parsing json array response
            // loop through each json object
            jsonResponse = "";
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject person = (JSONObject) jsonArray
                        .get(i);

                String name = person.getString("name");
                String email = person.getString("email");
                JSONObject phone = person
                        .getJSONObject("phone");
                String home = phone.getString("home");
                String mobile = phone.getString("mobile");

                jsonResponse += "Name: " + name + "\n\n";
                jsonResponse += "Email: " + email + "\n\n";
                jsonResponse += "Home: " + home + "\n\n";
                jsonResponse += "Mobile: " + mobile + "\n\n\n";

            }

            Log.i(TAG, jsonResponse);

            txtResponse.setText(jsonResponse);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
