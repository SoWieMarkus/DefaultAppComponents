package markus.wieland.defaultappelements.uielements.adapter;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public  abstract class DefaultViewHolder <T> extends RecyclerView.ViewHolder {

    public DefaultViewHolder(@NonNull View itemView) {
        super(itemView);
        bindViews();
    }

    public abstract void bindViews();

    public abstract void bindItemToViewHolder(T t);

    public final <V extends View> V findViewById(@IdRes int id){ return itemView.findViewById(id); }
}
