package aiportal.ai.com.scanner.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 *
 */
public class DiagonalLinearLayout extends LinearLayout {

    private final String TAG = DiagonalLinearLayout.class.getSimpleName();

    private int width, height;
    private Paint linePaint;

    public DiagonalLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public DiagonalLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(linePaint == null){
            linePaint = new Paint();
            linePaint.setColor(Color.BLACK);
            linePaint.setStrokeWidth(2);
        }
        canvas.save();
        canvas.drawLine(0,0,width, height, linePaint);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }
}
