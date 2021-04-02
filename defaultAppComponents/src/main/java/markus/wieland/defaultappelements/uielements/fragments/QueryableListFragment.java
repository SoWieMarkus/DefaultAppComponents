package markus.wieland.defaultappelements.uielements.fragments;

import android.widget.SearchView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.QueryableEntity;
import markus.wieland.defaultappelements.R;
import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;

public class QueryableListFragment<H extends QueryableEntity<Long>, G extends DefaultViewHolder<H>, T extends DefaultAdapter<H, G>> extends ListFragment<H, G, T> implements SearchView.OnQueryTextListener {

    private final int searchViewId;

    public QueryableListFragment() {
        super(R.layout.fragment_queryable_list, R.id.recyclerView, R.id.info);
        this.searchViewId = R.id.searchView;
    }

    public QueryableListFragment(@IdRes int searchViewId) {
        super(R.layout.fragment_queryable_list, R.id.recyclerView, R.id.info);
        this.searchViewId = searchViewId;
    }

    public QueryableListFragment(@LayoutRes int layout, @IdRes int recyclerViewId, @IdRes int textViewId, @IdRes int searchViewId) {
        super(layout, recyclerViewId, textViewId);
        this.searchViewId = searchViewId;
    }

    @Override
    public void bindViews() {
        super.bindViews();
        SearchView searchView = findViewById(searchViewId);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        List<H> listWithQuery = new ArrayList<>();
        for (H h : originalList) {
            if (h.getStringToApplyQuery().toLowerCase().contains(s.toLowerCase())) {
                listWithQuery.add(h);
            }
        }
        adapter.submitList(listWithQuery);
        return true;
    }
}
