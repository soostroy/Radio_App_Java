package com.example.onlineradio;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class hungary_page extends AppCompatActivity {

    public Button hungary;
    public Button jordan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hungary_pages);
        hungary = findViewById(R.id.hungary);
        jordan = findViewById(R.id.jordan);


        hungary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hungary_page.this,MainActivity.class);
                startActivity(intent);
            }
        });
        jordan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hungary_page.this,jordan_page.class);
                startActivity(intent);
            }
        });

    }

}
