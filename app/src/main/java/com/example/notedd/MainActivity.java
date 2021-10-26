package com.example.notedd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNote;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Model> noteList;

    DBHelper dbClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNote = findViewById(R.id.addingBtn);
        recyclerView= findViewById(R.id.recycler_view);

        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                //NewActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });



        noteList= new ArrayList<>();

        dbClass=new DBHelper(this);
        fetchAllNotesFromDatabse();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, MainActivity.this, noteList);
        recyclerView.setAdapter(adapter);
    }

    void fetchAllNotesFromDatabse(){
        Cursor cursor= dbClass.readAllData();

        if(cursor.getCount()==0){
            Toast.makeText(this, "No Data to show", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                noteList.add(new Model(cursor.getString(0),cursor.getString(1), cursor.getString(2)));
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);


                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}