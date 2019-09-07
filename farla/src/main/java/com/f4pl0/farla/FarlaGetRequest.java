package com.f4pl0.farla;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class FarlaGetRequest {

    Context context;
    RequestQueue requestQueue;
    String URL = "";
    onGetRequestListener listener;

    public FarlaGetRequest(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public interface onGetRequestListener{
        void onSuccess(String response);
        void onFailure(int error);
    }

    public FarlaGetRequest setURL(String URL){
        this.URL = URL;
        return this;
    }

    public FarlaGetRequest setListener(onGetRequestListener listener){
        this.listener = listener;
        return this;
    }

    public void execute(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        requestQueue.add(stringRequest);
    }

}
