package com.example.prototypeapi22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class snake_result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_result);

        Button replay_btn = (findViewById(R.id.snake_replay_button));
        replay_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent snakeTitle = new Intent(snake_result.this, snake_game.class);
                startActivity(snakeTitle);
            }
        });

        Button return_btn = findViewById(R.id.snake_return_button);
        return_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent snakeTitle = new Intent(snake_result.this, MainActivity.class);
                startActivity(snakeTitle);
            }
        });
    }
}
