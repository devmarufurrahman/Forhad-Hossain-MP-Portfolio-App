package com.example.forhadhossainmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DevelopmentActivities extends AppCompatActivity {

    Button postBtn1, postBtn2, postBtn3, postBtn4, postBtn5;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_development_activities);
        postBtn1 = findViewById(R.id.postBtn1);
        postBtn2 = findViewById(R.id.postBtn2);
        postBtn3 = findViewById(R.id.postBtn3);
        postBtn4 = findViewById(R.id.postBtn4);
        postBtn5 = findViewById(R.id.postBtn5);

        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // post details button
        postBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevelopmentActivities.this, PostDetailsActivity.class);
                intent.putExtra("img","img1");
                startActivity(intent);
            }
        });

        postBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevelopmentActivities.this, PostDetailsActivity.class);
                intent.putExtra("img","img2");
                startActivity(intent);
            }
        });
        postBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevelopmentActivities.this, PostDetailsActivity.class);
                intent.putExtra("img","img3");
                startActivity(intent);
            }
        });
        postBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevelopmentActivities.this, PostDetailsActivity.class);
                intent.putExtra("img","img4");
                startActivity(intent);
            }
        });
        postBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevelopmentActivities.this, PostDetailsActivity.class);
                intent.putExtra("img","img5");
                startActivity(intent);
            }
        });


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