package com.hayukleung.view.ProgressBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.hayukleung.view.BaseView;
import com.hayukleung.view.R;

/**
 * 条形进度条
 */
public class ProgressBar extends BaseView {

    private Paint mPaint;
    private RectF mRectF;

    private int mMax;
    private int mProgress;
    private int mBackgroundColor;
    private int mProgressColor;

    public ProgressBar(Context context) {
        super(context);
    }

    public ProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        postInvalidate();
        mMax = max;
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        postInvalidate();
        mProgress = progress;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
    }

    public void setProgressColor(int progressColor) {
        mProgressColor = progressColor;
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProgressBar);
        mMax = a.getInt(R.styleable.ProgressBar_max, 100);
        mProgress = a.getInt(R.styleable.ProgressBar_progress, 0);
        setBackgroundColor(a.getColor(R.styleable.ProgressBar_backgroundColor, 0xFF000000));
        setProgressColor(a.getColor(R.styleable.ProgressBar_progressColor, 0xFFFFFFFF));
        a.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(mBackgroundColor);
        mRectF.set(0, 0, getWidth(), getHeight());
        canvas.drawRect(mRectF, mPaint);
        mPaint.setColor(mProgressColor);
        mRectF.set(0, 0, getWidth() / mMax * mProgress, getHeight());
        canvas.drawRect(mRectF, mPaint);
    }
}
