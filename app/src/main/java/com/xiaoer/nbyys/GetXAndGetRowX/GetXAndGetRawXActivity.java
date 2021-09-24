package com.xiaoer.nbyys.GetXAndGetRowX;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaoer.nbyys.GetXAndGetRowX.View.MyView;
import com.xiaoer.nbyys.R;

public class GetXAndGetRawXActivity extends AppCompatActivity {

    private TextView mTvY;
    private TextView mTvRawY;
    private MyView mTvContent;
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_xand_get_raw_xactivity);
        initViews();

        mTvContent.setOnTouchListener(new MyView.OnTouchListener() {
            @Override
            public void onTouch(MotionEvent event) {
                float x = event.getY();
                float rawX = event.getRawY();
                mTvY.setText("getY:" + x);
                mTvRawY.setText("getRawY:" + rawX);
                mTvResult.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initViews() {
        mTvY = findViewById(R.id.tv_y);
        mTvRawY = findViewById(R.id.tv_raw_y);
        mTvContent = findViewById(R.id.tv_content);
        mTvResult = findViewById(R.id.tv_result);
    }
}