package com.hjq.mall.pro.essence.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjq.mall.R;
import com.hjq.mall.bean.essence.PostsListBean;
import com.hjq.mall.utils.DateUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EssenceVideoAdapter extends RecyclerView.Adapter<EssenceVideoAdapter.VideoAdapterViewHolder> {

    private Context context;
    private List<PostsListBean.PostList> list;

    public EssenceVideoAdapter(List<PostsListBean.PostList> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VideoAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(
                R.layout.item_essence_video_layout, parent, false);
        VideoAdapterViewHolder holder = new VideoAdapterViewHolder(v, true);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapterViewHolder holder, int position) {
        PostsListBean.PostList postList = this.list.get(position);
        // VolleyUtils.loadImage(context,holder.iv_header,postList.getProfile_image());
        Glide.with(context).load(postList.getProfile_image()).into(holder.iv_header);
        holder.tv_name.setText(postList.getName());
        holder.tv_time.setText(DateUtils.parseDate(postList.getCreate_time()));
        holder.tv_content.setText(postList.getText());
        holder.tv_like.setText(postList.getDing());
        holder.tv_dislike.setText(postList.getCai());
        holder.tv_forword.setText(postList.getRepost());
        holder.tv_comment.setText(postList.getComment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VideoAdapterViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView iv_header;
        public TextView tv_name;
        public TextView tv_time;
        public TextView tv_content;
        public ImageView iv_video;

        public LinearLayout ll_like;
        public TextView tv_like;

        public LinearLayout ll_dislike;
        public TextView tv_dislike;

        public LinearLayout ll_forword;
        public TextView tv_forword;

        public LinearLayout ll_comment;
        public TextView tv_comment;

        public int position;

        public VideoAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                iv_header = (CircleImageView) itemView
                        .findViewById(R.id.iv_header);
                tv_name = (TextView) itemView
                        .findViewById(R.id.tv_name);
                tv_time = (TextView) itemView
                        .findViewById(R.id.tv_time);
                tv_content = (TextView) itemView
                        .findViewById(R.id.tv_content);
                iv_video = (ImageView) itemView
                        .findViewById(R.id.iv_video);

                ll_like = (LinearLayout) itemView
                        .findViewById(R.id.ll_like);
                tv_like = (TextView) itemView
                        .findViewById(R.id.tv_like);

                ll_dislike = (LinearLayout) itemView
                        .findViewById(R.id.ll_dislike);
                tv_dislike = (TextView) itemView
                        .findViewById(R.id.tv_dislike);

                ll_forword = (LinearLayout) itemView
                        .findViewById(R.id.ll_forword);
                tv_forword = (TextView) itemView
                        .findViewById(R.id.tv_forword);

                ll_comment = (LinearLayout) itemView
                        .findViewById(R.id.ll_comment);
                tv_comment = (TextView) itemView
                        .findViewById(R.id.tv_comment);
            }

        }
    }
}
