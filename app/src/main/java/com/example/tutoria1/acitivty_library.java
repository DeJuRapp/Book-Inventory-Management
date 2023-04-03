package com.example.tutoria1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
//TODO open this view at the appropriate point
public class acitivty_library extends AppCompatActivity {
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        view = this.getWindow().getDecorView();
    }
}
