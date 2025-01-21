package com.example.shopapp.Adapter;

import android.content.Context;
import android.view.Gravity; // Import Gravity
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Model.MessageModel;
import com.example.shopapp.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<MessageModel> messages;
    private Context context;
    private String currentUserId; // Add this line to store current user's ID

    public MessageAdapter(List<MessageModel> messages, Context context, String currentUserId) {
        this.messages = messages;
        this.context = context;
        this.currentUserId = currentUserId; // Initialize currentUserId
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageModel message = messages.get(position);
        holder.messageText.setText(message.getMessageText());

        // Style messages based on sender/recipient
        if (message.getSenderId().equals(currentUserId)) {
            // Outgoing message
            holder.messageText.setBackgroundResource(R.drawable.outgoing_message_background);
            // Set layout gravity for outgoing message
            ViewGroup.LayoutParams params = holder.messageText.getLayoutParams();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            holder.messageText.setLayoutParams(params);
            holder.messageText.setGravity(Gravity.END); // Align to right
        } else {
            // Incoming message
            holder.messageText.setBackgroundResource(R.drawable.incoming_message_background);
            // Set layout gravity for incoming message
            ViewGroup.LayoutParams params = holder.messageText.getLayoutParams();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            holder.messageText.setLayoutParams(params);
            holder.messageText.setGravity(Gravity.START); // Align to left
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView messageText;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
        }
    }
}
