package com.neman.morph.morphwallet.Utils;

/**
 * Created by albert on 3/4/18.
 */



import android.content.Context;

import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.neman.morph.morphwallet.Utils.Constants;

import static com.neman.morph.morphwallet.Utils.Constants.SHARED_PREF_KEY;


public class ApiManager {



    private Context context;

    SharedPreferences sharedpreferences;
    SharedPreferences initsharedpreferences;

    public void sendGass(HashMap<String, String> params, final Context context) {
        this.context = context;

        sharedpreferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
        RequestQueue queue = Volley.newRequestQueue(context);

        String REGISTER_URL = "";

        JsonObjectRequest req = new JsonObjectRequest(REGISTER_URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            //  Log.e("responsepost: ", "" + response.toString(7));

                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString("first_name", response.getString("first_name").toString());


                            //  Log.e("apimanager_eu_id", ""+response.getString("id").toString());
                            editor.putString("transaction_ammount", "" + response.getString("ammount").toString());

                            editor.apply();

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }

        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("x-api-key", X_API_KEY);
                //..add other headers
                return params;
            }
        };

// add the request object to the queue to be executed
        queue.add(req);


    }//end send gas






    /*
    public void fitbitPatchRequest(HashMap<String, String> params, Context context) {

        sharedpreferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
        RequestQueue queue = Volley.newRequestQueue(context);


        String finish_url = REGISTER_URL + sharedpreferences.getString("eu_id", "null") + "/end/";
        //Log.e("access+token", "" + params.toString());

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.PATCH, finish_url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("x-api-key", X_API_KEY);

                return params;
            }
        };

// add the request object to the queue to be executed
        queue.add(req);


    }//end second patch


    //vop patch event

    public void vopPatchSend(HashMap<String, String> params, Context context) {

        sharedpreferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
        RequestQueue queue = Volley.newRequestQueue(context);

        String finish_url = REGISTER_URL + sharedpreferences.getString("eu_id", "null") + "";
        // Log.e("access+token", "" + params.toString());

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.PATCH, finish_url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Log.e("finishpatch: ", ""+response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("x-api-key", X_API_KEY);
                //..add other headers
                return params;
            }
        };

// add the request object to the queue to be executed
        queue.add(req);


    }//end second patch
*/

}
