package com.example.gesturedetection.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import com.example.gesturedetection.R;

import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class ColorChangingView extends View implements GestureDetector.OnGestureListener {
    private int color;
    private GestureDetector gd;


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
        color = getNewColor();
        setBackgroundColor(color);
    }

    public ColorChangingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
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
        setBackgroundColor(getNewColor());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        setBackgroundColor(getNewColor());
        return true;
    }


    private int getNewColor() {
        Random r = new Random();
        int val = r.nextInt(5);
        int newColor;

        switch (val) {
            case 0:
                newColor = Color.GREEN;
                break;
            case 1:
                newColor = Color.BLUE;
                break;
            case 2:
                newColor = Color.YELLOW;
                break;
            case 3:
                newColor = Color.RED;
                break;
            case 4:
                newColor = Color.WHITE;
                break;
            default:
                newColor = Color.CYAN;
                break;
        }
        return newColor;
    }

}

