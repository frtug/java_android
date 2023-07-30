package com.example.farmanalyticav2;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.VolleyError;
import java.util.HashMap;
import java.util.Map;
// created a class called apiManager which will manage all the api request but we still need to call them.
public class APIManager {

    private static final String BASE_URL = "https://api.farmanalyticaph.online/api/"; // Url of the backend server

    private RequestQueue requestQueue;
    private Context context;
    private String token = "";
    public APIManager(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    public void testing_work(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"error while working on api",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // GET Request
//    public void getHealthCheck(Response.Listener<String> successListener, Response.ErrorListener errorListener) {
//        String url = BASE_URL;
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, successListener, errorListener);
//        requestQueue.add(stringRequest);
//    }
//
//    // POST Request with JSON payload
//    public void login(String username, String password, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
//        String url = BASE_URL + "login";
//        JSONObject jsonBody = new JSONObject();
//        try {
//            jsonBody.put("username", username);
//            jsonBody.put("password", password);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
//                successListener,
//                errorListener
//        );
//
//        requestQueue.add(jsonObjectRequest);
//    }
//
//    // POST Request with JSON payload
//    public void signUp(String name, String username, String password, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
//        String url = BASE_URL + "signUp";
//        JSONObject jsonBody = new JSONObject();
//        try {
//            jsonBody.put("name", name);
//            jsonBody.put("username", username);
//            jsonBody.put("password", password);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
//                successListener,
//                errorListener
//        );
//        requestQueue.add(jsonObjectRequest);
//    }
//    public void getSeasons(final Response.Listener<JSONObject> successListener, final Response.ErrorListener errorListener) {
//        String url = BASE_URL + "seasons";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        successListener.onResponse(response);
//                    }
//                },
//                errorListener
//        ) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("Authorization", "Bearer " + token);
//                return headers;
//            }
//        };
//
//        requestQueue.add(jsonObjectRequest);
//    }

}
