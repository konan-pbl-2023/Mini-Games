package com.example.prototypeapi22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class setuna_game_single extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setuna_game_single);


        ImageView reactionMark = findViewById(R.id.reactionMark);//リアクションマークの取得
        reactionMark.setVisibility(View.GONE);

        //インターバル10msec
        long interval = 10;
        Random randomTimer = new Random();//乱数
        //時間作成
        long visibleReactiomTime = 5000 + randomTimer.nextInt(8000);
        final CountDown visibleCountDown = new CountDown(visibleReactiomTime, interval);


        ImageView a_player = findViewById(R.id.A_oneplayer);
        ImageView b_player = findViewById(R.id.player2);
        a_player.setImageResource(R.drawable.me);
        b_player.setImageResource(R.drawable.enemy);
        a_player.setVisibility(View.VISIBLE);
        b_player.setVisibility(View.VISIBLE);


        TextView A_text = (findViewById(R.id.A_text));
        TextView B_text = (findViewById(R.id.B_text));
        A_text.setTextSize(25);
        B_text.setTextSize(25);

        Button goHome = (findViewById(R.id.gohome));
        Button ready_Button = (findViewById(R.id.readyButton));

        goHome.setVisibility(View.GONE);
        goHome.setText("ホームに戻る");

        ready_Button.setText("READY");
        ready_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(ready == false) {
                    ready_Button.setText("押せ！");
                    visibleCountDown.start();
                    ready = true;
                }else {


                    if (inGame == true) {
                        if (reactionRun == true) {
                            a_reactionStartTime = System.currentTimeMillis();
                            double reactionTime = a_reactionStartTime - visibleTime;
                            A_text.setText("勝ち！" + reactionTime / 1000 + "sec");
                            B_text.setText("負け！");
                            b_player.setImageResource(R.drawable.enemyout);
                            inGame = false;
                            visibleCountDown.cancel();
                            goHome();
                        } else {
                            a_reactionStartTime = System.currentTimeMillis();
                            A_text.setText("お手付き！負け！");
                            a_player.setImageResource(R.drawable.meout);
                            B_text.setText("勝ち！");
                            inGame = false;
                            visibleCountDown.cancel();
                            goHome();
                        }
                    }
                }
            }
        });

    }


    class CountDown extends CountDownTimer {
        CountDown(long millInFuture, long countDownInterval) {
            super(millInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            visibleReaction();
        }
    }




    class EnemyCountDown extends CountDownTimer{

        public EnemyCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            if (inGame == true) {
                TextView A_text = (findViewById(R.id.A_text));
                TextView B_text = (findViewById(R.id.B_text));
                b_reactionStartTime = System.currentTimeMillis();
                double reactionTime = b_reactionStartTime - visibleTime;
                A_text.setText("負け！");
                B_text.setText("勝ち！" + reactionTime / 1000 + "sec");
                ImageView a_player = findViewById(R.id.A_oneplayer);
                a_player.setImageResource(R.drawable.meout);
                inGame = false;
                enemyReactionTimer.cancel();
                goHome();
            }

        }
    }




    //変数宣言
    boolean reactionRun = false;//ビックリマーク表示
    boolean inGame = true;
    long a_reactionStartTime;
    long b_reactionStartTime;
    boolean ready = false;

    long visibleTime;

    Random randomTimer = new Random();//乱数
    long enemyReactionTime = 220 + randomTimer.nextInt(400);
    final EnemyCountDown enemyReactionTimer = new EnemyCountDown(enemyReactionTime,10);


    private void visibleReaction() {
        //ビックリマーク表示処理
        ImageView reactionMark = findViewById(R.id.reactionMark);//リアクションマークの取得
        reactionMark.setVisibility(View.VISIBLE);
        visibleTime = System.currentTimeMillis();
        reactionRun = true;


        enemyReactionTimer.start();

    }

    private void goHome() {
        Button goHomeButton = (findViewById(R.id.gohome));
        goHomeButton.setVisibility(View.VISIBLE);
        //ready_Button.setText("ホームに戻る");
        goHomeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(setuna_game_single.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }
}