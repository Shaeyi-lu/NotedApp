package com.example.notedd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    Button save;
    EditText title, subtitle, note;
    DBHelper DB;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.back, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.back){
            Intent i = new Intent(NewActivity.this,MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "Note discarded", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        note = findViewById(R.id.note);


        save = findViewById(R.id.button);

        DB = new DBHelper(this);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleTXT = title.getText().toString();
                String subtitleTXT = subtitle.getText().toString();
                String noteTXT  = note.getText().toString();

                if(!TextUtils.isEmpty(titleTXT) && !TextUtils.isEmpty(noteTXT)){
                    DBHelper DB = new DBHelper(NewActivity.this);
                    DB.addData(titleTXT, subtitleTXT, noteTXT);

                    Intent intent = new Intent(NewActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(NewActivity.this, "Both Fields Required", Toast.LENGTH_SHORT).show();
                }


            }
        });





    }
}