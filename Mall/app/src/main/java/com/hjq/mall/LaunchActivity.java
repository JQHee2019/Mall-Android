package com.hjq.mall;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        // fullScreen(this);
        // hideBottomUIMenu();

        // 你们平时做启动页
        // 总结：
        // 有四种解决方案（企业级开发主要就是这四种方案）
        // 第一种方案：属性动画ObjectAnimator实现－－－－
        // 第二种方案：补间动画实现(例如：TranslateAnimation、ScaleAnimation、AlphaAnimation、RotateAnimation)
        // 第三种方案：定时器Timer实现
        // 第四种方案：Handler+Thread实现－－－AsyncTask封装
        // 今天采用第一种方案

        // 第一个参数－－－target:你要对哪个View绑定动画－－－今天我们要对ImageView绑定动画
        View target = findViewById(R.id.iv_bg);
        // 第二个参数－－－propertyName:你要执行什么动画－－－动画的属性名称
        // 缩放动画：scaleX
        // 渐变动画：
        // 第三个参数－－－动画变化范围（例如：缩放动画变化范围0.0-1.0之间）
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target, "alpha",
                0.0f, 1.0f);
        // 设置动画执行的时间（企业级开发标准：执行时间一般情况2-3秒钟）
        objectAnimator.setDuration(2000);
        // 启动动画
        objectAnimator.start();


        // 思考：动画执行完毕我们是不是要启动新的页面
        // 扩展知识点－－－设计模式－－－适配器模式
        // 项目开发需要定义很多的接口
        // google工程师非常牛逼---提供接口适配器：AnimatorListenerAdapter
        // 将来我们做开发我们是不是可以这么干？
        // java编程思想：设计模式
        objectAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivity(new Intent(LaunchActivity.this,
                        MainActivity.class));
                finish();
            }
        });
    }

    /*
     * @param activity
     */
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            /*
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                View decorView = window.getDecorView();
                decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
                    @Override
                    public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                        WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                        return defaultInsets.replaceSystemWindowInsets(
                                defaultInsets.getSystemWindowInsetLeft(),
                                0,
                                defaultInsets.getSystemWindowInsetRight(),
                                defaultInsets.getSystemWindowInsetBottom());
                    }
                });
                ViewCompat.requestApplyInsets(decorView);
                //将状态栏设成透明，如不想透明可设置其他颜色
                window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
            }
            //全屏显示
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            // 设置底部导航栏不会遮挡布局
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            */

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                window.setAttributes(attributes);
            }
        }
    }

    /**
     * 隐藏虚拟按键，并且设置成全屏
     */
    protected void hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE;
            decorView.setSystemUiVisibility(uiOptions);
            // 虚拟导航设置为半透明
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
