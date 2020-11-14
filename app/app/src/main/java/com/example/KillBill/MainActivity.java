package com.example.mehraneh.KillBill;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String myTag = "myMessages";
    Button btn_food,btn_clths,btn_trnspr,btn_applnce,btn_cmunctin,btn_entertainment,btn_stationery;

    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("KILL BILL");
        Log.i(myTag, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface d_n=Typeface.createFromAsset(getAssets(),"fonts/Mj_Alphabet_YasDL.com.ttf");
        TextView tv=(TextView)findViewById(R.id.btn_food);
        TextView tv1=(TextView)findViewById(R.id.btn_stationery);
        TextView tv2=(TextView)findViewById(R.id.btn_clothing);
        TextView tv3=(TextView)findViewById(R.id.btn_entertainment);
        TextView tv4=(TextView)findViewById(R.id.btn_appliances);
        TextView tv5=(TextView)findViewById(R.id.btn_comunication);
        TextView tv6=(TextView)findViewById(R.id.btn_transportation);


        tv.setTypeface(d_n);
        tv1.setTypeface(d_n);
        tv2.setTypeface(d_n);
        tv3.setTypeface(d_n);
        tv4.setTypeface(d_n);
        tv5.setTypeface(d_n);
        tv6.setTypeface(d_n);
        btn_food=(Button)findViewById(R.id.btn_food);
        btn_clths=(Button)findViewById(R.id.btn_clothing);
        btn_trnspr=(Button)findViewById(R.id.btn_transportation);
        btn_applnce=(Button)findViewById(R.id.btn_appliances);
        btn_cmunctin=(Button)findViewById(R.id.btn_comunication);
        btn_entertainment=(Button)findViewById(R.id.btn_entertainment);
        btn_stationery=(Button)findViewById(R.id.btn_stationery);
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent =new Intent(getApplicationContext(),FoodActivity.class);
                startActivity(intent);
            }
        });
        btn_clths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent =new Intent(getApplicationContext(),ClothingActivity.class);
                startActivity(intent);
            }
        });
        btn_trnspr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent =new Intent(getApplicationContext(),TransportActivity.class);
                startActivity(intent);
            }
        });
        btn_applnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent =new Intent(getApplicationContext(),ApplianceActivity.class);
                startActivity(intent);
            }
        });
        btn_cmunctin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent =new Intent(getApplicationContext(),CommunicationActivity.class);
                startActivity(intent);
            }
        });
        btn_entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent =new Intent(getApplicationContext(),EntertainmentActivity.class);
                startActivity(intent);
            }
        });
        btn_stationery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent =new Intent(getApplicationContext(),stationeryActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(myTag, "onStart");
    }

    @Override
    protected void onPostResume() {

        super.onPostResume();
        Log.i(myTag, "onPostResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(myTag, "onPuase");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(myTag, "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(myTag, "onDestroy");
    }
}
