package com.example.jwlrybox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private final String[] mobileValues;

	public ImageAdapter(Context context, String[] mobileValues) {
		this.context = context;
		this.mobileValues = mobileValues;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.mobile, null);

			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(mobileValues[position]);
			textView.setTextSize(19);

			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);

			String mobile = mobileValues[position];
			if (mobile.startsWith("Ring")) {
				imageView.setImageResource(R.drawable.android_logo);
			} else if (mobile.startsWith("Necklace")) {
				imageView.setImageResource(R.drawable.ios_logo);
			} else if (mobile.startsWith("Bangles")) {
				imageView.setImageResource(R.drawable.blackberry_logo);
			} else if (mobile.startsWith("Earings")) {
				imageView.setImageResource(R.drawable.earings_logo);
			} else if (mobile.startsWith("All")) {
				imageView.setImageResource(R.drawable.all_logo);
			} else {
				imageView.setImageResource(R.drawable.coisn_logo);
			}

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}

	@Override
	public int getCount() {
		return mobileValues.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}