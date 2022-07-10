package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;


public class EditNoteActivity extends AppCompatActivity {




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

        MyAdapter myAdapter = new MyAdapter();
        Notes notes = myAdapter.getNote();

        tittleInput.setText(notes.getTitle());
        descriptionInput.setText(notes.getDescription());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = tittleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                Long createdTime = System.currentTimeMillis();

                if(title.length()>0 && description.length()>0){

                    //Database code
                    realm.beginTransaction();
                    notes.setTitle(title);
                    notes.setDescription(description);
                    notes.setCreatedTime(createdTime);
                    realm.insertOrUpdate(notes);
                    realm.commitTransaction();



                    Toast.makeText(getApplicationContext(),"Note edited",Toast.LENGTH_SHORT).show();
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