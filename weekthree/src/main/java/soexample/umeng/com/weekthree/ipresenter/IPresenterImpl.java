package soexample.umeng.com.weekthree.ipresenter;

import soexample.umeng.com.weekthree.callback.MyCall;
import soexample.umeng.com.weekthree.iview.IView;
import soexample.umeng.com.weekthree.moudle.MyMoudleImpl;

public class IPresenterImpl implements IPresenter {
    private IView iView;
    private MyMoudleImpl myMoudle;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        myMoudle = new MyMoudleImpl();
    }

    @Override
    public void startRequest(String url) {
        myMoudle.startLogin(url, new MyCall() {
            @Override
            public void setData(Object data) {
                iView.success(data);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });
    }

    //防止内存泄漏
    public void onDetecth() {
        if (myMoudle != null) {
            myMoudle = null;
        }
        if (iView != null) {
            iView = null;
        }
        ;
    }
}
