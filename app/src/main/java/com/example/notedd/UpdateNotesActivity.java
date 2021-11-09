 package com.example.notedd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

 public class UpdateNotesActivity extends AppCompatActivity {

    EditText title, subtitle, note;
    Button updateNotes;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notes);

        title=findViewById(R.id.title);
        subtitle=findViewById(R.id.subtitle);
        updateNotes=findViewById(R.id.update );

        Intent i = getIntent();
        title.setText(i.getStringExtra("title"));
        note.setText(i.getStringExtra("note"));
        subtitle.setText(i.getStringExtra("subtitle"));
        id=i.getStringExtra("id");

        updateNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(note.getText().toString())){
                    DBHelper db = new DBHelper(UpdateNotesActivity.this);
                    db.updateNotes(title.getText().toString(), note.getText().toString(), subtitle.getText().toString() ,id);

                    Intent i = new Intent(UpdateNotesActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(UpdateNotesActivity.this, "Both Fields Required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}