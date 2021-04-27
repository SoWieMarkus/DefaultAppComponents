package markus.wieland.defaultappelements.uielements.fragments;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.defaultappelements.R;
import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;

public class ListFragment<H, G extends DefaultViewHolder<H>, T extends DefaultAdapter<H, G>> extends DefaultFragment {

    private RecyclerView recyclerView;
    private TextView textViewInfo;

    private final int recyclerViewId;
    private final int textViewInfoId;

    protected T adapter;
    protected List<H> originalList = new ArrayList<>();

    public ListFragment() {
        super(R.layout.fragment_list);
        this.recyclerViewId = R.id.recyclerView;
        this.textViewInfoId = R.id.info;
    }

    public ListFragment(@LayoutRes int layout, @IdRes int recyclerViewId, @IdRes int textViewInfoId) {
        super(layout);
        this.recyclerViewId = recyclerViewId;
        this.textViewInfoId = textViewInfoId;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (recyclerView != null) recyclerView.setAdapter(adapter);
        if (textViewInfo != null && adapter != null) textViewInfo.setVisibility(adapter.getList().isEmpty() ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void bindViews() {
        recyclerView = findViewById(recyclerViewId);
        textViewInfo = findViewById(textViewInfoId);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void initializeViews() {

    }

    @Override
    public void execute() {

    }

    public void setOriginalList(List<H> list) {
        this.originalList = list;
    }

    public void setAdapter(T adapter) {
        this.adapter = adapter;
        if (recyclerView == null) return;
        recyclerView.setAdapter(adapter);
        if (textViewInfo == null) return;
        textViewInfo.setVisibility(adapter.getList().isEmpty() ? View.VISIBLE : View.INVISIBLE);
    }

    public void setEmptyMessage(String emptyMessage){
        textViewInfo.setText(emptyMessage);
    }

    public T getAdapter() {
        return adapter;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public TextView getTextViewInfo() {
        return textViewInfo;
    }
}
