package omy.boston.bitmapwidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by LosFrancisco on 12-Apr-17.
 */

public class BitmapWidget extends View {
    private Bitmap mCache;
    private Paint mPaint;

    public BitmapWidget(Context context) {
        super(context);
        setMinimumWidth(200);
        setMinimumHeight(200);
        mPaint = new Paint();
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(1);
    }

    public void invalidateCache(){
        mCache = null;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        if (null == mCache){
            mCache = Bitmap.createBitmap(
                    getMinimumWidth(),
                    getMinimumHeight(),
                    Bitmap.Config.ARGB_8888);
            drawCachedBitmap(new Canvas(mCache));
        }
        canvas.drawBitmap(mCache, 0, 0, new Paint());
        canvas.drawLine(0, 0, mCache.getWidth(), 0, mPaint);
        canvas.drawLine(0, 0, 0, mCache.getHeight(), mPaint);
        canvas.drawLine(0, mCache.getHeight(), mCache.getWidth(), mCache.getHeight(), mPaint);
        canvas.drawLine(mCache.getWidth(), 0, mCache.getWidth(), mCache.getHeight(), mPaint);
        canvas.drawLine(0, 0, mCache.getWidth(), mCache.getHeight(), mPaint);
    }

    public void drawCachedBitmap(Canvas canvas){
        Bitmap image = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_delete);
        canvas.drawBitmap(image, 0, 0, new Paint());
    }
}
