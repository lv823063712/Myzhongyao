package soexample.umeng.com.weekthree.iview;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class NXHooldeView extends TextView implements ValueAnimator.AnimatorUpdateListener {
    protected Context mContext;
    protected Paint mPaint4Circle;
    protected int radius;
    private Point startPosition;

    public NXHooldeView(Context context) {
        this(context, null);
    }

    public NXHooldeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NXHooldeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mPaint4Circle = new Paint();
        mPaint4Circle.setColor(Color.RED);
        mPaint4Circle.setAntiAlias(true);

        setGravity(Gravity.CENTER);
        setText("1");
        setTextColor(Color.WHITE);
        setTextSize(12);

    }

    public void setStartPosition(Point startPosition) {
        startPosition.y -= 10;
        this.startPosition = startPosition;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

    }
}
