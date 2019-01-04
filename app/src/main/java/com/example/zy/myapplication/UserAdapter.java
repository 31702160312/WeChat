package com.example.zy.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

	ArrayList<UserInfo> weatherList;
	LayoutInflater inflater;

	public UserAdapter(Context context, ArrayList<UserInfo> weatherList) {
		inflater = LayoutInflater.from(context);
		this.weatherList = weatherList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return weatherList.size();
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
			view = inflater.inflate(R.layout.list_contact, null);
			holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.user = (TextView) view.findViewById(R.id.user);
			holder.img= (ImageView) view.findViewById(R.id.img);


			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.img.setImageResource(R.drawable.biaoqing);
		holder.name.setText(weatherList.get(position).getName());
		holder.user.setText(weatherList.get(position).getUser());


		return view;
	}

	class ViewHolder {
		TextView name;
		TextView user;
		ImageView img;

	}
}
