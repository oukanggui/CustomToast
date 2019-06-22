package com.baymax.customtoast.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baymax.customtoast.R;

/**
 * @author oukanggui
 * @date 2019-06-22
 * 描述:Toast处理辅助类
 * 使用：ToastHelper toastHelper = new ToastHelper(MainActivity.this);
 * #toastHelper.setView(R.layout.toast_custom)
 * #如果不使用默认的layout，而是自定义layout，需要在setText/setImage前调用setView方法
 * #自定义Toast布局中至少包含@+id/toast_icon以及toast_message两个id设置
 * toastHelper.setText("I am baymax from custom toast");
 * toastHelper.show();
 */
public class ToastHelper {
    private Toast mToast;
    private Context mContext;

    public ToastHelper(Context context) {
        // 建立Toast对象
        mContext = context;
        mToast = new Toast(mContext);
    }

    public void setView(int layoutId) {
        View view = LayoutInflater.from(mContext).inflate(layoutId, null, false);
        if (view == null) {
            throw new IllegalStateException("layoutId invalid");
        }
        mToast.setView(view);
    }

    /**
     * 设置展示Text
     *
     * @param message
     */
    public void setText(String message) {
        ensureToastRootView();
        View rootView = mToast.getView();
        TextView tvMessage = rootView.findViewById(R.id.toast_message);
        tvMessage.setText(message);
    }

    /**
     * 设置左边IconView
     *
     * @param resId
     */
    public void setImage(int resId) {
        ensureToastRootView();
        View rootView = mToast.getView();
        ImageView ivIcon = rootView.findViewById(R.id.toast_icon);
        ivIcon.setVisibility(View.VISIBLE);
        ivIcon.setImageResource(resId);
    }

    private void ensureToastRootView() {
        // 使用前，保证Toast的View不能为空
        if (mToast.getView() == null) {
            // 使用默认布局
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.toast_custom, null, false);
            mToast.setView(rootView);
        }
    }

    public void show() {
        show(Toast.LENGTH_SHORT);
    }

    public void show(final int duration) {
        Runnable toastRunnable = new Runnable() {
            @Override
            public void run() {
                mToast.setDuration(duration);
                mToast.show();
            }
        };
        if (mContext instanceof Activity) {
            final Activity activity = (Activity) mContext;
            if (activity != null && !activity.isFinishing()) {
                activity.runOnUiThread(toastRunnable);
            }
        } else {
            Handler handler = new Handler(mContext.getMainLooper());
            handler.post(toastRunnable);
        }
    }
}
