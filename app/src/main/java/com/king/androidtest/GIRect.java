package com.king.androidtest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Shader;

/**
 * Created by Administrator
 * DATE 2015/5/13.
 */
public class GIRect extends GraphicItemBase {
    public boolean isRoundConner;
    public PointF connerSize = new PointF(0.1f, 0.1f);

    public Shader fillStyle;
    public float borderSize = 1;
    public boolean showShadow;
    public int borderColor = 0xFF000000;
    public int fillColor = 0xFF00FF00;
    public int normalColor = 0xFFFF0000;

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
        if (isRoundConner) {
            int x = (int) (connerSize.x * displayRect.width());
            int y = (int) (connerSize.y * displayRect.height());
            canvas.drawRoundRect(new RectF(displayRect), x, y, paint);
        } else {
            canvas.drawRect(displayRect, paint);
        }
        //绘制边框
        paint.clearShadowLayer();
        paint.setShader(null);
        paint.setStrokeWidth(borderSize);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(borderColor);
        if (isRoundConner) {
            int x = (int) (connerSize.x * displayRect.width());
            int y = (int) (connerSize.y * displayRect.height());
            canvas.drawRoundRect(new RectF(displayRect), x, y, paint);
        } else {
            canvas.drawRect(displayRect, paint);
        }
    }

    @Override
    public void drawNormal(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(normalColor);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        if (showShadow) {
            paint.setShadowLayer(3, 3, 3, Color.GRAY);
        }
        if (isRoundConner) {
            int x = (int) (connerSize.x * displayRect.width());
            int y = (int) (connerSize.y * displayRect.height());
            canvas.drawRoundRect(new RectF(displayRect), x, y, paint);
        } else {
            canvas.drawRect(displayRect, paint);
        }
        //绘制边框
        paint.clearShadowLayer();
        paint.setShader(null);
        paint.setStrokeWidth(borderSize);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(borderColor);
        if (isRoundConner) {
            int x = (int) (connerSize.x * displayRect.width());
            int y = (int) (connerSize.y * displayRect.height());
            canvas.drawRoundRect(new RectF(displayRect), x, y, paint);
        } else {
            canvas.drawRect(displayRect, paint);
        }
    }
}
