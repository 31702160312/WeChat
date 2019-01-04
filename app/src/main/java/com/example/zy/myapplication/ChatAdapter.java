package com.example.zy.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {

	ArrayList<ChatInfo> chatlist;
	LayoutInflater inflater;

	public ChatAdapter(Context context, ArrayList<ChatInfo> chatlist) {
		inflater = LayoutInflater.from(context);
		this.chatlist = chatlist;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return chatlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = inflater.inflate(R.layout.chat_list, null);
			holder = new ViewHolder();
			holder.chat = (TextView) view.findViewById(R.id.textview_chat);
			holder.name = (TextView) view.findViewById(R.id.textview_name);
			holder.time = (TextView) view.findViewById(R.id.textview_time);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.chat.setText(chatlist.get(position).getChat());

		holder.time.setText(chatlist.get(position).getTime());
		holder.name.setText(chatlist.get(position).getName());


		return view;
	}

	class ViewHolder {
		TextView chat;
		TextView name;
		TextView time;

	}
}
