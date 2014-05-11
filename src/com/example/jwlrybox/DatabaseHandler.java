package com.example.jwlrybox;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.jwlrybox.Contact;

public class DatabaseHandler extends SQLiteOpenHelper {

	// Database Version

	private static final int DATABASE_VERSION = 10;

	// Database Name
	private static final String DATABASE_NAME = "DB_Jewels";

	// Contacts table name
	private static final String TABLE_CONTACTS = "TBL_Jewels";
	// public String TABLE_CONTACTS = "Jewels";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_DESC = "desc";
	private static final String KEY_CATEGORY = "category";
	private static final String KEY_WEIGHT = "weight";
	private static final String KEY_PRICE = "price";
	private static final String KEY_IMAGE = "img";

	public DatabaseHandler(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_DESC + " TEXT,"
				+ KEY_CATEGORY + " TEXT," + KEY_WEIGHT + " TEXT," + KEY_PRICE
				+ " TEXT," + KEY_IMAGE + " BLOB" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addContact(Contact contact, Contact img) {

		try {
			SQLiteDatabase db = this.getWritableDatabase();

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			img.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, out);
			ContentValues values = new ContentValues();

			values.put(KEY_CATEGORY, contact.getCategory()); // Contact Phone
			values.put(KEY_PRICE, contact.getPrice()); // Contact Phone
			values.put(KEY_WEIGHT, contact.getWeight()); // Contact Name
			values.put(KEY_DESC, contact.getDesc()); // Contact Name
			values.put(KEY_IMAGE, out.toByteArray());

			// Inserting Row
			db.insertOrThrow(TABLE_CONTACTS, null, values);
			db.close(); // Closing database connection
		} catch (Exception e) {

			System.out.println("Errrror is " + e);
		}
	}

	public ArrayList<Contact> getCategoryWise(String category) {
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		// String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS +
		// " WHERE desc = '" +category +"'";

		SQLiteDatabase db = this.getReadableDatabase();

		// Cursor cursor = db.rawQuery(selectQuery,null);

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
				KEY_DESC, KEY_CATEGORY, KEY_WEIGHT, KEY_PRICE, KEY_IMAGE },
				KEY_CATEGORY + "=?", new String[] { category }, null, null,
				null, null);

		int i = cursor.getCount();

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setDesc(cursor.getString(1));
				contact.setCategory(cursor.getString(2));
				contact.setWeight(cursor.getString(3));
				contact.setPrice(cursor.getString(4));

				byte[] blob = cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE));
				Bitmap bmp = BitmapFactory
						.decodeByteArray(blob, 0, blob.length);

				contact.setBitmap(bmp);

				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	// Getting All Contacts
	public ArrayList<Contact> getAllContacts() {
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setDesc(cursor.getString(1));
				contact.setCategory(cursor.getString(2));
				contact.setWeight(cursor.getString(3));
				contact.setPrice(cursor.getString(4));
				byte[] blob = cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE));
				Bitmap bmp = BitmapFactory
						.decodeByteArray(blob, 0, blob.length);

				contact.setBitmap(bmp);

				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	// Getting contacts Count
	public int getContactsCount(String category) {

		String countQuery;
		if (category.equalsIgnoreCase("All")) {
			countQuery = "SELECT  * FROM " + TABLE_CONTACTS;

		} else {
			countQuery = "SELECT  * FROM " + TABLE_CONTACTS
					+ " WHERE category = '" + category + "'";
		}
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int i = cursor.getCount();

		cursor.close();

		// return count
		return cursor.getCount();
	}
}
