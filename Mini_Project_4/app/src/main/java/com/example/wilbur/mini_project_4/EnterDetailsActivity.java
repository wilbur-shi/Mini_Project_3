package com.example.wilbur.mini_project_4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class EnterDetailsActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText descriptionEditText;
    private Button uploadPhotoBtn;
    private Button submitBtn;
    private ImageView uploadedPhotoImageView;
    private boolean pressedSubmit;
    private byte[] uploadedImage;
    public static final int SELECT_PHOTO = 100;
    public static final String NAME_KEY = "name";
    public static final String DESCRIPTION_KEY = "description";
    public static final String IMAGE_KEY = "image";
    public static final String IS_CREATE_NEW_KEY = "isCreateNew";
    private boolean isCreateNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        uploadedPhotoImageView = (ImageView) findViewById(R.id.uploadedPhotoImageView);
        uploadPhotoBtn = (Button) findViewById(R.id.uploadPhotoBtn);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        Intent received = getIntent();
        isCreateNew = received.getBooleanExtra(IS_CREATE_NEW_KEY, true);

        descriptionEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                descriptionEditText.setError(null);

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                descriptionEditText.setError(null);

            }
        });
        nameEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nameEditText.setError(null);

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                nameEditText.setError(null);

            }
        });

        uploadPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Save information and push to a new Parse object, close activity
                boolean noError = true;
                if (nameEditText.getText().toString().trim().equalsIgnoreCase("")) {
                    nameEditText.setError("This field can not be blank");
                    noError = false;
                }
                if (descriptionEditText.getText().toString().trim().equalsIgnoreCase("")) {
                    descriptionEditText.setError("This field can not be blank");
                    noError = false;
                }
                if (noError) {
                    pressedSubmit = true;
                    finish();
                }
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        if (pressedSubmit) {
            ParseObject parseObject = new ParseObject("Img");
            parseObject.put(NAME_KEY, nameEditText.getText().toString());
            parseObject.put(DESCRIPTION_KEY, descriptionEditText.getText().toString());
//            ParseFile file = new ParseFile("uploaded.jpg", uploadedImage);
//            if (file == null) {
//                Log.d("File Error", "File is null. noooo");
//            }
//            parseObject.put(IMAGE_KEY, file);
            parseObject.saveInBackground();
            MainActivity.locationAdapter.notifyDataSetChanged();
        }
        else {
            if(isCreateNew) {
                // TODO: SharedPreferences stuff to save draft

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // This is where we come back to the MainActivity and must process some requestCode
        if (resultCode == RESULT_OK) { // We need this if statement. This is how we know a request actually happened.
            switch (requestCode) { // You can use if statements, but with multiple requestCodes, this is better
                case SELECT_PHOTO: // This is why we had this constant defined earlier.
                    Uri selectedImage = data.getData(); // Get the selected image as a Uri. You can look up what that is.
                    uploadedImage = readBytes(selectedImage);

                if (uploadedImage != null) {
                    Bitmap bMap = BitmapFactory.decodeByteArray(uploadedImage, 0, uploadedImage.length);
                    uploadedPhotoImageView.setImageBitmap(bMap);
                } else {

                }

                    break; // Must need if you have multiple request codes in a switch statement.
            }
        }
    }

    public byte[] readBytes(Uri uri) {
        byte[] data = null;
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data = baos.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void sendImage(Uri image) {
        // TODO: PARSE OBJECT STUFF
    }

    public void choosePhoto() {
        Intent photoIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoIntent.setType("image/");
        if (photoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(photoIntent, SELECT_PHOTO); // Here we will start the Intent.
            // The SELECT_PHOTO is important for onActivityResult above (requestCode)
        }

    }

}
