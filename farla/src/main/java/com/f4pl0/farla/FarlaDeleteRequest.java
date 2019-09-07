package com.f4pl0.farla;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
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
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            listener.onFailure(Constants.NO_CONNECTION);
                        } else if (error instanceof AuthFailureError) {
                            listener.onFailure(Constants.AUTH_FAILURE);
                        } else if (error instanceof ServerError) {
                            listener.onFailure(Constants.SERVER_ERROR);
                        } else if (error instanceof NetworkError) {
                            listener.onFailure(Constants.NETWORK_ERROR);
                        } else if (error instanceof ParseError) {
                            listener.onFailure(Constants.PARSE_ERROR);
                        }
                    }
                }
        );
        requestQueue.add(deleteRequest);
    }
}
