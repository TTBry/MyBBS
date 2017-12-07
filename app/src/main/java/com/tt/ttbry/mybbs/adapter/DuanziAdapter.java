package com.tt.ttbry.mybbs.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.model.duanzi.CommentBean;
import com.tt.ttbry.mybbs.model.duanzi.Duanzi;
import com.tt.ttbry.mybbs.util.Check;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by TTBry on 2017/12/5.
 */

public class DuanziAdapter extends RecyclerView.Adapter<DuanziAdapter.DuanziViewHolder>{

    private  OnItemClickCallback mCallback;

    public interface OnItemClickCallback{
        void onItemClick(int position);
    }

    private Fragment mFragment;
    private List<Duanzi> mDuanziBeanList;

    public DuanziAdapter(Fragment fragment, List<Duanzi> duanziBeanList, OnItemClickCallback callback){
        this.mFragment = fragment;
        this.mDuanziBeanList = duanziBeanList;
        this.mCallback = callback;
    }

    @Override
    public DuanziViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.duanzi_item_layout, null);
        return new DuanziViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DuanziViewHolder holder, int position) {
        try {
            Duanzi duanziBean = mDuanziBeanList.get(position);
            if (!Check.isEmpty(duanziBean.getGroupBean().getUser().getAvatar_url())) {
                Glide.with(mFragment).load(duanziBean.getGroupBean().getUser().getAvatar_url()).into(holder.mCivAvatar);
            }
            holder.mTvContent.setText(duanziBean.getGroupBean().getText());
            holder.mTvAuthor.setText(duanziBean.getGroupBean().getUser().getName());

            if(duanziBean.getGroupBean().getHasComments() > 0){
                holder.mLlComment.setVisibility(View.VISIBLE);
                CommentBean commentBean = duanziBean.getCommentBeans().get(0);
                if (!Check.isEmpty(commentBean.getUserImage())){
                    Glide.with(mFragment).load(commentBean.getUserImage()).into(holder.mCivCommentAvatar);
                }
                if (!Check.isEmpty(commentBean.getMedalBeans()) && !Check.isEmpty(commentBean.getMedalBeans().get(0).getSmallIconUrl())){
                    Glide.with(mFragment).load(commentBean.getMedalBeans().get(0).getSmallIconUrl()).into(holder.mIvCommentMedal);
                }
                holder.mTvCommentAuthor.setText(commentBean.getUserName());
                holder.mTvCommentContent.setText(commentBean.getText());
            }else{
                holder.mLlComment.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mDuanziBeanList.size();
    }

    class DuanziViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CircleImageView mCivAvatar;
        private TextView mTvAuthor;
        private TextView mTvContent;

        private LinearLayout mLlComment;
        private CircleImageView mCivCommentAvatar;
        private TextView mTvCommentAuthor;
        private TextView mTvCommentContent;
        private ImageView mIvCommentMedal;

        public DuanziViewHolder(View itemView) {
            super(itemView);
            mCivAvatar = itemView.findViewById(R.id.duanzi_civ_avatar);
            mTvAuthor = itemView.findViewById(R.id.duanzi_tv_author);
            mTvContent = itemView.findViewById(R.id.duanzi_tv_content);
            mLlComment = itemView.findViewById(R.id.duanzi_comment_ll);
            mCivCommentAvatar = itemView.findViewById(R.id.duanzi_comment_civ_avatar);
            mTvCommentAuthor = itemView.findViewById(R.id.duanzi_comment_tv_author);
            mTvCommentContent = itemView.findViewById(R.id.duanzi_comment_tv_content);
            mIvCommentMedal = itemView.findViewById(R.id.duanzi_comment_iv_medal);
        }

        @Override
        public void onClick(View v) {
            mCallback.onItemClick(getAdapterPosition());
        }
    }


}
