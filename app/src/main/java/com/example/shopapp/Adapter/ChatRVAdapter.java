package com.example.shopapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Model.ChatModal;
import com.example.shopapp.R;

import java.util.ArrayList;

public class ChatRVAdapter extends RecyclerView.Adapter {

    private ArrayList<ChatModal>chatModalArrayList;
    private Context context;

    public ChatRVAdapter(ArrayList<ChatModal> chatModalArrayList, Context context) {
        this.chatModalArrayList = chatModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_rv_item,parent,false);
                return new UserViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg_rv_item,parent,false);
                return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatModal chatModal = chatModalArrayList.get(position);
        switch (chatModal.getSender()){
            case "user":
                ((UserViewHolder)holder).userTv.setText(chatModal.getMessage());
                break;
            case  "bot":
                ((BotViewHolder)holder).botMsgTv.setText(chatModal.getMessage());
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (chatModalArrayList.get(position).getSender()){
            case "user":
                return 0;

            case "bot":
                return 1;

            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return chatModalArrayList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        TextView userTv;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userTv=itemView.findViewById(R.id.idTvUser);
        }
    }
    public static  class BotViewHolder extends RecyclerView.ViewHolder{

        TextView botMsgTv;

        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botMsgTv=itemView.findViewById(R.id.idTvBot);
        }
    }
}
