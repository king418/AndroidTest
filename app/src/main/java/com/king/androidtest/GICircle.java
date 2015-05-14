package com.king.androidtest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;

/**
 * Created by King
 * DATE 2015/5/13.
 */
public class GICircle extends GraphicItemBase {

    public Shader fillStyle;
    public int fillColor = 0x00FFFFFF;
    public int normalColor = 0xFF00FF00;
    public boolean showShadow;


    @Override
    public void drawSelected(Canvas canvas) {
        Paint paint = new Paint();
        if (fillStyle != null) {
            paint.setShader(fillStyle);
        } else {
            paint.setColor(fillColor);
        }
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        if (showShadow) {
            paint.setShadowLayer(3, 3, 3, Color.GRAY);
        }
        canvas.drawCircle(displayRect.centerX(), displayRect.centerY(), displayRect.width(), paint);
    }

    @Override
    public void drawNormal(Canvas canvas) {
        Paint paint = new Paint();
        if (fillStyle != null) {
            paint.setShader(fillStyle);
        }else {
            paint.setColor(normalColor);
        }
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(displayRect.centerX(),displayRect.centerY(),displayRect.width()/2,paint);
    }
}
