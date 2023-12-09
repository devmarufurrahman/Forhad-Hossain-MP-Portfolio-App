package com.example.forhadhossainmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class PostDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView postDetails, postTitle;
    ImageView postImg;
    String imgIntent, titleIntent, detailsIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        // get intent value
        Intent intent = getIntent();
        imgIntent = intent.getStringExtra("img");

        //id define
        postDetails = findViewById(R.id.postDetails);
        postImg = findViewById(R.id.postImg);
        postTitle = findViewById(R.id.postTitle);

        // toolbar customize
        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // set post information
        setPost(imgIntent, titleIntent, detailsIntent);
    }

    private void setPost(String img, String title, String details) {
        if (img.equals("img1")){
            postImg.setImageResource(R.drawable.development_img1);
            postTitle.setText(R.string.post_title_1);
            postDetails.setText(R.string.post_details_1);
        } else if (img.equals("img2")) {
            postImg.setImageResource(R.drawable.development_img1);
            postTitle.setText(R.string.post_title_2);
            postDetails.setText(R.string.post_details_2);
        } else if (img.equals("img3")) {
            postImg.setImageResource(R.drawable.development_img2);
            postTitle.setText(R.string.post_title_3);
            postDetails.setText(R.string.post_details_3);
        } else if (img.equals("img4")) {
            postImg.setImageResource(R.drawable.development_img1);
            postTitle.setText(R.string.post_title_3);
            postDetails.setText(R.string.post_details_3);
        } else if (img.equals("img5")) {
            postImg.setImageResource(R.drawable.development_img1);
            postTitle.setText(R.string.post_title_3);
            postDetails.setText(R.string.post_details_3);
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