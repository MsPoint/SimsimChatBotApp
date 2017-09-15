package com.example.muamarsidik.simsimichatbot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.muamarsidik.simsimichatbot.R;
import com.example.muamarsidik.simsimichatbot.model.ChatModel;
import com.github.library.bubbleview.BubbleTextView;

import java.util.List;

/**
 * Created by Publish on 9/15/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private List<ChatModel> list_chat_models;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(List<ChatModel> list_chat_models, Context context) {
        this.list_chat_models = list_chat_models;
        this.context = context;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list_chat_models.size();
    }

    @Override
    public Object getItem(int i) {
        return list_chat_models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
        {
            if (list_chat_models.get(i).isSend)
                view = layoutInflater.inflate(R.layout.list_item_message,null);
            else
                view = layoutInflater.inflate(R.layout.list_item_message_rec,null);

            BubbleTextView text_massage = (BubbleTextView)view.findViewById(R.id.text_message);
            text_massage.setText(list_chat_models.get(i).message);
        }
        return view;
    }
}
