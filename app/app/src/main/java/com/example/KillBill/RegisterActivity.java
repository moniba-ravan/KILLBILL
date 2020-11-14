package com.example.mehraneh.KillBill;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    Button bRegister;
    EditText etusername,etpassword,etverifypassword,etage,etincome;
    String setusername,setpassword,setverifypassword,setage,setincome;
    String register_url = "http://104.236.83.237:7677/register";
    String login_url = "http://104.236.83.237:7677/login";
    SharedPreferences Token ,UserName , PassWord;
    RequestQueue queue;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        queue = Volley.newRequestQueue(this);
        bRegister = (Button) findViewById(R.id.btnregister);
        etusername=(EditText) findViewById(R.id.reusername
        );
        etpassword=(EditText) findViewById(R.id.rpassword);
        etverifypassword=(EditText) findViewById(R.id.rverifypas);
        etage=(EditText) findViewById(R.id.rage);
        etincome=(EditText) findViewById(R.id.rincome);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int connection = popupForCheckInternet();
                if (connection == 1) {
                    setusername = etusername.getText().toString();
                    setpassword = etpassword.getText().toString();
                    setverifypassword = etverifypassword.getText().toString();
                    setage = etage.getText().toString();
                    setincome = etincome.getText().toString();
                    if (setusername.equals("")) {
                        Toast.makeText(getApplicationContext(), "فیلد نام کاربری خالی است.", Toast.LENGTH_SHORT).show();
                    } else if (setpassword.equals("")) {
                        Toast.makeText(getApplicationContext(), "فیلد رمز خالی است.", Toast.LENGTH_SHORT).show();
                    } else if (setverifypassword.equals("")) {
                        Toast.makeText(getApplicationContext(), "فیلد تائید رمز خالی است.", Toast.LENGTH_SHORT).show();
                    } else if (setage.equals("")) {
                        Toast.makeText(getApplicationContext(), "فیلد سن خالی است.", Toast.LENGTH_SHORT).show();
                    } else if (setincome.equals("")) {
                        Toast.makeText(getApplicationContext(), "فیلد درآمد خالی است.", Toast.LENGTH_SHORT).show();
                    } else if (setusername.equals("admin")) {
                        Toast.makeText(getApplicationContext(), "این ایمیل قبلا ثبت شده است.", Toast.LENGTH_SHORT).show();
                    } else if (!setpassword.equals(setverifypassword)) {
                        Toast.makeText(getApplicationContext(), "رمز شما با تکرار آن یکسان نیست.", Toast.LENGTH_SHORT).show();
                    } else {

                        StringRequest postRequest = new StringRequest(Request.Method.POST, register_url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // response
                                        Log.d("Response", response);
                                        popUpForSuccessfullRegister();

                                        UserName = getSharedPreferences("user", MODE_PRIVATE);
                                        SharedPreferences.Editor userEditor = UserName.edit();
                                        userEditor.putString("user", setusername);
                                        userEditor.apply();

                                        PassWord = getSharedPreferences("pass", MODE_PRIVATE);
                                        SharedPreferences.Editor passEditor = PassWord.edit();
                                        passEditor.putString("pass", setpassword);
                                        passEditor.apply();

                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if (jsonObject.getString("success").equals("true")) {

                                                token = jsonObject.getString("token");

                                                Token = getSharedPreferences("token", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = Token.edit();
                                                editor.putString("token", token);
                                                editor.apply();

                                            }
                                        } catch (JSONException e) {
                                            Log.d("err", e.toString());
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        Log.d("Error.Response", "Error");

                                        switch (error.networkResponse.statusCode) {
                                            case 303:
                                                Toast.makeText(RegisterActivity.this, "user already exists!",
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
                                params.put("username", setusername);
                                params.put("password", setpassword);
                                params.put("income", setincome);
                                params.put("age", setage);

                                //Ezafe shoddd :-P
                                return params;
                            }
                        };
                        queue.add(postRequest);


                    }
                }
            }
    });
}


public  void popUpForSuccessfullRegister(){

    StringRequest postRequest = new StringRequest(Request.Method.POST, login_url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    // response
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getString("success").equals("true")) {

                            Log.d("log", "success:true");
                            //login
                            token = jsonObject.getString("token");
                            Log.d("Token : ", token);

                            UserName = getSharedPreferences("user", MODE_PRIVATE);
                            SharedPreferences.Editor userEditor = UserName.edit();
                            userEditor.putString("user", setusername);
                            userEditor.commit();

                            PassWord = getSharedPreferences("pass", MODE_PRIVATE);
                            SharedPreferences.Editor passEditor = PassWord.edit();
                            passEditor.putString("pass", setpassword);
                            passEditor.commit();


                            Token = getSharedPreferences("token", MODE_PRIVATE);
                            SharedPreferences.Editor editor = Token.edit();
                            editor.putString("token", token);
                            editor.apply();
                            Log.d("before catch : ", "register");
                            editor.putBoolean("hasLoggedIn", true);
                            editor.commit();



                        }
                        finish();
                        Intent intent = new Intent(getApplicationContext(), FeatureActivity.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                    }

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // error
                    Log.d("Error.Response", String.valueOf(error));


                }
            }
    ) {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();
            params.put("username", setusername);
            params.put("password", setpassword);
            return params;
        }
    };

    queue.add(postRequest);

}
public  void popupForUsernameExist(){
    Toast.makeText(RegisterActivity.this, "user already exists!",
            Toast.LENGTH_SHORT).show();
}

    public int popupForCheckInternet() {
        Log.d("connectiom","error connection");
        ConnectivityManager connectivityManager = (ConnectivityManager) RegisterActivity.this.getSystemService(RegisterActivity.this.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected())){
            return 1;
        }else{

            Toast.makeText(RegisterActivity.this, "خطای اتصال به اینترنت",
                    Toast.LENGTH_SHORT).show();
            return 0;
        }

    }
}







