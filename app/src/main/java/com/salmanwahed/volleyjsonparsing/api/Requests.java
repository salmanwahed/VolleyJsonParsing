package com.salmanwahed.volleyjsonparsing.api;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.salmanwahed.volleyjsonparsing.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by salman wahed on 6/12/15.
 */
public class Requests {
    private int mMethod;
    private String mApiUrl;
    private ResponseListener responseListener = null;

    public Requests(int method, String apiUrl, ResponseListener apiResponseListener){
        this.mMethod = method;
        this.mApiUrl = apiUrl;
        this.responseListener = apiResponseListener;
    }

    public void makeJsonObjectRequest(){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(mMethod,
                mApiUrl, (String)null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                responseListener.onReceiveJson(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.onError(error.getMessage());
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    public void makeJsonArrayRequest(){
        JsonArrayRequest req = new JsonArrayRequest(mApiUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        responseListener.onReceiveJsonArray(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.onError(error.getMessage());
                    }
        });

        AppController.getInstance().addToRequestQueue(req);
    }
}
