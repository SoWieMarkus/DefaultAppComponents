package markus.wieland.defaultappelements.uielements.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private final List<ViewPageAdapterItem> items;

    public ViewPageAdapter(@NonNull FragmentManager fm, List<markus.wieland.defaultappelements.uielements.viewpager.ViewPageAdapterItem> items) {
        super(fm);
        this.items = items;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return items.get(position).getTitle();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
