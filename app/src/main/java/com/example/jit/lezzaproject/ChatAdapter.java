package com.example.jit.lezzaproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatMessageHolder> {

    List<Message> messages;
    public ChatAdapter(List<Message> messages){
        this.messages= messages;
    }

    @NonNull
    @Override
    public ChatMessageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_item_layout,null);
        return new ChatMessageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageHolder chatMessageHolder, int i) {
        String text =messages.get(i).getSender() +" : "+messages.get(i).getTxt();
        chatMessageHolder.msgTV.setText(text);

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class ChatMessageHolder extends RecyclerView.ViewHolder {

        TextView msgTV;
        public ChatMessageHolder(@NonNull View itemView) {
            super(itemView);
            msgTV= itemView.findViewById(R.id.msgET);
        }
    }
}