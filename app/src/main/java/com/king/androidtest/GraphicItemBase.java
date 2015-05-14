package com.king.androidtest;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by Administrator
 * DATE 2015/5/13.
 */
public class GraphicItemBase {
    public boolean enable;//可点击
    public boolean selected;//被选中
    public RectF hotRect = new RectF();//热区
    public RectF paintRect = new RectF();//绘制区域
    public Rect displayRect = new Rect();//显示区域

    public final void draw(Canvas canvas){
        canvas.translate(displayRect.left,displayRect.top);
        if (selected){
            drawSelected(canvas);
        }else {
            drawNormal(canvas);
        }
    }

    public void drawSelected(Canvas canvas){

    }

    public void drawNormal(Canvas canvas){

    }

    public boolean onClick(int x,int y){
        return false;
    }


}
