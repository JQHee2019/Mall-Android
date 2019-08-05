package com.hjq.mall.pro.guide.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.hjq.mall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 * */
public class GuideActivity extends AppCompatActivity {

    private ConvenientBanner convenientBanner;
    private Button button;

    private List<Integer> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        convenientBanner = findViewById(R.id.cb_content);
        button = findViewById(R.id.btn_start);
    }

    private void initData() {
        datas.add(R.mipmap.ic_launcher);
        datas.add(R.mipmap.ic_launcher);
        datas.add(R.mipmap.ic_launcher);
    }

    private void initGuidePage() {
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LocalImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_guide_page;
            }
        }, datas);

        // 设置小圆点
        // convenientBanner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        convenientBanner.setPointViewVisible(true);
        // 是否循环播放
        convenientBanner.setCanLoop(false);

        convenientBanner.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }

            @Override
            public void onPageSelected(int index) {
                if (index == datas.size() - 1) {
                    button.setVisibility(View.VISIBLE);
                    convenientBanner.setPointViewVisible(false);
                } else {
                    button.setVisibility(View.GONE);
                    convenientBanner.setPointViewVisible(true);
                }
            }
        });
    }

    /**
     * 对应的holder
     */
    public class LocalImageHolderView extends Holder<Integer> {
        private ImageView mImageView;

        //构造器
        public LocalImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            mImageView = itemView.findViewById(R.id.iv_guide_page);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        @Override
        public void updateUI(Integer data) {
            mImageView.setImageResource(data);
        }
    }
}
