package com.xuhao.visualeffect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.xuhao.visualeffect.ui.MyListView;
import com.xuhao.visualeffect.util.Cheeses;

public class MainActivity extends AppCompatActivity {

    private MyListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLv = (MyListView) findViewById(R.id.mlv);
        mLv.setOverScrollMode(View.OVER_SCROLL_NEVER);//消除mEdgeGlowTop 和 mEdgeGlowBottom(清除边界回弹)


        // 加Header
        final View mHeaderView = View.inflate(MainActivity.this, R.layout.view_header, null);
        final ImageView mImage = (ImageView) mHeaderView.findViewById(R.id.iv);
        mLv.addHeaderView(mHeaderView);


        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 当布局填充结束之后, 此方法会被调用

                mLv.setParallaxImage(mImage);

                mHeaderView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        //填充数据
        mLv.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, Cheeses.NAMES));
    }
}
