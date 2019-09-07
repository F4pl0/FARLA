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

public class FarlaPutRequest {
    Context context;
    RequestQueue requestQueue;
    String URL = "";
    onPutRequestListener listener;
    Map<String, String> params = new HashMap<String, String>();

    public FarlaPutRequest(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public interface onPutRequestListener{
        void onSuccess(String response);
        void onFailure(int error);
    }

    public FarlaPutRequest addParam(String key, String value){
        params.put(key, value);
        return this;
    }

    public FarlaPutRequest removeParam(String key){
        params.remove(key);
        return this;
    }

    public FarlaPutRequest setURL(String URL){
        this.URL = URL;
        return this;
    }

    public FarlaPutRequest setListener(onPutRequestListener listener){
        this.listener = listener;
        return this;
    }

    public void execute(){
        StringRequest putRequest = new StringRequest(Request.Method.PUT, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        listener.onSuccess(response);
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
        requestQueue.add(putRequest);
    }
}
