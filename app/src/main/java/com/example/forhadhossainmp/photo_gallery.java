package com.example.forhadhossainmp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.forhadhossainmp.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class photo_gallery extends AppCompatActivity {
    GridView gridView;
    ArrayList<Integer> imgIds = new ArrayList<>(Arrays.asList(
            R.drawable.event1,
            R.drawable.message_slider_img,
            R.drawable.message_slider_img2,
            R.drawable.feature_image1,
            R.drawable.feature_image2,
            R.drawable.feature_image3,
            R.drawable.event3
    ));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        gridView = findViewById(R.id.girdView);


        gridView.setAdapter(new ImageAdapter(imgIds,photo_gallery.this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int itemPosition = imgIds.get(i);
                showDialogueBox(itemPosition);
            }
        });


    }



    public void showDialogueBox(int itemPosition){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialouge_layout);

        // getting custom view
        ImageView dialogImg;
        Button fullViewBtn, closeBtn;



        dialogImg = dialog.findViewById(R.id.dialogImage);
//        closeBtn = dialog.findViewById(R.id.closeBtn);
//        fullViewBtn = dialog.findViewById(R.id.fullViewBtn);

        dialogImg.setImageResource(itemPosition);

        dialog.setCancelable(true);

//        closeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });


//        fullViewBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(photo_gallery.this,FullViewImage.class);
//                intent.putExtra("imgId", itemPosition);
//                startActivity(intent);
//            }
//        });

        dialog.show();
    }

}