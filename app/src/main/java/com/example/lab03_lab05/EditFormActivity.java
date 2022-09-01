package com.example.lab03_lab05;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditFormActivity extends Activity {
    SharedPreferences sPref;
    SharedPreferences.Editor sPrefEditor;
    String username;
    EditText editText;
    Button button;
    Intent intent;
    CheckBox blue;
    CheckBox pink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editform);

        String tag = "com.zhangmegan.demo.sharedprefs";

        sPref = getSharedPreferences(tag, MODE_PRIVATE);
        sPrefEditor = sPref.edit();

        sPrefEditor.putBoolean("FinalActivity", false);
        sPrefEditor.putBoolean("EditFormActivity", true);

        editText = findViewById(R.id.imgTextBox);
        button = findViewById(R.id.imgEnter);
        blue = findViewById(R.id.blueflowers);
        pink = findViewById(R.id.pinkflowers);

        username = getIntent().getStringExtra("username");

        if (sPref.contains(username)) {
            editText.setText(sPref.getString(username, ""));
            blue.setChecked(sPref.getBoolean(username+"_blue", false));
            pink.setChecked(sPref.getBoolean(username+"_pink", false));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote = editText.getText().toString();
                if(!quote.equals("")) {
                    sPrefEditor.putString(username, quote);
                }
                sPrefEditor.putBoolean(username+"_blue", blue.isChecked());
                sPrefEditor.putBoolean(username+"_pink", pink.isChecked());

                sPrefEditor.apply();

                intent = new Intent(getApplicationContext(), FinalActivity.class);
                intent.putExtra("username", username);

                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Form Activity On Pause");

        String quote = editText.getText().toString();
        if(!quote.equals("")) {
            sPrefEditor.putString(username, quote);
        }
        sPrefEditor.putBoolean(username+"_blue", blue.isChecked());
        sPrefEditor.putBoolean(username+"_pink", pink.isChecked());

        sPrefEditor.apply();

        sPrefEditor.putBoolean("MainActivity", false);
        sPrefEditor.putBoolean("FinalActivity", false);
        sPrefEditor.putBoolean("EditFormActivity", true);
    }
}
