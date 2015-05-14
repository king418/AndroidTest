package com.king.androidtest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

/**
 * Created by King
 * DATE 2015/5/13.
 */
public class GIOval extends GraphicItemBase {

    public Shader fillStyle;
    public int fillColor = 0x00FFFFFF;
    public int normalColor = 0xFF00FF00;
    public boolean showShadow;

    @Override
    public void drawNormal(Canvas canvas) {
        super.drawNormal(canvas);
    }

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

        canvas.drawOval(displayRect.left, displayRect.top,
                displayRect.right, displayRect.bottom, paint);
    }
}
