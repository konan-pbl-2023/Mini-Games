package com.example.prototypeapi22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class speed_button extends AppCompatActivity {
    int ansIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_button);

        int array[]=new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        Random random = new Random();
        int index, index2, temp;
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(16);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
            //Log.v(array[i]);
        }
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(16);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
            //Log.v(array[i]);
        }
        for(int i=0;i< array.length;i++){
            System.out.println(array[i]);
        }

        int ans_correct[]=new int[16];
        for(int i=0;i<ans_correct.length;i++){
            for(int j=0;j< array.length;j++){
                if(array[j]==i+1){
                    ans_correct[j]=i;
                    break;
                }
            }
        }


        for(int i=0;i<ans_correct.length;i++){
            System.out.println(ans_correct[i]);
        }

        Button n_1 = findViewById(R.id.num_1);
        n_1.setText(String.valueOf(array[0]));
        Button n_2 = findViewById(R.id.num_2);
        n_2.setText(String.valueOf(array[1]));
        Button n_3 = findViewById(R.id.num_3);
        n_3.setText(String.valueOf(array[2]));
        Button n_4 = findViewById(R.id.num_4);
        n_4.setText(String.valueOf(array[3]));
        Button n_5 = findViewById(R.id.num_5);
        n_5.setText(String.valueOf(array[4]));
        Button n_6 = findViewById(R.id.num_6);
        n_6.setText(String.valueOf(array[5]));
        Button n_7 = findViewById(R.id.num_7);
        n_7.setText(String.valueOf(array[6]));
        Button n_8 = findViewById(R.id.num_8);
        n_8.setText(String.valueOf(array[7]));
        Button n_9 = findViewById(R.id.num_9);
        n_9.setText(String.valueOf(array[8]));
        Button n_10 = findViewById(R.id.num_10);
        n_10.setText(String.valueOf(array[9]));
        Button n_11 = findViewById(R.id.num_11);
        n_11.setText(String.valueOf(array[10]));
        Button n_12 = findViewById(R.id.num_12);
        n_12.setText(String.valueOf(array[11]));
        Button n_13 = findViewById(R.id.num_13);
        n_13.setText(String.valueOf(array[12]));
        Button n_14 = findViewById(R.id.num_14);
        n_14.setText(String.valueOf(array[13]));
        Button n_15 = findViewById(R.id.num_15);
        n_15.setText(String.valueOf(array[14]));
        Button n_16 = findViewById(R.id.num_16);
        n_16.setText(String.valueOf(array[15]));

        n_1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[0]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_1.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[1]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_2.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[2]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_3.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[3]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_4.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[4]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_5.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[5]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_6.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[6]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_7.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[7]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_8.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[8]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_9.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_10.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[9]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_10.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_11.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx==ans_correct[10]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_11.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_12.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[11]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_12.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_13.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[12]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_13.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_14.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[13]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_14.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_15.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[14]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_15.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });

        n_16.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ansIdx == ans_correct[15]) {
                    if(ansIdx==15) {
                        Intent atari = new Intent(speed_button.this,speed_clear.class);
                        startActivity(atari);
                    }
                    ansIdx++;
                    n_16.setVisibility(View.INVISIBLE);
                }else {
                    Intent hazure = new Intent(speed_button.this,speed_result.class);
                    startActivity(hazure);
                }
            }
        });



        Button numberButton = (findViewById(R.id.quit));
        numberButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent numberGame = new Intent(speed_button.this,MainActivity.class);
                startActivity(numberGame);
            }
        });

    }
}

//public abstract class speed_play extends AppCompatActivity implements Runnable {
//    final Handler handler = new Handler();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_speed_play);
//
//        // 2秒後に処理が呼ばれるようにメッセージキューに渡す
//        handler.postDelayed(this, 2000);
//    }
//
//    @Override
//    public void run() {
//        // 2秒後に行う処理をここに記述
//        TextView textView = findViewById(R.id.textView);
//        textView.setText("3");
//    }

//}