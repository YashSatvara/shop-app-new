package com.example.shopapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Model.BlogPostModel;
import com.example.shopapp.R;

import java.util.List;

public class BlogPostAdapter extends RecyclerView.Adapter<BlogPostAdapter.BlogViewHolder> {

    private List<BlogPostModel> blogPostList;
    private Context context;

    public BlogPostAdapter(List<BlogPostModel> blogPostList, Context context) {
        this.blogPostList = blogPostList;
        this.context = context;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.blog_post_item, parent, false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        BlogPostModel blogPost = blogPostList.get(position);
        holder.title.setText(blogPost.getTitle());
        holder.content.setText(blogPost.getContent());
    }

    @Override
    public int getItemCount() {
        return blogPostList.size();
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;

        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.blog_title);
            content = itemView.findViewById(R.id.blog_content);
        }
    }
}

