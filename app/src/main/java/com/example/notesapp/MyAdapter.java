package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Notes notes;

    public MyAdapter() {

    }

    public Notes getNote(){
        return this.notes;
    }

    //Realm code
    Context context;
    RealmResults<Notes> notesList;

    public MyAdapter(Context context,RealmResults<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        notes = notesList.get(position);
        holder.titleOutput.setText(notes.getTitle());
        holder.descriptionOutput.setText(notes.getDescription());
        String formatTime = DateFormat.getDateTimeInstance().format(notes.createdTime);
        holder.timeOutput.setText(formatTime);



//        //Edit note
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //return edit text field
//                Intent intent = new Intent(context,EditNoteActivity.class);
//                context.startActivity(intent);
//
//            }
//        });

        //Long Press code
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //Pop up menu
                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("DELETE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("DELETE")){
                            //Delete item
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            notes.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context.getApplicationContext(), "Note deleted",Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleOutput;
        TextView descriptionOutput;
        TextView timeOutput;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleOutput = itemView.findViewById(R.id.title_output);
            descriptionOutput = itemView.findViewById(R.id.description_output);
            timeOutput = itemView.findViewById(R.id.time_output);
        }
    }
}
