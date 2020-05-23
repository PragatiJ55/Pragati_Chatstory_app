package com.example.pragati;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Chat> mMessageList;
    private static final int VIEW_TYPE_MESSAGE_SENT = 0;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 1;
    private static final int VIEW_TYPE_IMAGE_SENT=2;
    private static final int VIEW_TYPE_IMAGE_RECEIVED=3;
    Utility utility;
    public MessageListAdapter(Context context, List<Chat> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        utility= new Utility(mContext);
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_right, parent, false);
            return new RightMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_left, parent, false);

            return new LeftMessageHolder(view);
        }
        else if (viewType == VIEW_TYPE_IMAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_right, parent, false);

            return new ImageMessageHolder(view);
        }
        else if (viewType == VIEW_TYPE_IMAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_left, parent, false);

            return new ImageMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat message = mMessageList.get(position);
        int c=0;
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((RightMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((LeftMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_IMAGE_SENT:
                ((ImageMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_IMAGE_RECEIVED:
                ((ImageMessageHolder)holder).bind(message);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utility.clicked_adapter();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
    @Override
    public int getItemViewType(int position) {
        Chat message = mMessageList.get(position);

        if(message.getImage()!=0 && message.getUser()==0){
            return VIEW_TYPE_IMAGE_SENT;
        }
        if(message.getImage()!=0 && message.getUser()==1){
            return VIEW_TYPE_IMAGE_RECEIVED;
        }
        if (message.getUser()==0) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else{
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    class LeftMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        public LeftMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body_left);
        }

        void bind(Chat message) {
            messageText.setText(message.getText());
        }
    }

    class RightMessageHolder extends RecyclerView.ViewHolder {

        TextView messageText;
        public RightMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body_right);


        }

        void bind(Chat message) {
            messageText.setText(message.getText());
        }
    }


    class ImageMessageHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView caption;
        public ImageMessageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            caption=itemView.findViewById(R.id.caption);
        }

        void bind(Chat message) {
            caption.setText(message.getText());
            imageView.setImageResource(message.getImage());
        }
    }



}
