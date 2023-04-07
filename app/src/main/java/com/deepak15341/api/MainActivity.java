package com.deepak15341.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private JsonObjectRequest jsonObjectRequest;
    /*private  RequestQueue requestQueue,requestQueueArray;*/
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*requestQueue = Volley.newRequestQueue(this);
        requestQueueArray = Volley.newRequestQueue(this);*/
        mRequestQueue = VolleySingleton.getInstance().getRequestQueue();
         jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://official-joke-api.appspot.com/jokes/random", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i("response", response + " ");
                try {
                    int id = response.getInt("id");
                    //Toast.makeText(MainActivity.this, id+" ", Toast.LENGTH_SHORT).show();
                    String setup = response.getString("setup");
                    //Toast.makeText(MainActivity.this, setup, Toast.LENGTH_SHORT).show();
                    String punchline = response.getString("punchline");
                    //Toast.makeText(MainActivity.this, punchline, Toast.LENGTH_SHORT).show();
                    String type = response.getString("type");
                    Toast.makeText(MainActivity.this, "ID : " + id + ", Type : " + type + ", Setup : " + setup + ", Punchline : " + punchline, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("responseerror", error.getMessage());
            }
        });
    mRequestQueue.add(jsonObjectRequest);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://official-joke-api.appspot.com/random_ten", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("responseArray", response+" " );
                for (int index =0;index<response.length();index++){
                    try {
                        JSONObject jsonObject =response.getJSONObject(index);
                        int id = jsonObject.getInt("id");
                        //Toast.makeText(MainActivity.this, id+" ", Toast.LENGTH_SHORT).show();
                        String setup =jsonObject.getString("setup");
                        //Toast.makeText(MainActivity.this, setup, Toast.LENGTH_SHORT).show();
                        String punchline = jsonObject.getString("punchline");
                        //Toast.makeText(MainActivity.this, punchline, Toast.LENGTH_SHORT).show();
                        String type = jsonObject.getString("type");
                        Toast.makeText(MainActivity.this, "ID : "+id+", Type : "+type+", Setup : "+setup+", Punchline : "+punchline , Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("responseerrorArray", error.getMessage() );
            }
        });

        mRequestQueue.add(jsonArrayRequest);

    }
}