package com.example.shopapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopapp.Model.PostModel;
import com.example.shopapp.R;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private final List<PostModel> postList;
    private final Context context;

    public PostAdapter(Context context, List<PostModel> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_last, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostModel post = postList.get(position);
        holder.name.setText(post.getName());
        holder.timeAgo.setText(post.getTimeAgo());
        holder.profile.setImageResource(post.getprofile());
        holder.min.setText(post.getMin());
        holder.profileImage.setImageResource(post.getProfileImageRes());
        holder.title.setText(post.getTitle());
        holder.subtitle.setText(post.getSubtitle());
        holder.heartIcon.setImageResource(post.getHeartIconRes());
        holder.eyeIcon.setImageResource(post.getEyeIconRes());
        holder.msgIcon.setImageResource(post.getMsgIconRes());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView name, timeAgo, min, title, subtitle;
        ImageView profileImage, heartIcon, eyeIcon, msgIcon, profile;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            timeAgo = itemView.findViewById(R.id.minutes_ago);
            profile = itemView.findViewById(R.id.profileimg);
            min = itemView.findViewById(R.id.min);
            profileImage = itemView.findViewById(R.id.rectangle_3);
            title = itemView.findViewById(R.id.the_art_of_);
            subtitle = itemView.findViewById(R.id.the_art_of_2);
            heartIcon = itemView.findViewById(R.id.heart);
            eyeIcon = itemView.findViewById(R.id.eye);
            msgIcon = itemView.findViewById(R.id.msg);
        }
    }
}
