package markus.wieland.defaultappelements.uielements.like_button;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

import androidx.annotation.ColorInt;

public class DotsView extends View {

    private static final int DOTS_COUNT = 6;
    private static final int OUTER_DOTS_POSITION_ANGLE = 360 / DOTS_COUNT;

    private final int[] colors = new int[]{0xFFf4a330, 0xFF34bdcd, 0xFFd3216e, 0xFF256b66};

    private int width = 0;
    private int height = 0;

    private final Paint[] circlePaints = new Paint[4];

    private int centerX;
    private int centerY;

    private float maxOuterDotsRadius;
    private float maxInnerDotsRadius;
    private float maxDotSize;

    private float currentProgress = 0;

    private final float[] currentRadius = new float[]{0, 0};
    private final float[] currentDotSize = new float[]{0, 0};

    private final ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public DotsView(Context context) {
        super(context);
        init();
    }

    public DotsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DotsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        for (int i = 0; i < circlePaints.length; i++) {
            circlePaints[i] = new Paint();
            circlePaints[i].setStyle(Paint.Style.FILL);
            circlePaints[i].setAntiAlias(true);
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        centerX = width / 2;
        centerY = height / 2;
        maxDotSize = 5;
        maxOuterDotsRadius = (float) width / 2 - maxDotSize * 2;
        maxInnerDotsRadius = 0.8f * maxOuterDotsRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawOuterDotsFrame(canvas);
        drawInnerDotsFrame(canvas);
    }

    private void drawOuterDotsFrame(Canvas canvas) {
        for (int i = 0; i < DOTS_COUNT; i++) {
            int cX = (int) (centerX + currentRadius[0] * Math.cos(i * OUTER_DOTS_POSITION_ANGLE * Math.PI / 180));
            int cY = (int) (centerY + currentRadius[0] * Math.sin(i * OUTER_DOTS_POSITION_ANGLE * Math.PI / 180));
            canvas.drawCircle(cX, cY, currentDotSize[0], circlePaints[i % circlePaints.length]);
        }
    }

    private void drawInnerDotsFrame(Canvas canvas) {
        for (int i = 0; i < DOTS_COUNT; i++) {
            int cX = (int) (centerX + currentRadius[1] * Math.cos((i * OUTER_DOTS_POSITION_ANGLE - 10) * Math.PI / 180));
            int cY = (int) (centerY + currentRadius[1] * Math.sin((i * OUTER_DOTS_POSITION_ANGLE - 10) * Math.PI / 180));
            canvas.drawCircle(cX, cY, currentDotSize[1], circlePaints[(i + 1) % circlePaints.length]);
        }
    }

    public void setCurrentProgress(float currentProgress) {
        this.currentProgress = currentProgress;

        updateInnerDotsPosition();
        updateOuterDotsPosition();
        updateDotsPaints();
        updateDotsAlpha();

        postInvalidate();
    }

    public float getCurrentProgress() {
        return currentProgress;
    }

    private void updateInnerDotsPosition() {
        if (currentProgress < 0.3f) {
            this.currentRadius[1] = (float) Utils.mapValueFromRangeToRange(currentProgress, 0, 0.3f, 0.f, maxInnerDotsRadius);
        } else {
            this.currentRadius[1] = maxInnerDotsRadius;
        }
        if (currentProgress == 0) {
            this.currentDotSize[1] = 0;
        } else if (currentProgress < 0.2) {
            this.currentDotSize[1] = maxDotSize;
        } else if (currentProgress < 0.5) {
            this.currentDotSize[1] = (float) Utils.mapValueFromRangeToRange(currentProgress, 0.2f, 0.5f, maxDotSize, 0.3 * maxDotSize);
        } else {
            this.currentDotSize[1] = (float) Utils.mapValueFromRangeToRange(currentProgress, 0.5f, 1f, maxDotSize * 0.3f, 0);
        }

    }

    private void updateOuterDotsPosition() {
        if (currentProgress < 0.3f) {
            this.currentRadius[0] = (float) Utils.mapValueFromRangeToRange(currentProgress, 0.0f, 0.3f, 0, maxOuterDotsRadius * 0.8f);
        } else {
            this.currentRadius[0] = (float) Utils.mapValueFromRangeToRange(currentProgress, 0.3f, 1f, 0.8f * maxOuterDotsRadius, maxOuterDotsRadius);
        }
        if (currentProgress == 0) {
            this.currentDotSize[0] = 0;
        } else if (currentProgress < 0.7) {
            this.currentDotSize[0] = maxDotSize;
        } else {
            this.currentDotSize[0] = (float) Utils.mapValueFromRangeToRange(currentProgress, 0.7f, 1f, maxDotSize, 0);
        }
    }

    private void updateDotsPaints() {
        if (currentProgress < 0.5f) {
            float progress = (float) Utils.mapValueFromRangeToRange(currentProgress, 0f, 0.5f, 0, 1f);
            circlePaints[0].setColor((Integer) argbEvaluator.evaluate(progress, colors[0], colors[1]));
            circlePaints[1].setColor((Integer) argbEvaluator.evaluate(progress, colors[1], colors[2]));
            circlePaints[2].setColor((Integer) argbEvaluator.evaluate(progress, colors[2], colors[3]));
            circlePaints[3].setColor((Integer) argbEvaluator.evaluate(progress, colors[3], colors[0]));
        } else {
            float progress = (float) Utils.mapValueFromRangeToRange(currentProgress, 0.5f, 1f, 0, 1f);
            circlePaints[0].setColor((Integer) argbEvaluator.evaluate(progress, colors[1], colors[2]));
            circlePaints[1].setColor((Integer) argbEvaluator.evaluate(progress, colors[2], colors[3]));
            circlePaints[2].setColor((Integer) argbEvaluator.evaluate(progress, colors[3], colors[0]));
            circlePaints[3].setColor((Integer) argbEvaluator.evaluate(progress, colors[0], colors[1]));
        }
    }

    public void setColors(@ColorInt int primaryColor, @ColorInt int secondaryColor) {
        colors[0] = primaryColor;
        colors[1] = secondaryColor;
        colors[2] = primaryColor;
        colors[3] = secondaryColor;
        invalidate();
    }

    private void updateDotsAlpha() {
        float progress = (float) Utils.clamp(currentProgress, 0.6f, 1f);
        int alpha = (int) Utils.mapValueFromRangeToRange(progress, 0.6f, 1f, 255, 0);
        circlePaints[0].setAlpha(alpha);
        circlePaints[1].setAlpha(alpha);
        circlePaints[2].setAlpha(alpha);
        circlePaints[3].setAlpha(alpha);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (width != 0 && height != 0)
            setMeasuredDimension(width, height);
    }


    public static final Property<DotsView, Float> DOTS_PROGRESS = new Property<DotsView, Float>(Float.class, "dotsProgress") {
        @Override
        public Float get(DotsView object) {
            return object.getCurrentProgress();
        }

        @Override
        public void set(DotsView object, Float value) {
            object.setCurrentProgress(value);
        }
    };

}
