package com.example.gesturedetection2.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import androidx.constraintlayout.solver.widgets.Rectangle;

import com.example.gesturedetection2.R;

import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class ColorChangingView extends View implements GestureDetector.OnGestureListener {
    private int color;

    private GestureDetector gd;

    private Paint circlePaint;
    private Paint rect1;
    private Paint rect2;
    private Paint rect3;
    private Paint rect4;




    public ColorChangingView(Context context) {
        super(context);
    }

    public ColorChangingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gd.onTouchEvent(event);
                return true;
            }
        });

        gd = new GestureDetector(getContext(), this);

        circlePaint = new Paint();
        circlePaint.setColor(Color.WHITE);
        rect1 = new Paint();
        rect1.setColor(Color.RED);
        rect2 = new Paint();
        rect2.setColor(Color.BLUE);
        rect3 = new Paint();
        rect3.setColor(Color.YELLOW);
        rect4 = new Paint();
        rect4.setColor(Color.GREEN);

    }

    public ColorChangingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,getWidth()/2,getHeight()/2, rect1);
        canvas.drawRect(getWidth(),0,getWidth()/2,getHeight()/2, rect2);
        canvas.drawRect(0,getHeight(),getWidth()/2,getHeight()/2, rect3);
        canvas.drawRect(getWidth(),getHeight(),getWidth()/2,getHeight()/2, rect4);

        canvas.drawCircle(getWidth()/2,getHeight()/2,250,circlePaint);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        float xd = e.getX();
        float yd = e.getY();

        float x = getWidth();
        float y = getHeight();

        if(xd < x/2 && yd < y/2) {
            System.out.println("Top left");
            circlePaint.setColor(Color.RED);
        }

        if(xd > x/2 && yd < y/2) {
            System.out.println("Top right");
            circlePaint.setColor(Color.BLUE);
        }

        if(xd > x/2 && yd > y/2) {
            System.out.println("Bottom right");
            circlePaint.setColor(Color.GREEN);
        }

        if(xd < x/2 && yd > y/2) {
            System.out.println("Bottom left");
            circlePaint.setColor(Color.YELLOW);
        }
        this.invalidate();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        //setBackgroundColor(getNewColor());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //setBackgroundColor(getNewColor());
        return true;
    }

}

