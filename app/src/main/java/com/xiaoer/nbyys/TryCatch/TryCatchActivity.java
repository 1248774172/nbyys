package com.xiaoer.nbyys.TryCatch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.xiaoer.nbyys.R;

import java.util.Arrays;
import java.util.Random;

public class TryCatchActivity extends AppCompatActivity {

    private TextView mTvResult;
    private Button mBtNotUse;
    private Button mBtUsed;

    @SuppressLint("HandlerLeak") Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle data = msg.getData();
            String result = (String) data.get("result");
            mTvResult.setText(result);
        }
    };
    private int[] mArr;
    private int[] mArr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.try_catch_activity_main);
        mTvResult = findViewById(R.id.tv_result);
        mBtNotUse = findViewById(R.id.bt_not_use);
        mBtUsed = findViewById(R.id.bt_used);
        Button mBtGetNEw = findViewById(R.id.bt_getNew);

        getRandomArr();
        mBtUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String handleArr = handleArr(mArr, true);
                        Bundle bundle = new Bundle();
                        bundle.putString("result", handleArr);
                        Message message = new Message();
                        message.setData(bundle);
                        mHandler.dispatchMessage(message);
                    }
                }).start();
            }
        });

        mBtNotUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String handleArr = handleArr(mArr2, false);
                        Bundle bundle = new Bundle();
                        bundle.putString("result", handleArr);
                        Message message = new Message();
                        message.setData(bundle);
                        mHandler.dispatchMessage(message);
                    }
                }).start();
            }
        });

        mBtGetNEw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomArr();
                mTvResult.setText("");
            }
        });

    }

    private void getRandomArr() {
        mArr = new int[1000000];
        Random random = new Random();
        for (int i = 0; i < mArr.length; i++) {
            mArr[i] = random.nextInt();
        }
        mArr2 = Arrays.copyOf(mArr, mArr.length);
    }

    private String handleArr(int[] arr, boolean tryCatch) {
        long startTime = System.currentTimeMillis();
        long endTime = 0;
        if (tryCatch) {
            try {
                quickSort(arr, 0, arr.length - 1);
                endTime = System.currentTimeMillis();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            quickSort(arr, 0, arr.length - 1);
            endTime = System.currentTimeMillis();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("共耗时：").append(endTime - startTime);
        if (arr.length <= 100) {
            stringBuilder.append(Arrays.toString(arr));
        }
        return stringBuilder.toString();
    }

    private void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int standard = arr[start];
        int left = start;
        int right = end;

        while (left < right) {
            while (left < right && arr[right] >= standard) {
                right--;
            }
            arr[left] = arr[right];

            while (left < right && arr[left] <= standard) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = standard;
        quickSort(arr, start, left - 1);
        quickSort(arr, left + 1, end);
    }
}