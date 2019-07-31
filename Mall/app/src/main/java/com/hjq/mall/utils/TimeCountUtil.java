package com.hjq.mall.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;

/**
 * button 倒计时
 */
public class TimeCountUtil extends CountDownTimer {

    private static final int TIME_TASCK = 1000;
    private Button button;
    private Context context;
    public TimeCountUtil(Context context, long millisInFuture, Button view) {
        super(millisInFuture, TIME_TASCK);
        button = view;
        this.context = context;
    }

    @Override
    public void onFinish() {// 计时完毕
        // button.setTextColor(context.getResources().getColor(R.color.text_white));
        // button.setBackgroundResource(R.drawable.button_background);
        button.setText("发送验证码");
        button.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程
        // button.setTextColor(context.getResources().getColor(R.color.home_item_count));
        button.setClickable(false);//防止重复点击
        // button.setBackgroundResource(R.drawable.gray_button_background);
        // button.setText(context.getString(R.string.send_count_down, millisUntilFinished / TIME_TASCK));
    }
}
