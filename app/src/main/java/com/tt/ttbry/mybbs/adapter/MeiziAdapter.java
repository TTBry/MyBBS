package com.tt.ttbry.mybbs.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.model.Meizi;
import com.tt.ttbry.mybbs.activity.DetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TTBry on 2017/12/5.
 */

public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.MeiziViewHolder> {
    private Fragment fragment;
    private List<Meizi> meiziList;
    private int windowWidth, windowHeight;
    public MeiziAdapter(Fragment fragment, List<Meizi> meiziList){
        this.fragment = fragment;
        this.meiziList = meiziList;
        WindowManager wm = fragment.getActivity().getWindowManager();
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        windowWidth = dm.widthPixels;
        windowHeight = dm.heightPixels;
    }

    @Override
    public MeiziAdapter.MeiziViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meizi_item_layout, parent, false);
        return new MeiziViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MeiziAdapter.MeiziViewHolder holder, final int position) {
        String url = meiziList.get(position).getImageUrl();
        Glide.with(fragment)
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.imgMeizi);

        holder.imgMeizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> resultList = new ArrayList<String>();
                for (Meizi meiziBean : meiziList) {
                    resultList.add(meiziBean.getImageUrl());
                }
                DetailActivity.startActivity(fragment.getActivity(), resultList, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meiziList.size();
    }

    static class MeiziViewHolder extends RecyclerView.ViewHolder{
        ImageView imgMeizi;
        View view;

        MeiziViewHolder(View itemView){
            super(itemView);
            this.imgMeizi = itemView.findViewById(R.id.img_meizi);
            this.view = itemView;
        }
    }
}
