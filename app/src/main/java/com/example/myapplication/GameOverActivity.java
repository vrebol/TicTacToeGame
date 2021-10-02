package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Intent i = getIntent();
        String winner = i.getStringExtra(MainActivity.z);
        String message;
        if(winner.equals("d")){
            message = "It was a draw!";
        }else{
            message = winner + " has won!";
        }
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }

    public void onClick(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}