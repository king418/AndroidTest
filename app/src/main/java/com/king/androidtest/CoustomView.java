package com.king.androidtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by Administrator
 * DATE 2015/5/13.
 */
public class CoustomView extends SurfaceView implements SurfaceHolder.Callback {

    private boolean isReady;
    private boolean isDirty;
    private PointF[] ps;
    private boolean isFirst = true;
    private GIRect[] giRects;

    public CoustomView(Context context) {
        this(context, null);
    }

    public CoustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.getHolder().addCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void initGIRect(){
        giRects = new GIRect[5];
        GIRect rect1 = new GIRect();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isFirst = true;
        ps = new PointF[5];

        isReady = true;
        isDirty = true;
        drawSurface();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void drawSurface() {
        if (!isReady || !isDirty) {
            return;
        }
        Canvas canvas = getHolder().lockCanvas();
        drawData(canvas);
        getHolder().unlockCanvasAndPost(canvas);
    }

    private void drawData(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Random random = new Random();

        if (isFirst) {
            int count = 0;
            while (count < 5) {
                PointF p = new PointF();
                p.x = random.nextInt(500);
                p.y = random.nextInt(1000);
                ps[count] = p;
                count++;
            }
        }
        if (isFirst) {
            for (int i = 0; i < 5; i++) {
                GIRect rect = new GIRect();
                rect.selected = false;
                rect.displayRect = new Rect((int) ps[i].x, (int) ps[i].y, (int) ps[i].x + 80, (int) ps[i].y + 100);
                rect.draw(canvas);
                giRects[i] = rect;
            }
            isFirst = false;
        } else {
            for (int i = 0; i < 5; i++) {
                giRects[i].draw(canvas);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_MOVE) {
            for (int i = 0; i < ps.length; i++) {
                float x = event.getX();
                float y = event.getY();
                if (x - ps[i].x <= 80 && x - ps[i].x >= 0 && y - ps[i].y <= 100 && y - ps[i].y >= 0) {
                    giRects[i].selected = true;
                } else {
                    giRects[i].selected = false;
                }
                drawSurface();
            }
            return true;
        } else if (action == MotionEvent.ACTION_DOWN) {
            return true;
        } else if (action == MotionEvent.ACTION_UP) {
            for (int i = 0; i < ps.length; i++) {
                giRects[i].selected = false;
                drawSurface();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }
}
