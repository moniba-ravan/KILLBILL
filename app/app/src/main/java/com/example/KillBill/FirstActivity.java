package com.example.mehraneh.KillBill;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class FirstActivity extends AppCompatActivity {

    Button bRegister, bLogin;
    EditText tUsername, tPassword;
    String user, pass;
    String login_url = "http://104.236.83.237:7677/login";
    RequestQueue queue;
    String token;
    SharedPreferences UserName, PassWord, Token;



    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.e("onCreate: ", "onCreate is Running");
        tUsername = (EditText) findViewById(R.id.username);
        tPassword = (EditText) findViewById(R.id.password);
        queue = Volley.newRequestQueue(this);
        bRegister = (Button) findViewById(R.id.btnregister);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        bLogin = (Button) findViewById(R.id.btnlogin);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int connection = popupForCheckInternet();
                if (connection == 1) {
                    user = tUsername.getText().toString();
                    pass = tPassword.getText().toString();
                    StringRequest postRequest = new StringRequest(Request.Method.POST, login_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    Log.d("Response", response);
                                    UserName = getSharedPreferences("user", MODE_PRIVATE);
                                    SharedPreferences.Editor userEditor = UserName.edit();
                                    userEditor.putString("user", user);
                                    userEditor.commit();

                                    PassWord = getSharedPreferences("pass", MODE_PRIVATE);
                                    SharedPreferences.Editor passEditor = PassWord.edit();
                                    passEditor.putString("pass", pass);
                                    passEditor.commit();


                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        if (jsonObject.getString("success").equals("true")) {

                                            token = jsonObject.getString("token");
                                            Log.d("Token : ", token);

                                            Token = getSharedPreferences("token", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = Token.edit();
                                            editor.putString("token", token);
                                            editor.apply();
                                            finish();
                                            Intent intent = new Intent(getApplicationContext(), FeatureActivity.class);
                                            startActivity(intent);
                                        }
                                    } catch (JSONException e) {
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Log.d("Error.Response", "Error");

                                    switch (error.networkResponse.statusCode) {
                                        case 404:
                                            Toast.makeText(FirstActivity.this, "نام کاربری یا رمز اشتباه است",
                                                    Toast.LENGTH_SHORT).show();
                                            break;

//                                  case 404:
//                                      popupForPhonenumberExist();
//                                      break;
                                    }
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
            }
        });


    }
    public int popupForCheckInternet() {
        Log.d("connectiom","error connection");
        ConnectivityManager connectivityManager = (ConnectivityManager) FirstActivity.this.getSystemService(FirstActivity.this.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected())){
            return 1;
        }else{

            Toast.makeText(FirstActivity.this, "خطای اتصال به اینترنت",
                    Toast.LENGTH_SHORT).show();
            return 0;
        }

    }
}

