package markus.wieland.defaultappelements.uielements.expandabletextview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class ExpandableTextView extends androidx.appcompat.widget.AppCompatTextView {


    public ExpandableTextView(Context context) {
        super(context);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
}
