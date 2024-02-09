package com.anirudha.customeui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;


public class OvalButton extends androidx.appcompat.widget.AppCompatButton {

    private Paint paint;

    public OvalButton(Context context) {
        super(context);
        init();
    }

    public OvalButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OvalButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    private void init() {
//        paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(getResources().getColor(android.R.color.holo_blue_light)); // Set your desired color
//    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_light)); // Use ContextCompat.getColor
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        // Draw an oval shape
        canvas.drawOval(0, 0, width, height, paint);
        super.onDraw(canvas);
    }
}

