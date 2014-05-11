package com.example.jwlrybox;

import android.graphics.Bitmap;

public class Contact {
	// private variables
	int _id;
	String _desc;
	String _category;
	String _weight;
	String _price;
	Bitmap _image;

	// Empty constructor
	public Contact() {

	}

	// constructor
	public Contact(int id, String desc, String category, String weight,
			String price, Bitmap img) {
		this._id = id;
		this._desc = desc;
		this._category = category;
		this._weight = weight;
		this._price = price;
		this._image = img;

	}

	// constructor
	public Contact(String desc, String category, String weight, String price) {
		this._desc = desc;
		this._category = category;
		this._weight = weight;
		this._price = price;
		// this._image = img;

	}

	public Contact(Bitmap img) {
		this._image = img;

	}

	// getting ID
	public int getID() {
		return this._id;
	}

	// setting id
	public void setID(int id) {
		this._id = id;
	}

	// getting name
	public String getDesc() {
		return _desc;
	}

	// setting name
	public void setDesc(String desc) {
		this._desc = desc;
	}

	// getting phone number
	public String getCategory() {
		return _category;
	}

	// setting phone number
	public void setCategory(String category) {
		this._category = category;
	}

	public String getWeight() {
		return _weight;
	}

	public void setWeight(String weight) {
		this._weight = weight;
	}

	public String getPrice() {
		return _price;
	}

	public void setPrice(String price) {
		this._price = price;
	}

	public Bitmap getBitmap() {
		return _image;
	}

	public void setBitmap(Bitmap bm) {
		this._image = bm;
	}

}
