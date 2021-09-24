package com.xiaoer.nbyys;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaoer.nbyys.GetXAndGetRowX.GetXAndGetRawXActivity;
import com.xiaoer.nbyys.TryCatch.TryCatchActivity;
import com.xiaoer.nbyys.ViewLocation.ViewLocationActivity;
import com.xiaoer.nbyys.requestCode.RequestCodeActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mLvRoot;
    private ArrayList<String> mTools;
    private ArrayList<Class> mActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        mLvRoot.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mLvRoot.setAdapter(new MyAdapter());
    }

    private void initData() {
        mTools = new ArrayList<>();
        mActivities = new ArrayList<>();
        mTools.add("fragment和activity调用startActivityForResult的区别");
        mActivities.add(RequestCodeActivity.class);
        mTools.add("try catch 会影响效率");
        mActivities.add(TryCatchActivity.class);
        mTools.add("getX 和 getRawX 的区别");
        mActivities.add(GetXAndGetRawXActivity.class);
        mTools.add("getLocationOnScreen 和 getLocationInWindow 的区别");
        mActivities.add(ViewLocationActivity.class);

    }

    private void initViews() {
        mLvRoot = findViewById(R.id.cv_root);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.rv_item, parent, false);
//            View inflate = View.inflate(getApplicationContext(), R.layout.rv_item, parent);
            return new MyViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull MyAdapter.MyViewHolder holder, int position) {
            holder.mTv_tool.setText(mTools.get(position));
            holder.mTv_tool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), mActivities.get(position)));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mTools.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView mTv_tool;

            public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
                super(itemView);
                mTv_tool = itemView.findViewById(R.id.tv_tool);
            }
        }
    }


}