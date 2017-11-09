package com.tt.ttbry.mybbs.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.model.Article;
import com.tt.ttbry.mybbs.ui.TieActivity;

import java.util.List;

/**
 * Created by TTBry on 2017/11/8.
 */

public class ArticleAdapter extends Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articles;
    private Context context;

    public ArticleAdapter(List<Article> articles){
        this.articles = articles;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView thumbImage;
        TextView titleTv;
        TextView timeTv;
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            thumbImage = view.findViewById(R.id.thumb_image);
            titleTv = view.findViewById(R.id.title_tv);
            timeTv = view.findViewById(R.id.time_tv);
        }
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.article_item_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(context, TieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", articles.get(position).getUrl());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder holder, int position) {
        Article article = articles.get(position);
        Glide.with(context).load(article.getThumb()).into(holder.thumbImage);
        holder.titleTv.setText(article.getTitle());
        holder.timeTv.setText("时间：" + article.getTime());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
