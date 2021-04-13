package markus.wieland.defaultappelements.uielements.activities;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import java.io.Serializable;

import markus.wieland.defaultappelements.R;

public abstract class CreateItemActivity<I extends Serializable> extends DefaultActivity {

    public static final String OBJECT_TO_EDIT = "markus.wieland.defaultappcomponents.createitemactivity.OBJECT_TO_EDIT";
    public static final String RESULT = "markus.wieland.defaultappcomponents.createitemactivity.RESULT";

    public static final int REQUEST_EDIT = 1;
    public static final int REQUEST_CREATE = 2;

    protected I item;

    public CreateItemActivity(int layout) {
        super(layout);
    }

    @Override
    protected void initialize() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_activity_create_item_close);
        }

        item = (I) getIntent().getSerializableExtra(OBJECT_TO_EDIT);
        super.initialize();
    }

    @Override
    public void execute() {
        if (item == null) initializeViewsAddMode();
        else initializeViewsEditMode();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_create_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.menu_activity_create_item_commit) {
            onCommitItem();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Will be executed if the instance which is passed with the intent is not null
     */
    public abstract void initializeViewsEditMode();

    /**
     * Will be executed if the instance which is passed with the intent is null
     */
    public abstract void initializeViewsAddMode();

    protected abstract boolean validateItem();

    protected abstract void onCommitItem();

    public void commitItem() {
        if (!validateItem()) return;
        Intent data = new Intent();
        data.putExtra(RESULT, item);
        setResult(RESULT_OK, data);
        finish();
    }
}
