package markus.wieland.defaultappelements.uielements.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

public abstract class DefaultFragment  extends Fragment {

    @LayoutRes
    private final int layout;

    private View view;

    public DefaultFragment(@LayoutRes int layout) {
        this.layout = layout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(layout, container, false);
        initialize();
        bindViews();
        return view;
    }

    public void initialize() {}

    public abstract void bindViews();

    public final <V extends View> V findViewById(@IdRes int id) {
        return view.findViewById(id);
    }
}
