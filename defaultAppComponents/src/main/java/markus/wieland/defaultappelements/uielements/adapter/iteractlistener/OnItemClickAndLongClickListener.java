package markus.wieland.defaultappelements.uielements.adapter.iteractlistener;

public interface OnItemClickAndLongClickListener<T> extends OnItemInteractListener<T> {
    void onClick(T t);
    void onLongClick(T t);
}
