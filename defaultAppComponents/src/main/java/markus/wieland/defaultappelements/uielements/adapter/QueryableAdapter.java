package markus.wieland.defaultappelements.uielements.adapter;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;

/**
 * @param <I> The data type of the key of your QueryableEntity
 * @param <T>
 * @param <H>
 */
public abstract class QueryableAdapter<I, T extends QueryableEntity<I>, H extends DefaultViewHolder<T>> extends DefaultAdapter<T, H> {

    public QueryableAdapter(OnItemInteractListener<T> onItemInteractListener) {
        super(onItemInteractListener);
    }

    @Override
    public void submitList(List<T> products) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DetectChangeInsideListCallback<I,T>(this.list, products));
        this.list.clear();
        this.list.addAll(products);
        result.dispatchUpdatesTo(this);
    }
}
