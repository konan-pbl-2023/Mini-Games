package com.example.prototypeapi22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class speed_result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_result);

        Button numberButton = (findViewById(R.id.speed_return_button));
        numberButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent numberGame = new Intent(speed_result.this,MainActivity.class);
                startActivity(numberGame);
            }
        });

        Button numberButton2 = (findViewById(R.id.speed_replay_button));
        numberButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent numberGame2 = new Intent(speed_result.this,speed_button.class);
                startActivity(numberGame2);
            }
        });

    }
}
