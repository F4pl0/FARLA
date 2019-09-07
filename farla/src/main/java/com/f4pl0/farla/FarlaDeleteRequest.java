package com.f4pl0.farla;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class FarlaDeleteRequest {

    Context context;
    RequestQueue requestQueue;
    String URL = "";
    onDeleteRequestListener listener;

    public FarlaDeleteRequest(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public interface onDeleteRequestListener{
        void onSuccess(String response);
        void onFailure(int error);
    }

    public FarlaDeleteRequest setURL(String URL){
        this.URL = URL;
        return this;
    }

    public FarlaDeleteRequest setListener(onDeleteRequestListener listener){
        this.listener = listener;
        return this;
    }

    public void execute(){
        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, URL,
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
        );
        requestQueue.add(deleteRequest);
    }
}
