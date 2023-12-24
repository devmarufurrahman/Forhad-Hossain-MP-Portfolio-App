package com.example.forhadhossainmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MediaActivity extends AppCompatActivity {

    CardView newsPost, photoGalleryCard, socialCard;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // id define
        newsPost = findViewById(R.id.newsPost);
        photoGalleryCard = findViewById(R.id.photoGalleryCard);
        socialCard = findViewById(R.id.socialCard);




        // news post activity
        newsPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this, NewsPost.class);
                startActivity(intent);
            }
        });


        // photo gallery view
        photoGalleryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this, photo_gallery.class);
                startActivity(intent);
            }
        });


        // Social Media Activity
        socialCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this, SocialMediaDetails.class);
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