package markus.wieland.defaultappelements.uielements.viewpager;

import androidx.fragment.app.Fragment;

public class ViewPageAdapterItem {

    private final String title;
    private final Fragment fragment;

    public ViewPageAdapterItem(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
