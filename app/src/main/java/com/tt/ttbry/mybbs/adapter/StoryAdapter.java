package com.tt.ttbry.mybbs.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.activity.ZhihuArticleActivity;
import com.tt.ttbry.mybbs.model.zhihu.ZhihuNews;
import com.tt.ttbry.mybbs.util.Check;

import java.util.List;

/**
 * Created by TTBry on 2017/12/7.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private List<ZhihuNews.StoriesBean> stories;
    private Context context;
    public StoryAdapter(List<ZhihuNews.StoriesBean> stories){
        this.stories = stories;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.story_item_layout, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        final ZhihuNews.StoriesBean story = stories.get(position);
        if(!Check.isEmpty(story.getImages())){
            Glide.with(context).load(story.getImages().get(0)).into(holder.ivThumb);
        }
        holder.tvTitle.setText(story.getTitle());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", story.getId());
                Intent intent = new Intent(context, ZhihuArticleActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder{
        ImageView ivThumb;
        TextView tvTitle;
        View view;

        public StoryViewHolder(View itemView){
            super(itemView);
            this.view = itemView;
            ivThumb = itemView.findViewById(R.id.thumb_image);
            tvTitle = itemView.findViewById(R.id.title_tv);
        }
    }
}
