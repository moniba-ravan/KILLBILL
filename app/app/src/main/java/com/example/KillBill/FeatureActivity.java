package com.example.mehraneh.KillBill;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FeatureActivity extends AppCompatActivity {
    Button bsubmit,baboutus;
    Button blogout;
    SharedPreferences Token ,UserName , PassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);
        bsubmit=(Button) findViewById(R.id.btnsubmit);
        blogout=(Button) findViewById(R.id.btnlogout);
        baboutus=(Button) findViewById(R.id.aboutus);
        bsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserName = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor userEditor = UserName.edit();
                userEditor.putString("user", null);
                userEditor.commit();

                PassWord = getSharedPreferences("pass", MODE_PRIVATE);
                SharedPreferences.Editor passEditor = PassWord.edit();
                passEditor.putString("pass", null);
                passEditor.commit();
                finish();
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
            }
        });
        baboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(intent);
            }
        });


    }
}