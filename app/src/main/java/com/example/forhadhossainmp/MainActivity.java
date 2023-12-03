package com.example.forhadhossainmp;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    ImageView  notificationIcon;
    LinearLayout event, speachBtn, complainBtn;
    ArrayList<String> eventList;
    ImageSlider imageSlider;
    ArrayList<SlideModel> imageList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigation Drawer------------------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_menu);
        toolbar = findViewById(R.id.appBar);
        setSupportActionBar(toolbar);


        // Bottom Navigation view start ===========================
        bottomNavigationView = findViewById(R.id.bottomNavView);
//        bottomNavigationView.setBackground(null);

        bottomNavigationView.setItemIconTintList(null);


        // id selection
        imageSlider = findViewById(R.id.imgSlider);


        // slider image
        imageList.add(new SlideModel(R.drawable.message_slider_img,  ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.message_slider_img2,  ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.cover_image,  ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(imageList);


        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setItemIconTintList(null);


        // Drawer item Click event ------
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.curriculumVitae){
                    Intent intent = new Intent(getApplicationContext(), PersonalBiography.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.home){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    finish();
                } else if (id == R.id.political_life){
                    Intent intent = new Intent(getApplicationContext(), PoliticatlBiography.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.media){
                    Intent intent = new Intent(getApplicationContext(), MediaActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                } else if (id == R.id.speech){
                    Intent intent = new Intent(getApplicationContext(), MessageAndSpeach.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                }

                return true;
            }
        });


        // bottom navigation implement

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId();
                if (id == R.id.event){

                    eventGet();

                } else if (id == R.id.development_activities){
                    Intent intent = new Intent(getApplicationContext(), DevelopmentActivities.class);
                    startActivity(intent);

                } else if (id == R.id.navMeeting){
                    Intent intent = new Intent(getApplicationContext(), GetAnAppointment.class);
                    startActivity(intent);
                }

                return true;
            }
        });



    }

    private void eventGet() {

        // Feature click event
        eventList=new ArrayList<>();
        eventList.add("পরবর্তী ইভেন্ট");
        eventList.add("বর্তমান ইভেন্ট");
        eventList.add("বিগত ইভেন্ট");


                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_spinner_layout_2);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                ListView listView=dialog.findViewById(R.id.list_view);
                ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,eventList);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // when item selected from list
                        // set selected item on textView
                        String item  = adapter.getItem(position);
                        switch (Objects.requireNonNull(item)){
                            case "পরবর্তী ইভেন্ট" :
                                Intent intent1 = new Intent(getApplicationContext(), NextEvent.class);
                                startActivity(intent1);
                                dialog.dismiss();
                                break;
                            case "বর্তমান ইভেন্ট" :
                                Intent intent2 = new Intent(getApplicationContext(), PresentEvent.class);
                                startActivity(intent2);
                                dialog.dismiss();
                                break;
                            case "বিগত ইভেন্ট" :
                                Intent intent3 = new Intent(getApplicationContext(), PastEvent.class);
                                startActivity(intent3);
                                dialog.dismiss();
                                break;
                        }
                    }
                });

    }



}