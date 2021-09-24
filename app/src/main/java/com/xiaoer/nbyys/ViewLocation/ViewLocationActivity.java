package com.xiaoer.nbyys.ViewLocation;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaoer.nbyys.GetXAndGetRowX.View.MyView;
import com.xiaoer.nbyys.R;

public class ViewLocationActivity extends AppCompatActivity {

    private TextView mTvY;
    private TextView mTvRawY;
    private MyView mTvContent;
    private TextView mTvResult;
    private LinearLayout mLlRoot;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int UN_CLICK = 3;
    public int CLICK_STATE = FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_xand_get_raw_xactivity);
        initViews();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int[] location = new int[2];
        mTvContent.getLocationOnScreen(location);
        mTvY.setText("此View在屏幕中的Y getLocationOnScreen:" + location[1]);
        mTvContent.getLocationInWindow(location);
        mTvRawY.setText("此View在窗口中的Y getLocationInWindow:" + location[1]);
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        View inflate = View.inflate(getApplicationContext(), R.layout.activity_get_xand_get_raw_xactivity, null);
        MyView view = inflate.findViewById(R.id.tv_content);
        TextView tvY = inflate.findViewById(R.id.tv_y);
        TextView rawY = inflate.findViewById(R.id.tv_raw_y);
        LinearLayout ll_root = inflate.findViewById(R.id.ll_root);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String text = rawY.getText().toString();
                rawY.setText(text+"\n那么问题来了，这里view贴在最上面，为什么会有值呢？");
                Button button = new Button(ll_root.getContext());
                button.setText("答案在这里");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        button.setVisibility(View.GONE);
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.setCancelable(true);
                        dialog.getWindow().getDecorView().getRootView().setBackgroundColor(Color.parseColor("#000000"));
                        rawY.setText("破案了，原来是因为dialog有个渐变的过程，实际高度是高一点的");
                        rawY.setTextColor(Color.WHITE);
                    }
                });
                ll_root.addView(button);
            }
        },1000);
        view.setOnTouchListener(new MyView.OnTouchListener() {
            @Override
            public void onTouch(MotionEvent event) {
                float x = event.getY();
                float rawX = event.getRawY();
                tvY.setText("getY:" + x);
                rawY.setText("getRawY:" + rawX);
            }
        });
        inflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                inflate.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                tvY.setText("此View在屏幕中的Y getLocationOnScreen:" + location[1]);
                view.getLocationInWindow(location);
                rawY.setText("此View在窗口中的Y getLocationInWindow:" + location[1]);
            }
        });
        dialog.setContentView(inflate);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    private void initViews() {
        mTvY = findViewById(R.id.tv_y);
        mTvRawY = findViewById(R.id.tv_raw_y);
        mTvContent = findViewById(R.id.tv_content);
        TextView textView3 = findViewById(R.id.textView3);
        textView3.setVisibility(View.GONE);
        mTvResult = findViewById(R.id.tv_result);
        mLlRoot = findViewById(R.id.ll_root);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Button button = new Button(getApplicationContext());
                button.setText("这有毛的区别？");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(CLICK_STATE == FIRST){
                            CLICK_STATE = SECOND;
                            TextView textView = new TextView(getApplicationContext());
                            textView.setText("这里 getLocationInWindow 和 getLocationOnScreen之所以一样是因为当前window占据了整个屏幕，在dialog中 会明显不同");
                            mLlRoot.addView(textView);
                            button.setText("真的吗？");
                        }else if(CLICK_STATE == SECOND){
                            CLICK_STATE = THIRD;
                            showDialog();
                            button.setText("so?");
                            mLlRoot.removeViewAt(1);
                        }else if(CLICK_STATE == THIRD){
                            CLICK_STATE = UN_CLICK;
                            mTvResult.setText("getLocationOnScreen: 获取的是当前view在整个屏幕中的位置 包括状态栏\n" + "\n" + "getLocationInWindow: 获取的是当前view在window中的位置");
                            mTvResult.setVisibility(View.VISIBLE);
                            button.setText("nbyys");
                        }else {
                            Toast.makeText(getApplicationContext(),"细不谈",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mLlRoot.addView(button);
            }
        }, 1000);
    }
}