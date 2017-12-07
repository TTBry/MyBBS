package com.tt.ttbry.mybbs.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.activity.TvActivity;
import com.tt.ttbry.mybbs.model.tttv.Channel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TTBry on 2017/12/6.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> {
    private List<Channel> channelList;
    private Context context;
    public ChannelAdapter(List<Channel> channels){
        this.channelList = channels;
    }

    @Override
    public ChannelAdapter.ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view  = LayoutInflater.from(context).inflate(R.layout.channel_item_layout, parent, false);
        ChannelViewHolder holder = new ChannelViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChannelAdapter.ChannelViewHolder holder, final int position) {
        holder.tvChannelName.setText(channelList.get(position).getChannelName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("channels", (Serializable) channelList);
                bundle.putInt("position", position);
                Intent intent = new Intent(context, TvActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return channelList.size();
    }

    public static class ChannelViewHolder extends RecyclerView.ViewHolder{
        TextView tvChannelName;
        View view;

        public ChannelViewHolder(View itemView){
            super(itemView);
            this.view = itemView;
            tvChannelName = itemView.findViewById(R.id.tv_channel_name);
        }

    }
}
