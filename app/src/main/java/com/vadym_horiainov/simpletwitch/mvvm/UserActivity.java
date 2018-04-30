package com.vadym_horiainov.simpletwitch.mvvm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vadym_horiainov.simpletwitch.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        String json = getIntent().getStringExtra("1");

        TextView textView = findViewById(R.id.user);
        textView.setText(json);

    }
}
