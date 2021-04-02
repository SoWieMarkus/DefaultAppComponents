package markus.wieland.defaultappelements.uielements.adapter.iteractlistener;

public interface OnItemClickAndCustomClickListener<T> extends OnItemInteractListener<T> {
    void onClick(T t);

    void onCustomClick(long key, T t);
}
