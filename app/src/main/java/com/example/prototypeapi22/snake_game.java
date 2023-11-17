package com.example.prototypeapi22;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

// 参考: https://blog.recruit.co.jp/rmp/mobile/remember_canvas1/
// https://ethanfjp.com/articles/blog/programing/20211219android#:~:text=Android%E3%81%AE%E7%94%BB%E9%9D%A2%E3%82%B5%E3%82%A4%E3%82%BA%E3%82%92%E5%8F%96%E5%BE%97%E3%81%99%E3%82%8B%E6%96%B9%E6%B3%95%20API29%20%28Android10%29%E3%81%BE%E3%81%A7%E3%81%AE%E6%96%B9%E6%B3%95%20android.graphics.Point%20%E3%82%92%E4%BD%BF%E3%81%84%E3%81%BE%E3%81%99%E3%80%82%20%E2%80%BB%E4%BB%A5%E4%B8%8B%E3%81%AFActivity%E3%81%AE%E4%B8%AD%E3%81%A7%E3%81%AE%E8%A8%98%E8%BF%B0%E3%81%AA%E3%81%AE%E3%81%A7%20this%20%E3%82%92%E4%BD%BF%E7%94%A8%E3%81%97%E3%81%A6%E3%81%84%E3%81%BE%E3%81%99%E3%80%82,point.x%3B%20int%20height%20%3D%20point.y%3B%20%E9%96%A2%E6%95%B0%E3%81%AB%E3%81%99%E3%82%8B%E3%82%88%E3%81%86%E3%81%AA%E5%A0%B4%E5%90%88%E3%81%AF%E5%BC%95%E6%95%B0%E3%81%A8%E3%81%97%E3%81%A6%20Activity%20%E3%82%92%E6%B8%A1%E3%81%97%E3%81%A6%E3%81%8F%E3%81%A0%E3%81%95%E3%81%84%E3%80%82
// http://blog.oukasoft.com/%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%A0/%E3%80%90android%E3%80%91surfaceview%E3%82%92%E4%BD%BF%E3%81%A3%E3%81%A6%E3%82%B2%E3%83%BC%E3%83%A0%E3%81%A3%E3%81%BD%E3%81%84%E3%82%A2%E3%83%97%E3%83%AA%E3%82%92%E4%BD%9C%E3%81%A3%E3%81%A6%E3%81%BF/
// https://tatoapps.wordpress.com/2013/08/17/surfaceview%E3%81%A8canvas%E3%81%A7%E7%94%BB%E9%9D%A2%E3%82%B5%E3%82%A4%E3%82%BA%E3%81%AB%E5%90%88%E3%82%8F%E3%81%9B%E3%81%A6%E6%8F%8F%E7%94%BB%E3%81%99%E3%82%8B%E6%96%B9%E6%B3%95-android/
public class snake_game extends AppCompatActivity {
    SnakeGameSurfaceView gameView = null;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        gameView = new SnakeGameSurfaceView(this);
        setContentView(gameView);
    }
    class SnakeGameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
        private SurfaceHolder holder = null;
        private Thread thread = null;
        private boolean isAttacked = true;

        private Bitmap background_image = BitmapFactory.decodeResource(getResources(), R.drawable.snake_back);

        private float screenWidth = 0;
        private float screenHeight = 0;

        private float scale = 1;
        private float touchedX = 0;
        private float touchedY = 0;

        private SnakeDiretion snakeDirection = SnakeDiretion.Right;

        private final float CANVAS_WIDTH = 900;
        private final float CANVAS_HEIGHT = 900;

        private final long fps = 30;
        private final long move_fps = 10;
        private final long WAIT_TIME = 1000/fps;
        private int move_timing_count = 0;
        private final int move_timing_count_limit = (int)move_fps;

        private final int snakeGameRows = 20;
        private SnakeGameController snakeGame = null;

        public SnakeGameSurfaceView(Context context) {
            super(context);
            SurfaceHolder holder = getHolder();
            holder.addCallback(this);
        }

        @Override
        public void surfaceCreated(@NonNull SurfaceHolder holder) {
            final float width = getWidth();
            final float height = getHeight();
            screenWidth = width;
            screenHeight = height;

            Bitmap snake_head_image = BitmapFactory.decodeResource(getResources(), R.drawable.snake_head);
            Bitmap snake_body_image = BitmapFactory.decodeResource(getResources(), R.drawable.snake_body);
            Bitmap snake_bend_body_image = BitmapFactory.decodeResource(getResources(), R.drawable.snake_bend_body);
            Bitmap snake_tail_image = BitmapFactory.decodeResource(getResources(), R.drawable.snake_tail);
            Bitmap feed_image = BitmapFactory.decodeResource(getResources(), R.drawable.snake_feed);

            snakeGame = new SnakeGameController(
                    snakeGameRows, (int)CANVAS_WIDTH,
                    background_image,
                    snake_head_image,
                    snake_body_image,
                    snake_bend_body_image,
                    snake_tail_image,
                    feed_image
            );
            this.holder = holder;
            thread = new Thread(this);
            thread.start();
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
            isAttacked = false;
            thread = null;
        }

        private void main_loop(Canvas canvas, boolean is_moving) {
            canvas.drawColor(Color.BLACK);
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawBitmap(background_image, 0, 0, new Paint());
            canvas.drawRect(new RectF(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT), paint);
            // canvas.drawRect(new RectF(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT), paint);
            snakeGame.draw_snake(canvas);

            if (is_moving && ! snakeGame.move_snake(snakeDirection)) {
                isAttacked = false;
            }
        }

        @Override
        public void run() {
            final float scaleX = screenWidth / CANVAS_WIDTH;
            final float scaleY = screenHeight / CANVAS_HEIGHT;
            final boolean wIsBigger = scaleX > scaleY;
            scale = Math.min(scaleX, scaleY);

            while (isAttacked) {
                final long timerStart = System.currentTimeMillis();

                Canvas canvas = holder.lockCanvas();

                main_loop(canvas, move_timing_count%move_timing_count_limit == 0);

                Paint paint = new Paint();
                paint.setColor(Color.BLUE);

//            canvas.translate(
//                    wIsBigger
//                            ? (screenWidth - CANVAS_WIDTH)*scale/2
//                            : 0,
//                    ! wIsBigger
//                            ? (screenHeight - CANVAS_HEIGHT)*scale/2
//                            : 0
//            );
                canvas.scale(scale, scale);

                holder.unlockCanvasAndPost(canvas);
                final long timerEnd = System.currentTimeMillis();
                if ((timerEnd - timerStart) < WAIT_TIME) {
                    try {
                        Thread.sleep(WAIT_TIME - (timerEnd - timerStart));
                    } catch (InterruptedException e) {}
                }

                if (move_timing_count%move_timing_count_limit == 0) {
                    move_timing_count = 0;
                }
                move_timing_count++;
            }

            Intent snakeResult = new Intent(snake_game.this, snake_result.class);
            startActivity(snakeResult);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            touchedX = event.getX() / scale;
            touchedY = event.getY() / scale;


            if (touchedX < CANVAS_WIDTH/5 && snakeDirection != SnakeDiretion.Right) {
                snakeDirection = SnakeDiretion.Left;
            } else if (touchedX > CANVAS_WIDTH - CANVAS_WIDTH/5 && snakeDirection != SnakeDiretion.Left) {
                snakeDirection = SnakeDiretion.Right;
            } else if (touchedY < CANVAS_HEIGHT/2 && snakeDirection != SnakeDiretion.Down) {
                snakeDirection = SnakeDiretion.Up;
            } else if (
                    touchedY > CANVAS_HEIGHT - CANVAS_HEIGHT/5
                            && snakeDirection != SnakeDiretion.Up
            ) {
                snakeDirection = SnakeDiretion.Down;
            }

            return true;
        }
    }

    enum SnakeDiretion {
        Up,
        Down,
        Right,
        Left,
    }

    class SnakeGameController {
        // 末尾要素をヘビの顔の座標としたヘビの体がある座標のリスト
        private final ArrayList<SnakeBody> snakeBody;
        private final int rows;
        // ヘビの体1マスのサイズ
        private final int size;
        private final int defaultSnakeLen = 3;
        private final SnakeDiretion defaultSnakeDirection = SnakeDiretion.Right;

        private final Bitmap background_image;
        private final Bitmap snake_head_image;
        private final Bitmap snake_body_image;
        private final Bitmap snake_bend_body_image;
        private final Bitmap snake_tail_image;
        private final Bitmap feed_image;

        private final Matrix m_up = new Matrix();
        private final Matrix m_right = new Matrix();
        private final Matrix m_down = new Matrix();
        private final Matrix m_left = new Matrix();

        public SnakeGameController(
                int rows, int screenWidth,
                Bitmap background_image,
                Bitmap snake_head_image,
                Bitmap snake_body_image,
                Bitmap snake_bend_body_image,
                Bitmap snake_tail_image,
                Bitmap feed_image
        ) {
            this.rows = rows;
            this.size = screenWidth/rows;

            this.background_image =
                    Bitmap.createScaledBitmap(background_image, size, size, true);
            this.snake_head_image =
                    Bitmap.createScaledBitmap(snake_head_image, size, size, true);
            this.snake_body_image =
                    Bitmap.createScaledBitmap(snake_body_image, size, size, true);
            this.snake_bend_body_image =
                    Bitmap.createScaledBitmap(snake_bend_body_image, size, size, true);
            this.snake_tail_image =
                    Bitmap.createScaledBitmap(snake_tail_image, size, size, true);
            this.feed_image =
                    Bitmap.createScaledBitmap(feed_image, size, size, true);

            m_up.setRotate(0, size/2, size/2);
            m_right.setRotate(90, size/2, size/2);
            m_down.setRotate(180, size/2, size/2);
            m_left.setRotate(270, size/2, size/2);

            snakeBody = new ArrayList<SnakeBody>();
            for (int i = 0; i <= defaultSnakeLen; i++) {
                snakeBody.add(new SnakeBody(i, rows/2 , defaultSnakeDirection));
            }
        }

        // ヘビを指定方向に動かす。壁や自分に衝突したときはfalseが返される。
        public boolean move_snake(SnakeDiretion d) {
            SnakeBody new_head = null;
            SnakeBody now_head = snakeBody.get(snakeBody.size()-1);
            int now_head_x = now_head.x;
            int now_head_y = now_head.y;
            switch (d) {
                case Up:
                    if (now_head_y <= 0) {
                        return false;
                    }
                    new_head = new SnakeBody(now_head_x, now_head_y - 1, d);
                    break;

                case Down:
                    if (now_head_y >= rows) {
                        return false;
                    }
                    new_head = new SnakeBody(now_head_x, now_head_y + 1, d);
                    break;

                case Left:
                    if (now_head_x <= 0) {
                        return false;
                    }
                    new_head = new SnakeBody(now_head_x - 1, now_head_y, d);
                    break;

                case Right:
                    if (now_head_x >= rows) {
                        return false;
                    }
                    new_head = new SnakeBody(now_head_x + 1, now_head_y, d);
                    break;
            }

            for (final SnakeBody v : snakeBody) {
                if (v.is_clashing(new_head)) {
                    return false;
                }
            }

            snakeBody.remove(0);
            snakeBody.add(new_head);

            return true;
        }

        public void draw_snake(Canvas canvas) {
            Paint paint = new Paint();
            int i = 0;
            for (SnakeBody v : snakeBody) {
                Bitmap b;
                Matrix m = (
                        v.d == SnakeDiretion.Up
                                ? m_up
                                : v.d == SnakeDiretion.Right
                                ? m_right
                                : v.d == SnakeDiretion.Down
                                ? m_down
                                // default:
                                : m_left
                );
                if (i == snakeBody.size()-1) { // 頭
                    b = snake_head_image;
                } else if (i == 0) { // しっぽ
                    b = snake_tail_image;
                } else { // 胴体
                    if (v.d == snakeBody.get(i-1).d) {
                        b = snake_body_image;
                    } else {
                        b = snake_bend_body_image;
                        SnakeDiretion lstD = snakeBody.get(i-1).d;
                        // NOTE: snake_bend_body は、
                        //    up: ┗
                        // right: ┏
                        //  down: ┓
                        //  left: ┛
                        switch (lstD) {
                            case Up:
                                switch (v.d) {
                                    case Right:
                                        m = m_right;
                                        break;
                                    default:
                                        m = m_down;
                                }
                                break;
                            case Down:
                                switch (v.d) {
                                    case Right:
                                        m = m_up;
                                        break;
                                    default:
                                        m = m_left;
                                }
                                break;
                            case Right:
                                switch (v.d) {
                                    case Up:
                                        m = m_left;
                                        break;
                                    default:
                                        m = m_down;
                                }
                                break;
                            default: // Left
                                switch (v.d) {
                                    case Up:
                                        m = m_up;
                                        break;
                                    default:
                                        m = m_right;
                                }
                                break;
                        }
                    }
                }

                b = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);

                canvas.drawBitmap(b, v.x*size, v.y*size, paint);

                i++;
            }
        }
    }

    class SnakeBody {
        public int x;
        public int y;
        public SnakeDiretion d;

        public SnakeBody(int x, int y, SnakeDiretion d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        public boolean is_clashing(SnakeBody other) {
            return other.x == this.x && other.y == this.y;
        }
    }
}

