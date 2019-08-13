package com.hjq.mall.pro.publish.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hjq.mall.R;

/**
 * 中间菜单发布功能
 */
public class PublishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.bottom_in,R.anim.bottom_silent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        findViewById(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.bottom_silent,R.anim.bottom_out);
    }
}
