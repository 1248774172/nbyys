package com.xiaoer.nbyys.requestCode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xiaoer.nbyys.R;

public class RequestCodeFragment extends Fragment {

    private TextView tv_show;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.requestcode_fragment_main, null);
        tv_show = inflate.findViewById(R.id.tv_show);

        return inflate;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        tv_show.setText("" + requestCode);
    }
}
