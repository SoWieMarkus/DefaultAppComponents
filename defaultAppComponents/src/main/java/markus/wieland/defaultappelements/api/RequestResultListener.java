package markus.wieland.defaultappelements.api;

public interface RequestResultListener<T> {
    void onLoad(T response);
    void onError(Exception e);
}
