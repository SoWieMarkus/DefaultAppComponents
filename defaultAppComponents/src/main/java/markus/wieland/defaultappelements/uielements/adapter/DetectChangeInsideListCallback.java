package markus.wieland.defaultappelements.uielements.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 *
 * @param <I> The data type of the id of your QueryableEntity
 * @param <T>
 */
public class DetectChangeInsideListCallback<I, T extends QueryableEntity<I>> extends DiffUtil.Callback {

    private final List<T> oldList;
    private final List<T> newList;

    public DetectChangeInsideListCallback(@NonNull List<T> oldList, @NonNull List<T> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getStringToApplyQuery().equals(newList.get(newItemPosition).getStringToApplyQuery());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
