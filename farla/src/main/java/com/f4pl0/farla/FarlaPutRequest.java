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

import java.util.HashMap;
import java.util.Map;

public class FarlaPutRequest {

    //region Vars
    Context context;
    RequestQueue requestQueue;
    String URL = "";
    onPutRequestListener listener;
    Map<String, String> params = new HashMap<String, String>();
    HashMap<String, String> headers = new HashMap<String, String>();
    //endregion

    //region Constructiors and interfaces
    public FarlaPutRequest(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public interface onPutRequestListener{
        void onSuccess(String response);
        void onFailure(int error);
    }
    //endregion

    //region General
    public FarlaPutRequest setURL(String URL){
        this.URL = URL;
        return this;
    }

    public FarlaPutRequest setListener(onPutRequestListener listener){
        this.listener = listener;
        return this;
    }
    //endregion

    //region Params
    public FarlaPutRequest addParam(String key, String value){
        params.put(key, value);
        return this;
    }

    public FarlaPutRequest removeParam(String key){
        params.remove(key);
        return this;
    }
    //endregion

    //region Headers
    public FarlaPutRequest addHeader(String key, String value){
        headers.put(key, value);
        return this;
    }

    public FarlaPutRequest removeHeader(String key){
        headers.remove(key);
        return this;
    }
    //endregion

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
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                return headers;
            }
        };
        requestQueue.add(putRequest);
    }
}
