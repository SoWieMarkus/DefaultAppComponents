package markus.wieland.defaultappelements.uielements.adapter;

public interface QueryableEntity<T> {
    boolean equals(Object o);
    T getId();
    String getStringToApplyQuery();
}
