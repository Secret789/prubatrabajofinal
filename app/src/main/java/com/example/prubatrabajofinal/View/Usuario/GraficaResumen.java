package com.example.prubatrabajofinal.View.Usuario;



import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

import com.example.prubatrabajofinal.R;

public class GraficaResumen extends View {
    private static int SELECTION_COUNT = 7;

    private static float FONT_SIZE = 40f;
    private static float START = 50;
    private static float FINISH = 50;
    private float mWidth;
    private float mHeight;
    private float mWidthPadded;
    private float mHeightPadded;
    private Paint mTextPaint;
    private Paint mDialPaint;
    private float mRadius;
    private int mActiveSelection;

    float []weekStats;
    String[]week;

    public GraficaResumen(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public void initArray(float[] weekStats, String[] week){
        this.week=week;
        this.weekStats=weekStats;
    }
    private void init() {
        // Paint styles used for rendering are created here, rather than at render-time. This
        // is a performance optimization, since onDraw() will get called frequently.
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(FONT_SIZE);

        mDialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDialPaint.setColor(getResources().getColor(R.color.backnavbar));

        // Initialize current selection. This will store where the dial's "indicator" is pointing.
        mActiveSelection = 0;

    }

    @Override
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.onPopulateAccessibilityEvent(event);

        // Detect what type of accessibility event is being passed in.
        int eventType = event.getEventType();

        // Common case: The user has interacted with our view in some way. State may or may not
        // have been changed. Read out the current status of the view.
        //
        // We also set some other metadata which is not used by TalkBack, but could be used by
        // other TTS engines.
        if (eventType == AccessibilityEvent.TYPE_VIEW_SELECTED ||
                eventType == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
            event.getText().add("Mode selected: " + Integer.toString(mActiveSelection + 1) + ".");
            event.setItemCount(SELECTION_COUNT);
            event.setCurrentItemIndex(mActiveSelection);
        }

        // When a user first focuses on our view, we'll also read out some simple instructions to
        // make it clear that this is an interactive element.
        if (eventType == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
            event.getText().add("Tap to change.");
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // Account for padding
        float xPadding = (float) (getPaddingLeft() + getPaddingRight());
        float yPadding = (float) (getPaddingTop() + getPaddingBottom());

        // Compute available width/height
        mWidth = w;
        mHeight = h;
        mWidthPadded = w - xPadding;
        mHeightPadded = h - yPadding;
        mRadius = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawRect(0,mHeight,mWidth,0,mDialPaint);
        // Draw text labels

        float []weekStatsInTable=new float[weekStats.length];

        float max=maxStat(weekStats);
        mDialPaint.setColor(Color.parseColor("#000000"));
        for (int i = 0; i < SELECTION_COUNT; i++) {
            float xData = computeXForPosition(i, mWidth);

            canvas.drawText(week[i], xData, mHeight-10, mTextPaint);

            //canvas.drawLine(xData,START,xData,mHeight-FINISH,mDialPaint);
            weekStatsInTable[i]=computeXStat(weekStats[i],max);
            canvas.drawText(Float.toString(weekStats[i]), xData, weekStatsInTable[i]-5, mTextPaint);




            mDialPaint.setColor(getResources().getColor(R.color.colorPrimaryLight));

            canvas.drawRect(xData-40,weekStatsInTable[i],xData+40,mHeight-50,mDialPaint);
            mDialPaint.setColor(Color.parseColor("#000000"));
            if(i>0){
                float xDataPast = computeXForPosition(i-1, mWidth);
                mDialPaint.setStrokeWidth(5);
                mDialPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
                canvas.drawLine(xDataPast,weekStatsInTable[i-1],xData,weekStatsInTable[i],mDialPaint);
                mDialPaint.setStrokeWidth(3);
            }
        }

        canvas.drawLine(50,START,mWidth-50,START,mDialPaint);
        canvas.drawLine(50,mHeight-FINISH,mWidth-50,mHeight-FINISH,mDialPaint);

        canvas.drawLine(50,START,50,mHeight-50,mDialPaint);
        canvas.drawLine(mWidth-50,START,mWidth-50,mHeight-50,mDialPaint);


        canvas.rotate(-90, 36, mHeight/2);
        canvas.drawText("Tiempo", 0, mHeight/2, mTextPaint);

        mDialPaint.setColor(getResources().getColor(R.color.backnavbar));
    }
    private float computeXForPosition(final int pos, final float width) {
        float espacio=width/(SELECTION_COUNT+1);
        return espacio+espacio*pos;
    }
    private float computeXStat(final float stat,float max) {
        float size=mHeight-START-FINISH-50;
        return mHeight-50-(stat*size/max);
    }
    private float maxStat(float[]stats) {
        float max=stats[0];
        for(int i=0;i<SELECTION_COUNT;i++){
            if(stats[i]>max){
                max=stats[i];
            }
        }
        return max;
    }
}