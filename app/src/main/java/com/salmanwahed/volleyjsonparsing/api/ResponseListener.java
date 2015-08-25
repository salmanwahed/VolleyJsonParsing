package com.salmanwahed.volleyjsonparsing.api;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by salman wahed on 6/12/15.
 */
public interface ResponseListener {
    public void onReceiveJson(JSONObject jsonObject);
    public void onError(String message);
    public void onReceiveJsonArray(JSONArray jsonArray);
}
