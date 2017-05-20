package rtrk.pnrs1.ra198_2013.taskmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.view.View;

import static rtrk.pnrs1.ra198_2013.taskmanager.R.color.lightblue;

/**
 * Created by Strahinja on 30.4.2017..
 */

public class MyCanvas extends View{
    private static final float RADIUS = 200.0f;
    private Bitmap background;
    private Paint mPaint = null;
    private float mRedRotation = 0.0f;
    private float mYellowRotation = 0.0f;
    private float mGreenRotation = 0.0f;
    private RectF mRectBg;
    private RectF mRectR;
    private RectF mRectY;
    private RectF mRectG;
    private int priority = 0;
    private int procent = 0;
    private int procentToDraw = 0;
    private boolean toDraw = false;

    public MyCanvas(Context context) {
        super(context);
        background = BitmapFactory.decodeResource(getResources(),R.drawable.grey);
        mPaint = new Paint();
        mRectR = new RectF();
        mRectBg = new RectF();
        mRectY = new RectF();
        mRectG = new RectF();
    }

    public void setProcentToDraw(int procent){
        this.procentToDraw = procent;
    }

    public void sedPriority(int priority){
        this.priority = priority;
    }

    public int getProcent(){
        return procent;
    }

    public void drawProcent(){
        this.procent++;

        if(this.procent != procentToDraw){
            toDraw = true;
        }

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //Pozadina
        mRectBg.top = 0;
        mRectBg.bottom = canvas.getHeight();
        mRectBg.left = 0;
        mRectBg.right = getWidth();
        canvas.drawBitmap(background,null,mRectBg,null);
        
        //High priority task
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.lightblue));
        canvas.drawArc(mRectR,0,360,true,mPaint);//crta pun krug
        mRectR.set(canvas.getWidth()*8/24-10,canvas.getHeight()*3/24,canvas.getWidth()*18/24-50,canvas.getHeight()*9/24-20);
        //canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/4,RADIUS,mPaint);
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.red));//Crta taj drugi krug za procente
        canvas.drawArc(mRectR,270,3.6f*90,true,mPaint);
        mPaint.setTextSize(40);
        canvas.drawText("High priority",canvas.getWidth()/2-100,canvas.getHeight()/4+250,mPaint);
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.black));
        mPaint.setTextSize(100);
        canvas.drawText(String.valueOf(90) + "%", canvas.getWidth()/2-50,canvas.getHeight()/4+25,mPaint);

        //Medium priority task
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.lightblue));
        canvas.drawArc(mRectY,0,360,true,mPaint);
        mRectY.set(100,canvas.getHeight()*5/8-200,500,canvas.getHeight()*5/8+200);
        //canvas.drawCircle(canvas.getWidth()/4,5*canvas.getHeight()/8,RADIUS,mPaint);
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.yellow));
        canvas.drawArc(mRectY,270,3.6f*67,true,mPaint);
        mPaint.setTextSize(40);
        canvas.drawText("Medium priority",canvas.getWidth()/4-140,5*canvas.getHeight()/8+250,mPaint);
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.black));
        mPaint.setTextSize(100);
        canvas.drawText(String.valueOf(67) + "%", 230,canvas.getHeight()*16/24-40,mPaint);

        //Low priority task
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.lightblue));
        canvas.drawArc(mRectG,0,360,true,mPaint);
        mRectG.set(600,canvas.getHeight()*5/8-200,1000,canvas.getHeight()*5/8+200);
        //canvas.drawCircle(3*canvas.getWidth()/4,5*canvas.getHeight()/8,RADIUS,mPaint);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.green));
        canvas.drawArc(mRectG, 270, 3.6f * 12, true, mPaint);
        mPaint.setTextSize(40);
        canvas.drawText("Low priority", 3 * canvas.getWidth() / 4 - 100, 5 * canvas.getHeight() / 8 + 250, mPaint);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.black));
        mPaint.setTextSize(100);
        canvas.drawText(String.valueOf(12) + "%", 740, canvas.getHeight() * 16 / 24 - 40, mPaint);

        if(toDraw==true){
            drawProcent();
        }

        //Da sacuva canvas kako ne bi prilikom menjanja procenta brisao postojece slike
        canvas.save();
    }
}
