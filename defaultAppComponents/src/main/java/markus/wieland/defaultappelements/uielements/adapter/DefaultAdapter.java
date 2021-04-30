package markus.wieland.defaultappelements.uielements.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;

public abstract class DefaultAdapter<T, H extends DefaultViewHolder<T>> extends RecyclerView.Adapter<H> {

    protected final List<T> list;
    protected OnItemInteractListener<T> onItemInteractListener;

    public DefaultAdapter(OnItemInteractListener<T> onItemInteractListener) {
        this.onItemInteractListener = onItemInteractListener;
        this.list = new ArrayList<>();
    }

    public View buildViewHolder(@LayoutRes int layout, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }

    public void setOnItemInteractListener(OnItemInteractListener<T> onItemInteractListener) {
        this.onItemInteractListener = onItemInteractListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void submitList(List<T> products) {
        this.list.clear();
        this.list.addAll(products);
        notifyDataSetChanged();
    }

    public void submitList(T[] list) {
        submitList(new ArrayList<>(Arrays.asList(list)));
    }

    public List<T> getList() {
        return list;
    }

    public OnItemInteractListener<T> getOnItemInteractListener() {
        return onItemInteractListener;
    }

    public T getItem(int position) {
        if (position >= 0 && position < list.size()) return list.get(position);
        else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull H holder, int position) {
        T item = getItem(position);
        if (item != null) {
            holder.bindItemToViewHolder(item);
        }
    }

    @NonNull
    @Override
    public abstract H onCreateViewHolder(@NonNull ViewGroup parent, int viewType);
}
