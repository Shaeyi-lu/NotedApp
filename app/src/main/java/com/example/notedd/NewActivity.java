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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    Button save;
    EditText title, subtitle, note;
    private String chosenNoteColor;
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
        chosenNoteColor = "#333333";

        final LinearLayout layoutChooseColor = findViewById(R.id.layoutChooseColor);
        final ImageView imageColor1 = layoutChooseColor.findViewById(R.id.imageColor1);
        final ImageView imageColor2 = layoutChooseColor.findViewById(R.id.imageColor2);
        final ImageView imageColor3 = layoutChooseColor.findViewById(R.id.imageColor3);
        final ImageView imageColor4 = layoutChooseColor.findViewById(R.id.imageColor4);

        layoutChooseColor.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenNoteColor = "#333333";
                imageColor1.setImageResource(R.drawable.ic_tick);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);

            }
        });

        layoutChooseColor.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenNoteColor = "#FDBE3B";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(R.drawable.ic_tick);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
            }
        });

        layoutChooseColor.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenNoteColor = "#FF4842";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(R.drawable.ic_tick);
                imageColor4.setImageResource(0);
            }
        });

        layoutChooseColor.findViewById(R.id.viewColor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenNoteColor = "##FF03DAC5";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(R.drawable.ic_tick);
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleTXT = title.getText().toString();
                String subtitleTXT = subtitle.getText().toString();
                String noteTXT  = note.getText().toString();

               /* title.setBackgroundColor(Integer.parseInt(chosenNoteColor));
                subtitle.setBackgroundColor(Integer.parseInt(chosenNoteColor));
                note.setBackgroundColor(Integer.parseInt(chosenNoteColor));*/

                if(!TextUtils.isEmpty(titleTXT) && !TextUtils.isEmpty(noteTXT))
                {
                    DBHelper DB = new DBHelper(NewActivity.this);
                    DB.addData(titleTXT, subtitleTXT, noteTXT);


                    Intent intent = new Intent(NewActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(NewActivity.this, "Note title and Note required", Toast.LENGTH_SHORT).show();
                }



            }
        });





    }
}