package markus.wieland.defaultappelements.uielements.loadingview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import markus.wieland.defaultappelements.R;

public class LoadingView extends FrameLayout {

    private ConstraintLayout loadingLayout;
    private ConstraintLayout showDataLayout;
    private ConstraintLayout offlineLayout;

    private LoadingState currentStatus;

    public LoadingView(@NonNull Context context) {
        super(context);
        initialize();
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.loading_view, this, true);
        setState(LoadingState.LOADING);
    }

    public void setState(LoadingState state){
        this.currentStatus = state;

        loadingLayout.setVisibility(currentStatus == LoadingState.LOADING ? VISIBLE : GONE);
        offlineLayout.setVisibility(currentStatus == LoadingState.OFFLINE ? VISIBLE : GONE);
        showDataLayout.setVisibility(currentStatus == LoadingState.SHOW_DATA ? VISIBLE : GONE);
    }

    public LoadingState getCurrentStatus() {
        return currentStatus;
    }
}
