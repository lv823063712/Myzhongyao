package soexample.umeng.com.weekthree;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import soexample.umeng.com.weekthree.adapter.MyAdapter;
import soexample.umeng.com.weekthree.base.BaseActivity;
import soexample.umeng.com.weekthree.bean.MyData;
import soexample.umeng.com.weekthree.ipresenter.IPresenterImpl;
import soexample.umeng.com.weekthree.iview.IView;

public class MainActivity<T> extends BaseActivity implements IView<T>, OnClickListener {

    private XRecyclerView MyRecy;
    private EditText My_Seach;
    private ImageView Switch_Layout;
    private int flag = 1;
    private IPresenterImpl presenter;
    private MyAdapter myAdapter;
    private ArrayList<MyData.DataBean> datas = new ArrayList<>();
    private int index = 1;
    private String mString = "电脑";
    private String mUrl = "http://www.zhaoapi.cn/product/searchProducts?keywords=电脑&page=";
    private ImageView MyBack;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        MyRecy = findViewById(R.id.MyRecy);
        My_Seach = findViewById(R.id.My_Seach);
        Switch_Layout = findViewById(R.id.Switch_Layout);
        MyBack = findViewById(R.id.MyBack);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        MyRecy.setLayoutManager(manager);
        MyRecy.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        MyRecy.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        MyRecy.setArrowImageView(R.drawable.xjt);


        MyRecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                datas.clear();
                index = 1;
                presenter.startRequest(mUrl + 1);
                MyRecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                index++;
                presenter.startRequest(mUrl + index);
                // Toast.makeText(MainActivity.this, index + "", Toast.LENGTH_SHORT).show();
                MyRecy.loadMoreComplete();
            }
        });

    }

    @Override
    protected void setOnClick() {
        // My_Seach.setOnEditorActionListener(this);
        Switch_Layout.setOnClickListener(this);
        MyBack.setOnClickListener(this);
    }

    @Override
    protected void proLogic() {
        presenter = new IPresenterImpl(this);
        setMyAdapter(flag);
        presenter.startRequest(mUrl + index);
    }

    @Override
    public void success(T data) {
        MyData myData = (MyData) data;
        datas.addAll(myData.getData());
        myAdapter.notifyDataSetChanged();
    }

    private void setMyAdapter(int flag) {
        if (flag == 1) {
            LinearLayoutManager manager = new LinearLayoutManager
                    (this, LinearLayoutManager.VERTICAL, false);
            MyRecy.setLayoutManager(manager);
            myAdapter = new MyAdapter(datas, flag);
            MyRecy.setAdapter(myAdapter);
        } else if (flag == 2) {
            GridLayoutManager manager = new GridLayoutManager
                    (this, 2, GridLayoutManager.VERTICAL, false);
            MyRecy.setLayoutManager(manager);
            myAdapter = new MyAdapter(datas, flag);
            MyRecy.setAdapter(myAdapter);
        }
    }

    @Override
    public void error(T error) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Switch_Layout:
                if (flag == 1) {
                    Switch_Layout.setImageResource(R.drawable.buju2);
                    flag = 2;
                } else {
                    Switch_Layout.setImageResource(R.drawable.buju1);
                    flag = 1;
                }
                setMyAdapter(flag);
                break;
            case R.id.MyBack:
                finish();
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

   /* @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //当actionId == XX_SEND 或者 XX_DONE时都触发
        //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
        //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
        if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE
                || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() &&
                KeyEvent.ACTION_DOWN == event.getAction())) {
            *//*datas.clear();
            String name = My_Seach.getText().toString();
            mString = name;*//*
            Toast.makeText(this, "进行搜索了", Toast.LENGTH_SHORT).show();

        }
        return false;

    }*/
}
