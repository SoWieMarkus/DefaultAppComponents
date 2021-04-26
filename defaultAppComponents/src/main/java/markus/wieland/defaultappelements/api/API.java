package markus.wieland.defaultappelements.api;

import android.app.Activity;

public abstract class API {

    protected final Activity context;

    public API(Activity context) {
        this.context = context;
    }

    protected  <T> void notifyClient(T t, APIResult<T> result) {
        try {
            context.runOnUiThread(() -> result.onLoad(t));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
