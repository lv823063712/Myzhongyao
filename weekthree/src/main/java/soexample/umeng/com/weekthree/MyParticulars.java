package soexample.umeng.com.weekthree;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MyParticulars extends AppCompatActivity {

    private ImageView MyGoods;
    private TextView Title;
    private TextView Price;
    private RadioButton shopCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_particulars);
        initView();

        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        String title = intent.getStringExtra("title");
        double mprice = intent.getDoubleExtra("price", 00);

        Title.setText(title);
        Price.setText(mprice + "0");
        if (!img.isEmpty()) {
            String[] split = img.split("\\|");
            if (split != null) {
                for (int j = 0; j < split.length; j++) {
                    String replace = split[j].replace("https", "http");
                    Glide.with(this).load(replace).into(MyGoods);
                }
            } else {
                img.replace("https", "http");
                Log.e("aaaa", img.toString());
                Glide.with(this).load(img).into(MyGoods);
            }
        }
    }

    private void initView() {
        MyGoods = (ImageView) findViewById(R.id.MyGoods);
        Title = (TextView) findViewById(R.id.Title);
        Price = (TextView) findViewById(R.id.Price);
        shopCar = (RadioButton) findViewById(R.id.shopCar);
        shopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}