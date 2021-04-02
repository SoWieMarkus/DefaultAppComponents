package markus.wieland.defaultappelements.uielements.activities;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public abstract class DefaultActivity extends AppCompatActivity {

    private final int layout;

    public DefaultActivity(@LayoutRes int layout) {
        this.layout = layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        initialize();
    }

    protected void initialize() {
        bindViews();
        initializeViews();
        execute();
    }

    public abstract void bindViews();

    public abstract void initializeViews();

    public abstract void execute();

    public int getLayout() {
        return layout;
    }
}