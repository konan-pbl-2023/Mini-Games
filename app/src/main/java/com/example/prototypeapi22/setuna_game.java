package com.example.prototypeapi22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class setuna_game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setuna_game);

        //リアクションマークの取得
        ImageView reactionMark = findViewById(R.id.reactionMark);
        reactionMark.setVisibility(View.GONE);

        //インターバル10msec
        long interval = 10;
        Random randomTimer = new Random();//乱数
        //時間作成
        long visibleReactiomTime = 5000 + randomTimer.nextInt(8000);
        final CountDown visibleCountDown = new CountDown(visibleReactiomTime,interval);


        /*画像の取得 設定*/
        ImageView a_player = findViewById(R.id.A_player);
        ImageView b_player = findViewById(R.id.B_player);
        a_player.setImageResource(R.drawable.me);
        b_player.setImageResource(R.drawable.enemy);
        a_player.setVisibility(View.VISIBLE);
        b_player.setVisibility(View.VISIBLE);

        /*音関連*/
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(3)
                .build();
        action[0] = soundPool.load(this,R.raw.attack,1);
        action[1] = soundPool.load(this,R.raw.dodon,1);


        /*テキスト取得　設定*/
        TextView A_text = (findViewById(R.id.A_text));
        TextView B_text = (findViewById(R.id.B_text));
        A_text.setTextSize(30);
        B_text.setTextSize(30);


        /*ボタン取得設定*/
        Button A_Button = (findViewById(R.id.A_PashButton));
        Button B_Button = (findViewById(R.id.B_PashButton));
        Button ready_Button = (findViewById(R.id.readyButton));
        A_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(inGame == true) {
                    if (reactionRun == true) {
                        soundPool.play(action[0],1f,1f,0,0,1f);
                        a_reactionStartTime = System.currentTimeMillis();
                        double reactionTime = a_reactionStartTime - visibleTime;
                        A_text.setText("勝ち！" + reactionTime/1000 + "sec");
                        B_text.setText("負け！");
                        b_player.setImageResource(R.drawable.enemyout);
                        inGame = false;
                        reactionRun = false;
                        visibleCountDown.cancel();
                        goHome();
                    } else {
                        soundPool.play(action[0],1f,1f,0,0,1f);
                        a_reactionStartTime = System.currentTimeMillis();
                        A_text.setText("お手付き！負け！");
                        a_player.setImageResource(R.drawable.meout);
                        B_text.setText("勝ち！");
                        inGame = false;
                        reactionRun = false;
                        visibleCountDown.cancel();
                        goHome();
                    }
                }
            }
        });


        B_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(inGame == true) {
                    if (reactionRun == true) {
                        soundPool.play(action[0],1f,1f,0,0,1f);
                        b_reactionStartTime = System.currentTimeMillis();
                        double reactionTime = b_reactionStartTime - visibleTime;
                        A_text.setText("負け！");
                        B_text.setText("勝ち！" + reactionTime / 1000 + "sec");
                        a_player.setImageResource(R.drawable.meout);
                        inGame = false;
                        reactionRun = false;
                        visibleCountDown.cancel();
                        goHome();
                    } else {
                        soundPool.play(action[0],1f,1f,0,0,1f);
                        b_reactionStartTime = System.currentTimeMillis();
                        A_text.setText("勝ち！");
                        B_text.setText("お手付き！負け！");
                        b_player.setImageResource(R.drawable.enemyout);
                        inGame = false;
                        reactionRun = false;
                        visibleCountDown.cancel();
                        goHome();
                    }
                }

            }
        });


        ready_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                soundPool.play(action[1],1f,1f,0,0,1f);
                invisivleReadyButton();
                visibleCountDown.start();
            }
        });

    }


    class CountDown extends  CountDownTimer{
        CountDown(long millInFuture,long countDownInterval){
            super(millInFuture,countDownInterval);
        }

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            visibleReaction();
        }
    }

    //変数宣言
    boolean reactionRun = false;//ビックリマーク表示
    boolean inGame = true;
    long a_reactionStartTime;
    long b_reactionStartTime;

    long visibleTime;
    public SoundPool soundPool;
    public int[] action = {0,0,0,0};



    private void visibleReaction() {
        //ビックリマーク表示処理
        ImageView reactionMark = findViewById(R.id.reactionMark);//リアクションマークの取得
        reactionMark.setVisibility(View.VISIBLE);
        visibleTime = System.currentTimeMillis();
        reactionRun = true;
    }


    private void invisivleReadyButton(){
        //readyボタン透明化
        Button ready_Button = (findViewById(R.id.readyButton));
        ready_Button.setVisibility(View.GONE);
    }

    private void goHome(){
        //ホームに戻るボタン
        Button ready_Button = (findViewById(R.id.readyButton));
        ready_Button.setVisibility(View.VISIBLE);
        ready_Button.setText("ホームに戻る");
        ready_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(setuna_game.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }
}