package com.example.tutoria1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView myIV = (ImageView) findViewById(R.id.imageView);

        //Picasso.get().load(("http://books.google.com/books/content?id=6izpDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api").replace("http://","https://")).into(myIV);
        //https://i.imgur.com/DvpvklR.png
        //http://books.google.com/books/content?id=6izpDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api

        configButton();
    }
    private void configButton()
    {
        Intent myIntent = new Intent(MainActivity.this, activity_search.class);
        ImageButton button_search = (ImageButton) findViewById(R.id.button_open_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(myIntent);
            }
        });

    }
}