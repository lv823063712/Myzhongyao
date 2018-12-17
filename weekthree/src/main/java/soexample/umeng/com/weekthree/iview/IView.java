package soexample.umeng.com.weekthree.iview;

public interface IView<T> {

    void success(T data);

    void error(T error);
}
