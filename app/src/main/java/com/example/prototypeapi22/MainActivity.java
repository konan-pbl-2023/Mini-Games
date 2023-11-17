package com.example.prototypeapi22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 //スネークゲームへの画面遷移ボタン
        ImageButton snakeButton = (findViewById(R.id.SnakeButton));
        snakeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent snakeGame = new Intent(MainActivity.this,snake_title.class);
                startActivity(snakeGame);
            }
        });

//数字ゲームへの画面遷移ボタン
        ImageButton numberButton = (findViewById(R.id.NumberButton));
        numberButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent numberGame = new Intent(MainActivity.this,speed_button.class);
                startActivity(numberGame);
            }
        });

//刹那ゲームへの画面遷移ボタン
        ImageButton setunaButton = (findViewById(R.id.SetunaButton));
        setunaButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent setunaGame = new Intent(MainActivity.this,setuna_title.class);
                startActivity(setunaGame);
            }
        });
    }
}