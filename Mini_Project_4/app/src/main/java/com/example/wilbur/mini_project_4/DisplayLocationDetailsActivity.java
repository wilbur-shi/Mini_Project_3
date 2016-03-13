package com.example.wilbur.mini_project_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayLocationDetailsActivity extends AppCompatActivity {
    private Button editLocationBtn;
    private TextView displayNameTextView;
    private TextView displayDescriptionTextView;
    private ImageView displayImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_location_details);

        editLocationBtn = (Button) findViewById(R.id.editBtn);
        displayDescriptionTextView = (TextView) findViewById(R.id.displayDescriptionTextView);
        displayNameTextView = (TextView) findViewById(R.id.displayNameTextView);

        Intent received = getIntent();
        Bundle extras = received.getExtras();
        String name = extras.getString(EnterDetailsActivity.NAME_KEY);
        String description = extras.getString(EnterDetailsActivity.DESCRIPTION_KEY);

        displayDescriptionTextView.setText(description);
        displayNameTextView.setText(name);

        editLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enterDetailsIntent = new Intent(getApplicationContext(), EnterDetailsActivity.class);
                enterDetailsIntent.putExtra(EnterDetailsActivity.IS_CREATE_NEW_KEY, false);
                startActivity(enterDetailsIntent);
            }
        });

    }
}
