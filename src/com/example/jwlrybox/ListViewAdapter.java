package com.example.jwlrybox;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	private static ArrayList<Contact> itemDetailsrrayList;
	
	private LayoutInflater l_Inflater;

	public ListViewAdapter(Context context, ArrayList<Contact> results) {
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itemDetailsrrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsrrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
			holder.txt_itemWeight = (TextView) convertView.findViewById(R.id.name);
			holder.txt_itemPrice = (TextView) convertView.findViewById(R.id.price);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txt_itemDescription.setText(itemDetailsrrayList.get(position).getDesc());
		holder.txt_itemWeight.setText(itemDetailsrrayList.get(position).getWeight() + " grams");
		holder.txt_itemPrice.setText("$ " +itemDetailsrrayList.get(position).getPrice());
		holder.itemImage.setImageBitmap(itemDetailsrrayList.get(position).getBitmap());
		
		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemDescription;
		TextView txt_itemWeight;
		TextView txt_itemPrice;
		ImageView itemImage;
	}
}
