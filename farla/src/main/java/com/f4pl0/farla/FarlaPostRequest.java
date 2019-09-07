package com.f4pl0.farla;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FarlaPostRequest {

    Context context;
    RequestQueue requestQueue;
    String URL = "";
    onPostRequestListener listener;
    Map<String, String>  params = new HashMap<String, String>();

    public FarlaPostRequest(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public interface onPostRequestListener{
        void onSuccess(String response);
        void onFailure(int error);
    }

    public FarlaPostRequest addParam(String key, String value){
        params.put(key, value);
        return this;
    }

    public FarlaPostRequest removeParam(String key){
        params.remove(key);
        return this;
    }

    public FarlaPostRequest setURL(String URL){
        this.URL = URL;
        return this;
    }

    public FarlaPostRequest setListener(onPostRequestListener listener){
        this.listener = listener;
        return this;
    }

    public void execute(){
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                return params;
            }
        };
        requestQueue.add(postRequest);
    }
}
