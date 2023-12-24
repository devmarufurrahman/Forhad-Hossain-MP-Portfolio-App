package com.example.forhadhossainmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserProfile extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout pressedLogin;
    String isContact;
    FloatingActionButton logOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        // id define
        pressedLogin = findViewById(R.id.pressedLogin);
        logOutBtn = findViewById(R.id.logOutBtn);

        //get shared preference
        SharedPreferences preferences = getSharedPreferences("SharePreference", MODE_PRIVATE);
        isContact = preferences.getString("contact","");


        // toolbar
        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // login option enable

        if (isContact.equals("")){
            pressedLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserProfile.this, LoginActivity.class);
                    startActivity(intent);

                }
            });

            logOutBtn.setVisibility(View.GONE);

        } else {
            pressedLogin.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}