package markus.wieland.defaultappelements.uielements.adapter.iteractlistener;

public interface OnItemClickListener<T> extends OnItemInteractListener<T> {
    void onClick(T t);
}
