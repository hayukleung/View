package com.hayukleung.view.CircleView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import android.util.AttributeSet;

import com.hayukleung.view.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * View
 * com.hayukleung.view.CircleView
 * CircleView.java
 * <p>
 * by hayukleung
 * at 2017-06-27 11:41
 */

public class CircleView extends BaseView {

    private static final int COUNT = 10;
    private List<Circle> mCircleList;
    private Paint mPaint;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mCircleList = new ArrayList<>(COUNT);
        Circle circle;

        circle = new Circle();
        circle.setName("1");
        circle.setSize(10);
        mCircleList.add(circle);

        circle = new Circle();
        circle.setName("2");
        circle.setSize(100);
        mCircleList.add(circle);

        circle = new Circle();
        circle.setName("3");
        circle.setSize(20);
        mCircleList.add(circle);

        circle = new Circle();
        circle.setName("4");
        circle.setSize(90);
        mCircleList.add(circle);

        circle = new Circle();
        circle.setName("5");
        circle.setSize(30);
        mCircleList.add(circle);

        circle = new Circle();
        circle.setName("6");
        circle.setSize(80);
        mCircleList.add(circle);

        circle = new Circle();
        circle.setName("7");
        circle.setSize(40);
        mCircleList.add(circle);

        circle = new Circle();
        circle.setName("8");
        circle.setSize(70);
        mCircleList.add(circle);

        circle = new Circle();
        circle.setName("9");
        circle.setSize(50);
        mCircleList.add(circle);

        circle = new Circle();
        circle.setName("10");
        circle.setSize(60);
        mCircleList.add(circle);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int size = wSize < hSize ? wSize : hSize;
        int sizeMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        setMeasuredDimension(sizeMeasureSpec, sizeMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        int radius = getWidth() / 2;

        mPaint.setColor(Color.parseColor("#2200FF00"));
        canvas.drawCircle(cx, cy, radius, mPaint);

        mPaint.setColor(Color.parseColor("#6600FF00"));
        for (int i = 0; i < mCircleList.size(); i++) {
            Circle circle = mCircleList.get(i);
            int r0 = radius / 5 + radius / 120 * circle.getSize();
            int r1 = radius / 360 * circle.getSize();
            double alpha = 2 * Math.PI / mCircleList.size() * i;
            int cx0 = (int) (cx + Math.sin(alpha) * r0);
            int cy0 = (int) (cy - Math.cos(alpha) * r0);
            canvas.drawCircle(cx0, cy0, r1, mPaint);
        }
    }

    public static class Circle implements Comparable<Circle> {

        private String name;
        private int size;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public int compareTo(@NonNull Circle o) {
            return this.size - o.size;
        }
    }
}
