package com.example.mehraneh.KillBill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApplianceActivity extends AppCompatActivity {
    EditText etfuniture,ettoiletry;
    String str_furniture,str_toilery;
    Button btnsumbitapp;
    String addFurniture_url = "http://104.236.83.237:7677/addFurniture";
    RequestQueue queue;
    String token , user,pass;
    SharedPreferences Token ,UserName,PassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance);
        this.setTitle("لوازم خانه");
        etfuniture=(EditText) findViewById(R.id.furniture);
        ettoiletry=(EditText) findViewById(R.id.toiletry);
        btnsumbitapp=(Button) findViewById(R.id.submitapp);
        user=getSharedPreferences("user",MODE_PRIVATE).getString("user",null);
        pass=getSharedPreferences("pass",MODE_PRIVATE).getString("pass",null);
        queue = Volley.newRequestQueue(this);

        login();
        btnsumbitapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int connection = popupForCheckInternet();
                if (connection == 1) {
                    Log.d("after login", "before string request");
                    StringRequest postRequest = new StringRequest(Request.Method.POST, addFurniture_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    Log.e("Response", response);
                                    finish();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Log.e("Error.Response", error.toString());
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            str_furniture=etfuniture.getText().toString();
                            str_toilery=ettoiletry.getText().toString();
                            if(str_furniture==null)
                                str_furniture="0";
                            if(str_toilery==null)
                                str_toilery="0";

                            params.put("furniture", str_furniture);
                            params.put("cosmetics", str_toilery);


                            return params;
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("token", token);
                            return headers;
                        }
                    };
                    queue.add(postRequest);
                }
            }
        });

    }
    public void login(){
        String login_url = "http://104.236.83.237:7677/login";


        StringRequest postRequest = new StringRequest(Request.Method.POST, login_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // response
                        Log.d("Response", response);
                        try {
                            Log.d("log", "intry");

                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("success").equals("true")) {

                                Log.d("log", "success:true");

                                token = jsonObject.getString("token");
                                Log.d("Token : ", token);

                                UserName = getSharedPreferences("user", MODE_PRIVATE);
                                SharedPreferences.Editor userEditor = UserName.edit();
                                userEditor.putString("user", user);
                                userEditor.commit();

                                PassWord = getSharedPreferences("pass", MODE_PRIVATE);
                                SharedPreferences.Editor passEditor = PassWord.edit();
                                passEditor.putString("pass", pass);
                                passEditor.commit();


                                Token = getSharedPreferences("token", MODE_PRIVATE);
                                SharedPreferences.Editor editor = Token.edit();
                                editor.putString("token", token);
                                editor.apply();
                                editor.putBoolean("hasLoggedIn", true);
                                editor.commit();



                            }
                        } catch (JSONException e) {
                        }

                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", String.valueOf(error));
                        //popupForErrorLogin();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", user);
                params.put("password", pass);
                return params;
            }
        };

        queue.add(postRequest);


    }
    public int popupForCheckInternet() {
        Log.d("connectiom","error connection");
        ConnectivityManager connectivityManager = (ConnectivityManager) ApplianceActivity.this.getSystemService(ApplianceActivity.this.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected())){
            return 1;
        }else{

            Toast.makeText(ApplianceActivity.this, "خطای اتصال به اینترنت",
                    Toast.LENGTH_SHORT).show();
            return 0;
        }

    }

}
