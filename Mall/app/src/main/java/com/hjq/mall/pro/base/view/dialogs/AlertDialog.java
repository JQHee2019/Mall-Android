package com.hjq.mall.pro.base.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hjq.mall.R;

/*

   public void showAlertDialog() {
        dialog = AlertDialog.Builder(this)
                .setTitle("通用对话框")
                .setMessage("这是一个漂亮的对话框")
                .setOnConfirmClickListener("确定", view -> {
                })
                .setOnCancelClickListener("取消", view -> {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                })
                .build()
                .shown();
    }

*/

public class AlertDialog extends Dialog {

    private final String TITLE;
    private final String MESSAGE;
    private final String CONFIRMTEXT;
    private final String CANCELTEXT;
    private final onConfirmClickListener ONCONFIRMCLICKLISTENER;
    private final onCancelClickListener ONCANCELCLICKLISTENER;

    public interface onConfirmClickListener {
        void onClick(View view);
    }

    public interface onCancelClickListener {
        void onClick(View view);
    }

    private AlertDialog(@NonNull Context context, String title, String message, String confirmText, String cancelText,
                        onConfirmClickListener onConfirmClickListener, onCancelClickListener onCancelClickListener) {
        super(context, R.style.AlertDialog);
        this.TITLE = title;
        this.MESSAGE = message;
        this.CONFIRMTEXT = confirmText;
        this.CANCELTEXT = cancelText;
        this.ONCONFIRMCLICKLISTENER = onConfirmClickListener;
        this.ONCANCELCLICKLISTENER = onCancelClickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_alert_dialog);
        setCanceledOnTouchOutside(false);
        initView();
    }

    public static Builder Builder(Context context) {
        return new Builder(context);
    }

    private void initView() {
        Button btnConfirm = findViewById(R.id.btn_confirm);
        Button btnCancel = findViewById(R.id.btn_cancel);
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvMessage = findViewById(R.id.tv_message);

        if (!TextUtils.isEmpty(TITLE)) {
            tvTitle.setText(TITLE);
        }
        if (!TextUtils.isEmpty(MESSAGE)) {
            tvMessage.setText(MESSAGE);
        }
        if (!TextUtils.isEmpty(CONFIRMTEXT)) {
            btnConfirm.setText(CONFIRMTEXT);
        }
        if (!TextUtils.isEmpty(CANCELTEXT)) {
            btnCancel.setText(CANCELTEXT);
        }

        btnConfirm.setOnClickListener(view -> {
            if (ONCONFIRMCLICKLISTENER == null) {
                throw new NullPointerException("clicklistener is not null");
            } else {
                ONCONFIRMCLICKLISTENER.onClick(view);
            }
        });
        btnCancel.setOnClickListener(view -> {
            if (ONCANCELCLICKLISTENER == null) {
                throw new NullPointerException("clicklistener is not null");
            } else {
                ONCANCELCLICKLISTENER.onClick(view);
            }
        });
    }

    public AlertDialog shown() {
        show();
        return this;
    }

    public static class Builder {
        private String mTitle;
        private String mMessage;
        private String mConfirmText;
        private String mCancelText;
        private onConfirmClickListener mOnConfirmClickListener;
        private onCancelClickListener mOnCcancelClickListener;
        private Context mContext;

        private Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public Builder setOnConfirmClickListener(String confirmText, onConfirmClickListener confirmclickListener) {
            this.mConfirmText = confirmText;
            this.mOnConfirmClickListener = confirmclickListener;
            return this;
        }

        public Builder setOnCancelClickListener(String cancelText, onCancelClickListener onCancelclickListener) {
            this.mCancelText = cancelText;
            this.mOnCcancelClickListener = onCancelclickListener;
            return this;
        }

        public AlertDialog build() {
            return new AlertDialog(mContext, mTitle, mMessage, mConfirmText, mCancelText,
                    mOnConfirmClickListener, mOnCcancelClickListener);
        }
    }
}
