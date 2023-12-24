package com.example.forhadhossainmp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;

public class GetAnAppointment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    Toolbar toolbar;
    Button dateAppointment;
    ShapeableImageView member_photo;
    int day, month, year, myday, myMonth, myYear;
    String birth_date = "", encodedImage = "";
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_an_appointment);
        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dateAppointment = findViewById(R.id.dateAppointment);
        member_photo = findViewById(R.id.member_photo);




        dateAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateAppointment.setError(null);
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(GetAnAppointment.this, GetAnAppointment.this,year, month,day);
                datePickerDialog.show();
            }
        });


        // select Image

        member_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Dexter.withActivity(GetAnAppointment.this)
                            .withPermission(Manifest.permission.READ_MEDIA_IMAGES)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse response) {

                                    Intent intent = new Intent(Intent.ACTION_PICK);
                                    intent.setType("image/*");
                                    startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
                                }
                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse response) {

                                    Toast.makeText(GetAnAppointment.this, "Permission Denied!!", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }


                            }).check();
                } else {

                    Dexter.withActivity(GetAnAppointment.this)
                            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse response) {

                                    Intent intent = new Intent(Intent.ACTION_PICK);
                                    intent.setType("image/*");
                                    startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);

                                }
                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse response) {

                                    Toast.makeText(GetAnAppointment.this, "Permission Denied!!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                                    token.continuePermissionRequest();
                                }
                            }).check();
                }
            }
        });




    }


// select image method

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==1 && resultCode==RESULT_OK){

            assert data != null;
            Uri filepath=data.getData();
            try {

                InputStream inputStream= getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                member_photo.setImageBitmap(bitmap);
                encodeBitmapImage();

            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodeBitmapImage() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,60,byteArrayOutputStream);
        byte[] bytesOfImage=byteArrayOutputStream.toByteArray();
        int lengthbmp = bytesOfImage.length;
        lengthbmp=lengthbmp/1024;
        System.out.println("image length : " + lengthbmp);

        if (lengthbmp>2048){


            Toast.makeText(this, "Image Too Large...select a smaller one", Toast.LENGTH_SHORT).show();

        } else if (lengthbmp==0){

            encodedImage="";

        }else{

            encodedImage= Base64.encodeToString(bytesOfImage, Base64.DEFAULT);
        }
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = dayOfMonth;
        myMonth = month;
        birth_date = (myYear + "-" + (myMonth+1) + "-" + myday);
        dateAppointment.setText(birth_date);
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