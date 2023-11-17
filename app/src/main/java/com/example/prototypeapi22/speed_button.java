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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_button);
//        int array[]=new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
//        Random random = new Random();
//        int index, index2, temp;
//        for (int i = array.length - 1; i > 0; i--)
//        {
//            index = random.nextInt(16);
//            temp = array[index];
//            array[index] = array[i];
//            array[i] = temp;
//            //Log.v(array[i]);
//        }
//        for(int i=0;i<16;i++){
//        System.out.println(array[i]);
//        }
//
//        int ans_correct[]=new int[16];
//        for(int i=1;i<17;i++){
//            for(int j=0;j<16;j++){
//                if(array[j]==i){
//                    ans_correct[i]=j;
//                    break;
//                }
//            }
//        }
//        for(int i=0;i<16;i++){
//            System.out.println(ans_correct[i]);
//        }

//        setContentView(R.layout.activity_speed_play);
//        Button n_1 = (findViewById(R.id.num_1));
//        n_1.setText(array[0]);
//        Button n_2 = (findViewById(R.id.num_2));
//        n_2.setText(array[1]);
//        Button n_3 = (findViewById(R.id.num_3));
//        n_3.setText(array[2]);
//        Button n_4 = (findViewById(R.id.num_4));
//        n_4.setText(array[3]);
//        Button n_5 = (findViewById(R.id.num_5));
//        n_5.setText(array[4]);
//        Button n_6 = (findViewById(R.id.num_6));
//        n_6.setText(array[5]);
//        Button n_7 = (findViewById(R.id.num_7));
//        n_7.setText(array[6]);
//        Button n_8 = (findViewById(R.id.num_8));
//        n_8.setText(array[7]);
//        Button n_9 = (findViewById(R.id.num_9));
//        n_9.setText(array[8]);
//        Button n_10 = (findViewById(R.id.num_10));
//        n_10.setText(array[9]);
//        Button n_11 = (findViewById(R.id.num_11));
//        n_11.setText(array[10]);
//        Button n_12 = (findViewById(R.id.num_12));
//        n_12.setText(array[11]);
//        Button n_13 = (findViewById(R.id.num_13));
//        n_13.setText(array[12]);
//        Button n_14 = (findViewById(R.id.num_14));
//        n_14.setText(array[13]);
//        Button n_15 = (findViewById(R.id.num_15));
//        n_15.setText(array[14]);
//        Button n_16 = (findViewById(R.id.num_16));
//        n_16.setText(array[15]);

//        public void onClick(View v){
//
//        }

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