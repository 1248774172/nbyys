package com.xiaoer.nbyys.requestCode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.xiaoer.nbyys.R;

public class RequestCodeActivity extends AppCompatActivity {

    private FrameLayout mFlRoot;
    private RequestCodeFragment mRequestCodeFragment;
    private TextView mTvActivityCode;
    private TextView mTv_error;
    private ConstraintLayout mConstraintLayout;
    private EditText mEt_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requestcode_activity_main);
        mFlRoot = findViewById(R.id.fl_root);
        mTv_error = findViewById(R.id.tv_error);
        mEt_code = findViewById(R.id.et_code);

        mRequestCodeFragment = new RequestCodeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_root, mRequestCodeFragment).commit();

        mTvActivityCode = findViewById(R.id.tv_activity_code);
    }

    public void click(View v) {
        try {
            String s = mEt_code.getText().toString();
            if (TextUtils.isEmpty(s)) {
                s = "0";
            }
            int i = Integer.parseInt(s);
            if (v.getId() == R.id.button_activity)
                startActivityForResult(new Intent(getApplicationContext(), RequestCodeActivity2.class), i);
            else
                mRequestCodeFragment.startActivityForResult(new Intent(getApplicationContext(), RequestCodeActivity2.class), i);
        } catch (Exception e) {
            mTv_error.setText(e.toString());
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mTvActivityCode.setText("" + requestCode);
//        if (myFragment != null) {
//            myFragment.onActivityResult(requestCode, resultCode, data);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}