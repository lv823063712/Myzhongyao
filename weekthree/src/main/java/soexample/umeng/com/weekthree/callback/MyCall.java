package soexample.umeng.com.weekthree.callback;

public interface MyCall<T> {

    void setData(T data);

    void setError(T error);
}
