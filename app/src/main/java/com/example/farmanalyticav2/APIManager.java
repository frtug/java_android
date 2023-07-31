package com.example.farmanalyticav2;

import android.content.Context;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
// created a class called apiManager which will manage all the api request but we still need to call them.
public class APIManager {

    private static final String BASE_URL = "https://api.farmanalyticaph.online/api"; // Url of the backend server

//    private final RequestQueue requestQueue;
    private final  Context context;
    private String token = "";
    private  String refresh_token = "";

    public APIManager(Context context) {
        this.context = context;
    }
    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(Object response);
    }

    // callBack to send a response back to the code
    public void testing_work(VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "Result: i m into testing work");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, BASE_URL,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void Login(String username,String password,VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", username+" "+password);
        String url = BASE_URL + "/login";
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                     token = response.getString("token");
                     refresh_token = response.getString("refresh_token");
                     volleyResponseListener.onResponse(response);
                }
                 catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                Toast.makeText(context,token,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null && error.networkResponse.statusCode == 401){
                    // call the api for refresh api
                    Toast.makeText(context,"Something went wrong, -"+error.networkResponse.statusCode,Toast.LENGTH_SHORT).show();

                }
                volleyResponseListener.onError("Something went wrong, -"+error.networkResponse.statusCode);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type","application/json");
//                headers.put("Authorization", "Bearer " + YOUR_ACCESS_TOKEN);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void SignUp(String full_name,String username,String password,String confirmPassword,VolleyResponseListener volleyResponseListener){
        String url = BASE_URL + "/signup";
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("password", password);
            jsonBody.put("full_name",full_name);
            jsonBody.put("confirmPassword",confirmPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    String msg = response.getString("msg");
                    String success = response.getString("success");
                    volleyResponseListener.onResponse(response);
                }
                catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                Toast.makeText(context,token,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null && error.networkResponse.statusCode == 401){
                    // call the api for refresh api
                    Toast.makeText(context,"Something went wrong, -"+error.networkResponse.statusCode,Toast.LENGTH_SHORT).show();

                }
                volleyResponseListener.onError("Something went wrong, -"+error.networkResponse.statusCode);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type","application/json");
//                headers.put("Authorization", "Bearer " + YOUR_ACCESS_TOKEN);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void getProfileDetails(VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL + "/user";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void getDistricts(String state,VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL + "/districts?state_name="+state;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void getStates(VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL + "/states";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void getSoil(VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL +"/soils";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void updateProfile(JSONObject profileDetails,VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL +"/user/update";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url,profileDetails, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void predictCrop(String district,VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL +"/A1/recommendCrop?district="+district;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void predictNpk(String crop, String district,VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL +"/A1/recommendNPK?district="+district;
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("crop", crop.toLowerCase());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void setScheduler(JSONObject data,VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL +"/scheduler";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,data, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void predictFertilizer(JSONObject reqDetails, String district,VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL +"/A1/recommendFertilizer?district="+district;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,reqDetails, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void predictCropApp2(JSONObject npkValues, String district,VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL +"/A2/recommendCrop?district="+district;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,npkValues, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void getWeatherDetails(String district,VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL +"/weather?district_name="+district;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void getSeasons(VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL +"/seasons";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void predictCropYield(String state, String district, String crop, String season, String area, VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL + String.format("recommendCropYield?state=%s&district=%s&season=%s",
                state, district.toUpperCase(), season);
        String modifiedCrop = Character.toUpperCase(crop.charAt(0)) + crop.substring(1);
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("crop",modifiedCrop);
            jsonObject.put("area",area);
        } catch (JSONException e) {
        throw new RuntimeException(e);
    }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
    public void getSeasonbyMonth(VolleyResponseListener volleyResponseListener){
        Log.i("APIManager", "work getProfileDetails");
        String url = BASE_URL +"/getSeasonbyMonth";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener.onResponse(response);
//                        Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("something wrong happened");
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                // Add more headers if needed
                return headers;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }



}
