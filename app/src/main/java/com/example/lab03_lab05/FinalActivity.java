package com.example.lab03_lab05;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FinalActivity extends Activity {
    TextView quote;
    Button editBtn;
    Button logoutBtn;
    ImageView blue1, blue2, pink;
    Intent intent;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.finallayout);

        String tag = "com.zhangmegan.demo.sharedprefs";

        sharedPref = getSharedPreferences(tag, MODE_PRIVATE);
        editor = sharedPref.edit();

        editor.putBoolean("FinalActivity", true);
        editor.putBoolean("EditFormActivity", false);

        blue1 = findViewById(R.id.blue1);
        blue2 = findViewById(R.id.blue2);
        pink = findViewById(R.id.pink);

        String username = getIntent().getStringExtra("username");
        quote = findViewById(R.id.quote);
        quote.setText(sharedPref.getString(username, ""));

        if(sharedPref.getBoolean(username+"_blue", false)) {
            blue1.setBackgroundResource(R.drawable.blueflower_removebg);
            blue2.setBackgroundResource(R.drawable.blueflower2_removebg);
        }
        if(sharedPref.getBoolean(username+"_pink", false)) {
            pink.setBackgroundResource(R.drawable.pinkflower);
        }

        editBtn = findViewById(R.id.editbtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), EditFormActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        logoutBtn = findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        editor.putBoolean("MainActivity", false);
        editor.putBoolean("FinalActivity", true);
        editor.putBoolean("EditFormActivity", false);
    }
}
