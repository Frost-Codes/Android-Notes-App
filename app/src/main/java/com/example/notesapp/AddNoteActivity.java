package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //Find by ID
        EditText tittleInput = findViewById(R.id.title_input);
        EditText descriptionInput = findViewById(R.id.description_input);
        Button saveBtn = findViewById(R.id.save_btn);

        //Initialize realm database
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = tittleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                Long createdTime = System.currentTimeMillis();

                if(title.length()>0 && description.length()>0){

                    //Database code
                    realm.beginTransaction();
                    Notes notes = realm.createObject(Notes.class);
                    notes.setTitle(title);
                    notes.setDescription(description);
                    notes.setCreatedTime(createdTime);
                    realm.commitTransaction();

                    Toast.makeText(getApplicationContext(),"Note saved",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Title or description should not be empty",
                            Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
}