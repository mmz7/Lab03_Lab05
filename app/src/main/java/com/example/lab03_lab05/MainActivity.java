package com.example.lab03_lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sPref;
    SharedPreferences.Editor sPrefEditor;
    TextView directions;
    EditText enterName;
    Button btn;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tag = "com.zhangmegan.demo.sharedprefs";

        sPref = getSharedPreferences(tag, MODE_PRIVATE);
        sPrefEditor = sPref.edit();

        directions = findViewById(R.id.usernameDir);
        enterName = findViewById(R.id.nameTextBox);
        btn = findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = enterName.getText().toString();

                if(username.length() > 0) {
                    if(sPref.contains(username)) {
                        System.out.println("hello");
                        intent = new Intent(getApplicationContext(), FinalActivity.class);
                    } else {
                        //sPrefEditor.putBoolean(username, true);
                        intent = new Intent(getApplicationContext(), EditFormActivity.class);
                    }
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if()
    }
}