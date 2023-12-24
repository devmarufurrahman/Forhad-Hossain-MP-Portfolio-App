package com.example.forhadhossainmp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {

    EditText name, contact, email, address, password, confirmPassword;
    Button loginBtn;
    ShapeableImageView user_photo;
    ProgressBar progressBar;
    String encodedImage = "";
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // id define
        name = findViewById(R.id.loginName);
        contact = findViewById(R.id.loginContact);
        email = findViewById(R.id.loginEmail);
        address = findViewById(R.id.loginAddress);
        loginBtn = findViewById(R.id.loginBtn);
        user_photo = findViewById(R.id.user_photo);



        user_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Dexter.withActivity(LoginActivity.this)
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

                                    Toast.makeText(LoginActivity.this, "Permission Denied!!", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }


                            }).check();
                } else {

                    Dexter.withActivity(LoginActivity.this)
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

                                    Toast.makeText(LoginActivity.this, "Permission Denied!!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                                    token.continuePermissionRequest();
                                }
                            }).check();
                }
            }
        });




        // login btn activity
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLogin();
            }
        });
    }

    private void submitLogin() {
        String nameValue = name.getText().toString();
        String contactValue = contact.getText().toString();
        String addressValue = name.getText().toString();
        String emailValue = email.getText().toString();

        boolean isValid = isValidEmail(emailValue);

        if (nameValue.equals("") || contact.length() != 11){
            name.setError("Please Input Name");
        } else if (contactValue.equals("")) {
            contact.setError("Please Input Contact");
        } else if (addressValue.equals("")) {
            address.setError("Please Input Address");
        } else if (isValid) {
            address.setError("Input Valid Email");
        } else {

            //Toast.makeText(LoginActivity.this, "Submited", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.VISIBLE);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[46];
                    field[0] = "userName";
                    field[1] = "email";
                    field[2] = "contact";
                    field[3] = "address";
                    field[4] = "photo";


                    //Creating array for data
                    String[] data = new String[46];
                    data[0] = nameValue;
                    data[1] = emailValue;
                    data[2] = contactValue;
                    data[3] = addressValue;
                    data[4] = encodedImage;
                    PutData putData = new PutData("", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult().trim();
                            Log.d("NEW MEMBER API : ",result);
                            progressBar.setVisibility(View.GONE);
                            if (result.equals("success")) {

                                // Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_SHORT).show();

                                Toast.makeText(LoginActivity.this, "Profile Created Successfully", Toast.LENGTH_SHORT).show();


                                SharedPreferences preferences = getSharedPreferences("SharePreference", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("contact", contactValue);
                                editor.apply();


                            } else if(result.equals("Contact Already in use. Try with Another One")) {
                                progressBar.setVisibility(View.GONE);
                                Log.i("PutData", result);
                                Toast.makeText(LoginActivity.this, "Contact Already in use. Try with Another One", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }
                        }
                    }
                    //End Write and Read data with URL
                }
            });
        }
    }

    // email validity
    public boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
                user_photo.setImageBitmap(bitmap);
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



}