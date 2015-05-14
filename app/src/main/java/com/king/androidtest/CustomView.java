package com.king.androidtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by King
 * DATE 2015/5/14.
 */
public class CustomView extends SurfaceView implements SurfaceHolder.Callback {

    private Point[] points;//保存画布的宽高
    private GIRect giRect;
    private Rect[] rects;
    private boolean[] selecteds;
    private SurfaceHolder holder;

    private int screenWidth;
    private int screenHeight;

    private Canvas canvas;

    public CustomView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public void drawRect(Canvas canvas, Rect rect, boolean selected) {
        giRect = new GIRect();
        giRect.displayRect = rect;
        if (selected) {
            giRect.drawSelected(canvas);
        } else {
            giRect.drawNormal(canvas);
        }
    }

    public void drawRoundRect(Canvas canvas, Rect rect, boolean selected) {
        giRect = new GIRect();
        giRect.displayRect = rect;
        giRect.isRoundConner = true;
        if (selected) {
            giRect.drawSelected(canvas);
        } else {
            giRect.drawNormal(canvas);
        }
    }

    public void drawOvel(Canvas canvas, Rect rect, boolean selected) {
        giRect = new GIRect();
        giRect.displayRect = rect;
        giRect.connerSize = new PointF(0.5f, 0.5f);
        giRect.isRoundConner = true;
        if (selected) {
            giRect.drawSelected(canvas);
        } else {
            giRect.drawNormal(canvas);
        }
    }

    private void initData() {
        rects = new Rect[5];

        selecteds = new boolean[5];

        screenHeight = getHeight();
        screenWidth = getWidth();

        points = new Point[5];
        points[0] = new Point(100, 100);//圆
        points[1] = new Point(150, 250);//矩形
        points[2] = new Point(200, 200);//正方形
        points[3] = new Point(150, 200);//椭圆
        points[4] = new Point(200, 300);//圆角矩形

        for (int i = 0; i < 5; i++) {
            selecteds[i] = false;
        }

    }

    private void drawData(SurfaceHolder holder) {
        canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        int rectLeft = 0;//displayRect左
        int rectTop = 0;//displayRect上
        int rectRight = 0;//displayRect右
        int rectBottom = 0;//displayRect下

        Random random = new Random();
        int i = 0;
        while (i < 5) {
            rectLeft = random.nextInt(screenWidth - 200);
            rectTop = random.nextInt(screenHeight - 200);
            int width = points[i].x;
            int height = points[i].y;
            int radius = getSqrt(width / 2, height / 2);//最长半径
            rectRight = rectLeft + width;
            rectBottom = rectTop + height;
            Rect rect = new Rect(rectLeft, rectTop, rectRight, rectBottom);
            int x = rectLeft + width / 2;
            int y = rectTop + height / 2;
            boolean isDraw = false;
            if (i >= 1) {
                for (int j = 0; j < i; j++) {
                    int x1 = x - (rects[j].left + points[j].x / 2);
                    int y1 = y - (rects[j].top + points[j].y / 2);
                    int radius1 = getSqrt(points[j].x, points[j].y);//最长半径
                    int distance = getSqrt(x1, y1);//两个图形中心点的距离
                    Log.i("------->", "distance" + rects[j].left);
                    Log.i("------->", "radius" + rects[j].top);
                    int i1 = radius + radius1;
                    if (distance > i1) {
                        isDraw = true;
                    } else {
                        break;
                    }
                }
                if (isDraw) {
                    rects[i] = rect;
                    i++;
                }
            } else {
                rects[i] = rect;
                i++;
            }
        }
        //Log.i("----->","-------->"+1);
        drawOvel(canvas, rects[0], selecteds[0]);
        drawRect(canvas, rects[1], selecteds[1]);
        drawRect(canvas, rects[2], selecteds[2]);
        drawOvel(canvas, rects[3], selecteds[3]);
        drawRoundRect(canvas, rects[4], selecteds[4]);
        holder.unlockCanvasAndPost(canvas);
    }

    private int getSqrt(int x, int y) {
        int ret = (int) Math.sqrt(x * x + y * y);
        return ret;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        initData();
        drawData(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        drawData(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isSelect(event);
                reDraw();
                break;
            case MotionEvent.ACTION_MOVE:
                isSelect(event);
                reDraw();
                break;
            case MotionEvent.ACTION_UP:

                for (int i = 0; i < rects.length; i++) {
                        selecteds[i] = false;
                }
                reDraw();
                break;
        }
        return true;
    }

    private void isSelect(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int length = rects.length;
        for (int i = 0; i < length; i++) {
            if (rects[i].contains(x, y)) {
                selecteds[i] = true;
            }else {
                selecteds[i] = false;
            }
        }
    }

    private void reDraw() {
        canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        drawOvel(canvas, rects[0], selecteds[0]);
        drawRect(canvas, rects[1], selecteds[1]);
        drawRect(canvas, rects[2], selecteds[2]);
        drawOvel(canvas, rects[3], selecteds[3]);
        drawRoundRect(canvas, rects[4], selecteds[4]);
        holder.unlockCanvasAndPost(canvas);
    }

}
